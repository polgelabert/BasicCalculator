package edu.upc.eetac.dsa.calculadora;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Pol on 20/11/2017.
 */

public class TractamentOperacio extends AppCompatActivity {


    String operacioATractar;
    Button modificar, borrar;
    Intent intent;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.atctivity_tractament_operacio);

        operacioATractar = getIntent().getExtras().getString("operacioATractar");

        EditText text1 =(EditText) findViewById(R.id.modificacioOperacioText);
        text1.setText(operacioATractar);


        modificar = (Button) findViewById(R.id.modificarBtn);
        modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = getIntent();
                intent.putExtra("modificar", operacioATractar);
                setResult(RESULT_OK, intent);         // Result_ok = -1
                finish();
            }
        });

        borrar = (Button) findViewById(R.id.borrarBtn);
        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = getIntent();
                intent.putExtra("borrarOp", operacioATractar);
                setResult(RESULT_CANCELED, intent);         // Result_canceled = 0
                finish();
            }
        });


    }



}
