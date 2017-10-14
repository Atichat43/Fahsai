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
public class Table implements java.io.Serializable {

    public int id;
    public int numberOfPeople;
    public boolean avaliable;
    public boolean checkingBill;
    //public ArrayList<Food> foodOrdered;
    //public ArrayList<Food> drinksOrdered;
    public Map<Food, Integer> foodOrdered;
    public Map<Food, Integer> drinksOrdered;
    public String enteredTime;
    public String order;

    public Table(int id) {
        this.id = id;
        //this.foodOrdered = new ArrayList();
        //this.drinksOrdered = new ArrayList();
        this.foodOrdered = new HashMap<Food, Integer>();
        this.drinksOrdered = new HashMap<Food, Integer>();

        this.reset();
    }

    public Table(Table otherTable, int order) {
        this.id = otherTable.id;
        this.foodOrdered = otherTable.foodOrdered;
        this.drinksOrdered = otherTable.drinksOrdered;
        this.numberOfPeople = otherTable.numberOfPeople;
        this.enteredTime = otherTable.enteredTime;
        this.avaliable = false;
        this.order = ""+order;
        for (int i = this.order.length();i<4;++i){
            this.order = "0"+this.order;
        }

    }

    public void setUp(int numberOfPeople, String enteredTime) {
        this.numberOfPeople = numberOfPeople;
        this.enteredTime = enteredTime;
        this.avaliable = false;
    }

    public void reset() {
        this.numberOfPeople = 0;
        this.avaliable = true;
        this.checkingBill = false;
        this.foodOrdered.clear();
        this.drinksOrdered.clear();
    }

    public int getNumberOfPeople() {
        return this.numberOfPeople;
    }

    private double getPrices() {
        double totalPrice = 0;

        for (Food i : foodOrdered.keySet()) {
            totalPrice += i.getPrice() * foodOrdered.get(i);
        }
        for (Food i : drinksOrdered.keySet()) {
            totalPrice += i.getPrice() * drinksOrdered.get(i);
        }
        return totalPrice;
    }

    public double getVAT() {
        return getPrices() * 0.07;
    }

    public double getServiceCharge() {
        return getPrices() * 0.15;
    }

    public double getTotalPrice() {
        return getPrices() + getVAT() + getServiceCharge();
    }

    @Override
    public String toString() {
        return "Table: " + id + "\nNumber of People: " + numberOfPeople + "\nEntered Time: " + enteredTime + "\n";
    }

    public String getEnteredTime() {
        return this.enteredTime;
    }

    public void addOrderd(Map<Food, Integer> selectedFood, Map<Food, Integer> selectedDrinks) {

        for (Food i : selectedFood.keySet()) {
            System.out.println(i + "\t" + selectedFood.get(i));
        }
        for (Food i : selectedFood.keySet()) {
            for (Food j : this.foodOrdered.keySet()) {
                if (i.getName(0).equals(j.getName(0))) {
                    System.out.println("adding Order");
                    System.out.println("\t\t" + this.foodOrdered.get(j) + "\t" + selectedFood.get(i));
                    selectedFood.replace(i, this.foodOrdered.get(j) + selectedFood.get(i));
                    this.foodOrdered.remove(j);
                    System.out.println("\t\t" + selectedFood.get(i));
                    break;
                }
            }
        }

        this.foodOrdered.putAll(selectedFood);

        for (Food i : selectedDrinks.keySet()) {
            for (Food j : this.drinksOrdered.keySet()) {
                if (i.getName(0).equals(j.getName(0))) {
                    selectedDrinks.replace(i, this.drinksOrdered.get(j) + selectedDrinks.get(i));
                    this.drinksOrdered.remove(j);
                    break;
                }
            }
        }

        this.drinksOrdered.putAll(selectedDrinks);

        /*
         for (Food i : selectedFood.keySet()) {
            if (this.foodOrdered.containsKey(i)) {
                this.foodOrdered.replace(i, this.foodOrdered.get(i) + selectedFood.get(i));
                selectedFood.remove(i);
            }
        }
        this.drinksOrdered.putAll(selectedDrinks);
        
         for (Food i : selectedDrinks.keySet()) {
            if (this.drinksOrdered.containsKey(i)) {
                this.drinksOrdered.replace(i, this.drinksOrdered.get(i) + selectedDrinks.get(i));
                selectedDrinks.remove(i);
            }
        }
        this.drinksOrdered.putAll(selectedDrinks);
         */
    }
    
    public String getOrder(){
        return this.order;
    }
}
