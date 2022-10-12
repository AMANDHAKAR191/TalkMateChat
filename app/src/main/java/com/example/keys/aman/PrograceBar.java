package com.example.keys.aman;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

import com.example.keys.R;

/* TODO Checked on 12/10/2022
    All is ok
 */
public class PrograceBar {
    final Activity activity;
    AlertDialog dialog;

    public PrograceBar(Activity thisActivity){
        activity = thisActivity;
    }
    public void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.prograce_bar_layout,null));
        dialog = builder.create();
        dialog.show();
    }
    public void dismissbar(){
        dialog.dismiss();
    }
}
