
package dbtransaction;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

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
    public ScheduledTaskList(){
       
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
    ArrayList<ScheduledTaskList> scheduledTask(){
        
      ArrayList<ScheduledTaskList> scheduledTask=new ArrayList<>();
      try {
             Class.forName("org.postgresql.Driver");
         } catch (ClassNotFoundException ex) {
             
             Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
             
         }
       
         try {
            Connection connection=DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","postmanager");
            System.out.println("postgres connected");
            String query="SELECT * FROM usersdb";
            Statement st=connection.createStatement();
            ResultSet result=st.executeQuery(query);
            DatabaseMetaData u= connection.getMetaData();
            u.supportsRefCursors();
            while(result.next()){
              scheduledTask.add(new ScheduledTaskList(result));
            }
            connection.close();
            result.close();
            st.close();
             
         }catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
            
         }
         return scheduledTask;
    }
    public void displaySchTask(javax.swing.JTable taskTableFE) throws SQLException{
        DefaultTableModel model=(DefaultTableModel)taskTableFE.getModel();
        int count=model.getRowCount();
        while(count-->0){
            model.removeRow(0);
        }
        ArrayList<ScheduledTaskList> list=scheduledTask();
        
       
        Object[]row=new Object[7];
        for(int i=0;i<list.size();i++){
            row[0]=list.get(i).getTaskName();
            row[1]=list.get(i).getSourceName();
            row[2]=list.get(i).getDestinationName();
            row[3]=list.get(i).getsTableName();
            row[4]=list.get(i).getdTableName();
            row[5]=list.get(i).getSchDate();
            row[6]=list.get(i).getDeatils();
            model.addRow(row);
        }
    }
    
    
}
