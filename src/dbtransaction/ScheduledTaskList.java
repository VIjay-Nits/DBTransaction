
package dbtransaction;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Vijay
 */
public class ScheduledTaskList {
    private String taskName,sourceName,destinationName,sTableName,dTableName,schDate,deatils;

    public ScheduledTaskList(ResultSet rs) {
        try{
        this.taskName = rs.getString("taskname");
        this.sourceName =rs.getString("ssource");
        this.destinationName = rs.getString("destination");
        this.sTableName = rs.getString("stablename");
        this.dTableName = rs.getString("dtablename");
        this.schDate = rs.getString("datenextrun");
        this.deatils = "I will add";
    
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
        }

    public ScheduledTaskList(String taskName, String sourceName, String destinationName, String sTableName, String dTableName, String schDate, String deatils) {
        this.taskName = taskName;
        this.sourceName = sourceName;
        this.destinationName = destinationName;
        this.sTableName = sTableName;
        this.dTableName = dTableName;
        this.schDate = schDate;
        this.deatils = deatils;
    }
    

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public String getsTableName() {
        return sTableName;
    }

    public void setsTableName(String sTableName) {
        this.sTableName = sTableName;
    }

    public String getdTableName() {
        return dTableName;
    }

    public void setdTableName(String dTableName) {
        this.dTableName = dTableName;
    }

    public String getSchDate() {
        return schDate;
    }

    public void setSchDate(String schDate) {
        this.schDate = schDate;
    }

    public String getDeatils() {
        return deatils;
    }

    public void setDeatils(String deatils) {
        this.deatils = deatils;
    }

    
    
}
