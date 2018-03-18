package loginscreen.solution.example.com.loginscreen;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ViewFlipper;

import loginscreen.solution.example.com.loginscreen.data.MyAppContract;
import loginscreen.solution.example.com.loginscreen.utils.DBUtils;
import loginscreen.solution.example.com.loginscreen.utils.Validation;

public class MainActivity extends AppCompatActivity {

    private Button mButtonLogin;
    private Button mButtonSignup;
    private Button mButtonSignin;
    private Button mButtonCreate;
    private EditText mName;
    private EditText mPhone;
    private EditText mEmail;
    private EditText mPassword;
    private EditText mEmailSignup;
    private EditText mPasswordSignup;

    private ViewFlipper mViewFlipper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        mButtonLogin = (Button) findViewById(R.id.bt_login);
        mButtonSignup = (Button) findViewById(R.id.bt_signup);
        mViewFlipper = (ViewFlipper) findViewById(R.id.view_flipper);
        mButtonSignin = (Button) findViewById(R.id.bt_sign_in);
        mButtonCreate = (Button) findViewById(R.id.bt_create);
        mName = (EditText) findViewById(R.id.et_name);
        mEmail = (EditText) findViewById(R.id.et_email);
        mPassword = (EditText) findViewById(R.id.et_password);
        mEmailSignup = (EditText) findViewById(R.id.et_email_signup);
        mPasswordSignup = (EditText) findViewById(R.id.et_password_signup);
        mPhone = (EditText) findViewById(R.id.et_phone);

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewFlipper.setDisplayedChild(0);
            }
        });

        mButtonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewFlipper.setDisplayedChild(1);
            }
        });

        mButtonSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean validation = true;
                if(!Validation.validateEmail(mEmail.getText().toString())){
                    mEmail.setError("Type a valid email");
                    validation = false;
                }

                if(!Validation.validatePassword(mPassword.getText().toString())){
                    mPassword.setError("Type a valid password");
                    validation = false;
                }
                if(validation){
                    Cursor cursor = DBUtils.getUser(MainActivity.this, mEmail.getText().toString());
                    if(cursor != null && !cursor.isAfterLast()){
                        int passwordIndex = cursor.getColumnIndex(MyAppContract.CredentialsEntry.COLUMN_PASSWORD);
                        String password = cursor.getString(passwordIndex);
                        if(password.equals(mPassword.getText().toString())){
                            int nameIndex = cursor.getColumnIndex(MyAppContract.CredentialsEntry.COLUMN_NAME);
                            int phoneIndex = cursor.getColumnIndex(MyAppContract.CredentialsEntry.COLUMN_PHONE);
                            String name = cursor.getString(nameIndex);
                            String phone = cursor.getString(phoneIndex);
                            Intent intent = new Intent(MainActivity.this, LoginWelcomeActivity.class);
                            intent.putExtra("name", name);
                            intent.putExtra("email", mEmail.getText().toString());
                            intent.putExtra("phone", phone);
                            startActivity(intent);
                            cursor.close();
                            return;
                        }
                    }
                    mName.setError("Wrong name or password");
                    mPassword.setError("Wrong name or password");
                    cursor.close();
                }
            }
        });

        mButtonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean validation = true;

                if(!Validation.validateName(mName.getText().toString())){
                    mName.setError("Type a valid name");
                    validation = false;
                }
                if(!Validation.validateEmail(mEmailSignup.getText().toString())){
                    mEmailSignup.setError("Type a valid email");
                    validation = false;
                }
                if(!Validation.validatePhone(mPhone.getText().toString())){
                    mPhone.setError("Type a valid phone");
                    validation = false;
                }

                if(!Validation.validatePassword(mPasswordSignup.getText().toString())){
                    mPasswordSignup.setError("Type a valid password");
                    validation = false;
                }

                if(validation){
                    String name = mName.getText().toString();
                    String password = mPasswordSignup.getText().toString();
                    String email = mEmailSignup.getText().toString();
                    String phone = mPhone.getText().toString();
                    boolean isPresent = DBUtils.insertUser(MainActivity.this, name, password, email, phone);
                    if(isPresent){
                        mEmailSignup.setError("User already registered");
                        return;
                    }
                    Intent intent = new Intent(MainActivity.this, LoginWelcomeActivity.class);
                    intent.putExtra("name", name);
                    intent.putExtra("email", email);
                    intent.putExtra("phone", phone);
                    startActivity(intent);
                }
            }
        });
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
