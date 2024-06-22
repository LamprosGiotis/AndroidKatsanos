package com.example.katsanosergasia.FireBaseDataBase;

import java.io.Serializable;

public class Content implements Serializable {
    private String title;
    private String stars;
    private String date;
    private String runtime; // Set to "N/A" for series
    private int seasons;    // Set to 0 for movies
    private int episodes;   // Set to 0 for movies
    private String review;
    private String imageUrl;
    private boolean isMovie; // True if it's a movie, false if it's a series

    public Content() {
        // Default constructor required for calls to DataSnapshot.getValue(Content.class)
    }

    public Content(String title, String stars, String date, String runtime, int seasons, int episodes, String review, String imageUrl, boolean isMovie) {
        this.title = title;
        this.stars = stars;
        this.date = date;
        this.runtime = runtime;
        this.seasons = seasons;
        this.episodes = episodes;
        this.review = review;
        this.imageUrl = imageUrl;
        this.isMovie = isMovie;
    }

    public String getTitle() {
        return title;
    }

    public String getStars() {
        return stars;
    }

    public String getDate() {
        return date;
    }

    public String getRuntime() {
        return runtime;
    }

    public int getSeasons() {
        return seasons;
    }

    public int getEpisodes() {
        return episodes;
    }

    public String getReview() {
        return review;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public boolean isMovie() {
        return isMovie;
    }

    public void setMovie(boolean movie) {
        isMovie = movie;
    }
}
