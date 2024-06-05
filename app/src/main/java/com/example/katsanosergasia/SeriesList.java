package com.example.katsanosergasia;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.snackbar.Snackbar;

public class SeriesList extends RecyclerView.Adapter<SeriesList.ViewHolder> {
    private final String[] titles = {"seinfeld", "better call saul", "breaking bad", "under the dome",
            "fargo", "game of thrones", "it crowd", "ozark"};
    private final String[] ratings = {"9", "9", "3",
            "2", "5", "7", "9",
            "9"};
    private final String[] episodes = {"10", "20", "30",
            "40", "50", "60", "70",
            "80"};
    private final String[] dates = {"2002", "2003", "2004",
            "2005", "2006", "2007", "2008",
            "2009"};
    private final int[] images = { R.drawable.seinfeld, R.drawable.bcs,
            R.drawable.breakingbad, R.drawable.dome, R.drawable.fargo,
            R.drawable.got, R.drawable.itcrowd, R.drawable.ozark };

    //Class that holds the items to be displayed (Views in card_layout)
    static class ViewHolder extends RecyclerView.ViewHolder {
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

                    Snackbar.make(v, "Click detected on item " + position,
                            Snackbar.LENGTH_LONG).show();
                }
            });
        }
    }

    //Methods that must be implemented for a RecyclerView.Adapter
    @NonNull
    @Override
    public SeriesList.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_movie_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SeriesList.ViewHolder holder, int position) {
        holder.itemTitle.setText(titles[position]);
        holder.itemRating.setText(ratings[position]);
        holder.itemImage.setImageResource(images[position]);
        holder.itemDuration.setText(episodes[position]);
        holder.itemDate.setText(dates[position]);
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }
}
