package br.ifpb.pod.pingclient;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import br.ifpb.pod.pingserver.PingServer;
import br.ifpb.pod.sender.Sender;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;

public class PingClientImpl extends UnicastRemoteObject implements PingClient {

    protected PingClientImpl() throws RemoteException {
        super();
    }

    public String[] ping() throws RemoteException {
        try {
            long pInicial = System.currentTimeMillis();
            String result = "";
            try {
                Registry registry = LocateRegistry.getRegistry("localhost", 8083);
                PingServer ps = (PingServer) registry.lookup("PingServer");
                String[] f = ps.ping();
            } catch (Exception ex) {
                result = "Servidor indisponivel";
            }
            long pFinal = System.currentTimeMillis();
            File pingFile = new File("C:\\Users\\Junior\\Documents\\pod-proj\\ping.txt");
            if (result.isEmpty()) {
                FileUtils.write(pingFile, Long.toString(pFinal-pInicial));
            } else {
                FileUtils.write(pingFile, "Servidor indisponivel");
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PingClientImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PingClientImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
