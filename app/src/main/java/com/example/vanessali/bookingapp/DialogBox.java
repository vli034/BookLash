package com.example.vanessali.bookingapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.widget.CalendarView;

public class DialogBox extends AppCompatDialogFragment {
    private DialogBoxListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Confirm Date")
                .setMessage("By clicking yes you are confirming the date of your appointment.")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onYesClicked();


                    }
                });
       return builder.create();
    }

    // Interface is created for communication between dialog box activity
    public interface DialogBoxListener{
        void onYesClicked();

    }
    // We've created a listener and set it to the activity where it shows up, using the override
    //onAttach method. Letting Context refer to the  activity  and the DialogBox listener

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (DialogBoxListener) context; //context is our activity
        } catch (ClassCastException e ){
            throw new ClassCastException(context.toString()+
                    "error, implement dialog listener in activity");
            /*if we forget to implement the listener in our activity this exception
            * will throw */

        }
    }
}
