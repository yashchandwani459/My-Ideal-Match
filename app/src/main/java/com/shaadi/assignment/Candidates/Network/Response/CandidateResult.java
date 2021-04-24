package com.shaadi.assignment.Candidates.Network.Response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CandidateResult {

    @SerializedName("results")
    private List<Candidate> candidates;

    @SerializedName("info")
    private Info info;

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public void setUsers(List<Candidate> users) {
        this.candidates = users;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }
}
