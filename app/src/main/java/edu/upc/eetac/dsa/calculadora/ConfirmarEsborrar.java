package edu.upc.eetac.dsa.calculadora;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Pol on 20/11/2017.
 */

public class ConfirmarEsborrar extends AppCompatActivity {

    Button si, no;
    Intent intent;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_esborrar);


        no = (Button) findViewById(R.id.no);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = getIntent();
                setResult(RESULT_CANCELED, intent);         // Result_cancelled = 0
                finish();
            }
        });

        si = (Button) findViewById(R.id.si);
        si.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(ConfirmarEsborrar.this, LlistaOperacions.class);
                intent.putExtra("vaciar",true);
                //intent = getIntent().putExtra("vaciar",true);
                //setResult(RESULT_OK, intent);               // Result_ok = -1
                finish();
            }
        });


    }


    /*public void noBorrar(View view){
        onBackPressed();
    }

    public void siBorrar(View view){

        intent = new Intent(ConfirmarEsborrar.this, PantallaPrincipal.class);
        startActivity(intent);
        finish();
    }*/



}
