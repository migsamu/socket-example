package org.iesfm.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerMain {

    public static void main(String[] args) {
        int port = 4000;

        try {
            // Creamos un servidor de socket que escucha en el puerto 4000
            ServerSocket ss = new ServerSocket(port);
            System.out.println("Esperando conexión...");
            // Esperamos a que un cliente se conecte a nuestro servidor
            Socket socket = ss.accept();
            // Abrimos writer para enviar datos al cliente
            try (PrintWriter writer = new PrintWriter(socket.getOutputStream())) {
                // Abrimos reader para leer lo que envía el cliente
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    // Escribimos el mensaje "Conexión establecida!" en el socket
                    writer.println("Conexión establecida!");
                    // Forzamos que se envíe el mensaje a través del socket
                    writer.flush();
                    // Leemos una línea que nos haya enviado el cliente
                    // El hilo se bloquea hasta que el cliente mande algo
                    String message = reader.readLine();
                    // Muestro el mensaje
                    System.out.println(message);
                    Thread.sleep(5000);
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
