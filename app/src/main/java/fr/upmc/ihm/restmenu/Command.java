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
import android.os.AsyncTask;
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
    private GridView gridView2;
    private GridView gridView3;
    private GridView gridView4;
    private GridView gridView5;

    private GridView gridViewMyCommand;
    private GridViewAdapter gridAdapter;
    private GridViewMyCommandAdapter gridAdapterMyCommand;
    private ArrayList<ImageItem> data = new ArrayList<ImageItem>();

    AdapterView<?> parentGridView;
    AdapterView<?> parentGridViewMyCommand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.command);

        gridView = (GridView) findViewById(R.id.gridView);
        gridView2 = (GridView) findViewById(R.id.gridView2);
        gridView3 = (GridView) findViewById(R.id.gridView3);
        gridView4 = (GridView) findViewById(R.id.gridView4);
        gridView5 = (GridView) findViewById(R.id.gridView5);

        gridViewMyCommand = (GridView) findViewById(R.id.gridViewMyCommand);

        gridAdapter = new GridViewAdapter(this, R.layout.grid_item_layout, getData(0));
        //new RetrieveData().execute();

        gridView.setAdapter(gridAdapter);
        gridView2.setAdapter(gridAdapter);
        gridView3.setAdapter(gridAdapter);
        gridView4.setAdapter(gridAdapter);
        gridView5.setAdapter(gridAdapter);

        gridView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                parentGridView = parent;

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

        gridViewMyCommand.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                parentGridViewMyCommand = parent;

                ImageItem item = (ImageItem) parent.getItemAtPosition(position);

                TextView counterZone = (TextView) parent.getChildAt(position).findViewById(R.id.myCounter);

                long viewId = view.getId();

                if (viewId == R.id.delateButton) {
                    int counter = Integer.parseInt((String) counterZone.getText());
                    String itemtag = gridAdapterMyCommand.dataGetter().get(position).getTag();
                    if (counter > 1) {
                        counter--;
                        counterZone.setText(String.valueOf(counter));

                        decrementCounter(itemtag, counter);

                    } else {
                        counter = 0;
                        gridAdapterMyCommand.dataGetter().remove(position);
                        gridViewMyCommand.setAdapter(gridAdapterMyCommand);
                        decrementCounter(itemtag, counter);
                    }
                } else {
                    Toast.makeText(Command.this, "Else!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    class RetrieveData extends AsyncTask<String,String,ArrayList<ImageItem>> {

        @Override
        protected ArrayList<ImageItem> doInBackground(String... arg0) {
            return getData(0);
        }

        protected void onPostExecute(ArrayList<ImageItem> result) {
            gridAdapter = new GridViewAdapter(Command.this, R.layout.grid_item_layout, result);
        }
    }

    private void fillCart(int elementPosition, int commandCount) {
        ImageItem item = getData(commandCount).get(elementPosition);
        data.add(item);
        gridAdapterMyCommand = new GridViewMyCommandAdapter(Command.this, R.layout.grid_mycommand_element, data);
        if (inCart(elementPosition, commandCount)) {
            gridAdapterMyCommand.dataGetter().remove(POS);
        }
        gridViewMyCommand.setAdapter(gridAdapterMyCommand);
    }

    private String print() {
        String res = new String();
        for (int i = 0; i < gridAdapterMyCommand.dataGetter().size(); i++)
            res += " " +gridAdapterMyCommand.dataGetter().get(i).getTag();
        return res;
    }

    private void decrementCounter(String itemtag, int counter) {
        for (int i = 0; i < gridAdapter.dataGetter().size(); i++) {
            if (itemtag.equals(gridAdapter.dataGetter().get(i).getTag())) {
                gridAdapter.dataGetter().get(i).setCounter(counter);
                TextView counterZoneInPosition = (TextView) parentGridView.getChildAt(i).findViewById(R.id.counter);
                counterZoneInPosition.setText(String.valueOf(counter));

                if (counter == 0) {
                    counterZoneInPosition.setVisibility(View.INVISIBLE);
                }

                break;
            }
        }
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

    public void showAppetizers(View view) {
        gridView.setVisibility(View.VISIBLE);

        gridView2.setVisibility(View.INVISIBLE);
        gridView3.setVisibility(View.INVISIBLE);
        gridView4.setVisibility(View.INVISIBLE);
        gridView5.setVisibility(View.INVISIBLE);
    }

    public void showMains(View view) {
        gridView2.setVisibility(View.VISIBLE);

        gridView.setVisibility(View.INVISIBLE);
        gridView3.setVisibility(View.INVISIBLE);
        gridView4.setVisibility(View.INVISIBLE);
        gridView5.setVisibility(View.INVISIBLE);
    }

    public void showDesserts(View view) {
        gridView3.setVisibility(View.VISIBLE);

        gridView.setVisibility(View.INVISIBLE);
        gridView2.setVisibility(View.INVISIBLE);
        gridView4.setVisibility(View.INVISIBLE);
        gridView5.setVisibility(View.INVISIBLE);
    }

    public void showBeverages(View view) {
        gridView4.setVisibility(View.VISIBLE);

        gridView.setVisibility(View.INVISIBLE);
        gridView2.setVisibility(View.INVISIBLE);
        gridView3.setVisibility(View.INVISIBLE);
        gridView5.setVisibility(View.INVISIBLE);
    }

    public void showTeasCoffes(View view) {
        gridView5.setVisibility(View.VISIBLE);

        gridView.setVisibility(View.INVISIBLE);
        gridView2.setVisibility(View.INVISIBLE);
        gridView3.setVisibility(View.INVISIBLE);
        gridView4.setVisibility(View.INVISIBLE);
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