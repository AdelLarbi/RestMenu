package fr.upmc.ihm.restmenu;

import android.graphics.Bitmap;

public class ImageItem {
    private Bitmap image;
    private String title;
    private String tag;
    private int counter;

    public ImageItem(Bitmap image, String title, int counter) {
        super();
        this.image = image;
        this.title = title;
        this.counter = counter;
        this.tag = title;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCounter() {
        return String.valueOf(counter);
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public String getTag() {
        return tag;
    }
}
