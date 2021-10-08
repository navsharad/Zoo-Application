package edu.sjsu.android.zooapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    private ArrayList<Animal> animals;
    private RecyclerViewClickListener listener;

    public Adapter(ArrayList<Animal> animals, RecyclerViewClickListener listener) {
        this.animals = animals;
        this.listener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView title;
        private ImageView image;

        public MyViewHolder(final View view) {
            super(view);
            title = view.findViewById(R.id.title);
            image = view.findViewById(R.id.image);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view, getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        return new MyViewHolder(itemView);
    }

    // Change info based on animal
    @Override
    public void onBindViewHolder(@NonNull Adapter.MyViewHolder holder, int position) {
        String title = animals.get(position).getTitle();
        int image = animals.get(position).getImage();
        holder.title.setText(title);
        holder.image.setImageResource(image);
    }

    @Override
    public int getItemCount() {
        return animals.size();
    }

    public interface RecyclerViewClickListener {
        void onClick(View v, int position);
    }


}