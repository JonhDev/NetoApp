package com.example.lista0404.fragments;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.cardview.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lista0404.R;

public class PanelAdministradorFragment extends Fragment {

    CardView modelos, refacciones, marcas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_panel_administrador, container, false);
        iniciarVariables(vista);
        iniciarListeners();
        return vista;
    }

    private void iniciarVariables(View vista) {
        modelos = vista.findViewById(R.id.card_modelos);
        refacciones = vista.findViewById(R.id.card_refacciones);
        marcas = vista.findViewById(R.id.card_marcas);
    }

    private void iniciarListeners() {
        modelos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                    transaction.add(R.id.frame_base, new ModelosFragment(), "Modelos").addToBackStack("Modelos").commit();
                } catch (Exception e) {
                    Toast.makeText(getContext(), "ERROR: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        refacciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                    transaction.add(R.id.frame_base, new RefaccionesFragment(), "Refacciones").addToBackStack("Refacciones").commit();
                } catch (Exception e) {
                    Toast.makeText(getContext(), "ERROR: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        marcas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                    transaction.add(R.id.frame_base, new MarcasFragment(), "Marcas").addToBackStack("Marcas").commit();
                } catch (Exception e) {
                    Toast.makeText(getContext(), "ERROR: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
