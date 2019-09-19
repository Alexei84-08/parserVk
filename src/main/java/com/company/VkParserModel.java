package com.company;

public class VkParserModel {
    private String idOwner;
    private String date;
    private String idPost;
    private String postText;

    void setIdOwner(String idOwner) {
        this.idOwner = idOwner;
    }

    void setDate(String date) {
        this.date = date;
    }

    void setIdPost(String idPost) {
        this.idPost = idPost;
    }

    void setPostText(String postText) {
        this.postText = postText;
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
