package br.ifpb.pod.receiver;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import br.ifpb.pod.serverapp.ServerApp;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReceiverImpl extends UnicastRemoteObject implements Receiver {

    private ServerApp server;

    public ReceiverImpl(ServerApp server) throws RemoteException {
        super();
        this.server = server;

    }

    public String sendServerApp(String msg) {
        // TODO Auto-generated method stub
        return null;
    }

    public String receive(String msg) throws RemoteException {
        System.out.println("Recebeu : "+ msg);
        return server.processMessage(msg);
    }

    

}
