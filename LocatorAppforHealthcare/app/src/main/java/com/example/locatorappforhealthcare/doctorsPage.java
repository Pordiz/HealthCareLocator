package com.example.locatorappforhealthcare;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.example.locatorappforhealthcare.TransitionActivity;


public class doctorsPage extends AppCompatActivity {
    public Button backBtn;
    public Button cardiologistButton;
    public Button dentistButton;
    public Button pediaButton;
    public Button famButton;
    public Button obsgyneButton;
    public Button confirmButton;
    private String selectedDoctorType;

    ImageView cardiologistIcon;
    ImageView cardiologistIconSelected;

    ImageView dentIcon;
    ImageView dentIconSelected;

    ImageView familyIcon;
    ImageView familyIconSelected;

    ImageView pediaIcon;
    ImageView pediaIconSelected;

    ImageView obsgyneIcon;
    ImageView obsgyeIconSelected;

    private void resetOptionButtons() {
        int defaultBgColor = Color.parseColor("#FFFFFF");
        int defaultTextColor = Color.parseColor("#007788");

        cardiologistButton.setBackgroundColor(defaultBgColor);
        cardiologistButton.setTextColor(defaultTextColor);
        cardiologistIcon.setVisibility(View.VISIBLE);
        cardiologistIconSelected.setVisibility(View.GONE);

        dentistButton.setBackgroundColor(defaultBgColor);
        dentistButton.setTextColor(defaultTextColor);
        dentIcon.setVisibility(View.VISIBLE);
        dentIconSelected.setVisibility(View.GONE);


        pediaButton.setBackgroundColor(defaultBgColor);
        pediaButton.setTextColor(defaultTextColor);
        pediaIcon.setVisibility(View.VISIBLE);
        pediaIconSelected.setVisibility(View.GONE);


        famButton.setBackgroundColor(defaultBgColor);
        famButton.setTextColor(defaultTextColor);
        familyIcon.setVisibility(View.VISIBLE);
        familyIconSelected.setVisibility(View.GONE);


        obsgyneButton.setBackgroundColor(defaultBgColor);
        obsgyneButton.setTextColor(defaultTextColor);
        obsgyneIcon.setVisibility(View.VISIBLE);
        obsgyeIconSelected.setVisibility(View.GONE);

    }

    private void setSelectedDoctorType(String doctorType) {
        resetOptionButtons();

        selectedDoctorType = doctorType;
        confirmButton.setVisibility(View.VISIBLE);

        int selectedBgColor = Color.parseColor("#007788");
        int selectedTextColor = Color.parseColor("#FFFFFF");


        switch (doctorType) {
            case "Cardiologist":
                cardiologistButton.setBackgroundColor(selectedBgColor);
                cardiologistButton.setTextColor(selectedTextColor);
                cardiologistIcon.setVisibility(View.GONE);
                cardiologistIconSelected.setVisibility(View.VISIBLE);
                break;

            case "Dentist":
                dentistButton.setBackgroundColor(selectedBgColor);
                dentistButton.setTextColor(selectedTextColor);
                dentIcon.setVisibility(View.GONE);
                dentIconSelected.setVisibility(View.VISIBLE);
                break;

            case "Pediatrician":
                pediaButton.setBackgroundColor(selectedBgColor);
                pediaButton.setTextColor(selectedTextColor);
                pediaIcon.setVisibility(View.GONE);
                pediaIconSelected.setVisibility(View.VISIBLE);
                break;

            case "Family Physician":
                famButton.setBackgroundColor(selectedBgColor);
                famButton.setTextColor(selectedTextColor);
                familyIcon.setVisibility(View.GONE);
                familyIconSelected.setVisibility(View.VISIBLE);
                break;

            case "Gynecologist":
                obsgyneButton.setBackgroundColor(selectedBgColor);
                obsgyneButton.setTextColor(selectedTextColor);
                obsgyneIcon.setVisibility(View.GONE);
                obsgyeIconSelected.setVisibility(View.VISIBLE);
                break;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_page);

        cardiologistIcon = (ImageView) findViewById(R.id.cardioIcon);
        cardiologistIconSelected = (ImageView) findViewById(R.id.cardioIconSelected);

        dentIcon = (ImageView) findViewById(R.id.dentistIcon);
        dentIconSelected = (ImageView) findViewById(R.id.dentistIconSelected);

        familyIcon = (ImageView) findViewById(R.id.famIcon);
        familyIconSelected = (ImageView) findViewById(R.id.famIconSelected);

        pediaIcon = (ImageView) findViewById(R.id.pedIcon);
        pediaIconSelected = (ImageView) findViewById(R.id.pedIconSelected);

        dentIcon = (ImageView) findViewById(R.id.dentistIcon);
        dentIconSelected = (ImageView) findViewById(R.id.dentistIconSelected);

        obsgyneIcon = (ImageView) findViewById(R.id.obsIcon);
        obsgyeIconSelected = (ImageView) findViewById(R.id.obsIconSelected);


        backBtn = (Button) findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMain();
            }
        });

        confirmButton = (Button) findViewById(R.id.confirmBtn);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedDoctorType != null) {
                    openMapActivity(selectedDoctorType);
                    resetOptionButtons();
                }
            }
        });


        cardiologistButton = (Button) findViewById(R.id.cardioBtn);
        cardiologistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelectedDoctorType("Cardiologist");
            }
        });

        dentistButton = (Button) findViewById(R.id.dentistBtn);
        dentistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelectedDoctorType("Dentist");
            }
        });

        famButton = (Button) findViewById(R.id.famBtn);
        famButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelectedDoctorType("Family Physician");
            }
        });

        pediaButton = (Button) findViewById(R.id.pediatriBtn);
        pediaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelectedDoctorType("Pediatrician");
            }
        });

        obsgyneButton = (Button) findViewById(R.id.obsgyneBtn);
        obsgyneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelectedDoctorType("Gynecologist");
            }
        });

    }

    public void openMain() {
        Intent intent = new Intent(this, MainPage.class);
        startActivity(intent);
    }

    public void openMapActivity(String doctorType) {
        Intent intent = new Intent(this, TransitionActivity.class);
        intent.putExtra("doctorType", doctorType);
        startActivity(intent);
    }
}