package loginscreen.solution.example.com.loginscreen.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import loginscreen.solution.example.com.loginscreen.data.MyAppContract.CredentialsEntry;

/**
 * Created by daniele on 18/03/18.
 */

public class MyAppDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    static final String DATABASE_NAME = "solution.db";

    public MyAppDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_ARTICLE_TABLE = "CREATE TABLE " + CredentialsEntry.TABLE_NAME + " (" +
                CredentialsEntry._ID + " INTEGER PRIMARY KEY," +
                CredentialsEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                CredentialsEntry.COLUMN_PASSWORD + " TEXT NOT NULL, " +
                CredentialsEntry.COLUMN_EMAIL + " TEXT UNIQUE NOT NULL, " +
                CredentialsEntry.COLUMN_PHONE + " TEXT NOT NULL" +
                ");";
        sqLiteDatabase.execSQL(SQL_CREATE_ARTICLE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CredentialsEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
