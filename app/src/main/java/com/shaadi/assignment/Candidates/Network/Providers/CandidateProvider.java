package com.shaadi.assignment.Candidates.Network.Providers;

import com.shaadi.assignment.Candidates.Network.Response.CandidateResult;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CandidateProvider {

    @GET("api")
    Single<CandidateResult> getUsersResponse(@Query("results") int value);

}
