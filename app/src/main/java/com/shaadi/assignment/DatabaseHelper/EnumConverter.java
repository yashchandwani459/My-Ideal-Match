package com.shaadi.assignment.DatabaseHelper;

import com.shaadi.assignment.Candidates.View.CandidateInfo;
import com.shaadi.assignment.Utility.AppConstants;

import androidx.room.TypeConverter;

public class EnumConverter {

    @TypeConverter
    public static int fromStatusToInt(CandidateInfo.CANDIDATE_STATUS value) {
        return value.ordinal();
    }

    @TypeConverter
    public static CandidateInfo.CANDIDATE_STATUS fromIntToStatus(int value) {
        return (CandidateInfo.CANDIDATE_STATUS.values()[value]);
    }
}

