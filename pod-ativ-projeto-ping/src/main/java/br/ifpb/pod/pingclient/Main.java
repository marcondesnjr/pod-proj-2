package br.ifpb.pod.pingclient;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import br.ifpb.pod.pingserver.PingServer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) throws RemoteException, NotBoundException, AlreadyBoundException {

        PingClient pc = new PingClientImpl();

        while (true) {
            try {
                Thread.sleep(5 * 1000);
                pc.ping();
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
