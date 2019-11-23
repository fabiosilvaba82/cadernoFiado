package com.mrc.appcadernofiado.util;

import android.util.Log;

import java.util.Calendar;

public class CodUtil {

    public static Integer gerarNimeroFiscal() {
        Calendar c = Calendar.getInstance();
        int dia = c.getTime().getDay();
        int mes = c.getTime().getMonth();
        int ano = c.getTime().getYear();

        int hora = c.getTime().getHours();
        int min = c.getTime().getMinutes();
        int sec = c.getTime().getSeconds();

        StringBuilder sb = new StringBuilder();
        sb.append(ano).append(hora).append(min).append(sec);
        Log.i("HHH", sb.toString());
        return new Integer(sb.toString());
    }
}
