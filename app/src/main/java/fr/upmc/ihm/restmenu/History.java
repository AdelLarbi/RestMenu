package fr.upmc.ihm.restmenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class History extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);
    }
    //FIXME 1: replace History.class with an appropriate class
    public void selectOurHistory(View view) {
        Intent intent = new Intent(this, History.class);
        startActivity(intent);
    }
    //FIXME goto FIXME 1
    public void selectOurTeam(View view) {
        Intent intent = new Intent(this, History.class);
        startActivity(intent);
    }
    //FIXME goto FIXME 1
    public void selectCookBook(View view) {
        Intent intent = new Intent(this, History.class);
        startActivity(intent);
    }
    //FIXME goto FIXME 1
    public void selectOurPassions(View view) {
        Intent intent = new Intent(this, History.class);
        startActivity(intent);
    }
    //FIXME goto FIXME 1
    public void selectInternational(View view) {
        Intent intent = new Intent(this, History.class);
        startActivity(intent);
    }
    //FIXME goto FIXME 1
    public void selectPartnerships(View view) {
        Intent intent = new Intent(this, History.class);
        startActivity(intent);
    }
}
