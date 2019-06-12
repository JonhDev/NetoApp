package com.example.lista0404;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PruebaArticulos extends AppCompatActivity {
    Spinner SpinnerPrueba;
    ArrayList<String> ArticuloArray;
    ArrayList<EnlistandoDatos> arrayarticulo;
    SQLiteDatabase db;
    Cursor cursor;
    TextView prueba;
    Button button, button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba_articulos);
        SpinnerPrueba = (Spinner)findViewById(R.id.spinnerPrueba);
        prueba = (TextView)findViewById(R.id.textViewPurebas) ;
        button = (Button)findViewById(R.id.button1000);
        button2 = (Button)findViewById(R.id.button2000);
        AbrirBase();
        Consultar();
        prueba.setText(cursor.getString(3).toString());

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HacerLista();
                Toast.makeText(getApplicationContext(), "Lista hecha", Toast.LENGTH_LONG).show();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ObtenerLista();
                iniciarSpinner();


            }
        });









    }






    private void Consultar() {



        try {

            cursor = db.rawQuery("Select * from ArticuloRefaccion", null);

            cursor.moveToFirst();
            Toast.makeText(getApplicationContext(), "Listo", Toast.LENGTH_LONG).show();
        }
        catch (Exception e){
            prueba.setText("No hay registro" + e.getMessage());
        }







    }

    public void HacerLista(){
        arrayarticulo = new ArrayList<EnlistandoDatos>();
        while (cursor.moveToNext()){
            arrayarticulo.add(new EnlistandoDatos(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6)
            ));
        }
    }


    public void ObtenerLista(){
        ArticuloArray = new ArrayList<String>();
        ArticuloArray.add("Seleccione");
        for(int i = 0;i<arrayarticulo.size();i++)
        {
            ArticuloArray.add(arrayarticulo.get(i).toString());
        }
    }


    private void AbrirBase() {
        try {
            db = openOrCreateDatabase("Refaccionaria", MODE_PRIVATE, null);
            prueba.setText("Base Abierta" );

        } catch (Exception e) {
            prueba.setText("Error al Abrir" + e.getMessage());
        }
    }

    private void iniciarSpinner() {
        ArrayAdapter<String> adapter5;
        adapter5 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,  ArticuloArray);
        SpinnerPrueba.setAdapter(adapter5);
    }
}
