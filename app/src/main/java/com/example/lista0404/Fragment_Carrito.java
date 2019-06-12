package com.example.lista0404;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class Fragment_Carrito extends Fragment {
    Button button;
    ShoppingCarInstance shoppingCar;
    TextView TextViewcantidad;
    TextView TextViewTotal;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fragment__carrito, container, false);

        TextViewcantidad = (TextView) v.findViewById(R.id.textViewCanProductos);
        TextViewTotal = (TextView) v.findViewById(R.id.textViewTotal);
        button = (Button) v.findViewById(R.id.buttonRegresarLista);
        shoppingCar = ShoppingCarInstance.getShoppingCar();

        String car = String.valueOf(shoppingCar.getTotalAmount());
        String cont = String.valueOf(shoppingCar.getTotalItemsInShoppingCar());
        TextViewcantidad.setText("# "+cont);
        TextViewTotal.setText("$ "+car);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent re = new Intent(getContext(), RefaccionariaMenu.class);
                startActivity(re);
            }
        });
        return v;

    }
}
