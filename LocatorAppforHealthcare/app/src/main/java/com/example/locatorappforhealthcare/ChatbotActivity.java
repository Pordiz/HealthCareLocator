package com.example.locatorappforhealthcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.EditText;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.List;
import java.util.ArrayList;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import android.os.Handler;
import android.animation.ValueAnimator;
import android.view.inputmethod.InputMethodManager;
import android.content.Context;
import android.widget.ImageButton;
import android.widget.Toolbar;


public class ChatbotActivity extends AppCompatActivity implements ChatAdapter.ChatOptionClickListener {
    private List<ChatMessage> messages;
    private ChatAdapter chatAdapter;
    private TreeNode currentNode;
    private ChatMessage typingDotsMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatbot);


        RecyclerView chatRecyclerView = findViewById(R.id.chat_recycler_view);
        chatRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        messages = new ArrayList<>();
        DecisionTreeBuilder decisionTreeBuilder = new DecisionTreeBuilder();
        currentNode = decisionTreeBuilder.buildDecisionTree();
        messages.add(new ChatMessage(currentNode.getQuestion(), ChatMessage.Type.NORMAL));
        chatAdapter = new ChatAdapter(messages, this, this);
        chatRecyclerView.setAdapter(chatAdapter);
        showChildren(currentNode);

        // Find the send_button and user_input views
        ImageButton sendButton = findViewById(R.id.send_button);
        EditText userInput = findViewById(R.id.user_input);

        // Set an OnClickListener for the send_button
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userMessage = userInput.getText().toString().trim();
                if (!userMessage.isEmpty()) {
                    onChatOptionClick(userMessage);
                    userInput.setText(""); // Clear the user_input EditText

                    // Hide the keyboard
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(userInput.getWindowToken(), 0);
                }
            }
        });

        ImageButton backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    private void scrollToBottom() {
        RecyclerView chatRecyclerView = findViewById(R.id.chat_recycler_view);
        chatRecyclerView.scrollToPosition(messages.size() - 1);
    }

    @Override
    public void onChatOptionClick(String option) {
        messages.add(new ChatMessage(option, ChatMessage.Type.USER));
        chatAdapter.notifyDataSetChanged();
        scrollToBottom();

        if (option.equals("Just Browsing")) {
            handleCommonQuestion(option, false);
        } else {
            TreeNode nextNode = findChildNode(currentNode, option);

            if (nextNode != null) {
                currentNode = nextNode;
                if (currentNode.getAdditionalQuestion() != null) {
                    displayTypingAnimation();
                    new Handler().postDelayed(() -> {
                        removeTypingAnimation();
                        messages.add(new ChatMessage(currentNode.getAdditionalQuestion(), ChatMessage.Type.NORMAL));
                        chatAdapter.notifyDataSetChanged();
                        scrollToBottom();
                        if (!currentNode.getChildren().isEmpty()) {
                            showChildren(currentNode);
                        }
                    }, 1500);
                } else if (!currentNode.getChildren().isEmpty()) {
                    showChildren(currentNode);
                }
            } else {
                handleCommonQuestion(option, false);
            }
        }
    }

    private void showChildren(TreeNode node) {
        // Remove previously displayed options
        messages.removeIf(chatMessage -> chatMessage.getType() == ChatMessage.Type.OPTION);

        displayTypingAnimation();
        new Handler().postDelayed(() -> {
            removeTypingAnimation();

            // Show the children as options
            for (TreeNode child : node.getChildren()) {
                messages.add(new ChatMessage(child.getQuestion(), ChatMessage.Type.OPTION));
            }
            chatAdapter.notifyDataSetChanged();
            scrollToBottom();
        }, 1500);
    }

    private TreeNode findChildNode(TreeNode parentNode, String userMessage) {
        for (TreeNode child : parentNode.getChildren()) {
            if (child.getQuestion().equals(userMessage)) {
                return child;
            }
        }
        return null;
    }

    private boolean isCommonQuestion(String userMessage) {
        // Add conditions to check if the userMessage is a common question
        return false;
    }

    private void handleCommonQuestion(String userMessage, boolean shouldProvideTop3Facilities) {
        if (userMessage.equals("Just Browsing")) {
            String justBrowsingResponse = "You are now in 'Just Browsing' mode. Feel free to ask me any questions related to common healthcare concerns, and I'll do my best to assist you. If you want to return to the main menu, type 'Main Menu'.";
            displayTypingAnimation();
            new Handler().postDelayed(() -> {
                removeTypingAnimation();
                messages.add(new ChatMessage(justBrowsingResponse, ChatMessage.Type.NORMAL));
                chatAdapter.notifyDataSetChanged();
                scrollToBottom();
            }, 1500);
        } else if (userMessage.equalsIgnoreCase("Main Menu")) {
            currentNode = new DecisionTreeBuilder().buildDecisionTree();
            messages.add(new ChatMessage(currentNode.getQuestion(), ChatMessage.Type.NORMAL));
            chatAdapter.notifyDataSetChanged();
            scrollToBottom();
            showChildren(currentNode);
        } else if (currentNode != null && currentNode.getAdditionalQuestion() != null) {
            getChatGPTResponse(userMessage, currentNode.getAdditionalQuestion(), shouldProvideTop3Facilities);
        } else {
            // Handle cases where there's no additional question or "Just Browsing" mode
            getChatGPTResponse(userMessage, "", shouldProvideTop3Facilities);
        }
    }

    private void displayTypingAnimation() {
        typingDotsMessage = new ChatMessage("", ChatMessage.Type.NORMAL);
        messages.add(typingDotsMessage);
        chatAdapter.notifyDataSetChanged();
        scrollToBottom();

        ValueAnimator typingDotsAnimator = ValueAnimator.ofInt(1, 4);
        typingDotsAnimator.setDuration(1000);
        typingDotsAnimator.setRepeatCount(ValueAnimator.INFINITE);
        typingDotsAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int dotCount = (Integer) animation.getAnimatedValue();
                String dots = new String(new char[dotCount]).replace("\0", ".");
                typingDotsMessage.setMessage(dots);
                chatAdapter.notifyItemChanged(messages.size() - 1);
            }
        });
        typingDotsAnimator.start();
    }

    private void removeTypingAnimation() {
        messages.remove(typingDotsMessage);
        chatAdapter.notifyDataSetChanged();
    }


    private OpenAIApi createOpenAIApi() {
        String apiKey = "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";

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws java.io.IOException {
                        Request originalRequest = chain.request();
                        Request requestWithApiKey = originalRequest.newBuilder()
                                .header("Authorization", "Bearer " + apiKey)
                                .build();
                        return chain.proceed(requestWithApiKey);
                    }
                })
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openai.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit.create(OpenAIApi.class);
    }

    private void getChatGPTResponse(String userMessage, String additionalQuestion, boolean shouldProvideTop3Facilities) {
        OpenAIApi openAIApi = createOpenAIApi();
        String doctorsAndServices = "Specialized doctors: cardiologist, dentist, family physician, pediatrician, obstetrician and gynecologist. Medical services: animal bite vaccine, anti-tetanus vaccine, children checkup, emergency care, general checkup, prenatal checkup.";

        String context = "Health Mate is a medical assistant chatbot that helps users find the right doctor or medical service based on their needs. The chatbot can answer questions about specialized doctors, medical services, and common healthcare concerns. " + doctorsAndServices;
        String userInteraction;

        if (additionalQuestion.isEmpty()) {
            userInteraction = "The user is in 'Just Browsing' mode and asked: \"" + userMessage + "\".";
        } else {
            userInteraction = "The user was asked: \"" + additionalQuestion + "\" and they responded: \"" + userMessage + "\".";
        }

        if (shouldProvideTop3Facilities) {
            userInteraction = "The user asked for the top 3 nearest facilities offering a specific service: \"" + userMessage + "\".";
        }

        String instructions = "Provide a helpful and empathetic response that focuses on the specific doctors or services relevant to the user's input. If the user's input is related to a specialized doctor or a medical service offered by the application, also suggest that they can locate the top 3 nearest facilities that offer the service. If the user expresses gratitude or appreciation, please acknowledge it and offer further assistance if needed.";
        String prompt = context + " " + userInteraction + " " + instructions;

        CompletionRequest completionRequest = new CompletionRequest("text-davinci-003", prompt, 1000, 1, 0.5);
        Call<CompletionResponse> call = openAIApi.getCompletion(completionRequest);
        call.enqueue(new Callback<CompletionResponse>() {
            @Override
            public void onResponse(Call<CompletionResponse> call, Response<CompletionResponse> response) {
                if (response.isSuccessful()) {
                    String chatGPTResponse = response.body().getChoices().get(0).getText().trim();
                    displayTypingAnimation();
                    new Handler().postDelayed(() -> {
                        removeTypingAnimation();
                        messages.add(new ChatMessage(chatGPTResponse, ChatMessage.Type.NORMAL));
                        chatAdapter.notifyDataSetChanged();
                        scrollToBottom();
                    }, 1500);
                } else {
                    displayTypingAnimation();
                    new Handler().postDelayed(() -> {
                        removeTypingAnimation();
                        messages.add(new ChatMessage("Sorry, I couldn't understand your question. Please try again.", ChatMessage.Type.NORMAL));
                        chatAdapter.notifyDataSetChanged();
                        scrollToBottom();
                    }, 1500);
                }
            }

            @Override
            public void onFailure(Call<CompletionResponse> call, Throwable t) {
                displayTypingAnimation();
                new Handler().postDelayed(() -> {
                    removeTypingAnimation();
                    messages.add(new ChatMessage("Sorry, I couldn't understand your question. Please try again.", ChatMessage.Type.NORMAL));
                    chatAdapter.notifyDataSetChanged();
                    scrollToBottom();
                }, 1500);
            }
        });
    }


}