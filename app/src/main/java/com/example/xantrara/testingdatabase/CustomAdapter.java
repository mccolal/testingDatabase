package com.example.xantrara.testingdatabase;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Fridge> list = new ArrayList<>();
    private LayoutInflater inflter;


    public CustomAdapter(Context applicationContext, ArrayList<Fridge> listReceived) {
        this.context = applicationContext;

        this.list = listReceived;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    public List<Fridge> getData(){
        return list;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }





    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflter.inflate(R.layout.fridge_list_view, viewGroup, false);

            holder = new ViewHolder();
            holder.btnDelete = (Button) convertView.findViewById(R.id.removeFridge);
            holder.textView = (TextView) convertView.findViewById(R.id.fridgeName);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textView.setText(list.get(position).GetName());


        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fridge temp = list.get(position);
                Uri fridges = Uri.parse(ContentProv.URL);

                list.remove(position);

                ContentProv.db.delete(ContentProv.FRIDGE_TABLE_NAME, "name=? AND _id=?", new String[] {
                        temp.GetName(),Integer.toString(temp.GetID())
                });
                notifyDataSetChanged();


            }
        });

        return convertView;
    }

    private static class ViewHolder {
        TextView textView;
        Button btnDelete;
    }
}