package com.mrc.appcadernofiado.util;

import android.content.Context;
import android.telephony.SmsManager;
import android.widget.Toast;

import com.mrc.appcadernofiado.model.Cliente;

public class SMSUtil {
    public static void enviarSMS(String telefone, String texto, Context context) {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(telefone, null, texto, null, null);
    }
}
