package cat.dam.pau.trucadesenviament_sms;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public EditText et_message;
    public TextView tv_num;

    public Button btn_1;
    public Button btn_2;
    public Button btn_3;
    public Button btn_4;
    public Button btn_5;
    public Button btn_6;
    public Button btn_7;
    public Button btn_8;
    public Button btn_9;
    public Button btn_sms;
    public Button btn_call;
    public Button btn_del;

    final int SEND_SMS_PERMISSION_CODE = 111;

    public int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assignElements();

        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_sms.setOnClickListener(this);
        btn_call.setOnClickListener(this);
        btn_del.setOnClickListener(this);

    }

    public void onClick(View v){
        switch (v.getId()){

            case (R.id.btn_1): if(count<9) setNum("1");  else toastLimit();  break;
            case (R.id.btn_2): if(count<9) setNum("2");  else toastLimit();  break;
            case (R.id.btn_3): if(count<9) setNum("3");  else toastLimit();  break;
            case (R.id.btn_4): if(count<9) setNum("4");  else toastLimit();  break;
            case (R.id.btn_5): if(count<9) setNum("5");  else toastLimit();  break;
            case (R.id.btn_6): if(count<9) setNum("6");  else toastLimit();  break;
            case (R.id.btn_7): if(count<9) setNum("7");  else toastLimit();  break;
            case (R.id.btn_8): if(count<9) setNum("8");  else toastLimit();  break;
            case (R.id.btn_9): if(count<9) setNum("9");  else toastLimit();  break;
            case (R.id.btn_sms): sendSms(); break;
            case (R.id.btn_call): if(count==9) call(); else toastInvalidTlf(); break;
            case (R.id.btn_del): tv_num.setText(""); count=0;  break;

        }
    }

    public void setNum(String num){
        if (count == 0){
            tv_num.setText("");
        }else{
            if (count % 3 == 0) {
                tv_num.setText(tv_num.getText().toString() + "-");
            }
        }

        tv_num.setText(tv_num.getText().toString() + num);
        count++;
    }

    public void sendSms(){
        if(et_message.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),R.string.sms_empty,Toast.LENGTH_SHORT).show();
        }else{
            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.SEND_SMS},SEND_SMS_PERMISSION_CODE);
            }else{
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(tv_num.getText().toString(),null,et_message.getText().toString(),null,null);
                Toast.makeText(getApplicationContext(),R.string.sms_sent,Toast.LENGTH_SHORT).show();
                et_message.setText("");
            }
        }
    }

    public void call(){
        String tlf = tv_num.getText().toString();
        tlf = tlf.replace("-","");
        System.out.println(tlf);
        if(!TextUtils.isEmpty(tlf)){
            String dial = "tel:" + tlf;
            startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));
        }else {
            toastInvalidTlf();
        }
    }

    public void toastInvalidTlf(){
        Toast.makeText(getApplicationContext(),R.string.invalid_num,Toast.LENGTH_SHORT).show();
    }

    public void toastLimit(){
        Toast.makeText(getApplicationContext(),R.string.num_limit,Toast.LENGTH_SHORT).show();
    }

    public void assignElements(){
        et_message = (EditText) findViewById(R.id.et_message);
        tv_num = (TextView) findViewById(R.id.tv_num);

        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_4 = (Button) findViewById(R.id.btn_4);
        btn_5 = (Button) findViewById(R.id.btn_5);
        btn_6 = (Button) findViewById(R.id.btn_6);
        btn_7 = (Button) findViewById(R.id.btn_7);
        btn_8 = (Button) findViewById(R.id.btn_8);
        btn_9 = (Button) findViewById(R.id.btn_9);
        btn_sms = (Button) findViewById(R.id.btn_sms);
        btn_call = (Button) findViewById(R.id.btn_call);
        btn_del = (Button) findViewById(R.id.btn_del);
    }
}