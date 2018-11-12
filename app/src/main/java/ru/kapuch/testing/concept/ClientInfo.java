package ru.kapuch.testing.concept;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ClientInfo extends Activity {

    private static final String EXTRA_CLIENTNO = "clientNo";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_info);

        int clientNo = (Integer)getIntent().getExtras().get(EXTRA_CLIENTNO);
//        Client_no_use client = Client_no_use.CLIENT_NOUSES[clientNo];

        try {
            SQLiteOpenHelper clientDatabaseHelper = new ClientDatabaseHelper(this);
            SQLiteDatabase db = clientDatabaseHelper.getReadableDatabase();
            Cursor cursor = db.query("CLIENT",
                    new String[]{"LTD", "COMPANY_NAME", "CONTACT_NAME", "CONTACT_PHONE",
                            "CONTACT_EMAIL", "DESCRIPTION"},
                    "_id = ?",
                    new String[] {Integer.toString(clientNo)},
                    null, null, null);
            if (cursor.moveToFirst()) {
                String ltdText = cursor.getString(0);
                String companyText = cursor.getString(1);
                String nameText = cursor.getString(2);
                String phoneText = cursor.getString(3);
                String emailText = cursor.getString(4);
                String descriptionText = cursor.getString(5);

                TextView ltd = findViewById(R.id.ltd);
                ltd.setText(ltdText);

                TextView company_name = findViewById(R.id.company_name);
                company_name.setText(companyText);

                TextView contact_name = findViewById(R.id.contact_name);
                contact_name.setText(nameText);

                TextView phone_number = findViewById(R.id.phone_number);
                phone_number.setText(phoneText);

                TextView mail = findViewById(R.id.email);
                mail.setText(emailText);

                TextView description = findViewById(R.id.description);
                description.setText(descriptionText);
            }
            cursor.close();
            db.close();
        } catch (SQLException e) {
            Toast toast = Toast.makeText(this, "Database Client_no_use Info unavailable",
                    Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
