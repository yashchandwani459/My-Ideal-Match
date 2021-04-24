package com.shaadi.assignment.Candidates.Network.Response;

import com.google.gson.annotations.SerializedName;

public class Registration {

    @SerializedName("date")
    private String date;
    @SerializedName("age")
    private String period;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

}
