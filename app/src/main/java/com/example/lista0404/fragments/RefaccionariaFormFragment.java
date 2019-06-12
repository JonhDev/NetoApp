package com.example.lista0404.fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lista0404.R;
import com.example.lista0404.database.RefaccionariaHelper;
import com.example.lista0404.datos.Modelo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;

public class RefaccionariaFormFragment extends Fragment {

    private TextView nombre, descripcion, precio, modelo, tipo;
    private ImageView agregar;
    private FloatingActionButton floatingActionButton;
    private Toolbar toolbar;

    private RefaccionariaHelper refaccionaria;
    private Hashtable<Integer, String> tipos;
    private ArrayList<Modelo> modelos;

    private static int tipoSeleccionado = -1;
    private static int modeloSeleccionado = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_refaccionaria_form, container, false);
        nombre = vista.findViewById(R.id.textView_nombre);
        descripcion = vista.findViewById(R.id.textView_descripcion);
        precio = vista.findViewById(R.id.textView_precio);
        tipo = vista.findViewById(R.id.textView_tipo);
        modelo = vista.findViewById(R.id.textView_modelo);
        agregar = vista.findViewById(R.id.imageView_agregar);
        floatingActionButton = vista.findViewById(R.id.floating_add);

        refaccionaria = new RefaccionariaHelper(getContext());

        tipos = refaccionaria.obtenerTipos();
        modelos = refaccionaria.obtenerModelos();

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
                guardarRefaccion();
            }
        });

        tipo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seleccionarTipo();
            }
        });

        modelo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seleccionarModelo();
            }
        });

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregarTipo();
            }
        });

        return vista;
    }

    private void guardarRefaccion() {
        if(tipoSeleccionado != -1 && modeloSeleccionado != -1) {
            String nombreRefa = nombre.getText().toString();
            String descripcionRefa = descripcion.getText().toString();
            double precioRefa = Double.parseDouble(precio.getText().toString());
            refaccionaria.insertarRefaccion(nombreRefa, tipoSeleccionado, descripcionRefa, precioRefa, modeloSeleccionado);
            getActivity().onBackPressed();
        } else {
            Toast.makeText(getContext(), "Ingresa todos los datos", Toast.LENGTH_LONG).show();
        }
    }

    private void agregarTipo() {
        LayoutInflater li = LayoutInflater.from(getContext());
        View vista = li.inflate(R.layout.vista_dialogo_input, null);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        final EditText input = vista.findViewById(R.id.editText_input);
        final TextView titulo = vista.findViewById(R.id.textView_titulo);
        titulo.setText("Agregar tipo");
        alertDialog.setTitle("");
        alertDialog.setView(vista);
        alertDialog.setCancelable(true)
                .setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(!input.getText().toString().isEmpty()) {
                            refaccionaria.insertarTipoRefaccion(input.getText().toString());
                            dialogInterface.dismiss();
                            tipos = refaccionaria.obtenerTipos();
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

    private void seleccionarTipo() {
        Object[] lista = Collections.list(tipos.elements()).toArray();
        final String[] tiposLista = Arrays.copyOf(lista, lista.length, String[].class);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setTitle("Selecciona un tipo");
        alertDialog.setSingleChoiceItems(tiposLista, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                tipoSeleccionado = Collections.list(tipos.keys()).get(i);
                tipo.setText(tiposLista[i]);
            }
        });
        alertDialog.setCancelable(false)
                .setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(tipoSeleccionado != -1) {
                            dialogInterface.dismiss();
                        }
                    }
                });
        AlertDialog dialog = alertDialog.create();
        if(tipos.size() > 0) {
            dialog.show();
        }
    }

    private void seleccionarModelo() {
        final String[] modelosLista = crearArray();
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setTitle("Selecciona un modelo");
        alertDialog.setSingleChoiceItems(modelosLista, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                modeloSeleccionado = modelos.get(i).getIdModelo();
                modelo.setText(modelosLista[i]);
            }
        });
        alertDialog.setCancelable(false)
                .setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(modeloSeleccionado != -1) {
                            dialogInterface.dismiss();
                        }
                    }
                });
        AlertDialog dialog = alertDialog.create();
        if(modelos.size() > 0) {
            dialog.show();
        }
    }

    private String[] crearArray() {
        String[] array = new String[modelos.size()];
        for (int i = 0; i < modelos.size(); i++) {
            array[i] = modelos.get(i).getNombreModelo();
        }
        return array;
    }

}
