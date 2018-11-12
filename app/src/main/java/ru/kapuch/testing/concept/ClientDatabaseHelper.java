package ru.kapuch.testing.concept;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ClientDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "conceptclient.db";
    private static final int DB_VERSION = 2;


    ClientDatabaseHelper (Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateMyDatabase(db, 1, DB_VERSION);
        db.execSQL("CREATE TABLE CLIENT (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "LTD TEXT, "
                + "COMPANY_NAME TEXT, "
                + "CONTACT_NAME TEXT, "
                + "CONTACT_PHONE TEXT, "
                + "CONTACT_EMAIL TEXT, "
                + "DESCRIPTION TEXT);");
        insertClient(db, "ООО", "Концепт Технологии", "Капустин Алексей Владимирович",
                "(495) 775-3175", "ka@c-tt.ru", "наша фирма");
        insertClient(db, "ЗАО", "Ребико", "Липин Макс Ефимович",
                " ", "maxlipin74@c-tt.ru", "конкуренты");

       }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDatabase(db, oldVersion, newVersion);
    }

    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
       if (oldVersion < 3){
            db.execSQL("ALTER TABLE CLIENT ADD COLUMN ALTERNATIVE_CONTACT TEXT;");
        }
    }

    private static void insertClient(SQLiteDatabase db, String ltd, String company_name,
                                     String contact_name, String contact_phone, String contact_email,
                                     String descriptionText) {
        ContentValues clientValue = new ContentValues();
        clientValue.put("LTD", ltd);
        clientValue.put("COMPANY_NAME", company_name);
        clientValue.put("CONTACT_NAME", contact_name);
        clientValue.put("CONTACT_PHONE", contact_phone);
        clientValue.put("CONTACT_EMAIL", contact_email);
        clientValue.put("DESCRIPTION", descriptionText);
        db.insert("CLIENT", null, clientValue);
    }

}
