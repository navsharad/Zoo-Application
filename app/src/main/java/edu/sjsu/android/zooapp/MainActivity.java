package edu.sjsu.android.zooapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends DefaultActivity {

    private ArrayList<Animal> animals;
    private RecyclerView recyclerView;
    private Adapter.RecyclerViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        animals = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);

        setAnimalInfo();
        setAdapter();
    }

    private void setAnimalInfo() {
        animals.add(new Animal("Lion", "A lion is a large cat with a muscular body and a short round head. Male lions are larger than females and have a prominent mane.", R.drawable.lion));
        animals.add(new Animal("Tiger", "A tiger is the largest living cat species. It has dark stripes around its orange fur. Tigers mainly prey on ungulates such as deer and wild boar.", R.drawable.tiger));
        animals.add(new Animal("Zebra", "Zebras are equines with black and white striped fur. Zebras are herbivores and feed mostly on grass.", R.drawable.zebra));
        animals.add(new Animal("Horse", "A horse belongs to the taxonomic family Equidae. Horses are herbivores and eat only plant material.", R.drawable.horse));
        animals.add(new Animal("Platypus", "Platypuses are among the few venomous mammals. Males have a spur on the back of their hind feet that is connected to a venom secreting gland.", R.drawable.platypus));

    }

    private void setAdapter() {
        setOnClickListener();
        Adapter adapter = new Adapter(animals, listener);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setOnClickListener() {
        listener = new Adapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {

                // warn user that the last animal is very scary
                if (position == animals.size() - 1) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                    alert.setTitle("WARNING");
                    alert.setMessage("The following animal is very scary. Do you wish to proceed?");
                    alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(MainActivity.this, "Proceed with caution", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), DescriptionActivity.class);
                            intent.putExtra("title", animals.get(position).getTitle());
                            intent.putExtra("description", animals.get(position).getDescription());
                            intent.putExtra("image", animals.get(position).getImage());
                            startActivity(intent);
                        }
                    });
                    alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(MainActivity.this, "Good Choice", Toast.LENGTH_SHORT).show();

                        }
                    });
                    alert.show();


                } else {
                    Intent intent = new Intent(getApplicationContext(), DescriptionActivity.class);
                    intent.putExtra("title", animals.get(position).getTitle());
                    intent.putExtra("description", animals.get(position).getDescription());
                    intent.putExtra("image", animals.get(position).getImage());
                    startActivity(intent);
                }
            }
        };
    }
}