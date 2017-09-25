package com.example.xantrara.testingdatabase;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;

import android.content.ContentValues;
import android.content.CursorLoader;

import android.database.Cursor;

import android.view.Menu;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

// TODO: Credit this https://www.flaticon.com/free-icon/fridge_186500#term=fridge&page=1&position=24
//TODO: Credit https://www.flaticon.com/free-icon/remove_189690#term=minus&page=1&position=35


public class MainActivity extends Activity {


    ArrayList<Fridge> fridgeList2 = new ArrayList<>();
    FridgeListAdapter fridgeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        refreshFridgeList();

        fridgeAdapter = new FridgeListAdapter(this, R.layout.fridge_list_view,fridgeList2);
        ListView fridgeListView = findViewById(R.id.fridgeSelection);

        fridgeListView.setAdapter(fridgeAdapter);

    }


    public void onClickAddName(View view) {
        // Add a new fridge record
        ContentValues values = new ContentValues();
        values.put(ContentProv.NAME,
                ((EditText)findViewById(R.id.fridgeEntryName)).getText().toString());



        Uri uri = getContentResolver().insert(
                ContentProv.CONTENT_URI, values);

        Toast.makeText(getBaseContext(),
                uri.toString(), Toast.LENGTH_LONG).show();
        refreshFridgeList();
        fridgeAdapter.notifyDataSetChanged();
    }
    public void onClickRetrieveStudents(View view) {
        // Retrieve student records
        String URL = "content://com.example.xantrara.testingdatabase.ContentProv";

        Uri students = Uri.parse(URL);
        Cursor c = managedQuery(students, null, null, null, "name");

        if (c.moveToFirst()) {
            do{
                Toast.makeText(this,
                        c.getString(c.getColumnIndex(ContentProv._ID)) +
                                ", " +  c.getString(c.getColumnIndex( ContentProv.NAME)),
                        Toast.LENGTH_SHORT).show();
            } while (c.moveToNext());
        }
    }

    public void refreshFridgeList(){
        fridgeList2.clear();
        String URL = "content://com.example.xantrara.testingdatabase.ContentProv";
        Fridge temp;

        Uri fridges = Uri.parse(URL);
        Cursor c = managedQuery(fridges, null, null, null, "name");

        if (c.moveToFirst()) {
            do{
                fridgeList2.add(new Fridge(c.getString(c.getColumnIndex( ContentProv.NAME))));
                /*Toast.makeText(this,
                        c.getString(c.getColumnIndex(ContentProv._ID)) +
                                ", " +  c.getString(c.getColumnIndex( ContentProv.NAME)),
                        Toast.LENGTH_SHORT).show();*/
            } while (c.moveToNext());
        }
    }
}