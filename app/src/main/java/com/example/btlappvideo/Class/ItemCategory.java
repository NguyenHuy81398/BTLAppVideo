package com.example.btlappvideo.Class;

import java.io.Serializable;

public class ItemCategory implements Serializable {
    String id;
    String provider_id;
    String category_id;
    String title;
    String avatar;
    public String file_mp4;
    String date_created;

    public ItemCategory(String id, String provider_id, String category_id, String title, String avatar, String file_mp4, String date_created) {
        this.id = id;
        this.provider_id = provider_id;
        this.category_id = category_id;
        this.title = title;
        this.avatar = avatar;
        this.file_mp4 = file_mp4;
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

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }
}
