package com.shaadi.assignment.Callbacks;

public interface CloudDataCallback<C> {

    void onSuccess(C data);

    void onError(String error);

}
