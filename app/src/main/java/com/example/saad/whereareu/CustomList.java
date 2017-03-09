package com.example.saad.whereareu;

/**
 * Created by SAAD on 03-07-2017.
 */


/**
 * Created by SaaD on 16-Nov-16.
 */
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by Belal on 7/22/2015.
 */
public class CustomList extends ArrayAdapter<String> {
    private String[] names;
    private String[] desc;
    private Integer[] imageid;
    private String [] times;
    private Activity context;

    public CustomList(Activity context, String[] names,   Integer[] imageid,String[] times) {
        super(context, R.layout.list_layout, names);
        this.context = context;
        this.names = names;
        this.desc = desc;
        this.imageid = imageid;
        this.times=times;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_layout, null, true);
        Button              b=  (Button)   listViewItem.findViewById(R.id.button5);
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewTime = (TextView) listViewItem.findViewById(R.id.textViewName2);
        // TextView textViewDesc = (TextView) listViewItem.findViewById(R.id.textViewDesc);
        ImageView image = (ImageView) listViewItem.findViewById(R.id.imageView);
        image.setMaxWidth(100);image.setMaxHeight(100);
        b.setText("");
        textViewName.setText(names[position]);
        textViewTime.setText(times[position]);
        //  textViewDesc.setText(desc[position]);
        image.setImageResource(imageid[position]);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "POSTITON: "+position, Toast.LENGTH_SHORT).show();
           try{
               MapsActivity.pdMarkers.show();
               MapsActivity.pdMarkers.dismiss();}
           catch (Exception r){Toast.makeText(context, r.getMessage(), Toast.LENGTH_SHORT).show();}

            }
        });

        return  listViewItem;
    }
}
