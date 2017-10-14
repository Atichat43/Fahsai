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
public class Service implements java.io.Serializable {
    protected String typeOfService;
    public int tableNumber;
    
    public Service(String type, int tableNumber){
        typeOfService = type;
        this.tableNumber = tableNumber;
    }
    
    public String getTypeOfService(){
        return typeOfService;
    }

    @Override
    public String toString() {
        return "Table : " + tableNumber + " - " + typeOfService; 
    }
    

}
