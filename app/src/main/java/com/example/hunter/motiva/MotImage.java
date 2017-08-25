package com.example.hunter.motiva;

/**
 * Created by hunter on 25/8/17.
 */

public class MotImage {
    public long id;
    public String img_url;
    public MotImage(){

    }

    public void setId(int id)
    {
        this.id=id;
    }

    public void setImg_url(String img_url)
    {
        this.img_url=img_url;
    }

    public long getId()
    {
        return id;

    }
    public String getImg_url()
    {
        return img_url;
    }



}
