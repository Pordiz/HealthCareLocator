
package com.example.locatorappforhealthcare;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Bundle;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import java.util.LinkedHashSet;
import java.util.Comparator;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.HashSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.List;
import com.google.android.gms.maps.model.Marker;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import android.view.MenuItem;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;



public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private BottomSheetBehavior<View> bottomSheetBehavior;
    private View bottomSheetLayout;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;
    private static final int PERMISSIONS_REQUEST_LOCATION = 1;
    private TextView facilityDistance1;
    private TextView facilityDistance2;
    private TextView facilityDistance3;
    private GoogleMap googleMap;
    private List<Marker> facilityMarkers = new ArrayList<>();

    private TextView facilityName1;
    private TextView facilityAddress1;
    private TextView facilityName2;
    private TextView facilityAddress2;
    private TextView facilityName3;
    private TextView facilityAddress3;

    private TextView facilityName4,facilityAddress4,facilityDistance4;
    private TextView facilityName5,facilityAddress5,facilityDistance5;
    private TextView facilityName6,facilityAddress6,facilityDistance6;
    private TextView facilityName7,facilityAddress7,facilityDistance7;
    private TextView facilityName8,facilityAddress8,facilityDistance8;
    private TextView facilityName9,facilityAddress9,facilityDistance9;
    private TextView facilityName10,facilityAddress10,facilityDistance10;

    private View facilityBottomSheet;
    private TextView facilityName;
    private TextView facilityAddress;
    private TextView facilityPhone;
    private TextView facilityOpeningHours;
    private TextView facilityCategory;
    private HashMap<Marker, Facility> markerFacilityMap = new HashMap<>();
    private BottomSheetDialog facilityBottomSheetDialog;
    private double currentSearchRadius = 5.0; //
    private boolean initialZoom = true;
    private int numberOfDisplayedFacilities = 3;
    private Location lastLocation;
    private Button increaseDisplayedFacilitiesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_map);

        MaterialButton facilityDirections1 = findViewById(R.id.facility_directions_1);
        MaterialButton facilityDirections2 = findViewById(R.id.facility_directions_2);
        MaterialButton facilityDirections3 = findViewById(R.id.facility_directions_3);
        MaterialButton facilityDirections4 = findViewById(R.id.facility_directions_4);
        MaterialButton facilityDirections5 = findViewById(R.id.facility_directions_5);
        MaterialButton facilityDirections6 = findViewById(R.id.facility_directions_6);
        MaterialButton facilityDirections7 = findViewById(R.id.facility_directions_7);
        MaterialButton facilityDirections8 = findViewById(R.id.facility_directions_8);
        MaterialButton facilityDirections9 = findViewById(R.id.facility_directions_9);
        MaterialButton facilityDirections10 = findViewById(R.id.facility_directions_10);

        facilityName1 = findViewById(R.id.facility_name_1);
        facilityAddress1 = findViewById(R.id.facility_address_1);
        facilityDistance1 = findViewById(R.id.facility_distance_1);

        facilityName2 = findViewById(R.id.facility_name_2);
        facilityAddress2 = findViewById(R.id.facility_address_2);
        facilityDistance2 = findViewById(R.id.facility_distance_2);

        facilityName3 = findViewById(R.id.facility_name_3);
        facilityAddress3 = findViewById(R.id.facility_address_3);
        facilityDistance3 = findViewById(R.id.facility_distance_3);

        facilityName4 = findViewById(R.id.facility_name_4);
        facilityAddress4 = findViewById(R.id.facility_address_4);
        facilityDistance4 = findViewById(R.id.facility_distance_4);

        facilityName5 = findViewById(R.id.facility_name_5);
        facilityAddress5 = findViewById(R.id.facility_address_5);
        facilityDistance5 = findViewById(R.id.facility_distance_5);

        facilityName6 = findViewById(R.id.facility_name_6);
        facilityAddress6 = findViewById(R.id.facility_address_6);
        facilityDistance6 = findViewById(R.id.facility_distance_6);

        facilityName7 = findViewById(R.id.facility_name_7);
        facilityAddress7 = findViewById(R.id.facility_address_7);
        facilityDistance7 = findViewById(R.id.facility_distance_7);

        facilityName8 = findViewById(R.id.facility_name_8);
        facilityAddress8 = findViewById(R.id.facility_address_8);
        facilityDistance8 = findViewById(R.id.facility_distance_8);

        facilityName9 = findViewById(R.id.facility_name_9);
        facilityAddress9 = findViewById(R.id.facility_address_9);
        facilityDistance9 = findViewById(R.id.facility_distance_9);

        facilityName10 = findViewById(R.id.facility_name_10);
        facilityAddress10 = findViewById(R.id.facility_address_10);
        facilityDistance10 = findViewById(R.id.facility_distance_10);

        bottomSheetLayout = findViewById(R.id.bottom_sheet);
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetLayout);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        bottomSheetBehavior.setPeekHeight(150);

        MaterialButton increaseDisplayedFacilitiesButton = findViewById(R.id.increase_displayed_facilities_button);
        increaseDisplayedFacilitiesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increaseDisplayedFacilities();
            }
        });
        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                    increaseDisplayedFacilitiesButton.setVisibility(View.GONE);
                } else if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    increaseDisplayedFacilitiesButton.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                // You can leave this empty if you don't need to handle the sliding event.
            }
        });


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_map:
                        getSupportFragmentManager().popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        increaseDisplayedFacilitiesButton.setVisibility(View.VISIBLE);
                        return true;

                    case R.id.menu_direction:
                        getSupportFragmentManager().popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                        increaseDisplayedFacilitiesButton.setVisibility(View.GONE);
                        return true;

                    case R.id.menu_add:
                        switchFragment(new AddFacilityFragment());
                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        increaseDisplayedFacilitiesButton.setVisibility(View.GONE);
                        return true;
                }
                return false;
            }
        });


        View facilityBottomSheet = getLayoutInflater().inflate(R.layout.facility_bottom_sheet, null);
        facilityBottomSheetDialog = new BottomSheetDialog(this);
        facilityBottomSheetDialog.setContentView(facilityBottomSheet);

        facilityName = facilityBottomSheet.findViewById(R.id.facility_name);
        facilityAddress = facilityBottomSheet.findViewById(R.id.facility_address);
        facilityPhone = facilityBottomSheet.findViewById(R.id.facility_phone);
        facilityOpeningHours = facilityBottomSheet.findViewById(R.id.facility_opening_hours);
        facilityCategory = facilityBottomSheet.findViewById(R.id.facility_category);

        BottomSheetBehavior<View> facilityBottomSheetBehavior = BottomSheetBehavior.from((View) facilityBottomSheet.getParent());
        facilityBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);


        facilityDirections1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!previousTopFacilities.isEmpty() && previousTopFacilities.size() > 0) {
                    openDirections(new LatLng(previousTopFacilities.get(0).getLatitude(), previousTopFacilities.get(0).getLongitude()));
                }
            }
        });

        facilityDirections2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!previousTopFacilities.isEmpty() && previousTopFacilities.size() > 1) {
                    openDirections(new LatLng(previousTopFacilities.get(1).getLatitude(), previousTopFacilities.get(1).getLongitude()));
                }
            }
        });

        facilityDirections3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!previousTopFacilities.isEmpty() && previousTopFacilities.size() > 2) {
                    openDirections(new LatLng(previousTopFacilities.get(2).getLatitude(), previousTopFacilities.get(2).getLongitude()));
                }
            }
        });

        facilityDirections4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!previousTopFacilities.isEmpty() && previousTopFacilities.size() > 3) {
                    openDirections(new LatLng(previousTopFacilities.get(3).getLatitude(), previousTopFacilities.get(3).getLongitude()));
                }
            }
        });

        facilityDirections5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!previousTopFacilities.isEmpty() && previousTopFacilities.size() > 4) {
                    openDirections(new LatLng(previousTopFacilities.get(4).getLatitude(), previousTopFacilities.get(4).getLongitude()));
                }
            }
        });

        facilityDirections6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!previousTopFacilities.isEmpty() && previousTopFacilities.size() > 5) {
                    openDirections(new LatLng(previousTopFacilities.get(5).getLatitude(), previousTopFacilities.get(5).getLongitude()));
                }
            }
        });

        facilityDirections7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!previousTopFacilities.isEmpty() && previousTopFacilities.size() > 6) {
                    openDirections(new LatLng(previousTopFacilities.get(6).getLatitude(), previousTopFacilities.get(6).getLongitude()));
                }
            }
        });

        facilityDirections8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!previousTopFacilities.isEmpty() && previousTopFacilities.size() > 7) {
                    openDirections(new LatLng(previousTopFacilities.get(7).getLatitude(), previousTopFacilities.get(7).getLongitude()));
                }
            }
        });

        facilityDirections9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!previousTopFacilities.isEmpty() && previousTopFacilities.size() > 8) {
                    openDirections(new LatLng(previousTopFacilities.get(8).getLatitude(), previousTopFacilities.get(8).getLongitude()));
                }
            }
        });

        facilityDirections10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!previousTopFacilities.isEmpty() && previousTopFacilities.size() > 9) {
                    openDirections(new LatLng(previousTopFacilities.get(9).getLatitude(), previousTopFacilities.get(9).getLongitude()));
                }
            }
        });


        // Initialize the FusedLocationProviderClient before requesting permissions
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        // Request location permissions
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSIONS_REQUEST_LOCATION);
        } else {
            initMap();
        }
    }


    private void initMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private List<Facility> previousTopFacilities = new ArrayList<>();

    private void displayTopNearestFacilities(List<FacilityDistance> facilityDistances) {
        Log.d("LocatorApp", "Facility distances size: " + facilityDistances.size());
        // Sort the facilityDistances list by distance
        Collections.sort(facilityDistances, new Comparator<FacilityDistance>() {
            @Override
            public int compare(FacilityDistance fd1, FacilityDistance fd2) {
                return Double.compare(fd1.getDistance(), fd2.getDistance());
            }
        });
        List<Facility> newTopFacilities = new ArrayList<>();
        List<Double> distances = new ArrayList<>();
        for (int i = 0; i < Math.min(numberOfDisplayedFacilities, facilityDistances.size()); i++) {
            Facility facility = facilityDistances.get(i).facility;
            distances.add(facilityDistances.get(i).getDistance()); // Add the distance to the distances list
            newTopFacilities.add(facility);
        }

        // Clear previous markers before adding new ones
        for (Marker marker : facilityMarkers) {
            marker.remove();
        }
        facilityMarkers.clear();
        Set<Facility> uniqueFacilities = new LinkedHashSet<>(newTopFacilities);
        newTopFacilities.clear();
        newTopFacilities.addAll(uniqueFacilities);



        // Add markers for top 3 nearest facilities
        for (Facility facility : newTopFacilities) {
            LatLng latLng = new LatLng(facility.getLatitude(), facility.getLongitude());
            Marker marker = googleMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .title(facility.getName())
                    .icon(scaleMarkerIcon(R.drawable.hospital_building, 80, 80)));
            facilityMarkers.add(marker);
            markerFacilityMap.put(marker, facility);

            Log.d("LocatorApp", "Added marker: " + facility.getName() + " at " + latLng.latitude + ", " + latLng.longitude);
        }

        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        if (lastLocation != null) {
            LatLng userLatLng = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());
            builder.include(userLatLng);
        }
        for (Facility facility : newTopFacilities) {
            LatLng latLng = new LatLng(facility.getLatitude(), facility.getLongitude());
            builder.include(latLng);
        }
        LatLngBounds bounds = builder.build();
        int padding = 100;
        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
        mMap.animateCamera(cu);


        previousTopFacilities = newTopFacilities;
        Log.d("TESTING", "Distances for top 3 facilities: " + distances);
        updateBottomSheet(newTopFacilities, distances);
    }


    private List<String> toStringList(JSONArray jsonArray) throws JSONException {
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            stringList.add(jsonArray.getString(i));
        }
        return stringList;
    }

    private List<Facility> getAllFacilities() {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        return dbHelper.getAllFacilities();
    }

    private List<FacilityDistance> calculateDistances(LatLng userLocation, List<Facility> facilities) {
        List<FacilityDistance> distances = new ArrayList<>();
        for (Facility facility : facilities) {
            LatLng facilityLocation = new LatLng(facility.getLatitude(), facility.getLongitude());
            double distance = LocationUtils.haversine(userLocation.latitude, userLocation.longitude, facilityLocation.latitude, facilityLocation.longitude);
            distances.add(new FacilityDistance(facility, distance));
        }

        return distances;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSIONS_REQUEST_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initMap();
            } else {
                // Permission denied
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        fusedLocationProviderClient.removeLocationUpdates(locationCallback);
    }

    private LocationRequest createLocationRequest() {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        return locationRequest;
    }

    private List<Facility> filterFacilitiesByDoctorType(List<Facility> facilities, String doctorType) {
        Set<Facility> filteredFacilitiesSet = new HashSet<>();

        for (Facility facility : facilities) {
            List<String> services = facility.getServices();
            if (services.contains(doctorType)) {
                filteredFacilitiesSet.add(facility);
            }
        }

        List<Facility> filteredFacilities = new ArrayList<>(filteredFacilitiesSet);
        return filteredFacilities;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        this.googleMap = googleMap;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }
        mMap.setMyLocationEnabled(true);

        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    LatLng userLatLng = new LatLng(location.getLatitude(), location.getLongitude());
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLatLng, 15));
                }
            }
        });

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                for (Facility facility : previousTopFacilities) {
                    LatLng latLng = new LatLng(facility.getLatitude(), facility.getLongitude());
                    if (marker.getPosition().equals(latLng)) {
                        displayFacilityBottomSheet(facility);
                        break;
                    }
                }
                return false;
            }
        });


        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        locationRequest = createLocationRequest();

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                lastLocation = locationResult.getLastLocation();

                LatLng userLatLng = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());
                Log.d("TESTING", "User location - Latitude: " + lastLocation.getLatitude() + ", Longitude: " + lastLocation.getLongitude());
                List<Facility> facilities = getAllFacilities();
                String doctorType = getIntent().getStringExtra("doctorType");
                if (doctorType != null) {
                    facilities = filterFacilitiesByDoctorType(facilities, doctorType);
                    Log.d("LocatorApp", "Filtered facilities size: " + facilities.size());
                }
                List<FacilityDistance> facilityDistances = calculateDistances(userLatLng, facilities);
                displayTopNearestFacilities(facilityDistances);


            }
        };


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
    }

    private void increaseDisplayedFacilities() {
        numberOfDisplayedFacilities += 3;
        if (lastLocation != null) {
            LatLng userLatLng = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());
            List<Facility> facilities = getAllFacilities();
            String doctorType = getIntent().getStringExtra("doctorType");
            if (doctorType != null) {
                facilities = filterFacilitiesByDoctorType(facilities, doctorType);
                Log.d("LocatorApp", "Filtered facilities size: " + facilities.size());
            }
            List<FacilityDistance> facilityDistances = calculateDistances(userLatLng, facilities);
            displayTopNearestFacilities(facilityDistances);
        }
    }



    private void updateFacilityInfo(TextView facilityName, TextView facilityAddress, TextView facilityDistance, Facility facility, double distance) {
        Log.d("TESTING", "Facility Name: " + facility.getName() + " | Distance: " + distance);
        facilityName.setText(facility.getName());
        facilityAddress.setText(facility.getAddressLine());

        if (distance < 1) {
            facilityDistance.setText(String.format(Locale.US, "%.1f m", distance * 1000));
        } else {
            facilityDistance.setText(String.format(Locale.US, "%.1f km", distance));
        }
    }

    private void updateBottomSheet(final List<Facility> facilities, final List<Double> distances) {
        if (facilities.isEmpty()) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
            return;
        }

        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                if (facilities.size() >= 1) {
                    updateFacilityInfo(facilityName1, facilityAddress1, facilityDistance1, facilities.get(0), distances.get(0));
                }

                if (facilities.size() >= 2) {
                    updateFacilityInfo(facilityName2, facilityAddress2, facilityDistance2, facilities.get(1), distances.get(1));
                }

                if (facilities.size() >= 3) {
                    updateFacilityInfo(facilityName3, facilityAddress3, facilityDistance3, facilities.get(2), distances.get(2));
                }
                if (facilities.size() >= 4) {
                    updateFacilityInfo(facilityName4, facilityAddress4, facilityDistance4, facilities.get(3), distances.get(3));
                }
                if (facilities.size() >= 5) {
                    updateFacilityInfo(facilityName5, facilityAddress5, facilityDistance5, facilities.get(4), distances.get(4));
                }
                if (facilities.size() >= 6) {
                    updateFacilityInfo(facilityName6, facilityAddress6, facilityDistance6, facilities.get(5), distances.get(5));
                }
                if (facilities.size() >= 7) {
                    updateFacilityInfo(facilityName7, facilityAddress7, facilityDistance7, facilities.get(6), distances.get(6));
                }
                if (facilities.size() >= 8) {
                    updateFacilityInfo(facilityName8, facilityAddress8, facilityDistance8, facilities.get(7), distances.get(7));
                }
                if (facilities.size() >= 9) {
                    updateFacilityInfo(facilityName9, facilityAddress9, facilityDistance9, facilities.get(8), distances.get(8));
                }
                if (facilities.size() >= 10) {
                    updateFacilityInfo(facilityName10, facilityAddress10, facilityDistance10, facilities.get(9), distances.get(9));
                }
            }
        });
    }
    private void openDirections(LatLng facilityLatLng) {
        Uri gmmIntentUri = Uri.parse("google.navigation:q=" + facilityLatLng.latitude + "," + facilityLatLng.longitude);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        } else {
            Toast.makeText(this, "Google Maps is not available.", Toast.LENGTH_SHORT).show();
        }
    }
    private BitmapDescriptor scaleMarkerIcon(int drawableResourceId, int width, int height) {
        // Retrieve the original image
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), drawableResourceId);

        // Scale the image
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, width, height, false);

        // Convert the scaled image to a BitmapDescriptor
        return BitmapDescriptorFactory.fromBitmap(scaledBitmap);
    }
    private void switchFragment(Fragment fragment) {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.container);
        if (currentFragment == null || !(currentFragment instanceof AddFacilityFragment)) {
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left)
                    .replace(R.id.container, fragment)
                    .addToBackStack(null)
                    .commit();
        }
    }

    private void displayFacilityBottomSheet(Facility facility) {
        facilityName.setText(facility.getName());
        facilityAddress.setText(facility.getAddressLine());
        facilityPhone.setText(facility.getPhoneNumber());
        facilityOpeningHours.setText(facility.getOpeningHours());
        facilityCategory.setText(facility.getFacilityCategory());
        facilityBottomSheetDialog.show();
    }
}