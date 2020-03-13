package com.example.practicaparcial1eco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    private EditText mensajeET,posXET,posYET;
    private Button enviarBtn;
    private Socket socket;
    private BufferedWriter writer;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mensajeET = findViewById(R.id.mensajeET);
        posXET = findViewById(R.id.posXET);
        posYET = findViewById(R.id.posYET);
        enviarBtn = findViewById(R.id.enviarBtn);

        ComunicacionTCP comm = new ComunicacionTCP(this);
        comm.solicitarConexion();

        enviarBtn.setOnClickListener(
                (v)->{
                        String x = posXET.getText().toString();
                        String y = posYET.getText().toString();
                        String men = mensajeET.getText().toString();
                        comm.mandarMesanje(x+":"+y+":"+men);
                }
        );

    }
}
