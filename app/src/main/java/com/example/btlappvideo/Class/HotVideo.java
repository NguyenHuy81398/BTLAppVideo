package com.example.btlappvideo.Class;

public class HotVideo {

    String id;
    String provider_id;
    String category_id;
    String title;
    String avatar;
    String file_mp4;
    int file_mp4_size;
    String date_created;
    String date_modified;
    String date_published;
    String youtube_url;

    public HotVideo(String id, String provider_id, String category_id, String title, String avatar, String file_mp4, int file_mp4_size, String date_created, String date_modified, String date_published, String youtube_url) {
        this.id = id;
        this.provider_id = provider_id;
        this.category_id = category_id;
        this.title = title;
        this.avatar = avatar;
        this.file_mp4 = file_mp4;
        this.file_mp4_size = file_mp4_size;
        this.date_created = date_created;
        this.date_modified = date_modified;
        this.date_published = date_published;
        this.youtube_url = youtube_url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProvider_id() {
        return provider_id;
    }

    public void setProvider_id(String provider_id) {
        this.provider_id = provider_id;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
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

    public int getFile_mp4_size() {
        return file_mp4_size;
    }

    public void setFile_mp4_size(int file_mp4_size) {
        this.file_mp4_size = file_mp4_size;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public String getDate_modified() {
        return date_modified;
    }

    public void setDate_modified(String date_modified) {
        this.date_modified = date_modified;
    }

    public String getDate_published() {
        return date_published;
    }

    public void setDate_published(String date_published) {
        this.date_published = date_published;
    }

    public String getYoutube_url() {
        return youtube_url;
    }

    public void setYoutube_url(String youtube_url) {
        this.youtube_url = youtube_url;
    }
}
