package org.iesfm.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientMain {
    public static void main(String[] args) {

        String severIp = "localhost";
        int serverPort = 4000;
        try {
            // Se crea el socket apuntando al servidor
            Socket socket = new Socket(severIp, serverPort);
            // Se abre writer para enviar al servidor
            try (PrintWriter writer = new PrintWriter(socket.getOutputStream())) {
                // Se abre reader para recibir datos del servidor
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    // Se escribe Hola en el socket
                    writer.println("Hola");
                    // Se envía Hola al servidor
                    writer.flush();
                    // Se lee lo que el servidor haya enviado
                    String message = reader.readLine();
                    // Se imprime
                    System.out.println(message);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
