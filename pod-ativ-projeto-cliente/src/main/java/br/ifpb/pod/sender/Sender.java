package br.ifpb.pod.sender;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Sender extends Remote {
	
	String sendMessage(String msg) throws RemoteException;

        String getResponse() throws RemoteException;
        
        String getPing() throws RemoteException;
        
}
