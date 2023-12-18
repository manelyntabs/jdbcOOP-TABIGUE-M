
package jdbcoop.activity.oop;

import jdbcoop.activity.oop.utils.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
/**
 *
 * @author liezel
 */
public class Main { 
    static Database db = new Database();
    static Connection conn = db.connect();
    static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
    
    System.out.println("1=insert 2=update");
    System.out.print("Choose option: ");
    String choose = sc.nextLine();
    
    switch(choose){
        case "1":
            insertStudent();
           break;
        case "2":
           System.out.print("Enter Id: ");
           String id = sc.nextLine();
            
           updateStudent(id);
           break;
    }
    
    viewAll();
        
    }
    
    public static void viewAll() {
        try {
            String sql = "SELECT * FROM students";
//            Statement st = conn.createStatement();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("id: "+rs.getInt("id") + " First Name: "+rs.getString("firstname") + " Last Name: "+ rs.getString("lastname") + " Address: "+ rs.getString("address"));
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }

    }
    
    public static void updateStudent(String id) {
        try {
            System.out.println("1=id 2=firstname 3=lastname 4=address");
            System.out.print("Pick column to update:");
            int column = sc.nextInt();
            String columnname = "";
            
            if(column==1){
               columnname = "id";
            }else if(column==2){
               columnname = "firstname";
            }else if(column==3){
                columnname = "lastname";
            }else if(column==4){
                columnname = "adress";
            }else{
                System.out.print("column not found");
            }
            
            sc = new Scanner(System.in);
            System.out.print("Enter new value for "+columnname+": " );
            String newValue = sc.nextLine();
            
            String sql = "update students set "+ columnname +"=? where id ="+id;
//            Statement st = conn.createStatement();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, newValue);
            
            ps.executeUpdate();
            System.out.println("Update successful");
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }

    }
    
     public static void insertStudent() {
        sc = new Scanner(System.in);
        
        System.out.print("Enter first name: ");
        String firstname = sc.nextLine();
        System.out.print("Enter last name: ");
        String lastname = sc.nextLine();
        System.out.print("Enter address: ");
        String address = sc.nextLine();
        
        try {
            String fname="Jean", lname = "Cute", add = "kaayu";
            String sql = "INSERT INTO `students`(`firstname`, `lastname`, `address`)"
                    +" VALUES (?,?,?)";
          
//            Statement st = conn.createStatement();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, firstname);
            ps.setString(2, lastname);
            ps.setString(3, address);
            
            ps.executeUpdate();
            System.out.println("Insert successful");
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }

    }
    
}
