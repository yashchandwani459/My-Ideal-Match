package com.shaadi.assignment.Candidates.Network.Impl;

import com.shaadi.assignment.Callbacks.CloudDataCallback;
import com.shaadi.assignment.Candidates.Network.Providers.CandidateProvider;
import com.shaadi.assignment.Candidates.Network.Response.Candidate;
import com.shaadi.assignment.Candidates.Network.Response.CandidateResult;
import com.shaadi.assignment.InstanceFactory.CloudServiceInstance;
import com.shaadi.assignment.Utility.AppConstants;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CandidateProviderImpl {

    private CandidateProvider userService;
    private CompositeDisposable compositeDisposable;

    public CandidateProviderImpl(CompositeDisposable compositeDisposable) {
        this.compositeDisposable = compositeDisposable;
        userService = CloudServiceInstance.getCloudServiceInstance();
    }

    public void getUsers(CloudDataCallback<List<Candidate>> callBack) {
        userService.getUsersResponse(AppConstants.RESPONSE_COUNT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<CandidateResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(CandidateResult userResponse) {
                        callBack.onSuccess(userResponse.getCandidates());
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onError(e.getMessage());
                    }
                });
    }

}
