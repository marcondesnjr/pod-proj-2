package br.ifpb.pod.serverapp;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerAppImpl extends UnicastRemoteObject implements ServerApp {

	protected ServerAppImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	public String processMessage(String msg) throws RemoteException {
            System.out.println("Recebido : "+ msg);
            try {
                Thread.sleep(10 * 1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ServerAppImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            ;
            System.out.println("Retornado" );
            return "msgProcessada : "+ msg;
	}

}
