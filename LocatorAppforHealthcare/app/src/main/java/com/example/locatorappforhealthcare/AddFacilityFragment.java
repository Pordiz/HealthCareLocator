package com.example.locatorappforhealthcare;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.radiobutton.MaterialRadioButton;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;



public class AddFacilityFragment extends Fragment {

    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int CAMERA_REQUEST = 2;
    private static final int REQUEST_CODE_PICK_LOCATION = 3;
    private Uri imageUri;
    private ImageView facilityImageView;
    private TextInputEditText latitudeInput;
    private TextInputEditText longitudeInput;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_layout, container, false);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) requireActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        RadioGroup servicesTypeRadioGroup = view.findViewById(R.id.services_type_radio_group);
        MaterialRadioButton doctorsRadioButton = view.findViewById(R.id.doctors_radio_button);
        MaterialRadioButton medicalServicesRadioButton = view.findViewById(R.id.medical_services_radio_button);
        MaterialRadioButton bothRadioButton = view.findViewById(R.id.both_radio_button);
        LinearLayout doctorsCheckboxes = view.findViewById(R.id.doctors_checkboxes);
        LinearLayout medicalServicesCheckboxes = view.findViewById(R.id.medical_services_checkboxes);
        MaterialButton submitFacilityButton = view.findViewById(R.id.submit_facility_button);
        MaterialButton uploadImageButton = view.findViewById(R.id.upload_image_button);
        facilityImageView = view.findViewById(R.id.facility_image);
        latitudeInput = view.findViewById(R.id.latitude_input);
        longitudeInput = view.findViewById(R.id.longitude_input);

        servicesTypeRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == doctorsRadioButton.getId()) {
                doctorsCheckboxes.setVisibility(View.VISIBLE);
                medicalServicesCheckboxes.setVisibility(View.GONE);
            } else if (checkedId == medicalServicesRadioButton.getId()) {
                doctorsCheckboxes.setVisibility(View.GONE);
                medicalServicesCheckboxes.setVisibility(View.VISIBLE);
            } else if (checkedId == bothRadioButton.getId()) {
                doctorsCheckboxes.setVisibility(View.VISIBLE);
                medicalServicesCheckboxes.setVisibility(View.VISIBLE);
            }
        });


        TextView facilityCategoryHint = view.findViewById(R.id.facility_category_hint);
        Spinner facilityCategorySpinner = view.findViewById(R.id.facility_category);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(requireContext(), R.array.facility_categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        facilityCategorySpinner.setAdapter(adapter);

        facilityCategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    facilityCategoryHint.setVisibility(View.GONE);
                } else {
                    facilityCategoryHint.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        submitFacilityButton.setOnClickListener(v -> {
            // Retrieve user input from views
            TextInputEditText facilityNameEditText = view.findViewById(R.id.facility_name);
            TextInputEditText facilityAddressEditText = view.findViewById(R.id.facility_address);
            TextInputEditText facilityPhoneEditText = view.findViewById(R.id.facility_phone);
            TextInputEditText facilityHoursEditText = view.findViewById(R.id.facility_hours);

            String facilityName1 = facilityNameEditText.getText().toString();
            String facilityAddress1 = facilityAddressEditText.getText().toString();
            String facilityPhone1 = facilityPhoneEditText.getText().toString();
            String facilityHours1 = facilityHoursEditText.getText().toString();
            String facilityCategory1 = facilityCategorySpinner.getSelectedItem().toString();

            // Retrieve and process selected services
            List<String> selectedServices = new ArrayList<>();
            addCheckedServices(view, R.id.doctors_cardiologist, selectedServices);
            addCheckedServices(view, R.id.doctors_dentist, selectedServices);
            addCheckedServices(view, R.id.doctors_family, selectedServices);
            addCheckedServices(view, R.id.doctors_pediatrician, selectedServices);
            addCheckedServices(view, R.id.doctors_gynecologist, selectedServices);
            addCheckedServices(view, R.id.services_animalbite, selectedServices);
            addCheckedServices(view, R.id.services_antitetanus, selectedServices);
            addCheckedServices(view, R.id.services_children, selectedServices);
            addCheckedServices(view, R.id.services_emergency, selectedServices);
            addCheckedServices(view, R.id.services_general, selectedServices);
            addCheckedServices(view, R.id.services_prenatal, selectedServices);

            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setType("message/rfc822");
            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"healthcarelocator@gmail.com"});
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "New Facility Request: " + facilityName1);
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Facility Name: " + facilityName1
                    + "\nFacility Address: " + facilityAddress1
                    + "\nFacility Phone: " + facilityPhone1
                    + "\nFacility Hours: " + facilityHours1
                    + "\nFacility Category: " + facilityCategory1
                    + "\nSelected Services: " + String.join(", ", selectedServices)
                    + "\nLatitude: " + latitudeInput.getText().toString()
                    + "\nLongitude: " + longitudeInput.getText().toString());

            if (imageUri != null) {
                emailIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
                emailIntent.setType(getMimeType(requireActivity(), imageUri));

                try {
                    startActivity(Intent.createChooser(emailIntent, "Send email using:"));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(requireActivity(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(requireActivity(), "Error attaching image. Please try again.", Toast.LENGTH_SHORT).show();
            }
        });

        MaterialButton pickLocationButton = view.findViewById(R.id.pick_location_button);
        pickLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), RequestMapActivity.class);
                startActivityForResult(intent, REQUEST_CODE_PICK_LOCATION);
            }
        });


        uploadImageButton.setOnClickListener(v -> {
            // Check for permissions and request if necessary
            if (ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PICK_IMAGE_REQUEST);
            } else {
                openImagePicker();
            }
        });

        return view;
    }

    private void openImagePicker() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setTitle("Choose Image Source");
        builder.setItems(new CharSequence[]{"Gallery", "Camera"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST);
                        break;
                    case 1:
                        if (ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.CAMERA}, CAMERA_REQUEST);
                        } else {
                            openCamera();
                        }
                        break;
                }
            }
        });
        builder.show();
    }
    private void openCamera() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "New Picture");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera");
        imageUri = requireActivity().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Handle result for image picker and camera
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(requireActivity().getContentResolver(), imageUri);
                facilityImageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(requireActivity().getContentResolver(), imageUri);
                facilityImageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (requestCode == REQUEST_CODE_PICK_LOCATION && resultCode == Activity.RESULT_OK && data != null) {
            // Handle result for location picker
            double latitude = data.getDoubleExtra("latitude", 0);
            double longitude = data.getDoubleExtra("longitude", 0);
            // Use the latitude and longitude as needed

            // Update the latitude and longitude TextInputEditText fields
            latitudeInput.setText(String.valueOf(latitude));
            longitudeInput.setText(String.valueOf(longitude));
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PICK_IMAGE_REQUEST) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openImagePicker();
            } else {
                Toast.makeText(requireActivity(), "Permission denied", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == CAMERA_REQUEST) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera();
            } else {
                Toast.makeText(requireActivity(), "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void addCheckedServices(View view, int checkBoxId, List<String> selectedServices) {
        MaterialCheckBox checkBox = view.findViewById(checkBoxId);
        if (checkBox.isChecked()) {
            selectedServices.add(checkBox.getText().toString());
        }
    }
    private File saveImageToExternalStorage(Bitmap bitmap) {
        File storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "LocatorAppForHealthcare");
        if (!storageDir.exists()) {
            if (!storageDir.mkdirs()) {
                Toast.makeText(requireActivity(), "Failed to create directory", Toast.LENGTH_SHORT).show();
                return null;
            }
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        File imageFile = new File(storageDir, "IMG_" + timeStamp + ".jpg");

        try {
            FileOutputStream fos = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fos);
            fos.close();
        } catch (IOException e) {
            Toast.makeText(requireActivity(), "Error saving image", Toast.LENGTH_SHORT).show();
        }
        return imageFile;
    }

    private String getMimeType(Context context, Uri uri) {
        String mimeType;
        if (uri.getScheme().equals(ContentResolver.SCHEME_CONTENT)) {
            ContentResolver cr = context.getContentResolver();
            mimeType = cr.getType(uri);
        } else {
            String fileExtension = MimeTypeMap.getFileExtensionFromUrl(uri.toString());
            mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtension.toLowerCase());
        }
        return mimeType;
    }
}
