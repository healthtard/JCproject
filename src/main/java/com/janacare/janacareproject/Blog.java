package com.janacare.janacareproject;

/**
 * Created by pradeep on 18/1/16.
 */
public class Blog {
    String title = "";
    String description = "";
    int image_url = 0;
    String link = "";

    public Blog(String title, String description, int image_url, String link) {
        this.description = description;
        this.title = title;
        this.image_url = image_url;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImage_url() {
        return image_url;
    }

    public void setImage_url(int image_url) {
        this.image_url = image_url;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
