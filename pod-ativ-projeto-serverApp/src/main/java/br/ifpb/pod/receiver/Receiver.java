package br.ifpb.pod.receiver;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Receiver extends Remote  {
    
        	
	String receive(String msg) throws RemoteException;
	
	
}
