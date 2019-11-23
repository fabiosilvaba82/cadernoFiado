package com.mrc.appcadernofiado.permissoes;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class Permissao {
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;

    public static boolean checkAndRequestPermissions(Context getContext, Activity getActivity) {
        int permissionSendMessage = ContextCompat.checkSelfPermission(getContext, Manifest.permission.SEND_SMS);
        int locationPermission = ContextCompat.checkSelfPermission(getContext, Manifest.permission.ACCESS_FINE_LOCATION);
        List<String> listPermissionsNeeded = new ArrayList<>();

        if (locationPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION);
            Toast.makeText(getContext, "1", Toast.LENGTH_SHORT).show();
        }
        if (permissionSendMessage != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.SEND_SMS);
            Toast.makeText(getContext, "2", Toast.LENGTH_SHORT).show();
        }
        if (listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(getActivity, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
            Toast.makeText(getContext, "3", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
