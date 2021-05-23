package ru.novitskiy.media_player;

import java.io.Serializable;

public class SongInfo implements Serializable
{
    private int id;
    private String title;
    private String artist;
    private String path;

    public SongInfo(){
        id = -1;
    }

    public SongInfo(int id, String title, String artist, String path)
    {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.path = path;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getArtist(){
        return artist;
    }

    public void setArtist(String artist){
        this.artist = artist;
    }

    public String getPath(){
        return path;
    }

    public void setPath(String path){
        this.path = path;
    }
}
