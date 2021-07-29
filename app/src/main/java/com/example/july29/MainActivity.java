package com.example.july29;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView mt1;
    private Button mb1;
    LocalBroadcastManager localBroadcastManager;
    private Reciever reciever;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mt1=findViewById(R.id.textView);
        mb1=findViewById(R.id.button);
        localBroadcastManager=LocalBroadcastManager.getInstance(this);
        Register();

        mb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent("com.my");
                intent.putExtra("Key","Hello Masai School");
                localBroadcastManager.sendBroadcast(intent);
//                mt1.setText(intent.getStringExtra("Key"));
            }
        });

    }
    private void Register(){
        reciever=new Reciever();
        IntentFilter intentFilter=new IntentFilter("com.my");
        localBroadcastManager.registerReceiver(reciever,intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(reciever);
    }

    private class Reciever extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
           // Toast.makeText(MainActivity.this,intent.getStringExtra("Key"),Toast.LENGTH_SHORT).show();
            if(intent!=null){
                String data=intent.getStringExtra("Key");
                mt1.setText(data);
            }

        }
    }

}