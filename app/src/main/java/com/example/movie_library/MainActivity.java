package com.example.movie_library;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView movieRecyclerView;
    private MovieAdapter adapter;
    private List<Movie> movies;
    private static final String TAG = "MainActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieRecyclerView = findViewById(R.id.movieRecyclerView);
        setupRecyclerView();
        loadMovieData();

        //Print the movies data to the log for debugging
        List<Movie> movies = JsonUtils.loadMoviesFromJson(this);
        if (movies != null) {
            for (Movie movie : movies) {
                if (movie != null) {
                    Log.d(TAG, "Movie: " + movie.getTitle() + ", Year: " + movie.getYear() + ", Genre: " + movie.getGenre() + ", Poster: " + movie.getPosterResource());
                } else {
                    Log.w(TAG, "Encountered a null movie object");
                }
            }
        } else {
            Log.w(TAG, "Movies list is null");
        }


    }

    //Set up the recycler view
    public void setupRecyclerView() {
        movieRecyclerView.setLayoutManager(new LinearLayoutManager(this)); //Make the recycler view to display the items in a vertically
        adapter = new MovieAdapter(movies); //Create a new adapter
        movieRecyclerView.setAdapter(adapter); //Set the adapter to the recycler view
    }

    //Load the movie data from the JSON file to thw movies list and update the adapter
    public void loadMovieData() {
        try {
            movies = JsonUtils.loadMoviesFromJson(this);
            if (movies != null) {
                adapter.updateMovies(movies);
            } else {
                showErrorMessage("Failed to load the data");
            }
        } catch (Exception e) {
            Log.e(TAG, "Failed to load the data", e);
        }
    }

    //Show an error message
    public void showErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}