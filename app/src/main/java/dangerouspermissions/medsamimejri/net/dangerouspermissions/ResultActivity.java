package dangerouspermissions.medsamimejri.net.dangerouspermissions;

import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        tv=(TextView)findViewById(R.id.textView);

        if(getIntent().getExtras()!=null){

            String msg=getIntent().getExtras().getString("message");
            tv.setText(msg);
        }
    }
}
