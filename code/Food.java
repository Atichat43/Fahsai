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

import java.io.Serializable;
import javax.swing.ImageIcon;

public class Food implements Serializable {
    private String englishName;
    private String thaiName;
    private double price;
    private String englishCategory;
    private String thaiCategory;
    private ImageIcon image;
    private int count;
    private boolean availability;

    public Food(String englishName, String thaiName, double price, String englishCategory, String thaiCategory, ImageIcon image,int count,boolean availability) {
        this.englishName = englishName;
        this.thaiName = thaiName;
        this.price = price;
        this.englishCategory = englishCategory;
        this.thaiCategory = thaiCategory;
        this.image = image;
        this.count = count;
        this.availability = availability;
    }

    public boolean isAvailability() {
        return availability;
    }
    
    public int getCount() {
        return count;
    }
    
    public String getName(int value) {
        if (value == 0) {
            return englishName;
        } else {
            return thaiName;
        }
    }

    public double getPrice() {
        return price;
    }

    public String getCategory(int value) {
        if (value == 0) {
            return englishCategory;
        } else {
            return thaiCategory;
        }
    }

    public ImageIcon getImage() {
        return image;
    }

    @Override
    public String toString() {
        StringBuffer sb= new StringBuffer();
        sb.append("Food: ");
        sb.append(this.englishName);
        sb.append(" : ");
        sb.append(this.thaiName);
        sb.append(" : ");
        sb.append(this.price);
        sb.append(" : ");
        sb.append(this.englishCategory);
        sb.append(" : ");
        sb.append(this.thaiCategory);
        sb.append(" : ");
        sb.append(this.count);
        sb.append(" : ");
        sb.append(this.availability);
        return sb.toString();
    }
    
}
