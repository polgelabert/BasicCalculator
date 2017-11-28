package edu.upc.eetac.dsa.calculadora;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.text.TextUtils;
import android.widget.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;

public class PantallaPrincipal extends AppCompatActivity {
    List<String> historialOperacions =new ArrayList<String>();
    String[] historialVector;
    String[] historialArray;
    List<String> historialList;
    int i =999;
    String tag="Events";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_principal);
        Log.d(tag,"Event onCreate");
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
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
            if(i==999){
                String operacio = (historialOperacions.size()+1 + " : " + num1 + " " + spin + " " + num2 + " = " + resultat);
                historialOperacions.add(operacio);

            }




            text3.setText(String.valueOf(resultat));

        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void orderLista() {
        int k = 0;
        String op;
        for(String s: historialOperacions){
            op = k + " : " + s;
            historialOperacions.remove(k);
            historialOperacions.add(op);
            k = k + 1;
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
            if (historialOperacions.size() == 0) throw new Error("No hi ha elements a l'historial");



            historialVector = new String[historialOperacions.size()];
            historialVector = historialOperacions.toArray(historialVector);

            // Intent que relaciona amb quina Pantalla anira.
            Intent intent = new Intent(PantallaPrincipal.this, LlistaOperacions.class);
            intent.putExtra("historial", historialVector);
            startActivityForResult(intent,100);

        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    protected void onActivityResult(int requestCode,int resultCode,Intent intent){
        super.onActivityResult(requestCode,resultCode,intent);
        EditText text1 =(EditText) findViewById(R.id.num1);
        EditText text2 =(EditText) findViewById(R.id.num2);
        EditText text3 =(EditText) findViewById(R.id.resultat);

        //Quan s'ha borrat l'historial
        if(resultCode == 2){
            boolean resultado = intent.getExtras().getBoolean("historialBorrado");

            if(resultado){
                historialOperacions.clear();
                text1.setText("");
                text2.setText("");
                text3.setText("");
            }

        }

        //Quan s'acciona la creu.
        if(resultCode ==4 ){
            text1.setText("");
            text2.setText("");
            text3.setText("");

        }


        if(resultCode == 3){
            String operacio = intent.getExtras().getString("modificar2");
            System.out.println("size historial ppal es: " + historialOperacions.size());
            String[] as = operacio.split(" ");
            i = Integer.parseInt(as[0]);
            text1.setText(as[2]);
            text2.setText(as[4]);
            text3.setText("");

            historialOperacions.remove(operacio);
            System.out.println("size historial ppal2 es: " + historialOperacions.size());
        }

        if (resultCode == 5){
            String operacio = intent.getExtras().getString("modificar3");
            historialOperacions.remove(operacio);
            for(int j = 0; j < historialOperacions.size(); j++){
                String[] as = historialOperacions.get(j).split(" ");
                as[0] = Integer.toString(j+1);
                String op = as[0] + " : " + as[2] + " " + as[3] + " " + as[4] + " = " + as[6];
                System.out.println(j + "se ha hecho a " + op);
                historialOperacions.remove(j);
                historialOperacions.add(op);
            }

            for(String s:historialOperacions)
                System.out.println(s);

            text1.setText("");
            text2.setText("");
            text3.setText("");
        }


    }
}
