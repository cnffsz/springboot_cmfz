package com.baizhi.entity;

import java.io.Serializable;

public class Chapter implements Serializable {
    private String id;
    private String title;
    private String musicSize;
    private String duration;
    private String downPath;
    private String uploadDate;
    private String albumId;

    @Override
    public String toString() {
        return "Chapter{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", musicSize='" + musicSize + '\'' +
                ", duration='" + duration + '\'' +
                ", downPath='" + downPath + '\'' +
                ", uploadDate='" + uploadDate + '\'' +
                ", albumId='" + albumId + '\'' +
                '}';
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

    public String getMusicSize() {
        return musicSize;
    }

    public void setMusicSize(String musicSize) {
        this.musicSize = musicSize;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDownPath() {
        return downPath;
    }

    public void setDownPath(String downPath) {
        this.downPath = downPath;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public Chapter() {

    }

    public Chapter(String id, String title, String musicSize, String duration, String downPath, String uploadDate, String albumId) {

        this.id = id;
        this.title = title;
        this.musicSize = musicSize;
        this.duration = duration;
        this.downPath = downPath;
        this.uploadDate = uploadDate;
        this.albumId = albumId;
    }
}
