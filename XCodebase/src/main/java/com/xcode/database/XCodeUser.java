package com.xcode.database;

import androidx.annotation.Nullable;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class XCodeUser {

    private static OnXCodebaseResponse listener;

    /**
     * This will create a login entry for the user in database. This entry contain information like username,email & password or other general info. like date,time etc.
     * if username is not unique then , {@link XCodeException} will be thrown in callback
     * @param username the username of the user
     * @param password the password of the user
     */
    public static void createUser(String username, String password, @Nullable OnXCodebaseResponse callback){
        listener = callback;
        ParseUser user = new ParseUser();
        user.setUsername(username);
        user.setPassword(password);
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                getCallbacks(e);
            }
        });
    }

    /**
     * Login existing user with the following credentials. If no user exist, {@link XCodeException} is thrown in callback
     * @param username the username of the user
     * @param password the password of the user
     */
    public static void loginUser(String username,String password,@Nullable OnXCodebaseResponse callback){
        listener = callback;
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                getCallbacks(e);
            }
        });
    }

    private static void getCallbacks(ParseException e) {
        if (e == null && listener != null)
            listener.onSuccessful(null);
        else if (listener != null)
            listener.onFailed(new XCodeException(e.getMessage().replace("Parse","XCodebase")));
    }
}
