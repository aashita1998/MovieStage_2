package com.example.aashitachowdary.moviestage_2;

import com.android.volley.toolbox.StringRequest;

class Trailer {
    //This is trailer pojo class
    String id;
    String key;
    String name;
    String type;

    public Trailer(String id, String key, String name, String type) {
        this.id = id;
        this.key = key;
        this.name = name;
        this.type = type;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
