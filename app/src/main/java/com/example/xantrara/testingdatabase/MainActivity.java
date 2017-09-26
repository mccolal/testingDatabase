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
    CustomAdapter fridgeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        fridgeAdapter = new CustomAdapter(getApplicationContext(), fridgeList2);
        ListView fridgeListView = findViewById(R.id.fridgeSelection);

        fridgeListView.setAdapter(fridgeAdapter);
        refreshFridgeList();
    }


    public void onClickAddName(View view) {
        // Add a new fridge record
        ContentValues values = new ContentValues();
        values.put(ContentProv.NAME,
                ((EditText)findViewById(R.id.fridgeEntryName)).getText().toString());



        Uri uri = getContentResolver().insert(
                ContentProv.CONTENT_URI, values);

        Toast.makeText(this,"Created!",Toast.LENGTH_SHORT).show();
        refreshFridgeList();

    }
    public void onClickRetrieveStudents(View view) {
        // Retrieve student records
        String URL = "content://com.example.xantrara.testingdatabase.ContentProv";

        Uri students = Uri.parse(URL);
        Cursor c = managedQuery(students, null, null, null, "name");

        if (c.moveToFirst()) {
            do{

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

                fridgeList2.add(new Fridge(c.getString(c.getColumnIndex( ContentProv.NAME)),
                        c.getInt(c.getColumnIndex( ContentProv._ID))));
                /*Toast.makeText(this,
                        c.getString(c.getColumnIndex(ContentProv._ID)) +
                                ", " +  c.getString(c.getColumnIndex( ContentProv.NAME)),
                        Toast.LENGTH_SHORT).show();*/
            } while (c.moveToNext());
        }
        fridgeAdapter.notifyDataSetChanged();
    }
}