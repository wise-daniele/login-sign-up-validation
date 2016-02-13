package loginscreen.solution.example.com.loginscreen;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {
Cursor cursor;
    DetailsDb db;
    TextView tv_name,tv_email,tv_phone;

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
        setContentView(R.layout.content_welcome);
        String email=getIntent().getStringExtra("email");
        String password=getIntent().getStringExtra("password");
        db=new DetailsDb(this);
        cursor=db.validate(email,password);
        tv_name=(TextView)findViewById(R.id.tv_name);
        tv_email=(TextView)findViewById(R.id.tv_email);
        tv_phone=(TextView)findViewById(R.id.tv_phone);
        cursor.moveToFirst();
        if(cursor!=null)
        {
           String name= cursor.getString(cursor.getColumnIndex("username"));
           String phone=cursor.getString(cursor.getColumnIndex("phone"));
           tv_name.setText(name);
           tv_email.setText(email);
           tv_phone.setText(phone);

        }

    }

}
