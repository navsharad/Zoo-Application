package edu.sjsu.android.zooapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

// Description page that displays when user clicks on an animal in the recycler view
public class DescriptionActivity extends DefaultActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.description_activity);
        TextView nameTV = findViewById(R.id.nameTV);
        TextView descriptionTV = findViewById(R.id.descriptionTV);
        ImageView imageIV = findViewById(R.id.imageIV);

        String title = "Blank Title";
        String description = "Blank Description";
        int image = 0;

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            title = extras.getString("title");
            description = extras.getString("description");
            image = extras.getInt("image");
        }

        nameTV.setText(title);
        descriptionTV.setText(description);
        imageIV.setImageResource(image);
    }
}
