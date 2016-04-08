package fr.upmc.ihm.restmenu;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import java.util.Iterator;
import java.util.List;

public class Games extends AppCompatActivity {

    ImageButton clashOfClansIcon;
    ImageButton limboIcon;
    ImageButton minecraftIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.games);

        clashOfClansIcon = (ImageButton) findViewById(R.id.clashOfClans);
        limboIcon = (ImageButton) findViewById(R.id.limboIcon);
        minecraftIcon = (ImageButton) findViewById(R.id.minecraftIcon);

        // How we did to find games package name
        showMepackagesInfo();
    }

    private void showMepackagesInfo() {

        PackageManager packageManager = this.getPackageManager();
        List<PackageInfo> appList = packageManager.getInstalledPackages(0);
        Iterator<PackageInfo> it = appList.iterator();

        while (it.hasNext()) {
            PackageInfo packageInfo = (PackageInfo) it.next();
            String packageName = packageInfo.applicationInfo.packageName;
            String label = "" + packageInfo.applicationInfo.loadLabel(packageManager);
            //Drawable icon = packageInfo.applicationInfo.loadIcon(packageManager);

            Log.i("Games", "packageName: " + label);
            Log.i("Games", "Label: " + label);
        }
    }

    public void onGameClick(View view) {
        try {
            Intent intent = new Intent((String)clashOfClansIcon.getTag());
            startActivityForResult(intent, 0);

        } catch (Exception e) {
            Uri marketUri = Uri.parse("market://details?id=" + (String)clashOfClansIcon.getTag());
            Intent marketIntent = new Intent(Intent.ACTION_VIEW,marketUri);
            startActivity(marketIntent);
        }
    }
}
