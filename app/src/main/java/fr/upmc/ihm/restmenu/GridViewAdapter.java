package fr.upmc.ihm.restmenu;

import java.util.ArrayList;

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
        TextView imageTitle;
        ImageView image;
        ImageButton infoButton;
        TextView counterZone;
    }

    public TextView getTextV() {
        return holder.imageTitle;
    }

    public int getCounter() {
        return Integer.parseInt((String)holder.counterZone.getText());
    }

    public void setCounter(int counter) {
        holder.counterZone.setText(String.valueOf(counter));
    }

    public void zoneCounterVisibility(int visibility) {
        holder.counterZone.setVisibility(visibility);
    }

    public GridViewAdapter(Context context, int layoutResourceId, ArrayList<ImageItem> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View row = convertView;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.imageTitle = (TextView) row.findViewById(R.id.text);
            holder.image = (ImageView) row.findViewById(R.id.image);
            holder.infoButton = (ImageButton) row.findViewById(R.id.infoButton);
            holder.counterZone = (TextView) row.findViewById(R.id.counter);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        ImageItem item = data.get(position);
        holder.imageTitle.setText(item.getTitle());
        holder.image.setImageBitmap(item.getImage());

        holder.imageTitle.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ((GridView) parent).performItemClick(v, position, 0);
            }
        });

        holder.image.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ((GridView) parent).performItemClick(v, position, 0);
                int i = Integer.parseInt((String) holder.counterZone.getText());
                i++;
                holder.counterZone.setText(String.valueOf(i));

                Log.i("Test ", "" + holder.imageTitle.getText());
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