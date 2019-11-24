package com.kalher.movietime.base;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class AbstractItem {

    @JsonProperty("id")
    protected Integer id;

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

}
