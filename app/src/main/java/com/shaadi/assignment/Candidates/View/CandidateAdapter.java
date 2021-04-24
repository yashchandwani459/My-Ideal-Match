package com.shaadi.assignment.Candidates.View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.shaadi.assignment.Callbacks.ItemListener;
import com.shaadi.assignment.R;
import com.shaadi.assignment.Utility.CircleBitmapTransformation;
import com.shaadi.assignment.databinding.ItemRowBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class CandidateAdapter extends RecyclerView.Adapter<CandidateAdapter.MainViewHolder> {

    private ItemListener listener;
    private List<CandidateInfo> candidateInfoList;

    public CandidateAdapter() {
        candidateInfoList = new ArrayList<>();
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        holder.bind(candidateInfoList.get(position));
    }

    @Override
    public int getItemCount() {
        return candidateInfoList.size();
    }

    public void setListener(ItemListener listener) {
        this.listener = listener;
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {
        ItemRowBinding binding;

        private MainViewHolder(ItemRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.fabConnect.setOnClickListener(v -> listener.onConnect(candidateInfoList.get(getAdapterPosition()), CandidateInfo.CANDIDATE_STATUS.ACCEPTED));
            binding.fabDecline.setOnClickListener(v -> listener.onDecline(binding.fabDecline, getAdapterPosition(),candidateInfoList.get(getAdapterPosition()), CandidateInfo.CANDIDATE_STATUS.DECLINED));
        }

        private void bind(CandidateInfo current) {
            binding.tvCreatedDate.setText(current.getRegistrationPeriod());
            binding.tvNameAge.setText(current.getFullNameAge());
            binding.tvLocation.setText(current.getLocation());
            binding.tvEmail.setText(current.getEmail());
            Picasso.get()
                    .load(current.getPicture())
                    .transform(new CircleBitmapTransformation())
                    .into(binding.ivProfileImage);

            if(current.getCandidate_status()== CandidateInfo.CANDIDATE_STATUS.NA){
                binding.layoutAcceptDeclined.setVisibility(View.VISIBLE);
                binding.layoutCurrentStatus.setVisibility(View.GONE);
            }else{
                binding.layoutCurrentStatus.setVisibility(View.VISIBLE);
                binding.layoutAcceptDeclined.setVisibility(View.GONE);

                if(current.getCandidate_status()== CandidateInfo.CANDIDATE_STATUS.ACCEPTED)
                    binding.tvCurrentStatus.setBackgroundResource(R.drawable.radius_accepted);
                else
                    binding.tvCurrentStatus.setBackgroundResource(R.drawable.radius_declined);

                binding.tvCurrentStatus.setText("MEMBER "+current.getCandidate_status());
            }
        }

    }

    public void setCandidates(List<CandidateInfo> candidateInfoList) {
        this.candidateInfoList.clear();
        this.candidateInfoList = candidateInfoList;
        notifyDataSetChanged();
    }

    public void removeUser(View viewToAnimate, int position) {
        Animation animation = AnimationUtils.loadAnimation(viewToAnimate.getContext(), android.R.anim.slide_in_left);
        viewToAnimate.startAnimation(animation);
        candidateInfoList.remove(position);
        notifyItemRemoved(position);
    }

}
