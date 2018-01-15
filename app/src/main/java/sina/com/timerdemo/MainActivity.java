package sina.com.timerdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;

import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {

    private Chronometer chronometer;
    private SimpleDateFormat sd;
    private SimpleDateFormat sdf;
    private long time;
    private Button btn;
    private TextViewTimer timer;
    private EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.btn);
        et = (EditText) findViewById(R.id.et);
        timer = (TextViewTimer) findViewById(R.id.timer);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String time = et.getText().toString().trim();
                if (TextUtils.isEmpty(time)){
                    return;
                }
                timer.setTimes(timer,time);
            }
        });





}



}
