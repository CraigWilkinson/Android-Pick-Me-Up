package com.example.craig.pick_me_up;

/**
 * Created by Craig on 14/09/2015.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NumberSQLlite extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "PhoneDB";

    public NumberSQLlite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PHONE_TABLE = "CREATE TABLE phone ( " +
                "number TEXT )";

        db.execSQL(CREATE_PHONE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS phone");
        this.onCreate(db);
    }

    private static final String TABLE_PHONE = "phone";
    private static final String KEY_PHONE = "phoneKey";


    public void addphoneNumber(phoneNumber number) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_PHONE, number.getNumber());
        db.insert(TABLE_PHONE,
                null,
                values);
    }

    public String getphoneNumber() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * from "+TABLE_PHONE;

        Cursor cursor = db.rawQuery(query,null);
            if (cursor != null)
                cursor.moveToFirst();


            phoneNumber onlyNumber = new phoneNumber(cursor.getString(0));

            return onlyNumber.getNumber();
        }
}

