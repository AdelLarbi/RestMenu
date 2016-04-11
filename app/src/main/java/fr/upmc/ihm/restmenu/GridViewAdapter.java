package fr.upmc.ihm.restmenu;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class GridViewAdapter extends ArrayAdapter<ImageItem> {

    private Context context;
    private int layoutResourceId;
    private ArrayList<ImageItem> data = new ArrayList<ImageItem>();

    private  ViewHolder holder;

    static class ViewHolder {
        TextView imagePrice;
        ImageView image;
        ImageButton infoButton;
    }

    public GridViewAdapter(Context context, int layoutResourceId, ArrayList<ImageItem> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    public ArrayList<ImageItem> dataGetter() {
        return this.data;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View row = convertView;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.imagePrice = (TextView) row.findViewById(R.id.price);
            holder.image = (ImageView) row.findViewById(R.id.image);
            holder.infoButton = (ImageButton) row.findViewById(R.id.infoButton);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        ImageItem item = data.get(position);
        int price = new Random().nextInt(20) + 4;
        holder.imagePrice.setText(String.valueOf(price) + " €");
        holder.image.setImageBitmap(item.getImage());
        holder.image.setTag(item.getTitle());

        holder.imagePrice.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ((GridView) parent).performItemClick(v, position, 0);
            }
        });

        holder.image.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ((GridView) parent).performItemClick(v, position, 0);
            }
        });

        holder.infoButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ((GridView) parent).performItemClick(v, position, 0);
            }
        });

        return row;
    }
}