package com.example.lista0404.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

public class CarritoFragment extends Fragment {

    private RecyclerView modeloRecycler;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private FloatingActionButton floatingActionButton;
    private Toolbar toolbar;

    private Hashtable<Integer, String> tipos;
    private ArrayList<Modelo> modelos;
    private ArrayList<Refaccion> refaccionesLista;
    private List<Carrito> carrito;

    private int usuarioId;

    RefaccionariaHelper refaccionaria;

    public CarritoFragment(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_carrito, container, false);
        modeloRecycler = vista.findViewById(R.id.recyclerView);
        toolbar = vista.findViewById(R.id.toolbar);
        floatingActionButton = vista.findViewById(R.id.floating_add);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(carrito != null && !carrito.isEmpty()) {
                    comprarCarrito();
                } else {
                    Toast.makeText(getContext(), "No hay elementos que comprar", Toast.LENGTH_SHORT).show();
                }
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

    private void comprarCarrito() {
        final AlertDialog.Builder dialogo = new AlertDialog.Builder(getActivity());
        dialogo.setMessage("Estas seguro de proceder a la compra?")
                .setPositiveButton("Comprar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        comprarCosasCarrito();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .create();
        dialogo.show();
    }

    private void comprarCosasCarrito() {
        for (Carrito carrito : carrito) {
            refaccionaria.actualizarAComprado(carrito.getIdCarrito());
        }
        Toast.makeText(getContext(), "Compra completa", Toast.LENGTH_LONG).show();
        iniciarCarrito();
    }

    private void iniciarCarrito() {
        carrito = refaccionaria.obtenerCarritoUsuario(usuarioId);
        mAdapter = new CarritoAdapter(carrito);
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

    class CarritoAdapter extends RecyclerView.Adapter<CarritoAdapter.ViewHolder> {
        private List<Carrito> mDataset;
        private TextView nombre, descripcion, modelo, tipo, precio;
        private Button eliminarCarrito;

        public class ViewHolder extends RecyclerView.ViewHolder {
            public ViewHolder(View v) {
                super(v);
            }
        }

        public CarritoAdapter(List<Carrito> myDataset) {
            mDataset = myDataset;
        }

        @Override
        public CarritoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.vista_refaccion_usuario, parent, false);
            nombre = view.findViewById(R.id.textView_nombre);
            descripcion = view.findViewById(R.id.textView_descripcion);
            modelo = view.findViewById(R.id.textView_modelo);
            tipo = view.findViewById(R.id.textView_tipo);
            precio = view.findViewById(R.id.textView_precio);
            eliminarCarrito = view.findViewById(R.id.boton_agregar);
            eliminarCarrito.setText("Eliminar del carrito");
            eliminarCarrito.setBackgroundColor(ContextCompat.getColor(getContext(), android.R.color.holo_red_dark));
            return new CarritoAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(CarritoAdapter.ViewHolder holder, final int position) {
            Refaccion ref = buscarRefaccionPorId(mDataset.get(position).getCarritoRefaccion());
            nombre.setText(ref.getNombreModelo());
            descripcion.setText(ref.getDescripcion());
            modelo.setText(obtenerNombreModelo(ref.getMarcaModelo()));
            tipo.setText("Tipo: " + tipos.get(ref.getTipo()));
            precio.setText(ref.getPrecio() + "");
            eliminarCarrito.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    refaccionaria.eliminarRefaccionDeCarrito(mDataset.get(position).getIdCarrito());
                    Toast.makeText(getContext(), "Eliminado", Toast.LENGTH_SHORT).show();
                    iniciarCarrito();
                }
            });
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
