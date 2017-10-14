/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Database {

    private Connection connection;
    private String url;

    public Database(String host, String username, String password) {
        url = "jdbc:mysql://" + host + ":3306/database?useSSL=true&user=" + username + "&password=" + password;
    }

    private void connect() {
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Couldn't Connect to Database", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        System.out.println("Database Connected");
    }

    private void disConnect() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Couldn't Disconnect from Database", "Error", JOptionPane.ERROR_MESSAGE);
            }
            System.out.println("Database Disconnected");
        }
    }

    public ArrayList<String> getEnglishCategory() {
        ArrayList<String> result = new ArrayList<>();

        try {
            connect();

            PreparedStatement query = connection.prepareStatement("select distinct EnglishCategory from food where Availability=?");
            query.setInt(1, 1);
            ResultSet rs = query.executeQuery();

            while (rs.next()) {
                String category = rs.getString("EnglishCategory");
                result.add(category);
            }

            query.close();
            rs.close();

        } catch (SQLException ex) {
            System.out.println("Couldn't retrieve English Category List");
        } finally {
            disConnect();
        }

        return result;
    }

    public ArrayList<String> getThaiCategory() {
        ArrayList<String> result = new ArrayList<>();

        try {
            connect();

            PreparedStatement query = connection.prepareStatement("select distinct ThaiCategory from food where Availability=?");
            query.setInt(1, 1);
            ResultSet rs = query.executeQuery();

            while (rs.next()) {
                String category = rs.getString("ThaiCategory");
                result.add(category);
            }

            query.close();
            rs.close();

        } catch (SQLException ex) {
            System.out.println("Couldn't retrieve Thai Category List");
        } finally {
            disConnect();
        }

        return result;
    }

    private BufferedImage getBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }

        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        return bimage;
    }

    public boolean isExist(String englishName) {
        try {
            connect();

            PreparedStatement query = connection.prepareStatement("select * from food where EnglishName=?");
            query.setString(1, englishName);
            ResultSet rs = query.executeQuery();
            int count = 0;
            while (rs.next()) {
                count++;
            }

            if (count == 0) {
                rs.close();
                query.close();
                return false;
            }

            rs.close();
            query.close();

        } catch (SQLException ex) {
            System.out.println("Check Exist Error");
        } finally {
            disConnect();
        }

        return true;
    }

    public boolean updateFood(String oldName, Food food) {
        try {
            connect();

            PreparedStatement query = connection.prepareStatement("update food set EnglishName=?, ThaiName=?, Price=?, EnglishCategory=?, ThaiCategory=?, Image=?, OrderCount=?,Availability=? where EnglishName=?");

            int col = 1;
            query.setString(col++, food.getName(0));
            query.setString(col++, food.getName(1));
            query.setDouble(col++, food.getPrice());
            query.setString(col++, food.getCategory(0));
            query.setString(col++, food.getCategory(1));

            ImageIcon originalImage = food.getImage();
            BufferedImage bimage = getBufferedImage(originalImage.getImage());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bimage, "jpg", baos);
            byte[] byteArray = baos.toByteArray();

            query.setBytes(col++, byteArray);
            query.setInt(col++, food.getCount());

            boolean available = food.isAvailability();
            int availability;
            if (available) {
                availability = 1;
            } else {
                availability = 0;
            }

            query.setInt(col++, availability);

            query.setString(col++, oldName);

            query.executeUpdate();
            query.close();

            return true;
        } catch (SQLException ex) {
            System.out.println("Couldn't update " + oldName);
        } catch (IOException ex) {
            System.out.println("Couldn't convert update image file");
        } finally {
            disConnect();
        }

        return false;
    }

    public boolean deleteFood(String name) {
        try {
            connect();

            PreparedStatement query = connection.prepareStatement("delete from food where EnglishName=?");

            query.setString(1, name);

            query.executeUpdate();

            query.close();

            return true;
        } catch (SQLException ex) {
            System.out.println("Couldn't delete " + name);
        } finally {
            disConnect();
        }

        return false;
    }

    public boolean addFood(Food food) {

        try {
            connect();

            PreparedStatement query = connection.prepareStatement("insert into food (EnglishName,ThaiName,Price,EnglishCategory,ThaiCategory,Image,OrderCount,Availability) values(?,?,?,?,?,?,?,?)");

            int col = 1;
            query.setString(col++, food.getName(0));
            query.setString(col++, food.getName(1));
            query.setDouble(col++, food.getPrice());
            query.setString(col++, food.getCategory(0));
            query.setString(col++, food.getCategory(1));

            ImageIcon originalImage = food.getImage();
            BufferedImage bimage = getBufferedImage(originalImage.getImage());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bimage, "jpg", baos);
            byte[] byteArray = baos.toByteArray();

            query.setBytes(col++, byteArray);
            query.setInt(col++, food.getCount());

            boolean available = food.isAvailability();
            int availability;
            if (available) {
                availability = 1;
            } else {
                availability = 0;
            }

            query.setInt(col++, availability);

            query.executeUpdate();
            query.close();
            return true;
        } catch (SQLException ex) {
            System.out.println("Couldn't Add Food to Database");
        } catch (IOException ex) {
            System.out.println("Couldn't Convert Food Image File.");
        } finally {
            disConnect();
        }
        return false;
    }

    public ArrayList<Food> getAllFood() {
        ArrayList<Food> result = new ArrayList<>();

        try {
            connect();

            PreparedStatement query = connection.prepareStatement("select * from food where Availability=?");
            query.setInt(1, 1);
            ResultSet rs = query.executeQuery();

            while (rs.next()) {
                String engName = rs.getString("EnglishName");
                String thaiName = rs.getString("ThaiName");
                double price = rs.getDouble("Price");
                String engCat = rs.getString("EnglishCategory");
                String thaiCat = rs.getString("ThaiCategory");
                Blob blob = rs.getBlob("Image");
                byte[] bytes = blob.getBytes(1, (int) blob.length());
                ImageIcon image = new ImageIcon(bytes);
                int count = rs.getInt("OrderCount");
                int available = rs.getInt("Availability");
                boolean availability;
                if (available == 1) {
                    availability = true;
                } else {
                    availability = false;
                }
                result.add(new Food(engName, thaiName, price, engCat, thaiCat, image, count, availability));
            }
            query.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Couldn't get all Food");
        } finally {
            disConnect();
        }
        return result;
    }

    public ArrayList<Food> getFoodByCategory(String category, int selectedLanguage) {
        ArrayList<Food> result = new ArrayList<>();

        try {
            connect();
            PreparedStatement query;
            if (selectedLanguage == 0) {
                query = connection.prepareStatement("select * from food where EnglishCategory=? and Availability=?");
            } else {
                query = connection.prepareStatement("select * from food where ThaiCategory=? and Availability=?");
            }
            query.setString(1, category);
            query.setInt(2, 1);
            ResultSet rs = query.executeQuery();

            while (rs.next()) {
                String engName = rs.getString("EnglishName");
                String thaiName = rs.getString("ThaiName");
                double price = rs.getDouble("Price");
                String engCat = rs.getString("EnglishCategory");
                String thaiCat = rs.getString("ThaiCategory");
                Blob blob = rs.getBlob("Image");
                byte[] bytes = blob.getBytes(1, (int) blob.length());
                ImageIcon image = new ImageIcon(bytes);
                int count = rs.getInt("OrderCount");
                int available = rs.getInt("Availability");
                boolean availability;
                if (available == 1) {
                    availability = true;
                } else {
                    availability = false;
                }
                result.add(new Food(engName, thaiName, price, engCat, thaiCat, image, count, availability));
            }

            rs.close();
            query.close();
        } catch (SQLException ex) {
            System.out.println("Couldn't get " + category + " foods.");
        } finally {
            disConnect();
        }

        return result;
    }

    public void updateCount(Food food, int count) {
        String englishName = food.getName(0);
        try {
            connect();
            PreparedStatement query = connection.prepareStatement("select OrderCount from food where EnglishName=?");
            query.setString(1, englishName);
            ResultSet rs = query.executeQuery();
            int originalCount = 0;
            while (rs.next()) {
                originalCount = rs.getInt("OrderCount");
            }

            query.close();
            rs.close();

            query = connection.prepareStatement("update food set OrderCount =? where EnglishName=?");
            query.setInt(1, count + originalCount);
            query.setString(2, englishName);
            query.executeUpdate();
            query.close();

        } catch (SQLException ex) {
            System.out.println("Couldn't update order count of " + englishName + ".");
        } finally {
            disConnect();
        }
    }

    public boolean addEmployee(Employee emp) {
        try {
            connect();

            PreparedStatement query = connection.prepareStatement("insert into employee (Name,Job,Salary,Age,BirthDate,JoinDate,Description,Image) values (?,?,?,?,?,?,?,?)");

            int col = 1;
            query.setString(col++, emp.getName());
            query.setString(col++, emp.getJob());
            query.setDouble(col++, emp.getSalary());
            query.setInt(col++, emp.getAge());
            query.setString(col++, emp.getBirthDate());
            query.setString(col++, emp.getJoinDate());
            query.setString(col++, emp.getDescription());

            ImageIcon originalImage = emp.getImage();
            BufferedImage bimage = getBufferedImage(originalImage.getImage());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bimage, "jpg", baos);
            byte[] byteArray = baos.toByteArray();

            query.setBytes(col++, byteArray);

            query.executeUpdate();

            query.close();

            return true;
        } catch (SQLException ex) {
            System.out.println("Couldn't add Employee");
        } catch (IOException ex) {
            System.out.println("Couldn't Convert Employee Image File.");
        } finally {
            disConnect();
        }

        return false;
    }

    public ArrayList<Employee> getEmployeeByJob(String jobCategory) {
        ArrayList<Employee> result = new ArrayList<>();

        try {
            connect();

            PreparedStatement query = connection.prepareStatement("select * from employee where Job=?");
            query.setString(1, jobCategory);

            ResultSet rs = query.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("Name");
                String job = rs.getString("Job");
                double salary = rs.getDouble("Salary");
                int age = rs.getInt("Age");
                String birthDate = rs.getString("BirthDate");
                String joinDate = rs.getString("JoinDate");
                String description = rs.getString("Description");
                Blob blob = rs.getBlob("Image");
                byte[] bytes = blob.getBytes(1, (int) blob.length());
                ImageIcon image = new ImageIcon(bytes);

                result.add(new Employee(id, name, job, salary, age, birthDate, joinDate, description, image));
            }

            rs.close();
            query.close();
        } catch (SQLException ex) {
            System.out.println("Couldn't get " + jobCategory);
        } finally {
            disConnect();
        }

        return result;
    }

    public boolean updateEmployee(Employee emp) {
        try {
            connect();

            PreparedStatement query = connection.prepareStatement("update employee set Name=?,Job=?,Salary=?,Age=?,BirthDate=?,JoinDate=?,Description=?,Image=? where ID=?");
            int col = 1;
            query.setString(col++, emp.getName());
            query.setString(col++, emp.getJob());
            query.setDouble(col++, emp.getSalary());
            query.setInt(col++, emp.getAge());
            query.setString(col++, emp.getBirthDate());
            query.setString(col++, emp.getJoinDate());
            query.setString(col++, emp.getDescription());

            ImageIcon originalImage = emp.getImage();
            BufferedImage bimage = getBufferedImage(originalImage.getImage());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bimage, "jpg", baos);
            byte[] byteArray = baos.toByteArray();

            query.setBytes(col++, byteArray);
            query.setInt(col++, emp.getId());

            query.executeUpdate();

            query.close();

            return true;
        } catch (SQLException ex) {
            System.out.println("Couldn't update employee");
        } catch (IOException ex) {
            System.out.println("Couln't covert employee Image");
        } finally {
            disConnect();
        }

        return false;
    }

    public boolean deleteEmployee(Employee emp) {
        try {
            connect();

            PreparedStatement query = connection.prepareStatement("delete from employee where ID=?");

            query.setInt(1, emp.getId());

            query.executeUpdate();

            query.close();

            return true;
        } catch (SQLException ex) {
            System.out.println("Couldn't delete employee");
        } finally {
            disConnect();
        }

        return false;
    }

    public ArrayList<String> managerGetEnglishCategory() {
        ArrayList<String> result = new ArrayList<>();

        try {
            connect();

            PreparedStatement query = connection.prepareStatement("select distinct EnglishCategory from food");
            ResultSet rs = query.executeQuery();

            while (rs.next()) {
                String category = rs.getString("EnglishCategory");
                result.add(category);
            }

            query.close();
            rs.close();

        } catch (SQLException ex) {
            System.out.println("Couldn't retrieve English Category List");
        } finally {
            disConnect();
        }

        return result;
    }

    public ArrayList<String> managerGetThaiCategory() {
        ArrayList<String> result = new ArrayList<>();

        try {
            connect();

            PreparedStatement query = connection.prepareStatement("select distinct ThaiCategory from food");
            ResultSet rs = query.executeQuery();

            while (rs.next()) {
                String category = rs.getString("ThaiCategory");
                result.add(category);
            }

            query.close();
            rs.close();

        } catch (SQLException ex) {
            System.out.println("Couldn't retrieve Thai Category List");
        } finally {
            disConnect();
        }

        return result;
    }

    public ArrayList<Food> managerGetAllFood() {
        ArrayList<Food> result = new ArrayList<>();

        try {
            connect();

            PreparedStatement query = connection.prepareStatement("select * from food order by OrderCount desc");
            ResultSet rs = query.executeQuery();

            while (rs.next()) {
                String engName = rs.getString("EnglishName");
                String thaiName = rs.getString("ThaiName");
                double price = rs.getDouble("Price");
                String engCat = rs.getString("EnglishCategory");
                String thaiCat = rs.getString("ThaiCategory");
                Blob blob = rs.getBlob("Image");
                byte[] bytes = blob.getBytes(1, (int) blob.length());
                ImageIcon image = new ImageIcon(bytes);
                int count = rs.getInt("OrderCount");
                int available = rs.getInt("Availability");
                boolean availability;
                if (available == 1) {
                    availability = true;
                } else {
                    availability = false;
                }
                result.add(new Food(engName, thaiName, price, engCat, thaiCat, image, count, availability));
            }
            query.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Couldn't get all Food");
        } finally {
            disConnect();
        }
        return result;
    }

    public ArrayList<Food> managerGetFoodByCategory(String category, int selectedLanguage) {
        ArrayList<Food> result = new ArrayList<>();

        try {
            connect();
            PreparedStatement query;
            if (selectedLanguage == 0) {
                query = connection.prepareStatement("select * from food where EnglishCategory=?");
            } else {
                query = connection.prepareStatement("select * from food where ThaiCategory=?");
            }
            query.setString(1, category);
            ResultSet rs = query.executeQuery();

            while (rs.next()) {
                String engName = rs.getString("EnglishName");
                String thaiName = rs.getString("ThaiName");
                double price = rs.getDouble("Price");
                String engCat = rs.getString("EnglishCategory");
                String thaiCat = rs.getString("ThaiCategory");
                Blob blob = rs.getBlob("Image");
                byte[] bytes = blob.getBytes(1, (int) blob.length());
                ImageIcon image = new ImageIcon(bytes);
                int count = rs.getInt("OrderCount");
                int available = rs.getInt("Availability");
                boolean availability;
                if (available == 1) {
                    availability = true;
                } else {
                    availability = false;
                }
                result.add(new Food(engName, thaiName, price, engCat, thaiCat, image, count, availability));
            }

            rs.close();
            query.close();
        } catch (SQLException ex) {
            System.out.println("Couldn't get " + category + " foods.");
        } finally {
            disConnect();
        }

        return result;
    }
}
