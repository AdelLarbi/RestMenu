package fr.upmc.ihm.restmenu;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Command extends AppCompatActivity {

    private GridView gridView;
    private GridViewAdapter gridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.command);

        gridView = (GridView) findViewById(R.id.gridView);
        gridAdapter = new GridViewAdapter(this, R.layout.grid_item_layout, getData());
        gridView.setAdapter(gridAdapter);

        gridView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ImageItem item = (ImageItem) parent.getItemAtPosition(position);

                TextView child = (TextView)parent.getChildAt(position).findViewById(R.id.counter);

                long viewId = view.getId();

                if (viewId == R.id.infoButton) {
                    //Create intent
                    Intent intent = new Intent(Command.this, DetailsActivity.class);
                    intent.putExtra("title", item.getTitle());
                    intent.putExtra("image", item.getImage());

                    //Start details activity
                    startActivity(intent);
                } else if (viewId == R.id.image || viewId == R.id.text) {
                    int counter = item.getCounter();
                    counter++;
                    item.setCounter(counter);
                    child.setVisibility(View.VISIBLE);
                    Toast.makeText(Command.this, "image!" + item.getCounter(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Command.this, "Else!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        /*infoButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(Command.this, "YASS!", Toast.LENGTH_LONG).show();
            }
        });*/
    }

    /**
     * Prepare some dummy data for gridview
     */
    private ArrayList<ImageItem> getData() {
        final ArrayList<ImageItem> imageItems = new ArrayList<>();
        TypedArray imgs = getResources().obtainTypedArray(R.array.image_ids);
        for (int i = 0; i < imgs.length(); i++) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imgs.getResourceId(i, -1));
            bitmap = ImageConverter.getRoundedCornerBitmap(bitmap, 20.0f);
            imageItems.add(new ImageItem(bitmap, "Image#" + i, 0));
        }
        return imageItems;
    }
}

class ImageConverter {

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float pixels) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }
}