package fr.upmc.ihm.restmenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
    }

    public void goFacebook(View view) {
        Toast toast = Toast.makeText(getApplicationContext(), "Facebook !", Toast.LENGTH_SHORT);
        toast.show();
    }

    public void goTwitter(View view) {
        Toast toast = Toast.makeText(getApplicationContext(), "Twitter !", Toast.LENGTH_SHORT);
        toast.show();
    }

    public void goInstagram(View view) {
        Toast toast = Toast.makeText(getApplicationContext(), "Instagram !", Toast.LENGTH_SHORT);
        toast.show();
    }
}
