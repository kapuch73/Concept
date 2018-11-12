package ru.kapuch.testing.concept;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class Client_list extends ListActivity {

    private SQLiteDatabase db;
    private Cursor cursor;

    private static final String EXTRA_CLIENTNO = "clientNo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView clientView = getListView();
        try {
            SQLiteOpenHelper clientDatabaseHelper = new ClientDatabaseHelper(this);
            db = clientDatabaseHelper.getReadableDatabase();

            cursor = db.query("CLIENT",
                    new String[]{"_id", "COMPANY_NAME", "CONTACT_NAME"},
                    null, null, null, null, null);

            CursorAdapter clientListAdapter = new SimpleCursorAdapter(this,
                    android.R.layout.simple_list_item_2,
                    cursor,
                    new String[]{"COMPANY_NAME", "CONTACT_NAME"},
                    new int[]{android.R.id.text1, android.R.id.text2},
                    0);
            clientView.setAdapter(clientListAdapter);

        } catch (SQLException e) {
            Toast toast = Toast.makeText(this, "Database Client_List", Toast.LENGTH_SHORT);
            toast.show();
        }

 /*

        ArrayAdapter<Client_no_use> clientListAdapter = new ArrayAdapter<Client_no_use>(
                this, android.R.layout.simple_list_item_1, Client_no_use.CLIENT_NOUSES);

*/

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cursor.close();
        db.close();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent(Client_list.this, ClientInfo.class);
        intent.putExtra(Client_list.EXTRA_CLIENTNO, (int) id);
        startActivity(intent);
    }
}
