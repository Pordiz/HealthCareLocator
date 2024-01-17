package com.example.locatorappforhealthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class servicePage extends AppCompatActivity {
    private Button prev1Btn;
    public Button AnimalBtn;
    public Button EmergencyButton;
    public Button TetanusButton;
    public Button ChildrenButton;
    public Button GeneralButton;
    public Button PrenatalButton;
    public Button confirmButton;
    private String selectedDoctorType;

    ImageView animalIcon;
    ImageView animalIconSelected;
    ImageView tetanusIcon;
    ImageView tetanusIconSelected;
    ImageView childrenIcon;
    ImageView childrenIconSelected;
    ImageView emergencyIcon;
    ImageView emergencyIconSelected;
    ImageView generalIcon;
    ImageView generalIconSelected;
    ImageView prenatalIcon;
    ImageView prenatalIconSelected;

    private void resetOptionButtons() {
        int defaultBgColor = Color.parseColor("#FFFFFF");
        int defaultTextColor = Color.parseColor("#007788");

        AnimalBtn.setBackgroundColor(defaultBgColor);
        AnimalBtn.setTextColor(defaultTextColor);
        animalIcon.setVisibility(View.VISIBLE);
        animalIconSelected.setVisibility(View.GONE);

        EmergencyButton.setBackgroundColor(defaultBgColor);
        EmergencyButton.setTextColor(defaultTextColor);
        emergencyIcon.setVisibility(View.VISIBLE);
        emergencyIconSelected.setVisibility(View.GONE);

        TetanusButton.setBackgroundColor(defaultBgColor);
        TetanusButton.setTextColor(defaultTextColor);
        tetanusIcon.setVisibility(View.VISIBLE);
        tetanusIconSelected.setVisibility(View.GONE);

        ChildrenButton.setBackgroundColor(defaultBgColor);
        ChildrenButton.setTextColor(defaultTextColor);
        childrenIcon.setVisibility(View.VISIBLE);
        childrenIconSelected.setVisibility(View.GONE);

        GeneralButton.setBackgroundColor(defaultBgColor);
        GeneralButton.setTextColor(defaultTextColor);
        generalIcon.setVisibility(View.VISIBLE);
        generalIconSelected.setVisibility(View.GONE);

        PrenatalButton.setBackgroundColor(defaultBgColor);
        PrenatalButton.setTextColor(defaultTextColor);
        prenatalIcon.setVisibility(View.VISIBLE);
        prenatalIconSelected.setVisibility(View.GONE);
    }

    private void setSelectedDoctorType(String doctorType) {
        resetOptionButtons();

        selectedDoctorType = doctorType;
        confirmButton.setVisibility(View.VISIBLE);

        int selectedBgColor = Color.parseColor("#007788");
        int selectedTextColor = Color.parseColor("#FFFFFF");

        switch (doctorType) {
            case "Animal Bite Vaccine":
                AnimalBtn.setBackgroundColor(selectedBgColor);
                AnimalBtn.setTextColor(selectedTextColor);
                animalIcon.setVisibility(View.GONE);
                animalIconSelected.setVisibility(View.VISIBLE);
                break;
            case "Anti Tetanus Vaccine":
                TetanusButton.setBackgroundColor(selectedBgColor);
                TetanusButton.setTextColor(selectedTextColor);
                tetanusIcon.setVisibility(View.GONE);
                tetanusIconSelected.setVisibility(View.VISIBLE);
                break;
            case "Emergency Care":
                EmergencyButton.setBackgroundColor(selectedBgColor);
                EmergencyButton.setTextColor(selectedTextColor);
                emergencyIcon.setVisibility(View.GONE);
                emergencyIconSelected.setVisibility(View.VISIBLE);
                break;
            case "Children Checkup":
                ChildrenButton.setBackgroundColor(selectedBgColor);
                ChildrenButton.setTextColor(selectedTextColor);
                childrenIcon.setVisibility(View.GONE);
                childrenIconSelected.setVisibility(View.VISIBLE);
                break;
            case "General Checkup":
                GeneralButton.setBackgroundColor(selectedBgColor);
                GeneralButton.setTextColor(selectedTextColor);
                generalIcon.setVisibility(View.GONE);
                generalIconSelected.setVisibility(View.VISIBLE);
                break;
            case "Prenatal":
                PrenatalButton.setBackgroundColor(selectedBgColor);
                PrenatalButton.setTextColor(selectedTextColor);
                prenatalIcon.setVisibility(View.GONE);
                prenatalIconSelected.setVisibility(View.VISIBLE);
                break;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_page);

        animalIcon = (ImageView) findViewById(R.id.aniIcon);
        animalIconSelected = (ImageView) findViewById(R.id.aniIconSelected);

        tetanusIcon = (ImageView) findViewById(R.id.tetaIcon);
        tetanusIconSelected = (ImageView) findViewById(R.id.tetaIconSelected);

        childrenIcon = (ImageView) findViewById(R.id.chilIcon);
        childrenIconSelected = (ImageView) findViewById(R.id.chilIconSelected);

        emergencyIcon = (ImageView) findViewById(R.id.emeIcon);
        emergencyIconSelected = (ImageView) findViewById(R.id.emeIconSelected);

        generalIcon = (ImageView) findViewById(R.id.genIcon);
        generalIconSelected = (ImageView) findViewById(R.id.genIconSelected);

        prenatalIcon = (ImageView) findViewById(R.id.preIcon);
        prenatalIconSelected = (ImageView) findViewById(R.id.preIconSelected);


        prev1Btn = (Button) findViewById(R.id.prev1Btn);
        prev1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMain1();}
        });

        confirmButton = (Button) findViewById(R.id.confirm1Btn);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedDoctorType != null) {
                    openMapActivity(selectedDoctorType);
                }
            }
        });

        AnimalBtn = (Button) findViewById(R.id.AnimalVBtn);
        AnimalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelectedDoctorType("Animal Bite Vaccine");
            }
        });
        EmergencyButton = (Button) findViewById(R.id.emergencyBtn);
        EmergencyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelectedDoctorType("Emergency Care");
            }
        });
        TetanusButton = (Button) findViewById(R.id.TetanusBtn);
        TetanusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelectedDoctorType("Anti Tetanus Vaccine");
            }
        });
        ChildrenButton = (Button) findViewById(R.id.ChildrenBtn);
        ChildrenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelectedDoctorType("Children Checkup");
            }
        });
        GeneralButton = (Button) findViewById(R.id.GeneralBtn);
        GeneralButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelectedDoctorType("General Checkup");
            }
        });
        PrenatalButton = (Button) findViewById(R.id.PrenatalBtn);
        PrenatalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelectedDoctorType("Prenatal");
            }
        });



    }
    public void openMain1(){
        Intent intent = new Intent(this, MainPage.class);
        startActivity(intent);
    }
    public void openMapActivity(String doctorType) {
        Intent intent = new Intent(this, TransitionActivity.class);
        intent.putExtra("doctorType", doctorType);
        startActivity(intent);
    }
}