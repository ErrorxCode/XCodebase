package com.xcode.database;

import androidx.annotation.Nullable;

/**
 * This is a callback interface that is invoked when a response is made to the server.
 * This tells weather the response is successful or failed.
 */
public interface OnXCodebaseResponse {
    void onSuccessful(@Nullable Object object);
    void onFailed(XCodeException e);
}
