package com.example.locatorappforhealthcare;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface OpenAIApi {
    @POST("v1/completions")
    Call<CompletionResponse> getCompletion(@Body CompletionRequest completionRequest);
}
