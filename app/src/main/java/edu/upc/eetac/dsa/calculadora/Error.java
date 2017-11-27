package edu.upc.eetac.dsa.calculadora;

import android.content.Context;
import android.widget.Toast;
import static android.widget.Toast.LENGTH_SHORT;

/**
 * Created by Pol on 24/11/2017.
 */

public class Error extends Exception {

    public Error( String msg) {
        super(msg);
        //Toast.makeText(context, msg , Toast.LENGTH_SHORT).show();
    }

}
