package ru.kapuch.testing.concept;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import static ru.kapuch.testing.concept.Client_list.EXTRA_CLIENTNO;

public class MainActivity extends Activity {

    private SQLiteDatabase db;
    private Cursor completeCursor;

    ListView menuView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        menuView = findViewById(R.id.main_menu);
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 2) {
                    Intent intent = new Intent(MainActivity.this, Client_list.class);
                    startActivity(intent);
                }
            }
        };
        setContentView(R.layout.activity_main);
        menuView.setOnItemClickListener(itemClickListener);

        ListView listComplete = findViewById(R.id.list_complete_client);
        try {
            SQLiteOpenHelper clientDatabaseHelper = new ClientDatabaseHelper(this);
            db = clientDatabaseHelper.getReadableDatabase();
            completeCursor = db.query("CLIENT",
                    new String[] {"_id", "COMPANY_NAME"},
                    "COMPLETE = 0",
                    null, null, null, null);
            CursorAdapter completeAdapter = new SimpleCursorAdapter(MainActivity.this,
                    android.R.layout.simple_list_item_1,
                    completeCursor,
                    new String[]{"COMPANY_NAME"},
                    new int[] {android.R.id.text1}, 0);
            listComplete.setAdapter(completeAdapter);
        } catch (SQLException e) {
            Toast toast = Toast.makeText(this, "CompleteAdapter not function", Toast.LENGTH_SHORT);
            toast.show();
        }

        listComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View view, int position, long id) {

                Intent intent = new Intent(MainActivity.this, Client_list.class);
                intent.putExtra(Client_list.EXTRA_CLIENTNO, (int)id );
                startActivity(intent);

            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        completeCursor.close();
        db.close();
    }
}
