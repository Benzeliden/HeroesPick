package com.example.des.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;


public class FragmentDialogResult extends DialogFragment {
    private static final String ARG_PARAM1 = "name";
    private static final String ARG_PARAM2 = "message";


    public static FragmentDialogResult newInstance(String name,String message) {
        FragmentDialogResult fragment = new FragmentDialogResult();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, name);
        args.putString(ARG_PARAM2, message);
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentDialogResult() {
        // Required empty public constructor
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstance){
        String name = getArguments().getString(ARG_PARAM1);
        String message = getArguments().getString(ARG_PARAM2);
        return new AlertDialog.Builder(getActivity())
                .setTitle(name)
                .setMessage(message)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int btn) {
                        Log.d("Dev", "Dialog closed");
                    }
                }).create();
    }


}
