package com.example.lista0404.fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
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

import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

public class MarcasFragment extends Fragment {

    private RecyclerView marcas;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private FloatingActionButton floatingActionButton;
    private Toolbar toolbar;

    RefaccionariaHelper refaccionaria;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_marcas, container, false);
        marcas = vista.findViewById(R.id.recyclerView);
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
                guardarMarca();
            }
        });
        marcas.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        marcas.setLayoutManager(layoutManager);
        refaccionaria = new RefaccionariaHelper(getContext());
        iniciarMarcas();
        return vista;
    }

    private void iniciarMarcas() {
        Hashtable<Integer, String> marcasLista = refaccionaria.obtenerMarcas();
        mAdapter = new MarcasAdapter(marcasLista);
        marcas.setAdapter(mAdapter);
    }

    private void guardarMarca() {
        LayoutInflater li = LayoutInflater.from(getContext());
        View vista = li.inflate(R.layout.vista_dialogo_input, null);
        String marca;
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setView(vista);
        final EditText input = vista.findViewById(R.id.editText_input);
        final TextView titulo = vista.findViewById(R.id.textView_titulo);

        titulo.setText("Ingresa una marca");
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

    private void guardarMarcaEnBD(String marcaString) {
        refaccionaria.insertarMarca(marcaString);
        iniciarMarcas();
    }

    class MarcasAdapter extends RecyclerView.Adapter<MarcasAdapter.ViewHolder> {
        private Hashtable<Integer, String> mDataset;
        private TextView marca;
        private ImageView delete;

        public class ViewHolder extends RecyclerView.ViewHolder {
            public ViewHolder(View v) {
                super(v);
            }
        }

        public MarcasAdapter(Hashtable<Integer, String> myDataset) {
            mDataset = myDataset;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent,
                                             int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_marcas, parent, false);
            marca = view.findViewById(R.id.textView_marca);
            delete = view.findViewById(R.id.delete_item);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            List<String> elements = Collections.list(mDataset.elements());
            final List<Integer> keys = Collections.list(mDataset.keys());
            marca.setText(elements.get(position));
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RefaccionariaHelper refaccionaria = new RefaccionariaHelper(view.getContext());
                    refaccionaria.eliminarMarca(keys.get(position));
                    iniciarMarcas();
                }
            });
        }

        @Override
        public int getItemCount() {
            return mDataset.size();
        }
    }

}
