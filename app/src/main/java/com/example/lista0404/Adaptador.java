package com.example.lista0404;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class Adaptador extends BaseAdapter {

    int [] images = {R.drawable.facia, R.drawable.cofre, R.drawable.faro, R.drawable.parabrisauno, R.drawable.escapetres, R.drawable.motordos, R.drawable.radiador, R.drawable.salpicadera, R.drawable.calavera, R.drawable.amortiguador};

    private static LayoutInflater inflater = null;

    Context Contexto;

    ArrayList <EnlistandoDatos> ArrayListArticulos;





    public Adaptador(Context Contexto, ArrayList Lista) {
        this.Contexto = Contexto;
        this.ArrayListArticulos = Lista;

        inflater = (LayoutInflater) Contexto.getSystemService(Contexto.LAYOUT_INFLATER_SERVICE);
    }

    
    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

        final View vista = inflater.inflate(R.layout.elemento_lista, null);

       //ArrayListArticulos = new ArrayList<EnlistandoDatos>();

        TextView Nombre = (TextView)vista.findViewById(R.id.textViewTitulo);

        TextView Precio = (TextView) vista.findViewById(R.id.textViewPrecio);
        TextView Marca = (TextView) vista.findViewById(R.id.textViewMarca);
        TextView Modelo = (TextView) vista.findViewById(R.id.textViewModelo);
        TextView Tipo = (TextView)vista.findViewById(R.id.textViewTipo);
        TextView Descripcion = (TextView)vista.findViewById(R.id.textViewDescripcion);
        ImageView imagen = (ImageView) vista.findViewById(R.id.imageViewProducto);

        Nombre.setText(ArrayListArticulos.get(i).getNombre().toString());

        Precio.setText("$ "+ArrayListArticulos.get(i).getPrecioA().toString());
        Marca.setText("Marca: "+ArrayListArticulos.get(i).getMarca().toString());
        Modelo.setText("Modelo: "+ArrayListArticulos.get(i).getModelo().toString());
        Tipo.setText("Refaccion: "+ArrayListArticulos.get(i).getCantidad().toString());
        Descripcion.setText("Descripcion: "+ArrayListArticulos.get(i).getDescripcion().toString());


        if(ArrayListArticulos.get(i).getCantidad().equals("Fascia")){
            imagen.setImageResource(images[0]);
        }
        if(ArrayListArticulos.get(i).getCantidad().equals("Cofre")){
            imagen.setImageResource(images[1]);
        }
        if(ArrayListArticulos.get(i).getCantidad().equals("Faro")){
            imagen.setImageResource(images[2]);
        }
        if(ArrayListArticulos.get(i).getCantidad().equals("Parabrisa")){
            imagen.setImageResource(images[3]);
        }
        if(ArrayListArticulos.get(i).getCantidad().equals("Escape")){
            imagen.setImageResource(images[4]);
        }
        if(ArrayListArticulos.get(i).getCantidad().equals("Motor")){
            imagen.setImageResource(images[5]);
        }
        if(ArrayListArticulos.get(i).getCantidad().equals("Radiador")){
            imagen.setImageResource(images[6]);
        }
        if(ArrayListArticulos.get(i).getCantidad().equals("Salpicadera")){
            imagen.setImageResource(images[7]);
        }
        if(ArrayListArticulos.get(i).getCantidad().equals("Calavera")){
            imagen.setImageResource(images[8]);
        }
        if(ArrayListArticulos.get(i).getCantidad().equals("Amortiguador")){
            imagen.setImageResource(images[9]);
        }




        return vista;
    }

    @Override
    public int getCount() {
        return ArrayListArticulos.size();
    }



    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


}
