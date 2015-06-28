package com.example.des.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;


public class FragmentDialogResult extends DialogFragment {
    private static final String ARG_PARAM1 = "name";


    public static FragmentDialogResult newInstance(String name) {
        FragmentDialogResult fragment = new FragmentDialogResult();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, name);
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentDialogResult() {
        // Required empty public constructor
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstance){
        String name = getArguments().getString(ARG_PARAM1);
        return new AlertDialog.Builder(getActivity())
                .setTitle(name)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int btn){

                    }
                }).create();
    }


}
