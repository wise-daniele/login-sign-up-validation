package loginscreen.solution.example.com.loginscreen;

import android.content.Intent;
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

public class MainActivity extends AppCompatActivity {

    private Button mButtonLogin;
    private Button mButtonSignup;
    private Button mButtonSignin;
    private Button mButtonCreate;
    private EditText mName;
    private EditText mPhone;
    private EditText mEmail2;
    private EditText mPassword2;

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
        mEmail2 = (EditText) findViewById(R.id.et_email_2);
        mPhone = (EditText) findViewById(R.id.et_phone);
        mPassword2 = (EditText) findViewById(R.id.et_password_2);


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

        mButtonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean validation = true;

                if(!validateName()){
                    mName.setError("Type a valid name");
                    validation = false;
                }
                if(!validateEmail()){
                    mEmail2.setError("Type a valid email");
                    validation = false;
                }
                if(!validatePhone()){
                    mPhone.setError("Type a valid phone");
                    validation = false;
                }

                if(!validatePassword()){
                    mPassword2.setError("Type a valid password");
                    validation = false;
                }

                if(validation){
                    Intent intent = new Intent(MainActivity.this, LoginWelcomeActivity.class);
                    intent.putExtra("name", mName.getText());
                    intent.putExtra("email", mEmail2.getText());
                    intent.putExtra("phone", mPhone.getText());
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

    private boolean validateEmail(){

        String email = mEmail2.getText().toString();
        if(Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            return true;
        }
        else{
            return false;
        }

    }

    private boolean validatePassword(){
        String passStr = mPassword2.getText().toString();
        if(passStr.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$")){
            return true;
        }
        else{
            return false;
        }

    }

    private boolean validateName(){
        String nameStr = mName.getText().toString();
        if(nameStr.matches("^[a-zA-Z0-9]*$")){
            return true;
        }
        else{
            return false;
        }
    }

    private boolean validatePhone(){
        Editable phoneStr = mPhone.getText();
        if(Patterns.PHONE.matcher(phoneStr).matches()){
            return true;
        }
        else{
            return false;
        }
    }
}
