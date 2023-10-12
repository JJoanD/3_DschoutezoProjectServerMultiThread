package com.example3;
import java.io.*;
import java.net.*;

public class ServerThread extends Thread {
    ServerSocket server = null;
    Socket client = null;
    String stringaRicevuta = null;
    String stringModifica = null;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;
    int numeroDaIndovinare;
    int numeroRicevuto;

    public ServerThread (Socket socketCliente){
        this.client = socketCliente;
    }

    public void run(){
        try{
            comunica();
        }catch(Exception e){ e.printStackTrace(System.out); }
    }

    public void comunica() throws Exception{
        //  //associo due oggetti al coket del client per effetuare la scrittura e la lettura , ossia gli stream
        inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
        outVersoClient = new DataOutputStream(client.getOutputStream());

        //creazione numero random da indovinare
        numeroDaIndovinare = (int) (Math.random() * 999 +1) ;
        
        System.out.println("numero da indovinare = " + numeroDaIndovinare);
        
        for(;;){
            numeroRicevuto = Integer.parseInt(inDalClient.readLine());
            System.out.println( "numero ricevuto  = " +  numeroRicevuto);

            if(numeroRicevuto == numeroDaIndovinare ){
                outVersoClient.writeBytes("hai indovinato" + "\n");
                //chiusura socket
                 System.out.println("chiusura socket " + client);
                client.close();
                break;
            }
            else if(numeroRicevuto > numeroDaIndovinare){
                outVersoClient.writeBytes("il numero da indovinare è più basso\"" + "\n");
                
            }
            else if(numeroRicevuto < numeroDaIndovinare){
                outVersoClient.writeBytes("il numero da indovinare è più alto\"" + "\n");
            }
        }
    }
}
