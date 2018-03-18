package loginscreen.solution.example.com.loginscreen.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by daniele on 18/03/18.
 */

public class MyAppContract {

    public static final String CONTENT_AUTHORITY = "loginscreen.solution.example.com.loginscreen";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_CREDENTIALS = "credentials";

    public static final class CredentialsEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_CREDENTIALS).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_CREDENTIALS;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_CREDENTIALS;

        public static final String TABLE_NAME = "credentials";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PASSWORD = "password";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_PHONE = "phone";

        public static Uri buildCredentialUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildCredentials(String email) {
            return CONTENT_URI.buildUpon().appendPath(email).build();
        }

        public static String getEmailFromUri(Uri uri) {
            return uri.getPathSegments().get(1);
        }

    }
}
