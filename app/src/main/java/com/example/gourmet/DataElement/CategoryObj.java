package com.example.gourmet.DataElement;

import android.graphics.drawable.Drawable;
import android.net.Uri;

import java.io.File;

public class CategoryObj {

    private String name;

    private String url;

    private String displayName;

    public CategoryObj(String name, String displayName, String url) {
        this.name = name;
        this.displayName = displayName;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl(){
        return url;
    }

    public String getDisplayName() {
        return displayName;
    }
}
