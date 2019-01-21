package com.example.aashitachowdary.moviestage_2;

public class Film {
    String title;
    String poster;
    String release_date;
    String overview;
    String vote;
    String id;
    public Film(String title, String poster, String release_date, String overview, String vote, String id) {
        this.title = title;
        this.poster = poster;
        this.release_date = release_date;
        this.overview = overview;
        this.vote = vote;
        this.id=id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }
}
