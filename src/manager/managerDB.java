package manager;

import connection.ConnectionClass;
import investor.Investor;
import investor.InvestorController;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class managerDB {
    public ObservableList fetchEarnings(ObservableList<Manager> memList) {
//fetch the values from the server
//set them as class objects
//add them to a list
//return list
        try {
            Connection connection = ConnectionClass.getConnection();
            ResultSet statement = connection.createStatement().executeQuery("select * from manager_earning");

            while(statement.next()) {
                memList.add(new Manager(statement.getInt("earning")));

            }
        } catch (SQLException ex) {
            Logger.getLogger(InvestorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return memList;
    }
    public ObservableList fetchHelpline(ObservableList<ManagerHelpline> memList) {
//fetch the values from the server
//set them as class objects
//add them to a list
//return list
        try {
            Connection connection = ConnectionClass.getConnection();
            ResultSet statement = connection.createStatement().executeQuery("select * from helpline");

            while(statement.next()) {
                memList.add(new ManagerHelpline(statement.getString("cust_name"),statement.getString("queryy")));

            }
        } catch (SQLException ex) {

            Logger.getLogger(InvestorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return memList;
    }
}
