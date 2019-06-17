package com.example.lista0404;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.lista0404.activities.AdministradorActivity;
import com.example.lista0404.activities.UsuarioActivity;
import com.example.lista0404.database.RefaccionariaHelper;

public class RefaccionariaNeto extends AppCompatActivity {
    Button buttonIngresar, buttonRegistrar;
    RadioButton RadioButtonUsuario, RadioButtonAdministrador;
    EditText EditTextUsuario, EditTextContraseña;
    RefaccionariaHelper refaccionaria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refaccionaria_neto);

        refaccionaria = new RefaccionariaHelper(getApplicationContext());

        AsociarVistas();
        EditTextUsuario.setText("");
        EditTextContraseña.setText("");

        iniciarListeners();
    }

    private void iniciarListeners() {
        buttonIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (RadioButtonAdministrador.isChecked()) {
                    ingresarAdministrador();
                }
                if (RadioButtonUsuario.isChecked()) {
                    ingresarUsuario();
                }
            }
        });

        buttonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (RadioButtonAdministrador.isChecked()) {
                    registrarAdmin();
                }
                if (RadioButtonUsuario.isChecked()) {
                    registrarUsuario();
                }
            }
        });
    }

    private void ingresarAdministrador() {
        String usuario = EditTextUsuario.getText().toString();
        String contrasena = EditTextContraseña.getText().toString();
        if(refaccionaria.loginAdministrator(usuario, contrasena)) {
            navegarAAdministrador();
        } else {
            Toast.makeText(getApplicationContext(), "No se puede ingresar", Toast.LENGTH_LONG).show();
        }
    }

    private void ingresarUsuario() {
        String usuario = EditTextUsuario.getText().toString();
        String contrasena = EditTextContraseña.getText().toString();
        int usuarioId = refaccionaria.loginUsuario(usuario, contrasena);
        if(usuarioId > -1) {
            navegarAUsuario(usuarioId);
        } else {
            Toast.makeText(getApplicationContext(), "No se puede ingresar", Toast.LENGTH_LONG).show();
        }
    }

    private void registrarAdmin() {
        try {
            String usuario = EditTextUsuario.getText().toString();
            String contrasena = EditTextContraseña.getText().toString();
            refaccionaria.insertarAdministrador(usuario, contrasena);
            Toast.makeText(getApplicationContext(), "Registrado!", Toast.LENGTH_LONG).show();
        } catch(Exception e) {
            Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void registrarUsuario() {
        try {
            String usuario = EditTextUsuario.getText().toString();
            String contrasena = EditTextContraseña.getText().toString();
            refaccionaria.insertarCliente(usuario, contrasena);
            Toast.makeText(getApplicationContext(), "Registrado!", Toast.LENGTH_LONG).show();
        } catch(Exception e) {
            Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void navegarAAdministrador() {
        Intent llamarAgregar = new Intent(getApplicationContext(), AdministradorActivity.class);
        startActivity(llamarAgregar);
    }

    private void navegarAUsuario(int idUsuario) {
        Intent llamarAgregar = new Intent(getApplicationContext(), UsuarioActivity.class);
        llamarAgregar.putExtra("id_usuario", idUsuario);
        startActivity(llamarAgregar);
    }

    private void navegarAUsuarios() {
        Intent llamarmenu = new Intent(getApplicationContext(), RefaccionariaMenu.class);
        startActivity(llamarmenu);
    }

    private void AsociarVistas() {
        buttonIngresar = (Button) findViewById(R.id.buttonIngresar);
        buttonRegistrar = (Button) findViewById(R.id.buttonRegistrar);
        RadioButtonUsuario = (RadioButton) findViewById(R.id.radioButtonUsuario);
        RadioButtonAdministrador = (RadioButton) findViewById(R.id.radioButtonAdmi);
        EditTextUsuario = (EditText) findViewById(R.id.editTextUsuario);
        EditTextContraseña = (EditText) findViewById(R.id.editTextContraseña);
    }
}