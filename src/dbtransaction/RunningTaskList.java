
package dbtransaction;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Vijay
 */
public class RunningTaskList {
    private String taskName,sourceName,destinationName,sTableName,dTableName,schDate,deatils;
    private String status;
    ArrayList<ResultSet>taskDetails=new ArrayList<>();
    public RunningTaskList(ResultSet rs) {
        try{
        this.taskName = rs.getString("taskname");
        this.sourceName =rs.getString("ssource");
        this.destinationName = rs.getString("destination");
        this.sTableName = rs.getString("stablename");
        this.dTableName = rs.getString("dtablename");
        this.schDate = rs.getString("datenextrun");
        this.deatils = "I will add";
        this.status="WAITING";
    
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
        }

    public RunningTaskList(String taskName, String sourceName, String destinationName, String sTableName, String dTableName, String schDate, String deatils) {
        this.taskName = taskName;
        this.sourceName = sourceName;
        this.destinationName = destinationName;
        this.sTableName = sTableName;
        this.dTableName = dTableName;
        this.schDate = schDate;
        this.status="WAITING";
        this.deatils = deatils;
    }
    public RunningTaskList(){
        
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    ArrayList<RunningTaskList> runTaskAl=new ArrayList<>();
ArrayList<RunningTaskList> runTask(){
      
      try {
             Class.forName("org.postgresql.Driver");
         } catch (ClassNotFoundException ex) {
             
             Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
             
         }
       
         try {
            Connection connection=DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","postmanager");
            System.out.println("postgres connected");
            LocalDate currentdate= LocalDate.now();
            currentdate=currentdate.plusDays(1);
            
             System.out.println(currentdate);
            String query="SELECT * FROM usersdb where datenextrun='"+currentdate+"'";
            Statement st=connection.createStatement();
            ResultSet result=st.executeQuery(query);
            DatabaseMetaData u= connection.getMetaData();
            u.supportsRefCursors();
            runTaskAl.clear();
            while(result.next()){
              runTaskAl.add(new RunningTaskList(result));
             // taskDetails.add(result);
            }
            //result.close();
            st.close();
             
         }catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
            
         }
         return runTaskAl;
    }
    public void displayRunTask(javax.swing.JTable runTableFE) throws SQLException{
        DefaultTableModel model=(DefaultTableModel)runTableFE.getModel();
        int count=model.getRowCount();
        while(count-->0){
            model.removeRow(0);
        }
        ArrayList<RunningTaskList> list=runTask();
        
       
        Object[]row=new Object[8];
        for(int i=0;i<list.size();i++){
            row[0]=list.get(i).getTaskName();
            row[1]=list.get(i).getSourceName();
            row[2]=list.get(i).getDestinationName();
            row[3]=list.get(i).getsTableName();
            row[4]=list.get(i).getdTableName();
            row[5]=list.get(i).getSchDate();
            row[6]=list.get(i).getDeatils();
            row[7]=list.get(i).getStatus();
            model.addRow(row);
        }
    }
    public ArrayList<ResultSet> getTaskDetails(){
        runTaskAl=runTask();
        return taskDetails;
    }
    
}
