package com.example.ncdexspot10341.boundserviceproject;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    BoundService boundService;
    boolean isBind = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);

        Intent intent = new Intent(this,BoundService.class);
        bindService(intent,serviceConnection,Context.BIND_AUTO_CREATE);
    }

    //methods called on click of buttons which gets messages from service.
    public void getFirstServiceMessage(View view){
            String message;
            message = boundService.getFirstMessage();
            textView.setText(message);
    }

    public void getSecondServiceMessage(View view){
        String message;
        message = boundService.getSecondMessage();
        textView.setText(message);
    }

    //serviceconnection created to pass it in bindservice for binding.
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //Created an instance of local service class whose getService method return boundService.
            //Through bound service, we can call the public methods of service.
            BoundService.LocalService localService = (BoundService.LocalService)service;
            boundService = localService.getService();
            isBind = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBind = false;
        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        if(isBind){
            //passed the service connection to unbind the service
            unbindService(serviceConnection);
            isBind = false;
            Log.d("Jogu","Service stopped");
        }
    }
}
