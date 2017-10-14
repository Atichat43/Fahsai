/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dhitiwat
 */
public class Clock extends Thread {

    Date dateObj;
    DateFormat dateFormat;

    public Clock() {
        dateFormat = new SimpleDateFormat("dd, EEE. MMM. yyyy  HH:mm:ss");
    }

    @Override
    public void run() {
        while (true) {
            dateObj = new Date();
            Counter.setTime(dateFormat.format(dateObj.getTime()));
            try {
                Thread.sleep(999);
            } catch (InterruptedException ex) {
                Logger.getLogger(Clock.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
