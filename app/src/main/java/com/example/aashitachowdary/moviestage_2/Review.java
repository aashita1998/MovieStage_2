package com.example.aashitachowdary.moviestage_2;

class Review {
    String author;
    String content;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Review(String author, String content) {
        this.author = author;
        this.content = content;
        //this is review pojo
    }
}
