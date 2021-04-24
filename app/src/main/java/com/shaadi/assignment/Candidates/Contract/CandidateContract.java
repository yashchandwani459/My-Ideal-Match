package com.shaadi.assignment.Candidates.Contract;

import com.shaadi.assignment.Base.ActivityBasePresenter;
import com.shaadi.assignment.Callbacks.CloudDataCallback;
import com.shaadi.assignment.Candidates.Network.Response.Candidate;
import com.shaadi.assignment.Candidates.View.CandidateInfo;

import java.util.List;

public interface CandidateContract {

    interface View {

        void setCandidates(List<CandidateInfo> userEntities);

        void toggleUsersVisibility(boolean show);

        void toggleLoading(boolean isLoading);

        void toggleErrorVisibility(boolean show);

        void setError(String error);

    }

    interface Presenter extends ActivityBasePresenter {
        void onResume(View view);
    }

    interface Model {
        void fetchCandidates(CloudDataCallback<List<Candidate>> callback);
    }

}
