package com.example.lista0404.fragments;


import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lista0404.R;
import com.example.lista0404.database.RefaccionariaHelper;
import com.example.lista0404.datos.Carrito;
import com.example.lista0404.datos.Modelo;
import com.example.lista0404.datos.Refaccion;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class ComprasFragment extends Fragment {

    private RecyclerView modeloRecycler;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private FloatingActionButton floatingActionButton;
    private Toolbar toolbar;

    private Hashtable<Integer, String> tipos;
    private ArrayList<Modelo> modelos;
    private ArrayList<Refaccion> refaccionesLista;
    private List<Carrito> carrito;

    RefaccionariaHelper refaccionaria;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_compras, container, false);
        modeloRecycler = vista.findViewById(R.id.recyclerView);
        toolbar = vista.findViewById(R.id.toolbar);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        modeloRecycler.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        modeloRecycler.setLayoutManager(layoutManager);
        refaccionaria = new RefaccionariaHelper(getContext());

        refaccionesLista = refaccionaria.obtenerRefacciones();
        tipos = refaccionaria.obtenerTipos();
        modelos = refaccionaria.obtenerModelos();

        iniciarCarrito();
        return vista;
    }

    private void iniciarCarrito() {
        carrito = refaccionaria.obtenerCompras();
        mAdapter = new ComprasAdapter(carrito);
        modeloRecycler.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        modeloRecycler.setAdapter(mAdapter);
    }

    private Refaccion buscarRefaccionPorId(int id) {
        for (Refaccion ref : refaccionesLista) {
            if(ref.getIdRefaccion() == id) {
                return ref;
            }
        }
        return null;
    }

    class ComprasAdapter extends RecyclerView.Adapter<ComprasAdapter.ViewHolder> {
        private List<Carrito> mDataset;
        private TextView nombre, descripcion, modelo, tipo, precio, usuario;

        public class ViewHolder extends RecyclerView.ViewHolder {
            public ViewHolder(View v) {
                super(v);
            }
        }

        public ComprasAdapter(List<Carrito> myDataset) {
            mDataset = myDataset;
        }

        @Override
        public ComprasAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.vista_compra, parent, false);
            nombre = view.findViewById(R.id.textView_nombre);
            descripcion = view.findViewById(R.id.textView_descripcion);
            modelo = view.findViewById(R.id.textView_modelo);
            tipo = view.findViewById(R.id.textView_tipo);
            precio = view.findViewById(R.id.textView_precio);
            usuario = view.findViewById(R.id.textView_cliente);
            return new ComprasAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ComprasAdapter.ViewHolder holder, final int position) {
            Refaccion ref = buscarRefaccionPorId(mDataset.get(position).getCarritoRefaccion());
            nombre.setText(ref.getNombreModelo());
            descripcion.setText(ref.getDescripcion());
            modelo.setText(obtenerNombreModelo(ref.getMarcaModelo()));
            tipo.setText("Tipo: " + tipos.get(ref.getTipo()));
            precio.setText("Precio: " + ref.getPrecio() + "");
            usuario.setText("Comprador: " + refaccionaria.nombreUsuario(mDataset.get(position).getCarritoCliente()));
        }

        private String obtenerNombreModelo(int index) {
            for (Modelo model : modelos) {
                if(model.getIdModelo() == index) {
                    return "Modelo: " + model.getNombreModelo();
                }
            }
            return "";
        }

        @Override
        public int getItemCount() {
            return mDataset.size();
        }
    }

}
