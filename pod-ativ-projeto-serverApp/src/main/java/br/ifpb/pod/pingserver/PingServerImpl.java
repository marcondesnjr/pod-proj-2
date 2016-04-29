package br.ifpb.pod.pingserver;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PingServerImpl extends UnicastRemoteObject implements PingServer {

    public PingServerImpl() throws RemoteException {
        super();
        // TODO Auto-generated constructor stub
    }

    public String[] ping() throws RemoteException {
        // TODO Auto-generated method stub
        return null;
    }

}
