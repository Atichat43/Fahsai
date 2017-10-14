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
public class SetupNewTable extends Service {
    public Table table;
    String typeOfSetup;
    // Coming In 
    // Leaving
    public SetupNewTable(Table table,int tableNumber,String type){
        super("Setup",tableNumber);
        this.table = table;
        this.typeOfSetup = type;
    }
    
    public Table getTableInfo(){
        return this.table;
    }
    
    public String getTypeOfSetup(){
        return this.typeOfSetup;
    }
    
}
