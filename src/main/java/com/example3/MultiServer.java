package com.example3;

import java.net.ServerSocket;
import java.net.Socket;

public class MultiServer {
    public void esecuzione(){
        try{
            ServerSocket serverSocket = new ServerSocket(6789);

            for(;;){
                //server va in esecuzione ed attende che un client si connetta
                System.out.println("Server in attesa che il client provi ad indovinare....");
                Socket socket = serverSocket.accept();

                System.out.println("Server socket " + socket);
                ServerThread serverThread = new ServerThread(socket);
                serverThread.start();
            }

        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server");
            System.exit(1);
        }
    }
}
