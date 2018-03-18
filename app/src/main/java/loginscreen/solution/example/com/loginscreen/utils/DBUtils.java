package loginscreen.solution.example.com.loginscreen.utils;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.net.Uri;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Vector;

import loginscreen.solution.example.com.loginscreen.data.MyAppContract;

/**
 * Created by daniele on 18/03/18.
 */

public class DBUtils {

    public static boolean insertUser(Context context, String name, String password, String email, String phone){

        Cursor cursor = getUser(context, email);

        cursor.moveToFirst();
        if(!cursor.isAfterLast()){
            return true;
        }

        ContentValues values = new ContentValues();
        values.put(MyAppContract.CredentialsEntry.COLUMN_NAME, name);
        values.put(MyAppContract.CredentialsEntry.COLUMN_PASSWORD, password);
        values.put(MyAppContract.CredentialsEntry.COLUMN_EMAIL, email);
        values.put(MyAppContract.CredentialsEntry.COLUMN_PHONE, phone);
        context.getContentResolver().insert(MyAppContract.CredentialsEntry.CONTENT_URI, values);
        return false;
    }

    public static Cursor getUser(Context context, String email){
        Uri chapterUri = MyAppContract.CredentialsEntry.buildCredentials(email);
        Cursor credentialCursor = context.getContentResolver().query(
                chapterUri,
                null,
                null,
                null,
                null
        );
        credentialCursor.moveToFirst();
        return credentialCursor;
    }

}
