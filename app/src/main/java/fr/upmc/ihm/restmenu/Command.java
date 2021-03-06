package fr.upmc.ihm.restmenu;

import android.annotation.TargetApi;
import android.app.Dialog;
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
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Command extends AppCompatActivity {

    private TextView priceZone;
    private LinearLayout shadaw;
    RelativeLayout.LayoutParams params;

    private GridView gridView;
    private GridView gridView2;
    private GridView gridView3;
    private GridView gridView4;
    private GridView gridView5;

    private GridView gridViewMyCommand;

    private GridViewAdapter gridAdapter;
    private GridViewAdapter gridAdapter2;
    private GridViewAdapter gridAdapter3;
    private GridViewAdapter gridAdapter4;
    private GridViewAdapter gridAdapter5;

    private Button button;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;

    Drawable drawable;
    Drawable drawable2;

    private GridViewMyCommandAdapter gridAdapterMyCommand;
    private ArrayList<ImageItem> data = new ArrayList<ImageItem>();

    AdapterView<?> parentGridView;
    AdapterView<?> parentGridViewMyCommand;

    private int price = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.command);

        priceZone = (TextView) findViewById(R.id.globalPrice);
        shadaw = (LinearLayout) findViewById(R.id.shadaw_hidden);
        params = (RelativeLayout.LayoutParams)shadaw.getLayoutParams();

        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);

        drawable = getResources().getDrawable(R.drawable.command_bottom);
        drawable2 = getResources().getDrawable(R.drawable.command_bottom2);

        gridView = (GridView) findViewById(R.id.gridView);
        gridView2 = (GridView) findViewById(R.id.gridView2);
        gridView3 = (GridView) findViewById(R.id.gridView3);
        gridView4 = (GridView) findViewById(R.id.gridView4);
        gridView5 = (GridView) findViewById(R.id.gridView5);

        gridViewMyCommand = (GridView) findViewById(R.id.gridViewMyCommand);

        gridAdapter = new GridViewAdapter(this, R.layout.grid_item_layout, getData(0, "a"));
        gridAdapter2 = new GridViewAdapter(this, R.layout.grid_item_layout, getData(0, "m"));
        gridAdapter3 = new GridViewAdapter(this, R.layout.grid_item_layout, getData(0, "d"));
        gridAdapter4 = new GridViewAdapter(this, R.layout.grid_item_layout, getData(0, "b"));
        gridAdapter5 = new GridViewAdapter(this, R.layout.grid_item_layout, getData(0, "t"));

        gridView.setAdapter(gridAdapter);
        gridView2.setAdapter(gridAdapter2);
        gridView3.setAdapter(gridAdapter3);
        gridView4.setAdapter(gridAdapter4);
        gridView5.setAdapter(gridAdapter5);

        gridView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onGridClick(parent, view, position, "a");
            }
        });

        gridView2.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onGridClick(parent, view, position, "m");
            }
        });

        gridView3.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onGridClick(parent, view, position, "d");
            }
        });

        gridView4.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onGridClick(parent, view, position, "b");
            }
        });

        gridView5.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onGridClick(parent, view, position, "t");
            }
        });

        gridViewMyCommand.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                parentGridViewMyCommand = parent;

                ImageItem item = (ImageItem) parent.getItemAtPosition(position);

                TextView counterZone = (TextView) parent.getChildAt(position).findViewById(R.id.myCounter);
                TextView thisPriceZone = (TextView) parent.getChildAt(position).findViewById(R.id.price);

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
                    priceZone.setText(String.valueOf(price) + " €");
                } else {
                    Toast.makeText(Command.this, "Else!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void onGridClick(AdapterView<?> parent, View view, int position, String type) {

        parentGridView = parent;

        ImageItem item = (ImageItem) parent.getItemAtPosition(position);

        TextView counterZone = (TextView) parent.getChildAt(position).findViewById(R.id.counter);
        TextView thisPriceZone = (TextView) parent.getChildAt(position).findViewById(R.id.price);

        long viewId = view.getId();

        if (viewId == R.id.infoButton) {
            //Create intent
            Intent intent = new Intent(Command.this, DetailsActivity.class);
            //intent.putExtra("title", item.getTitle());
            //intent.putExtra("image", item.getImage());

            //Start details activity
            startActivity(intent);
        } else if (viewId == R.id.image || viewId == R.id.text) {
            int counter = Integer.parseInt((String) counterZone.getText());
            int thisPrice = Integer.parseInt(((String)thisPriceZone.getText()).split(" ")[0]);
            counter++;
            price += thisPrice;
            counterZone.setText(String.valueOf(counter));
            priceZone.setText(String.valueOf(price) + " €");
            counterZone.setVisibility(View.VISIBLE);

            fillCart(position, counter, type);

            //Toast.makeText(Command.this, "image!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(Command.this, "Else!", Toast.LENGTH_SHORT).show();
        }
    }

    private void fillCart(int elementPosition, int commandCount, String type) {
        ImageItem item = getData(commandCount, type).get(elementPosition);
        data.add(item);
        gridAdapterMyCommand = new GridViewMyCommandAdapter(Command.this, R.layout.grid_mycommand_element, data);
        if (inCart(elementPosition, commandCount, type)) {
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

    private void decrementCounter(String itemTag, int counter) {
        GridViewAdapter myGridAdapter = null;
        if (itemTag.startsWith("a"))
            myGridAdapter = gridAdapter;
        else if (itemTag.startsWith("m"))
            myGridAdapter = gridAdapter2;
        else if (itemTag.startsWith("d"))
            myGridAdapter = gridAdapter3;
        else if (itemTag.startsWith("b"))
            myGridAdapter = gridAdapter4;
        else if (itemTag.startsWith("t"))
            myGridAdapter = gridAdapter5;

        for (int i = 0; i < myGridAdapter.dataGetter().size(); i++) {
            if (itemTag.equals(myGridAdapter.dataGetter().get(i).getTag())) {
                myGridAdapter.dataGetter().get(i).setCounter(counter);
                TextView counterZoneInPosition = (TextView) parentGridView.getChildAt(i).findViewById(R.id.counter);
                TextView thisPriceZone = (TextView) parentGridView.getChildAt(i).findViewById(R.id.price);
                int thisPrice = Integer.parseInt(((String)thisPriceZone.getText()).split(" ")[0]);
                price -= thisPrice;
                counterZoneInPosition.setText(String.valueOf(counter));

                if (counter == 0) {
                    counterZoneInPosition.setVisibility(View.INVISIBLE);
                }

                break;
            }
        }
    }

    private int POS = -1;
    private boolean inCart(int elementPosition, int commandCount, String type) {
        String itemTag = getData(commandCount, type).get(elementPosition).getTag();
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

    private ArrayList<ImageItem> getData(int counter, String type) {
        final ArrayList<ImageItem> imageItemsAppetizers = new ArrayList<>();
        final ArrayList<ImageItem> imageItemsMains = new ArrayList<>();
        final ArrayList<ImageItem> imageItemsDesserts = new ArrayList<>();
        final ArrayList<ImageItem> imageItemsBeverages = new ArrayList<>();
        final ArrayList<ImageItem> imageItemsTeasCoffes = new ArrayList<>();
        final ArrayList<ImageItem> imageItemsAll = new ArrayList<>();

        TypedArray imgs = getResources().obtainTypedArray(R.array.image_ids);

        for (int i = 0; i < imgs.length(); i++) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imgs.getResourceId(i, -1));
            bitmap = ImageConverter.getRoundedCornerBitmap(bitmap, 20.0f);
            String imageName = ((String) imgs.getText(i)).substring(13);

            if (type.equals("a") && imageName.startsWith(type)) {
                imageItemsAppetizers.add(new ImageItem(bitmap, imageName, counter));

            } else if (type.equals("m") && imageName.startsWith(type)) {
                imageItemsMains.add(new ImageItem(bitmap, imageName, counter));

            } else if (type.equals("d") && imageName.startsWith(type)) {
                imageItemsDesserts.add(new ImageItem(bitmap, imageName, counter));

            } else if (type.equals("b") && imageName.startsWith(type)) {
                imageItemsBeverages.add(new ImageItem(bitmap, imageName, counter));

            } else if (type.equals("t") && imageName.startsWith(type)) {
                imageItemsTeasCoffes.add(new ImageItem(bitmap, imageName, counter));

            } else {
                imageItemsAll.add(new ImageItem(bitmap, imageName, counter));
            }
        }
            if (type.equals("a"))
                return imageItemsAppetizers;

            else if (type.equals("m"))
                return imageItemsMains;

            else if (type.equals("d"))
                return imageItemsDesserts;

            else if (type.equals("b"))
                return imageItemsBeverages;

            else if (type.equals("t"))
                return imageItemsTeasCoffes;

            else
                return imageItemsAll;
    }


    public void doCommand(View view) {
        //Intent intent = new Intent(this, History.class);
        //startActivity(intent);
        Log.d("CDA", "onBackPressed Called");
        // custom dialog
        final Dialog dialog = new Dialog(Command.this);
        dialog.setContentView(R.layout.confirm_command_layout);
        // Custom Android Allert Dialog Title
        //dialog.setTitle("Code secret du serveur");

        Button dialogButtonCancel = (Button) dialog.findViewById(R.id.customDialogCancel);
        Button dialogButtonOk = (Button) dialog.findViewById(R.id.customDialogOk);
        // Click cancel to dismiss android custom dialog box
        dialogButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        // Your android custom dialog ok action
        // Action for custom dialog ok button click
        dialogButtonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                //moveTaskToBack(true);
                Intent intent = new Intent(Command.this, Home.class);
                startActivity(intent);
                Command.this.finish();
            }
        });

        dialog.show();
    }

    public void cancelCommand(View view) {
        Command.this.finish();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void showAppetizers(View view) {
        params.setMargins(0, 0, 0, 0);
        shadaw.setLayoutParams(params);

        button.setBackground(drawable);
        button2.setBackground(drawable2);
        button3.setBackground(drawable2);
        button4.setBackground(drawable2);
        button5.setBackground(drawable2);

        gridView.setVisibility(View.VISIBLE);
        gridView2.setVisibility(View.INVISIBLE);
        gridView3.setVisibility(View.INVISIBLE);
        gridView4.setVisibility(View.INVISIBLE);
        gridView5.setVisibility(View.INVISIBLE);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void showMains(View view) {
        params.setMargins(160, 0, 0, 0);
        shadaw.setLayoutParams(params);

        button2.setBackground(drawable);
        button.setBackground(drawable2);
        button3.setBackground(drawable2);
        button4.setBackground(drawable2);
        button5.setBackground(drawable2);

        gridView2.setVisibility(View.VISIBLE);
        gridView.setVisibility(View.INVISIBLE);
        gridView3.setVisibility(View.INVISIBLE);
        gridView4.setVisibility(View.INVISIBLE);
        gridView5.setVisibility(View.INVISIBLE);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void showDesserts(View view) {
        params.setMargins(320, 0, 0, 0);
        shadaw.setLayoutParams(params);

        button3.setBackground(drawable);
        button.setBackground(drawable2);
        button2.setBackground(drawable2);
        button4.setBackground(drawable2);
        button5.setBackground(drawable2);

        gridView3.setVisibility(View.VISIBLE);
        gridView.setVisibility(View.INVISIBLE);
        gridView2.setVisibility(View.INVISIBLE);
        gridView4.setVisibility(View.INVISIBLE);
        gridView5.setVisibility(View.INVISIBLE);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void showBeverages(View view) {
        params.setMargins(480, 0, 0, 0);
        shadaw.setLayoutParams(params);

        button4.setBackground(drawable);
        button.setBackground(drawable2);
        button2.setBackground(drawable2);
        button3.setBackground(drawable2);
        button5.setBackground(drawable2);

        gridView4.setVisibility(View.VISIBLE);
        gridView.setVisibility(View.INVISIBLE);
        gridView2.setVisibility(View.INVISIBLE);
        gridView3.setVisibility(View.INVISIBLE);
        gridView5.setVisibility(View.INVISIBLE);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void showTeasCoffes(View view) {
        params.setMargins(640, 0, 0, 0);
        shadaw.setLayoutParams(params);

        button5.setBackground(drawable);
        button.setBackground(drawable2);
        button2.setBackground(drawable2);
        button3.setBackground(drawable2);
        button4.setBackground(drawable2);

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