package com.example.katsanosergasia.FireBaseDataBase;

import java.io.Serializable;

public class Content implements Serializable {
    private String title;
    private String stars;
    private String date;
    private String runtime; // Ορισμός σε "N/A" για σειρές
    private int seasons;    // Ορισμός σε 0 για ταινίες
    private int episodes;   // Ορισμός σε 0 για ταινίες
    private String review;
    private String imageUrl;
    private boolean isMovie; // True αν είναι ταινία, false αν είναι σειρά

    public Content() {
        // Default constructor που απαιτείται για κλήσεις σε DataSnapshot.getValue(Content.class)
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
