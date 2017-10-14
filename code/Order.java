/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject;
import java.util.*;

/**
 *
 * @author Dhitiwat
 */
public class Order extends Service{
    
    public Map<Food,Integer> foodOrder;
    public Map<Food,Integer> drinksOrder;
    
    public Order( Map<Food,Integer> f,  Map<Food,Integer> d,int tableNum){
        super("Order",tableNum);
        foodOrder = f;
        drinksOrder = d;
        
    }
}
