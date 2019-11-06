package com.example.btlappvideo.Model;

public class Category {
    String id;
    String title;
    String thumb;

    public Category(String id, String title, String thumb) {
        this.id = id;
        this.title = title;
        this.thumb = thumb;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }
}
