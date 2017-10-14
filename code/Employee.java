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
import javax.swing.ImageIcon;

public class Employee {
    private int id;
    private String name;
    private String job;
    private double salary;
    private int age;
    private String birthDate;
    private String joinDate;
    private String description;
    private ImageIcon image;

    public Employee(int id, String name, String job, double salary, int age, String birthDate, String joinDate, String description, ImageIcon image) {
        this.id = id;
        this.name = name;
        this.job = job;
        this.salary = salary;
        this.age = age;
        this.birthDate = birthDate;
        this.joinDate = joinDate;
        this.description = description;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }

    public double getSalary() {
        return salary;
    }

    public int getAge() {
        return age;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public String getDescription() {
        return description;
    }

    public ImageIcon getImage() {
        return image;
    }
    
}
