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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Command extends AppCompatActivity {

    private GridView gridView;
    private GridView gridViewMyCommand;
    private GridViewAdapter gridAdapter;
    private GridViewMyCommandAdapter gridAdapterMyCommand;
    private ArrayList<ImageItem> data = new ArrayList<ImageItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.command);

        gridView = (GridView) findViewById(R.id.gridView);
        gridViewMyCommand = (GridView) findViewById(R.id.gridViewMyCommand);

        gridAdapter = new GridViewAdapter(this, R.layout.grid_item_layout, getData(0));

        gridView.setAdapter(gridAdapter);

        gridView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ImageItem item = (ImageItem) parent.getItemAtPosition(position);

                TextView counterZone = (TextView) parent.getChildAt(position).findViewById(R.id.counter);

                long viewId = view.getId();

                if (viewId == R.id.infoButton) {
                    //Create intent
                    Intent intent = new Intent(Command.this, DetailsActivity.class);
                    intent.putExtra("title", item.getTitle());
                    intent.putExtra("image", item.getImage());

                    //Start details activity
                    startActivity(intent);
                } else if (viewId == R.id.image || viewId == R.id.text) {
                    int counter = Integer.parseInt((String) counterZone.getText());
                    counter++;
                    counterZone.setText(String.valueOf(counter));
                    counterZone.setVisibility(View.VISIBLE);

                    fillCart(position, counter);

                    //Toast.makeText(Command.this, "image!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Command.this, "Else!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void fillCart(int elementPosition, int commandCount) {
        ImageItem item = getData(commandCount).get(elementPosition);
        //if (!inCart(elementPosition, commandCount)) {
        data.add(item);
        gridAdapterMyCommand = new GridViewMyCommandAdapter(Command.this, R.layout.grid_mycommand_element, data);
        if (inCart(elementPosition, commandCount)) {
            gridAdapterMyCommand.dataGetter().remove(POS);
        }
        gridViewMyCommand.setAdapter(gridAdapterMyCommand);

        Toast.makeText(Command.this, "Exist = " + POS, Toast.LENGTH_SHORT).show();
    }

    private String print() {
        String res = new String();
        for (int i = 0; i < gridAdapterMyCommand.dataGetter().size(); i++)
            res += " " +gridAdapterMyCommand.dataGetter().get(i).getTag();
        return res;
    }
    private int POS = -1;
    private boolean inCart(int elementPosition, int commandCount) {
        String itemTag = getData(commandCount).get(elementPosition).getTag();
        int exist = 0;
        for (int i = 0; i < gridAdapterMyCommand.dataGetter().size(); i++)
            if (itemTag.equals(gridAdapterMyCommand.dataGetter().get(i).getTag())) {
                if (exist == 0) POS = i;
                exist++;
                if (exist == 2)
                    return true;
            }

        return false;
    }

    /**
     * Prepare some dummy data for gridview
     */
    private ArrayList<ImageItem> getData(int counter) {
        final ArrayList<ImageItem> imageItems = new ArrayList<>();
        TypedArray imgs = getResources().obtainTypedArray(R.array.image_ids);
        for (int i = 0; i < imgs.length(); i++) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imgs.getResourceId(i, -1));
            bitmap = ImageConverter.getRoundedCornerBitmap(bitmap, 20.0f);
            imageItems.add(new ImageItem(bitmap, "Image#" + i, counter));
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