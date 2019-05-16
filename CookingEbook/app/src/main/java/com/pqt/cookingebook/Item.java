package com.pqt.cookingebook;

public class Item {
    public Item(String image, String text, String highResUrl, String url) {
        this.image = image;
        this.text = text;
        this.highResUrl = highResUrl;
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getHighResUrl() {
        return highResUrl;
    }

    public void setHighResUrl(String highResUrl) {
        this.highResUrl = highResUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String image;
    private String text;
    private String highResUrl;
    private String url;
}
