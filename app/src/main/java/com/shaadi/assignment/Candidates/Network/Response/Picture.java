package com.shaadi.assignment.Candidates.Network.Response;

import com.google.gson.annotations.SerializedName;

public class Picture {

    @SerializedName("large")
    private String largeUrl;

    public String getLargeUrl() {
        return largeUrl;
    }

}
