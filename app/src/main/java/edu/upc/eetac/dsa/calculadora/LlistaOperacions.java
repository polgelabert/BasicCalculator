package edu.upc.eetac.dsa.calculadora;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Pol on 20/11/2017.
 */

public class LlistaOperacions extends AppCompatActivity {

    List<String> historialList = new ArrayList<>();     // Llista final del historial d'operacions

    ListView listView;                  // llista del historial a mostrar.
    Intent intent, intent1;
    Intent tractamentOperacio;
    String operacio;
    String[] historialVector;

    Button cerrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_llista_operacions);

        FromBuffer2History();

        DisplayListHistory();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                tractamentOperacio = new Intent(LlistaOperacions.this, TractamentOperacio.class);
                tractamentOperacio.putExtra("operacioATractar", historialList.get(i));
                startActivityForResult(tractamentOperacio,200);
            }
        });


        cerrar = (Button) findViewById(R.id.cerrar);
        cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                //if(historialVector == null) finish();
                intent.putExtra("historial2Main", historialVector);
                setResult(4, intent);
                finish();
            }
        });

    }

    /**
     * Muestra la lista del historial de operaciones realizadas.
     */
    private void DisplayListHistory() {
        ArrayAdapter adapter= new ArrayAdapter<String> (this,android.R.layout.simple_list_item_1, historialList);
        listView = (ListView) findViewById(R.id.llistaHistorial);
        listView.setAdapter(adapter);
    }


    /**
     * Transcriu el String Buffer OperacionsSBuffer a una List<Strings> historialList que conté les
     * operacions
     */
    private void FromBuffer2History(){
        try{
            historialVector = (String[]) getIntent().getExtras().get("historial");
            historialList.addAll(Arrays.asList(historialVector));

        } catch (Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    public void borrarHistorial(View view) {
        try {

            if(historialList.size() == 0) throw new Error ("L'historial ja és buit!");
            intent = new Intent(LlistaOperacions.this, ConfirmarEsborrar.class);
            startActivityForResult(intent,100);

        } catch (Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }


    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode, resultCode, intent);

        boolean resultado = intent.getExtras().getBoolean("vaciarHistorial");

        if(requestCode == 100 && resultado){

            historialList.clear();          // Neteja historial.

            intent = getIntent();
            intent.putExtra("historialBorrado", true);
            setResult(2, intent);
            finish();

        }

        if(requestCode == 200){
            if(resultCode==RESULT_OK){
                String operacion = intent.getExtras().getString("modificar");

                historialList.remove(operacion);          // numOperacio és l'índex de la llista d'operacions que apunta a la operació modificada.
                System.out.println("size historial lista oeraciones es: " + historialList.size());
                intent = getIntent();
                intent.putExtra("modificar2", operacion);
                setResult(3, intent);
                finish();
            }
            if(resultCode==RESULT_CANCELED){
                String operacion = intent.getExtras().getString("borrarOp");
                historialList.remove(operacion);
                System.out.println("size historial lista oeraciones remove modificar es: " + historialList.size());

                intent = getIntent();
                intent.putExtra("modificar3", operacion);
                setResult(5, intent);
                finish();
            }


        }




    }



}