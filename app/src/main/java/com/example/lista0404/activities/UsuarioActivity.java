package com.example.lista0404.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.lista0404.R;
import com.example.lista0404.fragments.PanelAdministradorFragment;
import com.example.lista0404.fragments.UserPanelFragment;

public class UsuarioActivity extends AppCompatActivity {

    private int idUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);
        idUsuario = getIntent().getExtras().getInt("id_usuario");
        agregarPanel();
    }

    private void agregarPanel() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_base, new UserPanelFragment(idUsuario)).commit();
    }
}
