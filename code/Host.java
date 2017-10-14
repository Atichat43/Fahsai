/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject;

import java.io.IOException;

/**
 *
 * @author Dhitiwat
 */
public class Host {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        for (int i = 0 ;i <12;++i){
            Counter.notiBarStatus[i] = false;
        }
        String[] arguments = new String[] {"123"};
        SocketHost.main(arguments);
    }
    
}
