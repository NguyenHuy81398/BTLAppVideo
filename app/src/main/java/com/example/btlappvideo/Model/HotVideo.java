package com.example.btlappvideo.Model;

import java.io.Serializable;

public class HotVideo implements Serializable {

    String id;
    String title;
    String avatar;
    public String file_mp4;
    String date_published;

    public HotVideo(String id, String title, String avatar, String file_mp4, String date_published) {
        this.id = id;
        this.title = title;
        this.avatar = avatar;
        this.file_mp4 = file_mp4;
        this.date_published = date_published;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getFile_mp4() {
        return file_mp4;
    }

    public void setFile_mp4(String file_mp4) {
        this.file_mp4 = file_mp4;
    }

    public String getDate_published() {
        return date_published;
    }

    public void setDate_published(String date_published) {
        this.date_published = date_published;
    }
}
