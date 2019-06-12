package com.example.lista0404.activities;

import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lista0404.R;
import com.example.lista0404.fragments.PanelAdministradorFragment;

public class AdministradorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrador);
        agregarPanel();
    }

    private void agregarPanel() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_base, new PanelAdministradorFragment()).commit();
    }
}
