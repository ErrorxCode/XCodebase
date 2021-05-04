package com.xcode.database;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.Nullable;

import com.parse.DeleteCallback;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.Arrays;
import java.util.List;

public class XCodeBase {

    public static volatile String CLASS;
    private static boolean bool;
    private static int integer;
    private static Object obj;
    private static Object[] objArray;
    private static String str;
    private static ParseQuery<ParseObject> query;
    private static ParseObject object;
    private static OnXCodebaseResponse listener;


    /**
     * Connects your app to the server.This must be called before your
     * application can use the {@link XCodeBase} library. The recommended way is to put a call to
     * {@code XCodeBase.initialize} in your {@code Application}'s {@code onCreate} method:
     * <p/>
     * <pre>
     * public class MyApplication extends Application {
     *   public void onCreate() {
     *     XCodeBase.initialize(this,YOUR_PACKAGE);
     *   }
     * }
     * </pre>
     *
     * @param PackageName The package for your application.
     */
    public static void initialize(Context context,String PackageName) {
        CLASS = PackageName;
        query = ParseQuery.getQuery(CLASS);
        object = new ParseObject(CLASS);
        Parse.initialize(new Parse.Configuration.Builder(context)
                .applicationId("Tzfm1UvgkDNSu5oe5MF5sJwLJWjC074V7H12y9CR")
                .clientKey("GjBiPdXORxVqPzkcN4dc8KY0M8opIfTQRTW2fSqZ")
                .server("https://parseapi.back4app.com/")
                .build()
        );
        SharedPreferences preferences = context.getSharedPreferences("pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        if (!preferences.getBoolean("isUserCounted", false)){
            ParseQuery.getQuery("XCodebaseUsers").getInBackground("KCTDwowT6Z", new GetCallback<ParseObject>() {
                @Override
                public void done(ParseObject object, ParseException e) {
                    if (e == null) {
                        int i = object.getInt("Users");
                        i++;
                        object.put("Users", i);
                        object.saveInBackground();
                        editor.putBoolean("isUserCounted", true).apply();
                    } else {
                        editor.putBoolean("isUserCounted", false).apply();
                    }
                }
            });
        }
    }

    /**
     * This will save the given variable to the database with the unique id.
     * @param id the id to store variable as.
     * @param value the value that you want to store
     */
    public static void saveInt(String id,long value,@Nullable OnXCodebaseResponse callback){
        listener = callback;
        object.put(id,value);
        object.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                getCallbacks(null,e);
            }
        });
    }

    /**
     * This will save the given variable to the database with the unique id.
     * @param id the id to store variable as.
     * @param value the value that you want to store
     */
    public static void saveString(String id,String value,@Nullable OnXCodebaseResponse callback){
        listener = callback;
        object.put(id,value);
        object.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                getCallbacks(null,e);
            }
        });
    }

    /**
     * This will save the given variable to the database with the unique id.
     * @param id the id to store variable as.
     * @param value the value that you want to store
     */
    public static void saveBoolean(String id,boolean value,@Nullable OnXCodebaseResponse callback){
        listener = callback;
        object.put(id,value);
        object.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                getCallbacks(null,e);
            }
        });
    }

    /**
     * This will save all the variable to the database each with the same id.
     * @param id the id to store variable as.
     * @param objects the objects that you want to store
     */
    public static void saveAll(String id, Object[] objects,@Nullable OnXCodebaseResponse callback){
        listener = callback;
        object.addAll(id, Arrays.asList(objects));
        object.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                getCallbacks(null,e);
            }
        });
    }

    /**
     * This will fetch the object by the id provided. if the id is not unique, then there is no guarantee of the required result.
     * It will return any one of the variable matching the given id.
     * @param id the id of the variable
     * @return Object this may be null
     */
    public static @Nullable Object get(String id,@Nullable OnXCodebaseResponse callback){
        listener = callback;
        query.whereExists(id);
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if (e == null)
                    obj = object.get(id);
                getCallbacks(obj,e);
            }
        });
        return obj;
    }

    /**
     * Returns all the object associated with the given id.
     * @param id the id of the variable
     * @return Object[]
     */
    public static @Nullable Object[] getAll(String id,@Nullable OnXCodebaseResponse callback){
        listener = callback;
        query.whereExists(id).findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null)
                    objArray = objects.toArray();
                getCallbacks(objects,e);
            }
        });
        return objArray;
    }

    /**
     * This will return a int associated with the given id.
     * @param id the id of the variable
     * @return int : 0 if key is invalid or the value is not an integer
     */
    public static int getInt(String id,@Nullable OnXCodebaseResponse callback) {
        listener = callback;
        query.whereExists(id).getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    integer = object.getInt(id);
                }
                getCallbacks(integer,e);
            }
        });
        return integer;
    }


    /**
     * This will return a boolean associated with the given id.
     * @param id the id of the variable
     * @return boolean : false if key is invalid or the value is not an boolean.
     */
    public static boolean getBoolean(String id,@Nullable OnXCodebaseResponse callback){
        listener = callback;
        query.whereExists(id).getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if (e == null)
                    bool = object.getBoolean(id);
                 else
                     getCallbacks(bool,e);
            }
        });
        return bool;
    }

    /**
     * This will return a boolean associated with the given id.
     * @param id the id of the variable
     * @return String : this may be null
     */
    public static @Nullable String getString(String id,@Nullable OnXCodebaseResponse callback){
        listener = callback;
        query.whereExists(id).getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if (e == null)
                    str = object.getString(id);
                getCallbacks(str,e);
            }
        });
        return str;
    }

    private static void getCallbacks(Object object,ParseException e){
        if (e == null && listener != null)
            listener.onSuccessful(object);
        else if (listener != null) {
            if (object instanceof Integer && parseInt(object) == 0)
                listener.onFailed(new XCodeException("Key is invalid or the value is not an integer"));
            else if (object instanceof Boolean && !parseBoolean(object))
                listener.onFailed(new XCodeException("Key is invalid or the value is not an boolean"));
            else if (object == null)
                listener.onFailed(new XCodeException("Key is invalid or the value is not an String"));
            else
                listener.onFailed(new XCodeException(e.getMessage()));
        }
    }


    /**
     * This will update the value of existing variable.
     * @param id the id of the variable
     */
    public static void update(String id,Object value,@Nullable OnXCodebaseResponse callback){
        listener = callback;
        query.whereExists(id).getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if (e == null){
                    object.put(id,value);
                    object.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            getCallbacks(object,e);
                        }
                    });
                } else {
                    getCallbacks(null,e);
                }
            }
        });
    }

    /**
     * This will delete the variable of the given id.
     * @param id the id of the variable
     */
    public static void delete(String id,@Nullable OnXCodebaseResponse callback){
        listener =callback;
        query.whereExists(id).getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if (e == null){
                    object.deleteInBackground(new DeleteCallback() {
                        @Override
                        public void done(ParseException e) {
                            getCallbacks(null,e);
                        }
                    });
                } else {
                    getCallbacks(null,e);
                }
            }
        });
    }

    /**
     Returns the int from the object instance. This may be null if object do not contain the required variable
     * @param object that contain the value
     **/
    public static int parseInt(Object object){
        return Integer.parseInt(object.toString());
    }

    /**
     Returns the String from the object instance. This may be null if object do not contain the required variable
     * @param obj that contain the value
     **/
    public static String parseString(Object obj){
        return obj.toString();
    }

    /**
     Returns the boolean from the object instance. This may be null if object do not contain the required variable
     * @param obj that contain the value
     **/
    public static boolean parseBoolean(Object obj){
        return Boolean.parseBoolean(obj.toString());
    }
}
