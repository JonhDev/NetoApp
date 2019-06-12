package com.example.lista0404.fragments;


import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lista0404.R;
import com.example.lista0404.database.RefaccionariaHelper;
import com.example.lista0404.datos.Modelo;
import com.example.lista0404.datos.Refaccion;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Hashtable;

public class RefaccionesFragment extends Fragment {

    private RecyclerView modeloRecycler;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private FloatingActionButton floatingActionButton;
    private Toolbar toolbar;

    private Hashtable<Integer, String> tipos;
    private ArrayList<Modelo> modelos;

    RefaccionariaHelper refaccionaria;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_refacciones, container, false);
        modeloRecycler = vista.findViewById(R.id.recyclerView);
        floatingActionButton = vista.findViewById(R.id.floating_add);
        toolbar = vista.findViewById(R.id.toolbar);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                    transaction.add(R.id.frame_base, new RefaccionariaFormFragment(), "RefaccionesForm").addToBackStack("RefaccionesForm").commit();
                } catch (Exception e) {
                    Toast.makeText(getContext(), "ERROR: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
        modeloRecycler.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        modeloRecycler.setLayoutManager(layoutManager);
        refaccionaria = new RefaccionariaHelper(getContext());

        tipos = refaccionaria.obtenerTipos();
        modelos = refaccionaria.obtenerModelos();

        iniciarRefacciones();
        return vista;
    }

    private void iniciarRefacciones() {
        ArrayList<Refaccion> refaccionesLista = refaccionaria.obtenerRefacciones();
        mAdapter = new RefaccionesAdapter(refaccionesLista);
        modeloRecycler.setAdapter(mAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        iniciarRefacciones();
    }

    class RefaccionesAdapter extends RecyclerView.Adapter<RefaccionesAdapter.ViewHolder> {
        private ArrayList<Refaccion> mDataset;
        private TextView nombre, descripcion, modelo, tipo, precio;
        private ImageView delete;

        public class ViewHolder extends RecyclerView.ViewHolder {
            public ViewHolder(View v) {
                super(v);
            }
        }

        public RefaccionesAdapter(ArrayList<Refaccion> myDataset) {
            mDataset = myDataset;
        }

        @Override
        public RefaccionesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                            int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.vista_refaccion, parent, false);
            nombre = view.findViewById(R.id.textView_nombre);
            descripcion = view.findViewById(R.id.textView_descripcion);
            modelo = view.findViewById(R.id.textView_modelo);
            tipo = view.findViewById(R.id.textView_tipo);
            precio = view.findViewById(R.id.textView_precio);
            delete = view.findViewById(R.id.delete_item);
            return new RefaccionesAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RefaccionesAdapter.ViewHolder holder, final int position) {
            nombre.setText(mDataset.get(position).getNombreModelo());
            descripcion.setText(mDataset.get(position).getDescripcion());
            modelo.setText(obtenerNombreModelo(mDataset.get(position).getMarcaModelo()));
            tipo.setText(tipos.get(mDataset.get(position).getTipo()));
            precio.setText(mDataset.get(position).getPrecio() + "");
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RefaccionariaHelper refaccionaria = new RefaccionariaHelper(view.getContext());
                    refaccionaria.eliminarRefaccion(mDataset.get(position).getIdRefaccion());
                    iniciarRefacciones();
                }
            });
        }

        private String obtenerNombreModelo(int index) {
            for (Modelo model : modelos) {
                if(model.getIdModelo() == index) {
                    return model.getNombreModelo();
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
