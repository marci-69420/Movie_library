package com.example.movie_library;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieViewHolder extends RecyclerView.ViewHolder {
    //Attributes
    private ImageView posterImageView;
    private TextView titleTextView;
    private TextView yearTextView;
    private TextView genreTextView;

    //Constructor
    public MovieViewHolder(View itemView) {
        super(itemView);
        //Find the views by their id
        posterImageView = itemView.findViewById(R.id.posterImageView);
        titleTextView = itemView.findViewById(R.id.titleTextView);
        yearTextView = itemView.findViewById(R.id.yearTextView);
        genreTextView = itemView.findViewById(R.id.genreTextView);

    }

    //Fill the UI with the data from the Movie object
    public void bind(@NonNull Movie movie) {
        //Display the the UI with the attributes of the movie object, if the data is missing it will display "Unknown"
        titleTextView.setText(movie.getTitle() != null ? movie.getTitle() : "Unknown Title");
        yearTextView.setText(movie.getYear() != 0 ? String.valueOf(movie.getYear()) : "Unknown Year");
        genreTextView.setText(movie.getGenre() != null ? movie.getGenre() : "Unknown Genre");

        // Set the image resource
        if (movie.getPosterResource() != null) {
            int resId = itemView.getContext().getResources().getIdentifier(
                    movie.getPosterResource(), "drawable", itemView.getContext().getPackageName());
            if (resId != 0) {
                posterImageView.setImageResource(resId);
            } else {
                // Handle the case where the resource is not found
                posterImageView.setImageResource(R.drawable.placeholder_image);
            }
        } else {
            posterImageView.setImageResource(R.drawable.placeholder_image);
        }
    }
}
