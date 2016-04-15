package fr.upmc.ihm.restmenu;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;


public class Home extends AppCompatActivity {

    private  TextView welcome;
    private  TextView histoire;
    private  TextView langage;
    private  TextView jouer;
    private  TextView horaires;
    private  TextView commander;
    private  TextView contacter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        welcome = (TextView) findViewById(R.id.txtvWelcome);
        histoire = (TextView) findViewById(R.id.txtv1);
        langage = (TextView) findViewById(R.id.txtv2);
        jouer = (TextView) findViewById(R.id.txtv3);
        horaires = (TextView) findViewById(R.id.txtv4);
        commander = (TextView) findViewById(R.id.txtv5);
        contacter = (TextView) findViewById(R.id.txtv6);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (Integer.parseInt(android.os.Build.VERSION.SDK) > 5
                && keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            Log.d("CDA", "onKeyDown Called");
            onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");
        // custom dialog
        final Dialog dialog = new Dialog(Home.this);
        dialog.setContentView(R.layout.custom_dialog);
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
                Intent intent = new Intent(Home.this, TableSelector.class);
                startActivity(intent);
            }
        });

        dialog.show();
    }

    public void selectHistory(View view) {
        Intent intent = new Intent(this, History.class);
        startActivity(intent);
    }

    public void selectGallery(View view) {
        if (langage.getText().equals("Language")) {

            welcome.setText("Welcome");
            histoire.setText("History");
            langage.setText("لغة");
            jouer.setText("Play");
            horaires.setText("Hours");
            commander.setText("Command");
            contacter.setText("Contact");

        } else if (langage.getText().equals("لغة")) {

            welcome.setText("مرحبا");
            histoire.setText("من نحن");
            langage.setText("Langage");
            jouer.setText("لعب");
            horaires.setText("ساعات العمل");
            commander.setText("أطلب");
            contacter.setText("إتصل بنا");

        } else if (langage.getText().equals("Langage")) {

            welcome.setText("Bienvenue");
            histoire.setText("Histoire");
            langage.setText("Language");
            jouer.setText("Jouer");
            horaires.setText("Horaires");
            commander.setText("Commander");
            contacter.setText("Contacter");
        }
        /*Locale locale = new Locale("ar");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        this.getApplicationContext().getResources().updateConfiguration(config, null);*/
    }

    public void selectGames(View view) {
        Intent intent = new Intent(this, Games.class);
        startActivity(intent);

        /*final PackageManager pm = getPackageManager();
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        for (ApplicationInfo packageInfo: packages) {
            Log.i("Home", "Installed Package: " + packageInfo.packageName);
            Log.i("Home", "Source Dir: " + packageInfo.sourceDir);
            Log.i("Home", "Launch Activity: " + pm.getLaunchIntentForPackage(packageInfo.packageName));
        }*/
    }

    public void selectHours(View view) {
        Intent intent = new Intent(this, Hours.class);
        startActivity(intent);
    }

    public void selectCommand(View view) {
        Intent intent = new Intent(this, Command.class);
        startActivity(intent);
    }

    public void selectContact(View view) {
        Intent intent = new Intent(this, Contact.class);
        startActivity(intent);
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
