package com.janacare.janacareproject;

/**
 * Created by pradeep on 18/1/16.
 */
public class Blog {
    String title = "";
    String description = "";
    String image_url = "";
    String link = "";

    public Blog(String description, String title, String image_url, String link) {
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

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
