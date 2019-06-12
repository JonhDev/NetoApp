package com.example.lista0404;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class FragBienvenida extends Fragment {

    TextView TextViewBienvenida;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.fragment_frag_bienvenida, container, false);
        TextViewBienvenida = (TextView) v.findViewById(R.id.textViewBienvenida);




        TextViewBienvenida.setText("Bienvenid@ ");
        return  v;
    }


}
