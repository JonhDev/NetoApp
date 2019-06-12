package com.example.lista0404;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Iniciolol extends AppCompatActivity {
    SQLiteDatabase db;
    Cursor cursor;
    ShoppingCarInstance shoppingCar;
    int Parseo;

    ListView listViewPrincipal;
   Button buttonregreso;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lol);
        AbrirBase();
        Consultar();

        listViewPrincipal = (ListView) findViewById(R.id.Lista);
        shoppingCar = ShoppingCarInstance.getShoppingCar();
        buttonregreso = (Button)findViewById(R.id.buttonsRegresar);


        listViewPrincipal.setAdapter(new Adaptador(this, arrayarticulo));





        listViewPrincipal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Parseo = Integer.parseInt(arrayarticulo.get(position).getPrecioA().toString());
                shoppingCar.addPriceToTotal(Parseo);
                Toast.makeText(getApplication(), "Agregaste al carrito: "+arrayarticulo.get(position).getNombre().toString(), Toast.LENGTH_LONG).show();
                Toast.makeText(getApplication(), "Total $ "+shoppingCar.getTotalAmount()+" Cantidad de articulos "+shoppingCar.getTotalItemsInShoppingCar(), Toast.LENGTH_LONG).show();

            }
        });




        buttonregreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lol = new Intent (getApplicationContext(), RefaccionariaMenu.class);
                startActivity(lol);
            }
        });
    }



    private void AbrirBase() {
        try {
            db = openOrCreateDatabase("Refaccionaria", MODE_PRIVATE, null);


        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
        }
    }

   // ArrayList ArticuloArray;
    ArrayList<EnlistandoDatos> arrayarticulo;

    private void Consultar() {

        EnlistandoDatos ArtiuculoRefa = null;
        arrayarticulo = new ArrayList<EnlistandoDatos>();
        cursor = db.rawQuery("Select * from ArticuloRefaccion", null);

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
}



