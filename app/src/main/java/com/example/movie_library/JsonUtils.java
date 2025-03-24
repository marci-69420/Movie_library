package com.example.movie_library;
import static android.content.ContentValues.TAG;

import android.content.Context;
import java.util.List;
import android.util.Log;
import java.util.ArrayList;

import android.content.res.Resources;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.IOException;

/**
 * This class handles the JSON file that contains the movies data
 *
 */

public class JsonUtils {

    /**
     * This method reads the JSON file and fills a list with Movie objects with error handling
     *
     * @return List of Movie objects
     */
    public static List<Movie> loadMoviesFromJson(Context context) {
        String json = null;
        //Read the JSON file
        try {
            json = readJson(context);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, "Error reading JSON file", Toast.LENGTH_LONG).show();
        }
        if (json == null) {
            return null;
        }

        List<Movie> movies = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            //Iterate over the JSON array and fill the list with Movie objects
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String title = jsonObject.optString("title", "Unknown Title");
                int year = jsonObject.optInt("year", 0);
                String genre = jsonObject.optString("genre", "Unknown Genre");
                String posterResource = jsonObject.optString("poster", null);

                Movie movie = new Movie(title, year, genre, posterResource);
                movies.add(movie);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(context, "Error filling the json file", Toast.LENGTH_LONG).show();
        }
        return movies;
    }

    /**
     * This method reads the JSON file with error handling
     * @return String with the JSON file content
     */
    private static String readJson(Context context) throws IOException {
        Resources recources = context.getResources();
        //Open the resource file form raw directory
        InputStream inputStream = recources.openRawResource(R.raw.movies); //Create a byte array (buffer) to hold the json file

        try{

            //Read the data from the file
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            inputStream.close();
            //Convert the buffer into string
            return new String(buffer, "UTF-8");
        } catch (IOException e) {
            //Reading error handling
            Log.e(TAG, "Error reading JSON file", e);
            Toast.makeText(context, "Error reading JSON file", Toast.LENGTH_LONG).show();
            return null;
        }


    }


}
