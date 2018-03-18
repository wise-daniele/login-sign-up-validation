package loginscreen.solution.example.com.loginscreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class LoginWelcomeActivity extends AppCompatActivity {

    private TextView mTvName;
    private TextView mTvEmail;
    private TextView mTvPhone;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent in=new Intent(LoginWelcomeActivity.this,MainActivity.class);
        in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        startActivity(in);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_welcome);

        mTvName = (TextView) findViewById(R.id.tv_name);
        mTvEmail = (TextView) findViewById(R.id.tv_email);
        mTvPhone = (TextView) findViewById(R.id.tv_phone);
        Bundle bundle = getIntent().getExtras();

        mTvName.setText(bundle.getString("name"));
        mTvEmail.setText(bundle.getString("email"));
        mTvPhone.setText(bundle.getString("phone"));
    }
}
