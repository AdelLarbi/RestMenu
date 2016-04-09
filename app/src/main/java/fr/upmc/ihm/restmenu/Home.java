package fr.upmc.ihm.restmenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;


public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
    }

    public void selectHistory(View view) {
        Intent intent = new Intent(this, History.class);
        startActivity(intent);
    }

    public void selectGallery(View view) {
        Intent intent = new Intent(this, Gallery.class);
        startActivity(intent);
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
