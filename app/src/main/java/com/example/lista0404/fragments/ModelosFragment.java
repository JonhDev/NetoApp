package com.example.lista0404.fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.afollestad.materialdialogs.DialogBehavior;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lista0404.R;
import com.example.lista0404.database.RefaccionariaHelper;
import com.example.lista0404.datos.Modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

public class ModelosFragment extends Fragment {

    private RecyclerView modeloRecycler;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private FloatingActionButton floatingActionButton;
    private Toolbar toolbar;

    private Hashtable<Integer, String> marcas;

    RefaccionariaHelper refaccionaria;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_modelos, container, false);

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
                agregarModelo();
            }
        });
        modeloRecycler.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        modeloRecycler.setLayoutManager(layoutManager);
        refaccionaria = new RefaccionariaHelper(getContext());
        marcas = refaccionaria.obtenerMarcas();
        iniciarModelos();

        return vista;
    }

    private void iniciarModelos() {
        ArrayList<Modelo> listaModelos = refaccionaria.obtenerModelos();
        mAdapter = new ModelosAdapter(listaModelos);
        modeloRecycler.setAdapter(mAdapter);
    }

    private void agregarModelo() {
        String[] marcasLista = Collections.list(marcas.elements()).toArray(String[]::new);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setTitle("Ingresa una marca");
        alertDialog.setSingleChoiceItems(marcasLista, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alertDialog.setCancelable(true)
                .setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(!input.getText().toString().isEmpty()) {
                            guardarMarcaEnBD(input.getText().toString());
                            dialogInterface.dismiss();
                        }
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog dialog = alertDialog.create();
        dialog.show();
    }

    class ModelosAdapter extends RecyclerView.Adapter<ModelosAdapter.ViewHolder> {
        private ArrayList<Modelo> mDataset;
        private TextView marca, modelo;
        private ImageView delete;

        public class ViewHolder extends RecyclerView.ViewHolder {
            public ViewHolder(View v) {
                super(v);
            }
        }

        public ModelosAdapter(ArrayList<Modelo> myDataset) {
            mDataset = myDataset;
        }

        @Override
        public ModelosAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                          int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_marcas, parent, false);
            modelo = view.findViewById(R.id.textView_modelo);
            marca = view.findViewById(R.id.textView_marca);
            delete = view.findViewById(R.id.delete_item);
            return new ModelosAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ModelosAdapter.ViewHolder holder, final int position) {
            modelo.setText(mDataset.get(position).getNombreModelo());
            marca.setText(marcas.get(mDataset.get(position).getMarcaModelo()));
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RefaccionariaHelper refaccionaria = new RefaccionariaHelper(view.getContext());
                    refaccionaria.eliminarModelo(mDataset.get(position).getIdModelo());
                    iniciarModelos();
                }
            });
        }

        @Override
        public int getItemCount() {
            return mDataset.size();
        }
    }

}
