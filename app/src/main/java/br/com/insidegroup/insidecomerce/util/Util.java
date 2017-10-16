package br.com.insidegroup.insidecomerce.util;


import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;

import br.com.insidegroup.insidecomerce.MainActivity;
import br.com.insidegroup.insidecomerce.R;

public class Util {


    public  static void showNotification(Context c, String str){

        NotificationCompat.Builder nt;
        nt = new NotificationCompat.Builder(c);
        nt.setAutoCancel(true);

        nt.setTicker("ticker");

        nt.setSmallIcon(R.drawable.logo);

      //  Vibrator v = (Vibrator)c.getSystemService(Context.VIBRATOR_SERVICE);
      //  v.vibrate(300);


        nt.setContentTitle("Hellow World Notification android!");
        nt.setContentText(str);

        nt.setWhen(System.currentTimeMillis());

        Intent intent = new Intent(c, MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(c, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        nt.setContentIntent(pIntent);

        NotificationManager notificationManager = (NotificationManager) c.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(1, nt.build());
    }


    public static void ExibirMensagemNaTela(Activity act, String msg) {

        Toast toast = Toast.makeText(act, msg, Toast.LENGTH_LONG);
        toast.show();

    }






}
