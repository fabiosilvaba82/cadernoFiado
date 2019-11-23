package com.mrc.appcadernofiado.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtil {
    public static Date converterData(String dataCriacao) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:MM:ss");
        try {
            return sdf.parse(dataCriacao);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
