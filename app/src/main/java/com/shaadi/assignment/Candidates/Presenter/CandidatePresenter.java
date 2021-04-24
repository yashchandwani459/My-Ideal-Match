package com.shaadi.assignment.Candidates.Presenter;

import android.os.Handler;
import android.os.Looper;

import com.shaadi.assignment.Application.HeartBeatApplication;
import com.shaadi.assignment.Callbacks.CloudDataCallback;
import com.shaadi.assignment.Candidates.Contract.CandidateContract;
import com.shaadi.assignment.Candidates.Models.CandidateModel;
import com.shaadi.assignment.Candidates.Network.Response.Candidate;
import com.shaadi.assignment.Candidates.View.CandidateInfo;
import com.shaadi.assignment.DatabaseHelper.AppDatabase;
import com.shaadi.assignment.DatabaseHelper.AppExecutors;
import com.shaadi.assignment.R;
import com.shaadi.assignment.Utility.NetworkHelper;
import com.shaadi.assignment.Utility.StringHelper;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class CandidatePresenter implements CandidateContract.Presenter {

    private StringHelper stringHelper;
    private CandidateContract.View view;
    private CompositeDisposable compositeDisposable;
    private CandidateContract.Model model;
    private AppDatabase database;

    public CandidatePresenter(CandidateContract.View view, StringHelper stringHelper) {
        this.view = view;
        this.stringHelper = stringHelper;
        compositeDisposable = new CompositeDisposable();
        model = new CandidateModel(compositeDisposable);
        database = AppDatabase.getInstance(HeartBeatApplication.getAppInstance());
    }

    @Override
    public void onResume(CandidateContract.View view) {
        this.view=view;
    }

    @Override
    public boolean isViewAvailable() {
        return view != null;
    }

    @Override
    public void create() {

        List<CandidateInfo> displayableUser;

        if (isViewAvailable()) {
            view.toggleLoading(true);
            view.toggleUsersVisibility(false);
            view.toggleErrorVisibility(false);
        }

        if(NetworkHelper.isConnected()){
            model.fetchCandidates(new CloudDataCallback<List<Candidate>>() {
                @Override
                public void onSuccess(List<Candidate> data) {

                    for (Candidate candidate : data) {
                        CandidateInfo candidateInfo=new CandidateInfo(candidate, stringHelper);
                        insertCandidateIntoDB(candidateInfo);
                    }

                    getAllCandidatesFromDB();
                }

                @Override
                public void onError(String error) {
                    if (isViewAvailable()) {
                        view.toggleUsersVisibility(false);
                        view.toggleErrorVisibility(true);
                        view.toggleLoading(false);
                        view.setError(error);
                    }
                }
            });
        }else{
            getAllCandidatesFromDB();
        }

    }

    public void insertCandidateIntoDB(CandidateInfo candidateInfo){
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                database.candidateDao().insertCandidate(candidateInfo);
            }
        });
    }

    public void setCandidates(List<CandidateInfo> displayableUser){
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                if (isViewAvailable()) {
                    view.toggleUsersVisibility(true);
                    view.toggleErrorVisibility(false);
                    view.toggleLoading(false);
                    view.setCandidates(displayableUser);
                }
            }
        });

    }

    public void getAllCandidatesFromDB(){
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {

                List<CandidateInfo> candidateInfoList=database.candidateDao().loadAllCandidates();

                if(candidateInfoList.size()==0){
                    if (isViewAvailable()) {
                        view.toggleUsersVisibility(false);
                        view.toggleErrorVisibility(true);
                        view.toggleLoading(false);
                        view.setError(""+HeartBeatApplication.getAppInstance().getString(R.string.no_data));
                    }
                }else{
                    setCandidates(candidateInfoList);
                }

            }
        });
    }

    @Override
    public void onPause() {
        view=null;
    }

    @Override
    public void onDestroy() {
        view = null;
        compositeDisposable.clear();
    }
}
