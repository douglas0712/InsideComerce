package br.com.insidegroup.insidecomerce;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import br.com.insidegroup.insidecomerce.InsideService;

public class BroadCastReceive extends BroadcastReceiver{


    @Override
    public void onReceive(Context context, Intent intent) {


       intent = new Intent(context, InsideService.class);
       context.startService(intent);

    }
}
