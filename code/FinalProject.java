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
public class FinalProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        /*
        String s = "This.is.The.End";
        String s1 = ".";
        String[] temp = s.split("\\.");
        for (String i : temp) {
            System.out.println("-> " + i);
        }
        System.out.println(s.substring(0, s.length() - 1));
        if (s1.equals(".")) {
            System.out.println("in");
        }

        System.out.println("MOD:        -1 % 8 = " + (-1 % 8));
        System.out.println("Algo MOD:   -1 % 8 = " + (((-1 % 8) + 8) % 8));
        System.out.println("floorMod:   -1 % 8 = " + Math.floorMod(-1, 8));

        Map<String, Integer> a = new HashMap<String, Integer>();
        Map<String, Integer> b = new HashMap<String, Integer>();

        a.put("Charn", 1);
        a.put("Bestie", 2);
        b.put("Boss", 1);
        b.put("Charn", 3);
        b.remove("Charn");
        b.put("First", 0);
        a.put("Apple", 9);
        System.out.println(a);
        System.out.println(b);

        b.putAll(a);
        System.out.println(b);

        for (String i : b.keySet()) {
            if (i.equals("First")) {
                System.out.println("Found");
                b.remove(i);
            }
        }

        System.out.println(b);
        */
        
        
       Database db = new Database("192.168.1.45","charn","123456");
       ArrayList<String> categories = db.getEnglishCategory();
       for (String i:categories){
           System.out.println(i);
       }
       ArrayList<Food> foodAccordingToCetagory = db.getFoodByCategory(categories.get(0), 0);
       for (Food i:foodAccordingToCetagory){
           System.out.println(i);
       }
    }

}
