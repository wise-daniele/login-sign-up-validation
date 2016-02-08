package loginscreen.solution.example.com.loginscreen;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class NewSignup extends AppCompatActivity {
DetailsDb db;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent in=new Intent(NewSignup.this,MainActivity.class);
        in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        startActivity(in);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_signup);
        String name=getIntent().getStringExtra("name");
        String email=getIntent().getStringExtra("email");
        String password=getIntent().getStringExtra("password");
        String phone=getIntent().getStringExtra("phone");

        TextView text=(TextView)findViewById(R.id.textView);
        text.setText("Hi "+name+" you have signed up successfully");
        db=new DetailsDb(this);
        ContentValues values=new ContentValues();
        values.put(DetailsDb.USERNAME,name);
        values.put(DetailsDb.EMAIL,email);
        values.put(DetailsDb.PASSWORD,password);
        values.put(DetailsDb.PHONE,phone);
        Log.d("DetailsDb","Lets Insert");
        db.insert(values);


    }

}
