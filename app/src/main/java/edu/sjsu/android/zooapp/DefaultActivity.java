package edu.sjsu.android.zooapp;

import android.content.Intent;
import android.net.Uri;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

// Used to add overflow menu giving users the option to uninstall or go to the information page
public class DefaultActivity extends AppCompatActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.information) {
            Intent intent = new Intent(getApplicationContext(), InformationActivity.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.uninstall) {
            Intent i = new Intent(Intent.ACTION_DELETE);
            i.setData(Uri.parse("package:edu.sjsu.android.zooapp"));
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}
