package com.company;

public class VkParserModel {
    private String idOwner;
    private String date;
    private String idPost;
    private String postText;

    public void setIdOwner(String idOwner) {
        this.idOwner = idOwner;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setIdPost(String idPost) {
        this.idPost = idPost;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public String getIdOwner() {
        return idOwner;
    }

    public String getDate() {
        return date;
    }

    public String getIdPost() {
        return idPost;
    }

    public String getPostText() {
        return postText;
    }

    @Override
    public String toString() {
        return "VkParserModel{" +
                "idOwner='" + idOwner + '\'' +
                ", date='" + date + '\'' +
                ", idPost='" + idPost + '\'' +
                ", idPostText='" + postText + '\'' +
                '}';
    }
}
