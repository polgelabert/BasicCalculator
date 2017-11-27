package edu.upc.eetac.dsa.calculadora;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.text.TextUtils;
import android.widget.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;

public class PantallaPrincipal extends AppCompatActivity {
    List<String> historialOperacions =new ArrayList<String>();
    String[] historialVector;
    String[] historialArray;
    String tag="Events";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_principal);
        Log.d(tag,"Event onCreate");
    }


    public void mostrarResultat(View view){
        try {
            EditText text1 =(EditText) findViewById(R.id.num1);
            EditText text2 =(EditText) findViewById(R.id.num2);
            EditText text3 =(EditText) findViewById(R.id.resultat);
            Spinner spinner =(Spinner) findViewById(R.id.spinner);
            String spin = spinner.getSelectedItem().toString();

            // Comprova que cap dels dos nombres sigui null.
            checkNombreInvalid(text1);
            checkNombreInvalid(text2);

            int num1 = Integer.parseInt(text1.getText().toString());
            int num2 = Integer.parseInt(text2.getText().toString());
            int resultat=0;


            switch (spin){
                case "+":
                    resultat = num1 + num2;
                    break;

                case "-":
                    resultat = num1 - num2;
                    break;

                case "*":
                    resultat = num1 * num2;
                    break;

                case "/":
                    if(num2!=0){

                        resultat = num1/num2;
                        if((num1%num2)!= 0) throw new Error("La calculadora només mostra resultats enters.");
                    }
                    else
                        throw new Error("Impossible dividir entre 0.");
                    break;
            }

            String operacio = ((historialOperacions.size()+1) + ": " + num1 + " " + spin + " " + num2 + " = " + resultat);
            historialOperacions.add(operacio);
            text3.setText(String.valueOf(resultat));
            //System.out.println(historialOperacions.get(0).toCharArray());
            historialVector = new String[historialOperacions.size()];
            historialVector = historialOperacions.toArray(historialVector);
            for(String s : historialVector)
                System.out.println(s);

        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Comprova si el nombre introduit és null.
     * @param text És un nombre introduit pel usuari
     * @throws Error Toast indicant el motiu de l'error.
     */
    private void checkNombreInvalid(EditText text) throws Error {
        if(TextUtils.isEmpty(text.getText())) throw new Error( "Algun dels dos nombres noBorrar és vàlid.");
    }

    /**
     * Reseteja els valors dels camps text1, text2 i text3 a 0.
     * @param view
     */
    public void c(View view){
        EditText text1 =(EditText) findViewById(R.id.num1);
        EditText text2 =(EditText) findViewById(R.id.num2);
        EditText text3 =(EditText) findViewById(R.id.resultat);
        text1.setText("0");
        text2.setText("0");
        text3.setText("0");
    }

    public void mostrarHistorial(View view){
        try {
           // historialArray = new String[historialOperacions.size()];
            //historialOperacions.toArray().toString();

            //StringBuffer OperacionsSBuffer = new StringBuffer();

            //if(historialOperacions.size() == 0) throw new Error ("L'historialList d'operaciones està buit.");

            // Emplenar el buffer (OperacionsSBuffer) amb el historialList de operacions.
            //for (int i = 0; i < historialOperacions.size(); i++){
            //    OperacionsSBuffer.append(historialOperacions.get(i));
            //    if(i< historialOperacions.size()-1) OperacionsSBuffer.append(",");
            //}

            // Intent que relaciona a que Pantalla anira.
            Intent intent = new Intent(PantallaPrincipal.this, LlistaOperacions.class);
            //intent.putExtra("OperacionsSBuffer", OperacionsSBuffer.toString());     // traspas de paràmetre serialitzable
            //intent.putExtra("historial", historialOperacions.toArray());     // traspas de paràmetre serialitzable
            intent.putExtra("historial", historialVector);
            startActivityForResult(intent,100);
        }
        catch (Exception e){
            e.printStackTrace();
            //Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();

        }
    }

    protected void onActivityResult(int requestCode,int resultCode,Intent intent){
        super.onActivityResult(requestCode,resultCode,intent);
        EditText text1 =(EditText) findViewById(R.id.num1);
        EditText text2 =(EditText) findViewById(R.id.num2);
        EditText text3 =(EditText) findViewById(R.id.resultat);

        if(resultCode == 3){
            String operacio = intent.getExtras().getString("modificar");
            String[] as = operacio.split(" ");
            text1.setText(as[1]);
            text2.setText(as[3]);
            //text3.setText(as[5]);
        }

        else if(resultCode == RESULT_OK){
            historialOperacions = new ArrayList<String>();
            String histo = intent.getExtras().getString("lista");
            String[] list=histo.split(",");
            String s,j;
            for (int i=1;i<=list.length;i++){
                    s=list[i-1];
                    j=(i)+s.substring(1,s.length());
                    historialOperacions.add(j);
            }
            text1.setText("0");
            text2.setText("0");
            text3.setText("0");
        }
    }
}
