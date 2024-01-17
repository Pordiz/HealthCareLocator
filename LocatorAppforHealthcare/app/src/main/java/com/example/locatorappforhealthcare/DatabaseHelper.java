package com.example.locatorappforhealthcare;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.List;
import android.util.Log;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.Arrays;
import android.text.TextUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "healthcare_facilities.db";
    public static final String TABLE_NAME = "facilities";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "LATITUDE";
    public static final String COL_4 = "LONGITUDE";
    public static final String COL_5 = "ADDRESS";
    public static final String COL_6 = "PHONE";
    public static final String COL_7 = "OPENING_HOURS";
    public static final String COL_8 = "CATEGORY";
    public static final String COL_9 = "DOCTOR_TYPE";
    public static final String COL_10 = "SERVICES";


    private static final String create_table = "CREATE TABLE " + TABLE_NAME + " (" +
            COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_2 + " TEXT, " +
            COL_3 + " REAL, " +
            COL_4 + " REAL, " +
            COL_5 + " TEXT, " +
            COL_6 + " TEXT, " +
            COL_7 + " TEXT, " +
            COL_8 + " TEXT, " +
            COL_9 + " TEXT, " +
            COL_10 + " TEXT)";

    private static final String DROP_TABLE_FACILITIES = "DROP TABLE IF EXISTS facilities";

    public DatabaseHelper(Context context) {
        super(context, "healthcare_facilities.db", null, 1);
        if (isDatabaseEmpty()) {
            addFacilitiesFromJson(context);
        }
    }
    private void addFacilitiesFromJson(Context context) {
        try {
            InputStream inputStream = context.getAssets().open("data.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();

            String json = new String(buffer, StandardCharsets.UTF_8);
            JSONArray jsonArray = new JSONArray(json);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String name = jsonObject.getString("name");
                double latitude = jsonObject.getDouble("latitude");
                double longitude = jsonObject.getDouble("longitude");
                String address = jsonObject.getString("address");
                String phone = jsonObject.getString("phone");

                String opening_hours = "";
                if (jsonObject.has("opening_hours")) {
                    opening_hours = jsonObject.getString("opening_hours");
                }

                String category = jsonObject.getString("category");
                String doctorType = "";
                if (jsonObject.has("doctorType")) {
                    doctorType = jsonObject.getString("doctorType");
                }

                JSONArray servicesJsonArray = jsonObject.getJSONArray("services");
                List<String> services = new ArrayList<>();
                for (int j = 0; j < servicesJsonArray.length(); j++) {
                    services.add(servicesJsonArray.getString(j));
                }


                Facility facility = new Facility(name, latitude, longitude, address, phone, opening_hours, category, doctorType, services);
                addFacility(facility);

            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }



    @SuppressWarnings("Range")
    public List<Facility> getAllFacilities() {
        List<Facility> facilityList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (res.moveToFirst()) {
            do {
                String servicesString = "";
                int servicesColumnIndex = res.getColumnIndex(COL_10);

                for (int i = 0; i < res.getColumnCount(); i++) {

                }

                if (servicesColumnIndex != -1) {
                    try {
                        servicesString = res.getString(servicesColumnIndex);
                    } catch (Exception e) {
                    }
                }
                List<String> servicesList = Arrays.asList(servicesString.split(","));

                Facility facility = new Facility(
                        res.getString(res.getColumnIndex(COL_2)),
                        res.getDouble(res.getColumnIndex(COL_3)),
                        res.getDouble(res.getColumnIndex(COL_4)),
                        res.getString(res.getColumnIndex(COL_5)),
                        res.getString(res.getColumnIndex(COL_6)),
                        res.getString(res.getColumnIndex(COL_7)),
                        res.getString(res.getColumnIndex(COL_8)),
                        res.getString(res.getColumnIndex(COL_9)),
                        servicesList);

                if (!facilityList.contains(facility)) {
                    facilityList.add(facility);
                }

            } while (res.moveToNext());
        }

        res.close();
        return facilityList;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_FACILITIES);
        onCreate(db);
    }

    public boolean isDatabaseEmpty() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_NAME, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        return count == 0;
    }

    public boolean addFacility(Facility facility) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, facility.getName());   
        contentValues.put(COL_3, facility.getLatitude());
        contentValues.put(COL_4, facility.getLongitude());
        contentValues.put(COL_5, facility.getAddressLine());
        contentValues.put(COL_6, facility.getPhoneNumber());
        contentValues.put(COL_7, facility.getOpeningHours());
        contentValues.put(COL_8, facility.getFacilityCategory());
        contentValues.put(COL_9, facility.getDoctorType());
        contentValues.put(COL_10, TextUtils.join(",", facility.getServices()));

        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }

}