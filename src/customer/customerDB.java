package customer;

import BookCar.BookNowController;
import connection.ConnectionClass;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
//class for customer data base
public class customerDB {
    public ObservableList fetchBookingsTable(ObservableList<BookingHist> memList) {
//this will fetch the bookings table from the data base
        try {
//            convert it into a class objects
//            and add it into a list
//            then will return the list
            Connection connection = ConnectionClass.getConnection();
            ResultSet statement = connection.createStatement().executeQuery("select * from bookings");

            while(statement.next()) {
                memList.add(new BookingHist(statement.getInt("carNo"),statement.getInt("user_id"),
                        statement.getInt("hour"),statement.getInt("min"),statement.getInt("sec"),
                        statement.getInt("day"),statement.getInt("month"),statement.getInt("year"),statement.getInt("amount" )));

            }
        } catch (SQLException ex) {
            Logger.getLogger(BookNowController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return memList;
    }
    public ObservableList fetchRidesTable(ObservableList<Rides> memList) {
//fetch the rides table from mysql data base
        try {
            Connection connection = ConnectionClass.getConnection();
            ResultSet statement = connection.createStatement().executeQuery("select * from ridesprogress");
//            convert it into a class objects
//            and add it into a list
//            then will return the list
            while(statement.next()) {
                memList.add(new Rides(statement.getInt("carNo"),statement.getInt("id"),
                        statement.getInt("hour"),statement.getInt("min"),statement.getInt("sec"),
                        statement.getInt("day"),statement.getInt("month"),statement.getInt("year")));

            }
        } catch (SQLException ex) {
            Logger.getLogger(BookNowController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return memList;
    }
        PreparedStatement pst = null;
    public PreparedStatement calculateAmount(int car_No,int user_id,int hour,int min,int sec,int day,int month,int year,int amount){
        try {
            Connection connection = ConnectionClass.getConnection();
            //pst = connection.prepareStatement("insert into bookings (carNo,user_id,hour,min,sec,day,month,year,amount)values(car_No,user_id,hour,min,sec,day,month,year,amount)");
            String sql = ("insert into bookings (carNo,user_id,hour,min,sec,day,month,year,amount)values(?,?,?,?,?,?,?,?,?)");
            pst = connection.prepareStatement(sql);
            pst.setInt(1, car_No);
            pst.setInt(2,user_id);
            pst.setInt(3,hour);
            pst.setInt(4,min);
            pst.setInt(5,sec);
            pst.setInt(6,day);
            pst.setInt(7,month);
            pst.setInt(8,year);
            pst.setInt(9,amount);
            pst.execute();
//            seperate both the investor's earnings and the manager's earnings
//            add them into the data base

            int invest_amount=(amount*80)/100;
            int manager_amount=amount-invest_amount;
            String sql1 = ("insert into investor_earning (earning) values (?)");
            pst = connection.prepareStatement(sql1);
            pst.setInt(1, invest_amount);
            pst.execute();
            String sql2 = ("insert into manager_earning (earning)values(?)");
            pst = connection.prepareStatement(sql2);
            pst.setInt(1, manager_amount);
            pst.execute();
            String sql3= ("drop table ridesprogress");
            pst = connection.prepareStatement(sql3);
            pst.execute(sql3);
            String sql4= ("create table ridesprogress (carNo INT NOT NULL,id INT NULL,hour INT NULL,min INT NULL,sec INT NULL,day INT NULL,month INT NULL,year INT NULL,PRIMARY KEY (carNo))");
            pst = connection.prepareStatement(sql4);
            pst.execute(sql4);
            return pst;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
//    this will add the investor helpline query into the mysql server
    public PreparedStatement setInvestHelpline() {
        try {
            Connection connection = ConnectionClass.getConnection();
            pst = connection.prepareStatement("insert into helpline (queryy,cust_name)values(?,?)");
            // JOptionPane.showMessageDialog(null, "Connection Established");
            return pst;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    //    this will add the customer into the mysql server
    public PreparedStatement setCustomerInfoTable() {
        try {
            Connection connection = ConnectionClass.getConnection();
            pst = connection.prepareStatement("insert into customer_info (name,password,age,DOB,phone,email)values(?,?,?,?,?,?)");
            // JOptionPane.showMessageDialog(null, "Connection Established");
            return pst;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    //    this will add the  helpline query into the mysql server
    public PreparedStatement setHelpline() {
        try {
            Connection connection = ConnectionClass.getConnection();
            pst = connection.prepareStatement("insert into helpline (queryy,cust_name)values(?,?)");
            // JOptionPane.showMessageDialog(null, "Connection Established");
            return pst;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }


}
