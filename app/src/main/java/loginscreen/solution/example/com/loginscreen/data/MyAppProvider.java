package loginscreen.solution.example.com.loginscreen.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by daniele on 18/03/18.
 */

public class MyAppProvider extends ContentProvider{

    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private MyAppDbHelper mOpenHelper;

    static final int CREDENTIALS = 100;
    static final int CREDENTIALS_EMAIL = 101;

    private static final String sCredentialsSelection =
            MyAppContract.CredentialsEntry.TABLE_NAME +
                    "." + MyAppContract.CredentialsEntry.COLUMN_EMAIL + " = ? ";

    private Cursor getCredentialsByEmail(Uri uri, String[] projection, String sortOrder) {
        //get chapter id from DB
        String email = MyAppContract.CredentialsEntry.getEmailFromUri(uri);

        String[] selectionArgs;
        String selection;

        selection = sCredentialsSelection;
        selectionArgs = new String[]{email};

        Cursor cursor = mOpenHelper.getReadableDatabase().query(MyAppContract.CredentialsEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );
        return cursor;
    }

    static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = MyAppContract.CONTENT_AUTHORITY;
        matcher.addURI(authority, MyAppContract.PATH_CREDENTIALS, CREDENTIALS);
        matcher.addURI(authority, MyAppContract.PATH_CREDENTIALS + "/*", CREDENTIALS_EMAIL);
        return matcher;
    }

    @Override
    public boolean onCreate() {
        mOpenHelper = new MyAppDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        // Use the Uri Matcher to determine what kind of URI this is.
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case CREDENTIALS:
                return MyAppContract.CredentialsEntry.CONTENT_TYPE;
            case CREDENTIALS_EMAIL:
                return MyAppContract.CredentialsEntry.CONTENT_ITEM_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor retCursor;
        switch (sUriMatcher.match(uri)) {
            case CREDENTIALS: {
                retCursor = mOpenHelper.getReadableDatabase().query(
                        MyAppContract.CredentialsEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            }
            case CREDENTIALS_EMAIL: {
                retCursor = getCredentialsByEmail(uri, projection, sortOrder);
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        Uri returnUri;
        switch (match) {
            case CREDENTIALS: {
                long _id = db.insert(MyAppContract.CredentialsEntry.TABLE_NAME, null, values);
                if ( _id > 0 )
                    returnUri = MyAppContract.CredentialsEntry.buildCredentialUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int rowsDeleted;
        if ( null == selection ) selection = "1";
        switch (match) {
            case CREDENTIALS:
                rowsDeleted = db.delete(
                        MyAppContract.CredentialsEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int rowsUpdated;

        switch (match) {
            case CREDENTIALS:
                rowsUpdated = db.update(MyAppContract.CredentialsEntry.TABLE_NAME, values, selection,
                        selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsUpdated;
    }

}
