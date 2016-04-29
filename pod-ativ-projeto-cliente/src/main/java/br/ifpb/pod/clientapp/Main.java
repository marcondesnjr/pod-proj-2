package br.ifpb.pod.clientapp;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import br.ifpb.pod.sender.Sender;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) throws AccessException, RemoteException, NotBoundException {

        Registry registry = LocateRegistry.getRegistry("localhost", 8082);
        Sender sender = (Sender) registry.lookup("Sender");
        new Thread(new RespFinder(sender)).start();
        new Thread(new PingT(sender)).start();
        Client client = new Client();

        client.setSender(sender);
        while (true) {
            String str = new Scanner(System.in).nextLine();
            System.out.println(client.runConsole(str));
        }
    }

    private static class RespFinder implements Runnable {

        private Sender sender;

        public RespFinder(Sender sender) {
            this.sender = sender;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    String response = sender.getResponse();
                    if (response != null) {
                        System.out.println(response);
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    private static class PingT implements Runnable {

        private Sender sender;

        public PingT(Sender sender) {
            this.sender = sender;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    String ping = sender.getPing();
                    try {
                        if (Integer.valueOf(ping) > 3) {
                            System.out.println("Ping muito alto");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(ping);

                    }
                    Thread.sleep(5000);
                } catch (RemoteException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

}
