package BookCar;

import connection.ConnectionClass;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.LocalDateTime;  // Import the LocalDateTime class
import java.time.format.DateTimeFormatter;  // Import the DateTimeFormatter class

// following is a public Booking Data base class.
//
public class bookDB {
    public ObservableList fetchIsbCarTable(ObservableList<Car> memList) {
//    this function will fetch the table from mysql table
//        This will run a query on the table and will select all the entries and convert it into a list of
//        class objects and return the list of all the cars that reside in the islamabad car table
        try {
            Connection connection = ConnectionClass.getConnection();
            ResultSet statement = connection.createStatement().executeQuery("select * from islamabadcars");

            while(statement.next()) {
                memList.add(new Car(statement.getString("sector"),
                        statement.getString("type"),
                        statement.getInt("model"),
                        statement.getInt("number"),
                        statement.getString("color"),
                        statement.getInt("available")));

            }
        } catch (SQLException ex) {
            Logger.getLogger(BookNowController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return memList;
    }
//    this function will get all the rides in the rides in progress table in mysql
//    after getting the rows, it will make objects and append it into a list which will be returned
    public int fetchRidesTable() {
        int counter=0;
        try {
            Connection connection = ConnectionClass.getConnection();
            ResultSet statement = connection.createStatement().executeQuery("select * from ridesprogress");
            while(statement.next()) {
                counter++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookNowController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return counter;
    }
// this function will prepare a booking table
//    this will enter all the possible coloumns in the row of a booking table and set values to it
//    it will run the prepared statement to set the values
    PreparedStatement pst = null;
    public PreparedStatement setBookTable(String car) {
        try {
            Connection connection = ConnectionClass.getConnection();
            LocalDateTime myDateObj = LocalDateTime.now();
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String formattedDate = myDateObj.format(myFormatObj);
            String day=formattedDate.split("-")[0];
            String month=formattedDate.split("-")[1];
            String year=formattedDate.split(" ")[0].split("-")[2];
            String hour=formattedDate.split(" ")[1].split(":")[0];
            String min=formattedDate.split(" ")[1].split(":")[1];
            String sec=formattedDate.split(" ")[1].split(":")[2];
            int d1=Integer.parseInt(day);
            int mo1=Integer.parseInt(month);
            int y1=Integer.parseInt(year);
            int h1=Integer.parseInt(hour);
            int m1=Integer.parseInt(min);
            int s1=Integer.parseInt(sec);
            int userid=1;
            String sql = ("insert into ridesprogress (carNo,id,hour,min,sec,day,month,year)values(?,?,?,?,?,?,?,?)");
            pst = connection.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(car));
            pst.setInt(2,userid);
            pst.setInt(3,h1);
            pst.setInt(4,m1);
            pst.setInt(5,s1);
            pst.setInt(6,d1);
            pst.setInt(7,mo1);
            pst.setInt(8,y1);
            pst.execute();
            pst.close();
            // JOptionPane.showMessageDialog(null, "Connection Established");
            return pst;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}
