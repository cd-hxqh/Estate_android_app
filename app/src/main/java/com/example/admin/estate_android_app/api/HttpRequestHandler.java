package com.example.admin.estate_android_app.api;

/**
 * Created by yw on 2015/5/22.
 */
public interface HttpRequestHandler<E> {
    public void onSuccess(E data);

    public void onSuccess(E data, int totalPages, int currentPage);

    public void onFailure(String error);
}
