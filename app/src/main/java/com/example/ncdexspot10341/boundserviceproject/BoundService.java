package com.example.ncdexspot10341.boundserviceproject;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class BoundService extends Service {

    //created IBinder instance to return it from onBind method
    private final IBinder mBinder = new LocalService();
    public BoundService() {
    }


    public class LocalService extends Binder{
        //method to provide an interface to communicate between activity and service.
        BoundService getService(){
            return BoundService.this;
        }
    }

    //define two public methods to call from activity on methods through service.
    public String getFirstMessage(){
        return "Hello All";
    }

    public String getSecondMessage(){
        return "Bound Service Example";
    }

    @Override
    public void onCreate() {
        Toast.makeText(this,"Service Created",Toast.LENGTH_SHORT).show();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
       return mBinder;
    }
}
