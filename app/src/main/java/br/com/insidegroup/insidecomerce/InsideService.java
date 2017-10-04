package br.com.insidegroup.insidecomerce;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import br.com.insidegroup.insidecomerce.Controles.ControleGeral;

public class InsideService extends Service {
    private Looper mServiceLooper;
    private ServiceHandler mServiceHandler;

    private Intent intencao;
    final static String ACTION = "SERVICO_TEST";
    // Handler that receives messages from the thread
    private final class ServiceHandler extends Handler {
        public ServiceHandler(Looper looper) {
            super(looper);
        }
        int count =0;
        int max = 100;
        int tempo = 10000;
        @Override
        public void handleMessage(Message msg) {




            while (count < max){
                try{

                    Log.i("Script", "Count: "+count+", ----------------------------------------------------------------");

                    ControleGeral.checarDistacia(InsideService.this, count);
                    Thread.sleep(tempo);


                }catch (InterruptedException e){
                    e.printStackTrace();

                }
                count++;

            }

            stopSelf(msg.arg1);
        }
    }

    @Override
    public void onCreate() {
        Log.i("Script", "onCreate");
        HandlerThread thread = new HandlerThread("ServiceStartArguments");
        thread.start();


        mServiceLooper = thread.getLooper();
        mServiceHandler = new ServiceHandler(mServiceLooper);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        this.intencao = intent;
        Log.i("Script", "onStartCommand");

        Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();

        Message msg = mServiceHandler.obtainMessage();
        msg.arg1 = startId;
        mServiceHandler.sendMessage(msg);


        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // We don't provide binding, so return null
        return null;
    }

    @Override
    public void onDestroy() {
        Log.i("Script", "onDestroy");
        Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show();
    }



}