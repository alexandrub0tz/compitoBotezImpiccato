package com.example;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

public class serverMultiThread extends Thread {
    
    Socket socket;
    String parolaScelta;

    public serverMultiThread(Socket sc,String ps){
        this.socket = sc;
        this.parolaScelta = ps;
    }


    public void run(){
            System.out.println("un client si é connesso");
            System.out.println(parolaScelta);


       
 
            try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                    out.writeBytes("Connessione effettuata. Digita ESC per uscire" + '\n');
                
                ArrayList<String> parolaOscurata = new ArrayList<>();
                for(int i = 0; i < parolaScelta.length();i++){
                    parolaOscurata.add("*");
                }

                String parolaUnitaOscurata = "";
                for(String po : parolaOscurata){
                    parolaUnitaOscurata += po;
                }

                out.writeBytes(parolaUnitaOscurata + "\n");

                int contatore = 0;
                while(true) {

                    contatore++;
                    
                    String parolaClient = in.readLine();

                
                
                    
                    if(parolaScelta.contains(parolaClient)){
                        ArrayList<Integer> listaIndici = new ArrayList<Integer>();
                        for(int i = 0; i < parolaScelta.length();i++){
                           if(parolaScelta.charAt(i) == parolaClient.charAt(0)){
                            listaIndici.add(i);
                           }
                        }

                        for(int i = 0; i < listaIndici.size(); i++){
                            parolaOscurata.set(listaIndici.get(i), parolaClient);
                        }


                    }


                    String parolaOscurataString = "";
                    for(String po : parolaOscurata){
                        parolaOscurataString += po;
                    }

                    

                    if(parolaOscurataString.equals(parolaScelta)){
                        String messaggio = "bravo hai indovinato in " + contatore + " tentativi";
                        out.writeBytes(messaggio + '\n');
                    } else {
                        out.writeBytes(parolaOscurataString + '\n');
                    }

                } 

            } catch (IOException e) {
                e.printStackTrace();
            }

        
            
            
    }
}

