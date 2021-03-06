package com.shaadi.assignment.Candidates.Network.Response;

import com.google.gson.annotations.SerializedName;

public class Candidate {

    @SerializedName("name")
    private Name name;
    @SerializedName("location")
    private Location location;
    @SerializedName("email")
    private String email;
    @SerializedName("dob")
    private DateOfBirth dateOfBirth;
    @SerializedName("registered")
    private Registration registration;
    @SerializedName("picture")
    private Picture picture;


    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public DateOfBirth getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(DateOfBirth dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public Registration getRegistration() {
        return registration;
    }

    public void setRegistration(Registration registration) {
        this.registration = registration;
    }

}
