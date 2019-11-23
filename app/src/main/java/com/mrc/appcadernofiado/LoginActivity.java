package com.mrc.appcadernofiado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    Button btnLogar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogar = findViewById(R.id.btnLogar);
        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pesquisa usuário e senha no cadastro
                String texto;
                if (1 == 1) {
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                    texto = "Bem vindo!";
                    finish();
                } else {
                    texto = "Usuário ou senha Inválidos.";
                }
                Toast.makeText(getApplicationContext(), texto, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
