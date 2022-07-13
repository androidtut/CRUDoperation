package com.example.crudoperation;

public class Blogdata {
    String title,desc,imageurl;

    public Blogdata(String title, String desc, String imageurl) {
        this.title = title;
        this.desc = desc;
        this.imageurl = imageurl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
