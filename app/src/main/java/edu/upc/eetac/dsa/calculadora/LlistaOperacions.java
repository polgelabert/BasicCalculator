package edu.upc.eetac.dsa.calculadora;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

    String OperacionsSBuffer;           //String Buffer inicial de totes les operacions separades per , .
    String[] OperacionsArray;           // Array[String] de les operacions sense la , .
    ListView listView;                  // llista del historial a mostrar.
    Intent intent, intent1;
    Intent tractamentOperacio;
    String operacio;
    String[] historialVector;


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
                startActivityForResult(tractamentOperacio,2);
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

    protected void onActivityResult(int requestCode, int resultCode, Intent intent){

        super.onActivityResult(requestCode, resultCode, intent);
        boolean v = true;
        System.out.println(v);
        v = getIntent().getExtras().getBoolean("vaciar");
                //getBoolean("vaciar");
        System.out.println(v);

        if(requestCode == 100){
            System.out.println(v);
            if(v==true){
                for(String s : historialList)
                    System.out.println(s);

                historialList.clear();          // Neteja historial.

                for(String s : historialList)
                    System.out.println(s);

                //DisplayListHistory();           // Tornem a mostrar l'historial buit.

                DisplayListHistory();

            }
        }

        /*if(requestCode == 2)
            if(resultCode == RESULT_OK){

                operacio =  getIntent().getExtras().getString("modificar");
                historialList.remove(operacio);          // numOperacio és l'índex de la llista d'operacions que apunta a la operació modificada.

                //intent.putExtra("modificar", operacio);
               //setResult(3, intent);
                //finish();
            }

            if(resultCode == RESULT_CANCELED){
                operacio =  getIntent().getExtras().getString("borrar");
                historialList.remove(operacio);
                DisplayListHistory();
            }
        {
            String result=intent.getExtras().getString("modify");
            intent1.putExtra("modificar", result);
            setResult(1, intent1);
            finish();
        }*/


        /*if(resultCode == 1){

            String result = intent.getExtras().getString("delete");
            for (int i = 0; i < historialList.size(); i++){

                if(historialList.get(i).equals(result)) historialList.remove(i);
            }

            ArrayAdapter adapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, historialList);
            ListView listView=(ListView)findViewById(R.id.llistaHistorial);
            listView.setAdapter(adapter);
        }
        else if(resultCode==2)
        {
            String result=intent.getExtras().getString("modify");
            intent1.putExtra("modify",result);
            setResult(1,intent1);
            finish();
        }*/
    }

    /**
     * Transcriu el String Buffer OperacionsSBuffer a una List<Strings> historialList que conté les
     * operacions
     */
    private void FromBuffer2History(){

        // Posem el String Buffer (OperaiconsSBuffer) a List
        //OperacionsSBuffer = getIntent().getExtras().getString("historial");
        //OperacionsArray = getIntent().getExtras().getStringArray("historial");
        //List<String> listahistorial = (List<String>) getIntent().getExtras().get("historial");
        historialVector = (String[]) getIntent().getExtras().get("historial");
        //System.out.println(listahistorial.get(0).toCharArray());
                //OperacionsSBuffer.split(",");               // OperacionsArray = vector de totes les operacions (sense ,)


        historialList.addAll(Arrays.asList(historialVector));


    }

    public void borrarHistorial(View view) {
        //throw new Exception ("Llista d'historial buida.")
        try {
            if (historialList.size() == 0) ;
            intent = new Intent(LlistaOperacions.this, ConfirmarEsborrar.class);
            startActivity(intent);


        } catch (Exception e){
            //e.getStackTrace();
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    public void atras(View view){
        Intent intent1 = getIntent();
        StringBuffer list = new StringBuffer();
        for (int i = 0; i< historialList.size(); i++){
            list.append(historialList.get(i));
            if(i< historialList.size()-1)
            list.append(",");
        }
        intent1.putExtra("lista",list.toString());
        setResult(RESULT_OK,intent1);
        finish();
    }
}