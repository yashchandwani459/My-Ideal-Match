package com.shaadi.assignment.Candidates.View;

import com.shaadi.assignment.Candidates.Network.Response.Candidate;
import com.shaadi.assignment.DatabaseHelper.EnumConverter;
import com.shaadi.assignment.Utility.StringHelper;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "candidate")
public class CandidateInfo {

    private String fullNameAge;
    private String location;
    @NonNull
    @PrimaryKey(autoGenerate = false)
    private String email;

    private String registrationPeriod;
    private String picture;
    private CANDIDATE_STATUS candidate_status;

    public enum CANDIDATE_STATUS {
        NA(0),
        ACCEPTED(1),
        DECLINED(2);

        public final int value;

        CANDIDATE_STATUS(int newValue) {
            value = newValue;
        }

        public int getValue() {
            return value;
        }
    }

    public CandidateInfo() {

    }

    public CandidateInfo(String fullNameAge, String email, String location, String registrationPeriod, String picture, CANDIDATE_STATUS candidateStatus) {
        this.fullNameAge = fullNameAge;
        this.email = email;
        this.location = location;
        this.registrationPeriod = registrationPeriod;
        this.picture = picture;
        this.candidate_status =candidateStatus;
    }

    public CandidateInfo(Candidate user, StringHelper stringHelper) {
        fullNameAge = user.getName().getFirstName().concat(user.getName().getLastName().concat(stringHelper.getAge(Integer.parseInt(user.getDateOfBirth().getAge()))));

        location = user.getLocation().getStreet().getName()
                .concat(stringHelper.getComma())
                .concat(user.getLocation().getCity())
                .concat(stringHelper.getComma())
                .concat(user.getLocation().getState())
                .concat(stringHelper.getComma())
                .concat(user.getLocation().getPostcode());
        email = user.getEmail();
        if (user.getRegistration().getPeriod().equals(stringHelper.getZero()) ||
                user.getRegistration().getPeriod().equals(stringHelper.getOne())) {
            registrationPeriod = stringHelper.getToday();
        } else if (user.getRegistration().getPeriod().equals(stringHelper.getTwo())) {
            registrationPeriod = stringHelper.getYesterday();
        } else {
            registrationPeriod = stringHelper.getSomeDaysAgo(
                    Integer.parseInt(user.getRegistration().getPeriod()));
        }
        picture = user.getPicture().getLargeUrl();

        candidate_status = EnumConverter.fromIntToStatus(0);

    }

    public CANDIDATE_STATUS getCandidate_status() {
        return candidate_status;
    }

    public void setCandidate_status(CANDIDATE_STATUS candidate_status) {
        this.candidate_status = candidate_status;
    }

    public String getFullNameAge() {
        return fullNameAge;
    }

    public void setFullNameAge(String fullNameAge) {
        this.fullNameAge = fullNameAge;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegistrationPeriod() {
        return registrationPeriod;
    }

    public void setRegistrationPeriod(String registrationPeriod) {
        this.registrationPeriod = registrationPeriod;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

}
