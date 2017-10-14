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
public class Message extends Service{
    
    public String messageType;
    // check bill
    // call waiter
    public Message(String s,int tableNum){
        super("Message",tableNum);
        this.messageType = s;
    }
    
}
