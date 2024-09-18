package com.example.listviewlab;

import androidx.annotation.NonNull;

public class cake {
    private int thumbnail;
    private String title;
    private String description;
    cake(int mythumbnail, String mytitle, String mydescription)
    {
        this.description = mydescription;
        this.title = mytitle;
        this.thumbnail = mythumbnail;
    }
    public int getThumbnail(){
        return thumbnail;
    }

    public String gettitle(){
        return title;
    }

    public String getdescription(){
        return description;
    }
    @NonNull
    @Override
    public String toString() {
        return title;
    }
}
