package br.ifpb.pod.serverapp;

import br.ifpb.pod.pingserver.PingServer;
import br.ifpb.pod.pingserver.PingServerImpl;
import br.ifpb.pod.receiver.Receiver;
import br.ifpb.pod.receiver.ReceiverImpl;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {

    public static void main(String[] args) throws RemoteException, AlreadyBoundException, NotBoundException {
        //REGISTRANDO O SERVER APP
        System.setProperty("java.rmi.server.hostname", "localhost");
        Registry registry = LocateRegistry.createRegistry(8080);
        ServerApp s = new ServerAppImpl();
        registry.bind("ServerApp", s);

        //REGISTRANDO O PING SERVER
        System.setProperty("java.rmi.server.hostname", "localhost");
        registry = LocateRegistry.createRegistry(8083);
        PingServer ps = new PingServerImpl();
        registry.bind("PingServer", ps);

        //PEGANDO O SERVER APP
        registry = LocateRegistry.getRegistry("localhost", 8080);
        ServerApp serverApp = (ServerApp) registry.lookup("ServerApp");

        //REGISTRANDO O RECEIVER
        System.setProperty("java.rmi.server.hostname", "localhost");
        registry = LocateRegistry.createRegistry(8081);
        Receiver r = new ReceiverImpl(serverApp);
        registry.bind("Receiver", r);

    }

}
