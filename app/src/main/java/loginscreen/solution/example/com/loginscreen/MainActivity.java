package loginscreen.solution.example.com.loginscreen;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {
    ViewFlipper flipper;
    private static final String TAG = MainActivity.class.getSimpleName();
    static DetailsDb db;
    static EditText tv_email, tv_password, tv_phone, tv_name;
    static String email, password, number, name;
    static ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        flipper = (ViewFlipper) findViewById(R.id.view_flipper);
        final Button b1 = (Button) findViewById(R.id.bt_login);
        final Button b2 = (Button) findViewById(R.id.bt_signup);
        final Button log = (Button) findViewById(R.id.bt_sign_in);
        final Button signup = (Button) findViewById(R.id.bt_create);
        boolean e = false, n = false;
        b1.setBackgroundResource(R.color.buttoncolor);
        tv_email = (EditText) findViewById(R.id.et_email);
        tv_password = (EditText) findViewById(R.id.et_password);
        tv_phone = (EditText) findViewById(R.id.et_phone);
        tv_name = (EditText) findViewById(R.id.et_name);

        final LinearLayout lt_name = (LinearLayout) findViewById(R.id.lt_name);
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setCancelable(true);
        db = new DetailsDb(this);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b2.setBackgroundResource(R.color.buttoncolor);
                b1.setBackgroundResource(R.color.white);
                lt_name.setVisibility(View.VISIBLE);
                flipper.setDisplayedChild(1);

            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b1.setBackgroundResource(R.color.buttoncolor);
                b2.setBackgroundResource(R.color.white);
                lt_name.setVisibility(View.INVISIBLE);
                flipper.setDisplayedChild(0);
            }
        });
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate_credentials()) {
                    CheckCredentials ob = new CheckCredentials(getBaseContext());
                    if (ob.isauthenticate(email, password)) {
                        Intent in = new Intent(MainActivity.this, WelcomeActivity.class);
                        in.putExtra("email", email);
                        in.putExtra("password", password);
                        startActivity(in);
                    } else {
                        Toast.makeText(MainActivity.this, R.string.loginfail, Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate_info()) {
                    String name = tv_name.getText().toString();
                    String email = tv_email.getText().toString();
                    String password = tv_password.getText().toString();
                    String phone = tv_phone.getText().toString();

                    ContentValues values = new ContentValues();
                    values.put(DetailsDb.USERNAME, name);
                    values.put(DetailsDb.EMAIL, email);
                    values.put(DetailsDb.PASSWORD, password);
                    values.put(DetailsDb.PHONE, phone);
                    db.insert(values);
                }
            }
        });

    }

    public static boolean validate_credentials() {
        email = tv_email.getText().toString();
        password = tv_password.getText().toString();
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            tv_email.setError("Invalid Email");
            return false;
        }
        if (password.isEmpty()) {
            tv_password.setError("Password can be empty!!");
            return false;
        }
        return true;
    }

    public static boolean validate_info() {

        email = tv_email.getText().toString();
        password = tv_password.getText().toString();
        number = tv_phone.getText().toString();
        name = tv_name.getText().toString();

        String regexm = "^[0-9]{10}$";
        String regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*(_|[^\\w])).+$";
        boolean valid = true;
        if (name.isEmpty())
            tv_name.setError("Name can't be empty");
        else tv_name.setError(null);
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            tv_email.setError("Invalid Email");
            valid = false;
        } else
            tv_email.setError(null);
        if (number.isEmpty() || number.length() != 10 || !number.matches(regexm)) {
            tv_phone.setError("Invalid Phone");
            valid = false;
        } else
            tv_phone.setError(null);
        if (password.isEmpty() || !password.matches(regexp)) {

            tv_password.setError("Password must contain one uppercase,one lowercase,1 digit and one special character");
            valid = false;
        } else if (password.length() < 6)
            tv_password.setError("Password should be atleast 6 characters long");
        else
            tv_password.setError(null);

        return valid;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
