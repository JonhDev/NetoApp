package com.example.lista0404;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class AgregarCampo extends AppCompatActivity {
    Cursor cursor;
    SQLiteDatabase db;
    EditText CampoMarca, CampoModelo, CampoTipo;
    Spinner Marcas;
    RadioButton Marca, Modelo, Tipo;
    Button regresar, agregar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_campo);
        asociarvistas();
        AbrirBaseDeDatos();
        db.execSQL("create table if not exists  Marcas (NombreMarca text)");
        db.execSQL("create table if not exists  Modelos (Marca text, Modelo text)");
         db.execSQL("create table if not exists  Tipo (NombreTipo text)");


          HacerListaMarcas();

/*
        ArrayAdapter<String> adapter5;
        adapter5 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, MarcaArray);
        Marcas.setAdapter(adapter5);
*/



        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Marca.isChecked() == true) {

                    agregarmarca();
                }
                if (Modelo.isChecked() == true) {
                    agregarmodelo();

                }

                if (Tipo.isChecked() == true) {

                    agregartipo();
                }
            }
        });

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent llamarmenu = new Intent(getApplicationContext(), AgregarArticulo.class);
                startActivity(llamarmenu);
            }
        });
    }

    private void AbrirBaseDeDatos() {
        try {
            db = openOrCreateDatabase("Refaccionaria002", MODE_PRIVATE, null);


        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error ", Toast.LENGTH_LONG).show();
        }
    }


    ArrayList<String> MarcaArray;
    ArrayList<EnlistarMarca> arrayaMarca;

    private void HacerListaMarcas() {
        cursor = db.rawQuery("Select * from Marcas", null);
        cursor.moveToFirst();
        arrayaMarca = new ArrayList<EnlistarMarca>();
        while (cursor.moveToNext()) {
            arrayaMarca.add(new EnlistarMarca(
                    cursor.getString(0)
            ));
        }

        MarcaArray = new ArrayList<>();
        for (int i = 0; i < arrayaMarca.size(); i++) {
            MarcaArray.add(arrayaMarca.get(i).toString());
        }



    }

    private void agregarmodelo() {
        try {
            db.execSQL("insert into  Modelos " +
                    "values( '" +Marcas.getSelectedItemPosition()+"','"+Modelo.getText()+"')");


            Toast.makeText(getApplicationContext(), "Campo creado ", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error ", Toast.LENGTH_LONG).show();
        }

    }

    private void agregartipo() {
        try {
            db.execSQL("insert into  Tipo " +
                    "values( '" +Tipo.getText()+"')");


            Toast.makeText(getApplicationContext(), "Campo creado ", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error ", Toast.LENGTH_LONG).show();
        }



    }
        private void agregarmarca () {


            try {
                db.execSQL("insert into  Marcas " +
                        "values( '" +Marca.getText()+"')");


                Toast.makeText(getApplicationContext(), "Campo creado ", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Error ", Toast.LENGTH_LONG).show();
            }



        }



        private void asociarvistas () {
            CampoMarca = (EditText) findViewById(R.id.editTextMarcaCampo);
            CampoModelo = (EditText) findViewById(R.id.editTextModeloCampo);
            CampoTipo = (EditText) findViewById(R.id.editTextRefaccionTipoCampo);
            Marca = (RadioButton) findViewById(R.id.radioButtonMarca);
            Modelo = (RadioButton) findViewById(R.id.radioButtonModelo);
            Tipo = (RadioButton) findViewById(R.id.radioButtonTipo);
            Marcas = (Spinner) findViewById(R.id.spinnerTipoRefaccion);
            regresar = (Button) findViewById(R.id.buttonRegresarAArticulos);
            agregar = (Button) findViewById(R.id.buttonAgregarCampo);
        }


}

