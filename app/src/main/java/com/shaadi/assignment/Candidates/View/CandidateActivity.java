package com.shaadi.assignment.Candidates.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.shaadi.assignment.Callbacks.ItemListener;
import com.shaadi.assignment.Candidates.Contract.CandidateContract;
import com.shaadi.assignment.Candidates.Presenter.CandidatePresenter;
import com.shaadi.assignment.DatabaseHelper.AppDatabase;
import com.shaadi.assignment.DatabaseHelper.AppExecutors;
import com.shaadi.assignment.R;
import com.shaadi.assignment.Utility.StringHelper;
import com.shaadi.assignment.databinding.ActivityMainBinding;

import java.util.List;

public class CandidateActivity extends AppCompatActivity implements CandidateContract.View {

    private ActivityMainBinding binding;
    private CandidatePresenter presenter;
    private CandidateAdapter candidateAdapter;
    private Context context;
    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        context = this;
        database = AppDatabase.getInstance(context);
        presenter = new CandidatePresenter(this, new StringHelper(context));
        initRecyclerView();
        presenter.create();

        candidateAdapter.setListener(new ItemListener() {
            @Override
            public void onConnect(CandidateInfo candidateInfo, CandidateInfo.CANDIDATE_STATUS status) {
                candidateInfo.setCandidate_status(status);
                updateStatusInDB(candidateInfo);
                candidateAdapter.notifyDataSetChanged();
                Toast.makeText(context, getString(R.string.member_accepted), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onDecline(View viewToAnimate, int position, CandidateInfo candidateInfo,CandidateInfo.CANDIDATE_STATUS status) {
                candidateInfo.setCandidate_status(status);
                updateStatusInDB(candidateInfo);
                candidateAdapter.notifyDataSetChanged();
                Toast.makeText(context, getString(R.string.member_declined), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void setCandidates(List<CandidateInfo> userEntities) {
        candidateAdapter.setCandidates(userEntities);
    }

    @Override
    public void toggleUsersVisibility(boolean show) {
        binding.recyclerView.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void toggleLoading(boolean isLoading) {
        binding.progressBarLoading.setVisibility(isLoading ? View.VISIBLE : View.GONE);
    }

    @Override
    public void toggleErrorVisibility(boolean show) {
        binding.textViewErrorMsg.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setError(String error) {
        binding.textViewErrorMsg.setText(error);
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        binding.recyclerView.setLayoutManager(linearLayoutManager);
        candidateAdapter = new CandidateAdapter();
        binding.recyclerView.setAdapter(candidateAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume(this);
    }

    @Override
    protected void onPause() {
        presenter.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    public void updateStatusInDB(CandidateInfo candidateInfo){
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                database.candidateDao().updateCandidate(candidateInfo);
            }
        });
    }
}