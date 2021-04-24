package com.shaadi.assignment.DatabaseHelper;

import com.shaadi.assignment.Candidates.Network.Response.Candidate;
import com.shaadi.assignment.Candidates.View.CandidateInfo;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface CandidateDao {

    @Query("SELECT * FROM candidate")
    List<CandidateInfo> loadAllCandidates();

    @Insert
    void insertCandidate(CandidateInfo candidateDBModel);

    @Update
    void updateCandidate(CandidateInfo candidateDBModel);

    @Delete
    void delete(CandidateInfo candidateDBModel);

    @Query("SELECT * FROM candidate WHERE email = :email")
    CandidateInfo loadCandidateByEmail(String email);

}
