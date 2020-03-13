package com.example.practicaparcial1eco;

import android.content.Intent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ComunicacionTCP extends Thread{

    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;
    private AppCompatActivity app;

    public ComunicacionTCP(AppCompatActivity app){
        this.app =app;
    }

    //Hilo de recepcion
    public void run() {
        try {

            this.socket = new Socket("192.168.43.106",5000);
            //socket = new Socket("172.168.43.106", 5000);

            //Reader
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            this.reader=new BufferedReader(isr);

            //Wtriter
            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            this.writer=new BufferedWriter(osw);

            while(true) {
                recibirMensajes();
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //Solicitar conexion
    public void solicitarConexion(){
        this.start();
    }

    //Mandar un mensaje
    public void mandarMesanje(String mensaje){
        new Thread(
                ()->{
                    try {
                        writer.write(mensaje+"\n");
                        writer.flush();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
        ).start();
    }

    //Recibir mensajes
    public void recibirMensajes() throws IOException{
        String line = reader.readLine();
        System.out.println("<<<" + line);

    }

    //Cerrar conexion
    public void cerrarConexion(){
        try {
            socket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
