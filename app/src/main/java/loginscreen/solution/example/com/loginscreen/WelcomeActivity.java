package loginscreen.solution.example.com.loginscreen;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {
Cursor cursor;
    DetailsDb db;
    TextView text;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent in=new Intent(WelcomeActivity.this,MainActivity.class);
        in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        startActivity(in);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        String email=getIntent().getStringExtra("email");
        String password=getIntent().getStringExtra("password");
        db=new DetailsDb(this);
        cursor=db.validate(email,password);
        text=(TextView)findViewById(R.id.welcometext);
        cursor.moveToFirst();
        if(cursor!=null)
        {
           String name= cursor.getString(cursor.getColumnIndex("username"));
           String phone=cursor.getString(cursor.getColumnIndex("phone"));
           text.setText("Name:\t"+name+"\nEmail:\t"+email+"\nphone:\t"+phone);

        }
        else
            text.setText("No such user");

    }

}
