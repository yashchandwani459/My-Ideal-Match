package com.shaadi.assignment.Candidates.Network.Response;

import com.google.gson.annotations.SerializedName;

public class DateOfBirth {

    @SerializedName("date")
    private String dateOfBirth;
    @SerializedName("age")
    private String age;

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
