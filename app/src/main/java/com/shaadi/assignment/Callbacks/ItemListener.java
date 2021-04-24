package com.shaadi.assignment.Callbacks;

import android.view.View;

import com.shaadi.assignment.Candidates.View.CandidateInfo;

public interface ItemListener {
    void onConnect(CandidateInfo candidateInfo, CandidateInfo.CANDIDATE_STATUS isAccepted);

    void onDecline(View viewToAnimate, int position,CandidateInfo candidateInfo, CandidateInfo.CANDIDATE_STATUS isAccepted);

}