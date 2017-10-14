/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject;

/**
 *
 * @author Dhitiwat
 */
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class SocketClient {

    String serverHostName ;
    Socket echoSocket = null;
    ObjectOutputStream out = null;
    ObjectInputStream in = null;

    public SocketClient(String host) {
        this.serverHostName = host;
        System.out.println("Attempting to connect to host " + serverHostName + " on port 10008.");
        while (true) {
            try {
                echoSocket = new Socket(serverHostName, 10008);
                out = new ObjectOutputStream(echoSocket.getOutputStream());
                in = new ObjectInputStream(echoSocket.getInputStream());
                System.out.println("Connected");
                break;
            } catch (UnknownHostException e) {
                //System.err.println("Don't know about the host: " + serverHostName[hostNameIndex]);
                JFrame frame = new JFrame("Error Occured");
                JOptionPane.showMessageDialog(frame,"Don't know about the host: " + serverHostName,"Host Unknown",JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            } catch (IOException e) {
                //System.err.println("Couldn't get I/O for the connection to: " + serverHostName);
                JFrame frame = new JFrame("Error Occured");
                JOptionPane.showMessageDialog(frame,"Couldn't get I/O for the connection to: " + serverHostName + "\nTry Again","Couldn't Connect To Host",JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    public void send(Service o) throws IOException {
        System.out.println("Sending object: " + o + " to server");
        out.writeObject(o);
        out.flush();
        System.out.println("Object sent");

    }

    public Service recieve() {
        Service s = null;
        try {
            s = (Service) in.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return s;
    }

    @Override
    public void finalize() throws IOException {
        out.close();
        in.close();
        echoSocket.close();
    }

  

}
