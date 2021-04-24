package com.shaadi.assignment.InstanceFactory;

import com.shaadi.assignment.Application.HeartBeatApplication;
import com.shaadi.assignment.Candidates.Network.Providers.CandidateProvider;
import com.shaadi.assignment.R;

public class CloudServiceInstance {

    private static volatile CandidateProvider candidateProvider = null;

    private CloudServiceInstance() {

        if (candidateProvider != null) {
            throw new RuntimeException(""+ HeartBeatApplication.getAppInstance().getString(R.string.instance_error));
        }

    }

    public static CandidateProvider getCloudServiceInstance() {

        if (candidateProvider == null) {
            synchronized (CloudServiceInstance.class) {
                if (candidateProvider == null) {
                    candidateProvider = RetrofitInstance.getRetrofitInstance()
                            .create(CandidateProvider.class);
                }
            }
        }
        return candidateProvider;

    }

}
