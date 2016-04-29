package br.ifpb.pod.sender;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

import br.ifpb.pod.pingclient.PingClient;
import br.ifpb.pod.receiver.Receiver;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.SocketTimeoutException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;

public class SenderImpl extends UnicastRemoteObject implements Sender {

    private int MAX_LATENCY = 3; //seconds

    protected SenderImpl() throws RemoteException {
        super();
    }

    public String sendMessage(String msg) throws RemoteException {
        File msgFile = new File("C:\\Users\\Junior\\Documents\\pod-proj\\msg.txt");
        
        try {
            FileUtils.write(msgFile, msg);
        } catch (IOException ex) {
            Logger.getLogger(SenderImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        new Thread(() -> {
            while (true) {
                try {
                    if (msgFile.exists()) {
                        System.out.println("tentou mandar: " + msg);
                        String mensagem = new Scanner(msgFile).nextLine();
                        Registry registry = LocateRegistry.getRegistry("localhost", 8081);
                        Receiver receiver = (Receiver) registry.lookup("Receiver");
                        String resp = receiver.receive(mensagem);
                        
                        System.out.println("Recebeu resposta : "+ resp);
                        File respFile = new File("C:\\Users\\Junior\\Documents\\pod-proj\\resp.txt");
                        FileUtils.write(respFile, resp);
                        return;
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(SenderImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }).start();
        return "RECEBIDO PELO SENDER";
    }

    @Override
    public String getResponse() throws RemoteException {
        try {
            File respFile = new File("C:\\Users\\Junior\\Documents\\pod-proj\\resp.txt");
            if (respFile.exists()) {
                String result = null;
                Scanner scan = new Scanner(respFile);
                if (scan.hasNext()) {
                    result = scan.nextLine();
                }
                PrintWriter pw = new PrintWriter(respFile);
                pw.close();
                return result;
            } else {
                return null;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SenderImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public String getPing() throws RemoteException {
        File pingFile = new File("C:\\Users\\Junior\\Documents\\pod-proj\\ping.txt");
        if (pingFile.exists()) {
            try {
                return new Scanner(pingFile).nextLine();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(SenderImpl.class.getName()).log(Level.SEVERE, null, ex);

            }
        }
        return null;
    }

}
