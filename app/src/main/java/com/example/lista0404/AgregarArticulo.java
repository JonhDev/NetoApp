package com.example.lista0404;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.MediaStore;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class AgregarArticulo extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    SQLiteDatabase db;
    Button ButtonIraMenu, ButtonAgregarRefaccion, Campo;
    Spinner SpinnerMarca,SpinnerModelo, SpinnerCanidad, SpinnerTipo;
    EditText EditTexNombreRefaccion, EditTextPrecioRefaccion, EditTextDescripcion;
    ImageView ImagenFoto;

            //1 audi
            String audi [] = {"A3","A5","A7","A8","Q2"};
            //2 Ford
            String ford [] =  {"FOCUS","FIESTA","GALAXY","GT","EDGE"};
            //3 BMW
            String BMW [] =  {"GRAN TURISMO","BERLINA","CABRIO","X3","X2"};
            //4 chevrolet
            String chevrolet [] = {"AVEO","SONIC","MALIBU","CAMARO","CRUZE"};
            //5 Nissan
            String Nissan [] =  {"SENTRA","TIIDA","VERSA","NOTE","MURANO"};


    String [] Marca = {"Audi","Ford","BMW","Chevrolet","Nissan"};

    String [] RefaccionTipo = {"Fascia","Cofre","Faro","Parabrisa","Escape","Motor","Radiador","Salpicadera","Calavera","Amortiguador"};

    ArrayList<String> Itemss = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_articulo);
        AbrirBase();
        CrearTablas();
        AsociarVistas();
        SpinnerModelo.setOnItemSelectedListener(this);
        SpinnerMarca.setOnItemSelectedListener(this);
        SpinnerTipo.setOnItemSelectedListener(this);
        SpinnerCanidad.setOnItemSelectedListener(this);

        for (int i = 1; i<=50; i++)
        {
            Itemss.add(i+"");
        }


        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,Itemss);
        SpinnerCanidad.setAdapter(adapter1);
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,RefaccionTipo);
        SpinnerTipo.setAdapter(adapter4);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,Marca);
        SpinnerMarca.setAdapter(adapter2);








        ImagenFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             CargarFoto();
            }
        });

ButtonAgregarRefaccion.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        RegistrarRefaccion();
    }
});
        ButtonIraMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent llamarmenu = new Intent(getApplicationContext(), RefaccionariaMenu.class);
                startActivity(llamarmenu);
            }
        });
        Campo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent llamarCampo = new Intent(getApplicationContext(), AgregarCampo.class);
                startActivity(llamarCampo);
            }
        });

    }

    private void AsociarVistas() {
        ImagenFoto = (ImageView)findViewById(R.id.imageViewFoto);
        ButtonIraMenu = (Button)findViewById(R.id.buttonIrAMenu);
        ButtonAgregarRefaccion = (Button)findViewById(R.id.buttonAgregarRefaccion);
      SpinnerCanidad = (Spinner)findViewById(R.id.spinnerCantidadRefaccion);
        SpinnerModelo = (Spinner)findViewById(R.id.spinneModelo);
        SpinnerMarca = (Spinner)findViewById(R.id.spinnerMarca);
       SpinnerTipo = (Spinner)findViewById(R.id.spinnerTipoRefaccion);
        EditTexNombreRefaccion = (EditText)findViewById(R.id.editTextNombreRefaccion);
        EditTextDescripcion = (EditText)findViewById(R.id.editTextDescripcionRefaccion);
        EditTextPrecioRefaccion = (EditText)findViewById(R.id.editTextPrecioRefaccion);
        Campo = (Button)findViewById(R.id.buttonCampo);
    }

    public void CargarFoto () {
     Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
      intent.setType("image/");
      startActivityForResult(intent.createChooser(intent, "Seleccione la aplicacion"),10);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK)
        {
            Uri path = data.getData();
            ImagenFoto.setImageURI(path);
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> a, View v, int p, long id) {


switch (a.getId()){
    case R.id.spinnerMarca:
        switch (p){
            case 0:
                ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,audi);

                SpinnerModelo.setAdapter(adapter3);
                break;
            case 1:
                ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,ford);

                SpinnerModelo.setAdapter(adapter5);
                break;
            case 2:
                ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,BMW);

                SpinnerModelo.setAdapter(adapter6);
                break;
            case 3:
                ArrayAdapter<String> adapter7 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,chevrolet);

                SpinnerModelo.setAdapter(adapter7);
                break;
            case 4:
                ArrayAdapter<String> adapter8 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,Nissan);

                SpinnerModelo.setAdapter(adapter8);
                break;
        }
            break;

}
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void AbrirBase() {
        try {
            db = openOrCreateDatabase("Refaccionaria", MODE_PRIVATE, null);


        } catch (Exception e) {
            EditTextPrecioRefaccion.setText("Error " + e.getMessage());
        }
    }
    public void CrearTablas() {
        try {
            db.execSQL("create table if not exists ArticuloRefaccion (Marca text, Modelo text, NombreRefaccion text, Tipo text, Cantidad text, Descripcion text, Precio text)");

        } catch (Exception e) {
            EditTextDescripcion.setText("Error al Crear Tabla" + e.getMessage());
        }


    }

    public void RegistrarRefaccion() {
        try {
            db.execSQL("insert into  ArticuloRefaccion " +
                    "values( '" + SpinnerMarca.getSelectedItem().toString() +
                    "','" + SpinnerModelo.getSelectedItem().toString() +
                    "','" + EditTexNombreRefaccion.getText()+
                    "','" + SpinnerTipo.getSelectedItem().toString()+
                    "','" + SpinnerCanidad.getSelectedItem().toString()+
                    "','" + EditTextDescripcion.getText()+
                    "','" + EditTextPrecioRefaccion.getText()+"')");


            Toast.makeText(getApplicationContext(), "Articulo Agregado ", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            EditTextDescripcion.setText("Error " + e.getMessage());
        }
    }
}
