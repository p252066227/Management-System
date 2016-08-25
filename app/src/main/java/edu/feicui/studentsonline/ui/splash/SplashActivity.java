package edu.feicui.studentsonline.ui.splash;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.ButterKnife;
import edu.feicui.studentsonline.R;
import edu.feicui.studentsonline.activity.OnlineActivity;
import edu.feicui.studentsonline.commons.ActivityUtils;

/**
 * Created by Administrator on 2016/8/23.
 */
public class SplashActivity extends AppCompatActivity {

    private ActivityUtils activityUtils;
    private Button btnLogin,btnEnter;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        btnLogin = (Button) findViewById(R.id.btnLogin);
//        btnEnter = (Button) findViewById(R.id.btnEnter);
//        btnEnter.setOnClickListener(listener);
        btnLogin.setOnClickListener(listener);
        activityUtils = new ActivityUtils(this);
        ButterKnife.bind(this);

    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btnLogin:
                    activityUtils.startActivity(OnlineActivity.class);
                    finish();
                    break;
//                case R.id.btnEnter:
//                    activityUtils.startActivity(MainActivity.class);
//                    finish();
//                    break;
            }
        }
    };
}
