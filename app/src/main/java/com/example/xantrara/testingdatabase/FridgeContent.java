package com.example.xantrara.testingdatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class FridgeContent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fridge_content);

        TextView fridgeName = (TextView)findViewById(R.id.fridgeTitle);


        Bundle extras = getIntent().getExtras();

        Fridge fridge;

        if (extras != null){
            fridge = (Fridge)getIntent().getSerializableExtra("fridgeObject");
            fridgeName.setText(fridge.GetName());
        } else {
            fridgeName.setText("null");
        }

    }
}
