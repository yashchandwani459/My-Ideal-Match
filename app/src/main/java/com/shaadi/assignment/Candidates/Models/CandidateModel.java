package com.shaadi.assignment.Candidates.Models;

import com.shaadi.assignment.Callbacks.CloudDataCallback;
import com.shaadi.assignment.Candidates.Contract.CandidateContract;
import com.shaadi.assignment.Candidates.Network.Impl.CandidateProviderImpl;
import com.shaadi.assignment.Candidates.Network.Providers.CandidateProvider;
import com.shaadi.assignment.Candidates.Network.Response.Candidate;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class CandidateModel implements CandidateContract.Model {

    private CandidateProviderImpl candidateProvider;

    public CandidateModel(CompositeDisposable compositeDisposable) {
        candidateProvider = new CandidateProviderImpl(compositeDisposable);
    }

    @Override
    public void fetchCandidates(CloudDataCallback<List<Candidate>> callback) {
        candidateProvider.getUsers(new CloudDataCallback<List<Candidate>>() {
            @Override
            public void onSuccess(List<Candidate> data) {
                callback.onSuccess(data);
            }

            @Override
            public void onError(String error) {
                callback.onError(error);
            }
        });
    }

}
