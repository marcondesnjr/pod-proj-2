package br.ifpb.pod.clientapp;

import java.rmi.RemoteException;

import br.ifpb.pod.sender.Sender;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

    private Sender sender;

    public void setSender(Sender sender) {
        this.sender = sender;

    }

    public String runConsole(String msg) {
        try {

            return sender.sendMessage(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

}
