package loginscreen.solution.example.com.loginscreen;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
    private static final String TAG=MainActivity.class.getSimpleName();
    static  EditText text1,text2,text3,text4,text5;
    static  String email,password,number,firstname,lastname;
    static ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        flipper=(ViewFlipper)findViewById(R.id.viewFlipper);
        final Button b1=(Button)findViewById(R.id.login);
        final Button b2=(Button)findViewById(R.id.signup);
        final Button log=(Button)findViewById(R.id.login1);
        final Button signup=(Button)findViewById(R.id.create);
        boolean e=false,n=false;
        b1.setBackgroundResource(R.color.buttoncolor);
        text1=(EditText)findViewById(R.id.email1);
        text2=(EditText)findViewById(R.id.pass1);
        text3=(EditText)findViewById(R.id.phone);
        text4=(EditText)findViewById(R.id.firstname);
        text5=(EditText)findViewById(R.id.lastname);

        final LinearLayout layout=(LinearLayout)findViewById(R.id.names);
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setCancelable(true);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b2.setBackgroundResource(R.color.buttoncolor);
                b1.setBackgroundResource(R.color.white);
                layout.setVisibility(View.VISIBLE);
                flipper.setDisplayedChild(1);

            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b1.setBackgroundResource(R.color.buttoncolor);
                b2.setBackgroundResource(R.color.white);
                layout.setVisibility(View.INVISIBLE);

                flipper.setDisplayedChild(0);


            }
        });
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(validate_credentials())
               {
                   CheckCredentials ob=new CheckCredentials(getBaseContext());
                   if(ob.isauthenticate(email,password))
                   {
                       Intent in=new Intent(MainActivity.this,WelcomeActivity.class);
                       in.putExtra("email",email);
                       in.putExtra("password",password);
                       startActivity(in);
                   }
                   else
                   {
                       Toast.makeText(MainActivity.this,R.string.loginfail,Toast.LENGTH_LONG).show();
                   }

               }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(validate_info()) {


                    Intent in=new Intent(MainActivity.this,NewSignup.class);
                    in.putExtra("name",firstname+" "+lastname);
                    in.putExtra("email",email);
                    in.putExtra("password",password);
                    in.putExtra("phone",number);
                    startActivityForResult(in,0);

                }

            }
        });


    }
    public static boolean validate_credentials()
    {
        email=text1.getText().toString();
        password=text2.getText().toString();
        if(email.isEmpty()||!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            text1.setError("Invalid Email");
            return false;
        }
        if(password.isEmpty())
        {
            text2.setError("Password can be empty!!");
            return false;
        }
        return true;
    }
    public static boolean validate_info()
    {

        email=text1.getText().toString();
        password=text2.getText().toString();
        number=text3.getText().toString();
        firstname=text4.getText().toString();
        lastname=text5.getText().toString();

        String regexm="^[0-9]{10}$";
        String regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*(_|[^\\w])).+$";
        boolean valid=true;
        Log.d(TAG,"email:"+email);
        Log.d(TAG,"number"+number);
        Log.d(TAG,password);
        if(firstname.isEmpty())
            text4.setError("First Name cant be empty");
        else text4.setError(null);
        if(lastname.isEmpty())
            text5.setError("Last name cant be empty");
        else
            text5.setError(null);
        if(email.isEmpty()||!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            text1.setError("Invalid Email");
            valid=false;
        }
        else
            text1.setError(null);
        if(number.isEmpty()||number.length()!=10||!number.matches(regexm))
        {
            text3.setError("Invalid Phone");
            valid= false;
        }
        else
            text3.setError(null);
        if(password.isEmpty()||!password.matches(regexp))
        {

            text2.setError("Password must contain one uppercase,one lowercase,1 digit and one special character");
            valid=false;
        }
        else if (password.length()<6)
            text2.setError("Password should be atleast 6 characters long");
        else
            text2.setError(null);
        Log.d(TAG, String.valueOf(valid));

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
