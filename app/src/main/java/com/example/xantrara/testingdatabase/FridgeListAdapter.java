package com.example.xantrara.testingdatabase;

import android.app.ListActivity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Xantrara on 25-Sep-17.
 */

public class FridgeListAdapter extends ArrayAdapter<Fridge> {

    public FridgeListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public FridgeListAdapter(Context context, int resource, ArrayList<Fridge> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.fridge_list_view, null);
        }

        Fridge p = getItem(position);

        if (p != null) {
            TextView fridgeName = (TextView) v.findViewById(R.id.fridgeName);
            //TextView tt2 = (TextView) v.findViewById(R.id.categoryId);

            if (fridgeName != null) {
                fridgeName.setText(p.GetName());
            }

            //if (tt2 != null) {
            //    tt2.setText(p.getCategory().getId());
            //}

        }

        return v;
    }

}

