package investor;

import BookCar.Car;
import connection.ConnectionClass;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class investorDB {

    public ObservableList fetchInvestedCarTable(ObservableList<Car> memList) {
//fetch the values from the server
//set them as class objects
//add them to a list
//return list
        try {
            Connection connection = ConnectionClass.getConnection();
            ResultSet statement = connection.createStatement().executeQuery("select * from islamabadcars");

            while(statement.next()) {
                memList.add(new Car(statement.getString("sector"), statement.getString("type"), statement.getInt("model"),
                        statement.getInt("number"), statement.getString("color"), statement.getInt("available")));

            }
        } catch (SQLException ex) {
            Logger.getLogger(InvestorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return memList;
    }
    public ObservableList fetchEarnings(ObservableList<Investor> memList) {
//fetch the values from the server
//set them as class objects
//add them to a list
//return list

        try {
            Connection connection = ConnectionClass.getConnection();
            ResultSet statement = connection.createStatement().executeQuery("select * from investor_earning");

            while(statement.next()) {
                memList.add(new Investor(statement.getInt("earning")));

            }
        } catch (SQLException ex) {
            Logger.getLogger(InvestorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return memList;
    }

    PreparedStatement pst = null;
    public PreparedStatement setIsbCarsTable() {
//        set the values in the table
        try {
            Connection connection = ConnectionClass.getConnection();
            pst = connection.prepareStatement("insert into islamabadcars (sector,type,model,number,color, available)values(?,?,?,?,?,1)");
            // JOptionPane.showMessageDialog(null, "Connection Established");
            return pst;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    public PreparedStatement setInvestorInfoTable() {
//        set the values in the table
        try {
            Connection connection = ConnectionClass.getConnection();
            pst = connection.prepareStatement("insert into investor_info (name,password,age,DOB,phone,email)values(?,?,?,?,?,?)");

            // JOptionPane.showMessageDialog(null, "Connection Established");
            return pst;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    public PreparedStatement setInvestHelpline() {
//        set the values in the table
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
    public ObservableList fetchInfo(ObservableList<InvestorInfo> memList){
//fetch the values from the server
//set them as class objects
//add them to a list
//return list

        try {
            Connection connection = ConnectionClass.getConnection();
            ResultSet statement = connection.createStatement().executeQuery("select * from investor_info");

            while(statement.next()) {
                memList.add(new InvestorInfo(statement.getString("name"),statement.getString("password"),statement.getInt("age"),statement.getString("phone"),statement.getString("DOB"),statement.getString("email")));

            }
        } catch (SQLException ex) {
            Logger.getLogger(InvestorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return memList;
    }
}
