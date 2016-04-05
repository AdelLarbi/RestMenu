package fr.upmc.ihm.restmenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by adel on 04/04/16.
 */
public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
    }

    public void selectTable(View view) {
        Intent intent = new Intent(this, TableSelector.class);
        startActivity(intent);
    }

    public void setCode(View view) {
        Intent intent = new Intent(this, SecretCode.class);
        startActivity(intent);
    }
}
