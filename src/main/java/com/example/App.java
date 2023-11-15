package com.example;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ArrayList<String> parole = new ArrayList<String>() {
            {
            add("libbro");
            add("computer");
            add("pizza");
            add("Lasagna");
            add("scuola");
            }
        };



        try {

            double numeroRandom =  (Math.random() * parole.size());
            String parolaScelta = parole.get((int)numeroRandom);

            ServerSocket server = new ServerSocket(3000);
            while(true){
                Socket socket = server.accept();
                Thread t = new serverMultiThread(socket,parolaScelta);
                t.start();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
