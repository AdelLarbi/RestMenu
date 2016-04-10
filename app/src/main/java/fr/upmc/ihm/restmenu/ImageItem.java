package fr.upmc.ihm.restmenu;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.TextView;

public class ImageItem {
    private Bitmap image;
    private String title;

    public ImageItem(Bitmap image, String title, int counter) {
        super();
        this.image = image;
        this.title = title;
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
}
