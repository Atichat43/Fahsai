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
import java.net.*;
import java.io.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class SocketHost extends Thread {

    protected Socket clientSocket;
    static ServerSocket serverSocket = null;
    ObjectOutputStream out;
    ObjectInputStream in;

    public static void main(String[] args) throws IOException {
        try {
            serverSocket = new ServerSocket(10008);
            System.out.println("Connection Socket Created");
            try {
                while (true) {
                    System.out.println("Waiting for Connection");
                    new SocketHost(serverSocket.accept());
                }
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port: 10008.");
            System.exit(1);
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                System.err.println("Could not close port: 10008.");
                System.exit(1);
            }
        }
    }

    private SocketHost(Socket clientSoc) {

        clientSocket = clientSoc;
        start();
    }

    public void run() {
        System.out.println("New Communication Thread Started");

        try {
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new ObjectInputStream(clientSocket.getInputStream());
            Service demand;
            while (true) {
                System.out.println("Waiting for demand: ");
                demand = (Service) in.readObject();
                System.out.println(demand);
                Counter.respondToCommand(demand);

            }

        } catch (Exception e) {
            //System.err.println(e);
            JFrame frame = new JFrame("Error Occured");
            JOptionPane.showMessageDialog(frame, "Someting went wrong with the host", "Host Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

    }

}
