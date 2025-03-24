package com.example.movie_library;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {
    // Attributes
    private List<Movie> movies;

    // Constructor
    public MovieAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    /**
     * This method creates a new view holder
     */
    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //convert the XML layout to a view object
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    /**
     * Bind the view holder to the data
     */
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        //Fetch the movie object from the current row of list and bind it to the view holder
        holder.bind(movies.get(position));
    }

    @Override
    /**
     * Return the total number of movies in the list
     */
    public int getItemCount() {
        if (movies != null) {
            return movies.size();
        } else {
            return 0;
        }
    }

    /**
     * Update the list of movies with new data
     */
    public void updateMovies(List<Movie> newMovies) {
        this.movies = newMovies;
        //Tell the recycler view that the data has changed
        notifyDataSetChanged();
    }
}

