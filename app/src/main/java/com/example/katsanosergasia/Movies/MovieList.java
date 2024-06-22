package com.example.katsanosergasia.Movies;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.katsanosergasia.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class MovieList extends RecyclerView.Adapter<MovieList.ViewHolder> {



    private final String[] titles = {"lock stock", "snatch", "sin city", "fight club",
            "se7en", "carlito's way", "Donnie Brasco", "Heat"};
    private final String[] ratings = {"9", "9", "3",
            "2", "5", "7", "9",
            "9"};
    private final String[] durations = {"2", "2:25", "2",
            "2", "2:10", "2:40", "2:40",
            "1:30"};
    private final String[] dates = {"2002", "2003", "2004",
            "2005", "2006", "2007", "2008",
            "2009"};
    private final int[] images = { R.drawable.lockstock, R.drawable.snatch,
            R.drawable.sincity, R.drawable.fightclub, R.drawable.se7en,
            R.drawable.carlito, R.drawable.donnie, R.drawable.heat };

    private Context context;
    private List<Integer> imageList;

    public MovieList(Context context, List<Integer> imageList) {
        this.context = context;
        this.imageList = imageList;
    }


    //Class that holds the items to be displayed (Views in card_layout)
    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImage;
        TextView itemTitle;
        TextView itemRating;
        TextView itemDuration;
        TextView itemDate;

        public ViewHolder(View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.item_image);
            itemTitle = itemView.findViewById(R.id.item_title);
            itemRating = itemView.findViewById(R.id.item_rating);
            itemDate = itemView.findViewById(R.id.item_episodes);

            itemDuration = itemView.findViewById(R.id.item_date);




            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Intent intent = new Intent(context, ViewsMActivity.class);
                        intent.putExtra("imageResId", imageList.get(position));
                        context.startActivity(intent);
                    }
                }
            });
        }
    }



    //Methods that must be implemented for a RecyclerView.Adapter
    @NonNull
    @Override
    public MovieList.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_movie_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MovieList.ViewHolder holder, int position) {
        holder.itemTitle.setText(titles[position]);
        holder.itemRating.setText(ratings[position]);
        holder.itemImage.setImageResource(images[position]);
        holder.itemDuration.setText(durations[position]);
        holder.itemDate.setText(dates[position]);
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }
}
