
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
   private String source;
    private  String sUserName;
    private  String sPassword;
    private  String sHost;
    private  int sPort;
    private  String sDataBaseName="";
    private  String sTableName;

    private  String destination;
    private  String dUserName;
    private  String dPassword;
    private  String dHost;
    private  int dPort;
    private  String dDataBaseName;
    private  String dTableName;

    private String email="vijaygupta131999@gmail.com";
    private String taskName;
    private String constraint="2020-05-23";
    private String columnName;
    private LocalDate datelastrun;
    private LocalDate schDate;
    private LocalDate creationDate;
    private  int occurence=7;
    private  int count=0;
    
    private String status,detail;
    ArrayList<ResultSet>taskDetails=new ArrayList<>();
    public RunningTaskList(ResultSet oobj) {
        try{
                this.source=oobj.getString("ssource");
                this.sUserName=oobj.getString("susername");
                this.sPassword=oobj.getString("spassword");
                this.sHost=oobj.getString("shost");
                this.sPort=oobj.getInt("sport");
                this.sDataBaseName=oobj.getString("sdatabasename");
                this.sTableName=oobj.getString("stablename");
        
                this.destination=oobj.getString("destination");
                this.dUserName=oobj.getString("dusername");
                this.dPassword=oobj.getString("dpassword");
                this.dHost=oobj.getString("dhost");
                this.dPort=oobj.getInt("dport");
                this.dDataBaseName=oobj.getString("ddatabasename");
                this.dTableName=oobj.getString("dtablename");
                
                this.taskName=oobj.getString("taskname");
                this.creationDate=oobj.getDate("dateofcreation").toLocalDate();
                this.schDate=oobj.getDate("datenextrun").toLocalDate();
                this.datelastrun=oobj.getDate("datelastrun").toLocalDate();
                this.occurence=oobj.getInt("occurrence");
                this.count=oobj.getInt("mycount");
                this.columnName=oobj.getString("constraintcolumnname");
                
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
        }

    public RunningTaskList(String taskName, String sourceName, String destinationName, String sTableName, String dTableName, String schDate, String deatils) {
        this.taskName = taskName;
        this.source = sourceName;
        this.destination = destinationName;
        this.sTableName = sTableName;
        this.dTableName = dTableName;
        this.schDate = LocalDate.parse(schDate);
        this.status="WAITING";
        this.detail= deatils;
    }
    public RunningTaskList(){
        
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
            row[1]=list.get(i).getSource();
            row[2]=list.get(i).getDestination();
            row[3]=list.get(i).getsTableName();
            row[4]=list.get(i).getdTableName();
            row[5]=list.get(i).getSchDate();
            row[6]=list.get(i).getDetail();
            row[7]=list.get(i).getStatus();
            model.addRow(row);
        }
    }
    public ArrayList<ResultSet> getTaskDetails(){
        runTaskAl=runTask();
        return taskDetails;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getsUserName() {
        return sUserName;
    }

    public void setsUserName(String sUserName) {
        this.sUserName = sUserName;
    }

    public String getsPassword() {
        return sPassword;
    }

    public void setsPassword(String sPassword) {
        this.sPassword = sPassword;
    }

    public String getsHost() {
        return sHost;
    }

    public void setsHost(String sHost) {
        this.sHost = sHost;
    }

    public int getsPort() {
        return sPort;
    }

    public void setsPort(int sPort) {
        this.sPort = sPort;
    }

    public String getsDataBaseName() {
        return sDataBaseName;
    }

    public void setsDataBaseName(String sDataBaseName) {
        this.sDataBaseName = sDataBaseName;
    }

    public String getsTableName() {
        return sTableName;
    }

    public void setsTableName(String sTableName) {
        this.sTableName = sTableName;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getdUserName() {
        return dUserName;
    }

    public void setdUserName(String dUserName) {
        this.dUserName = dUserName;
    }

    public String getdPassword() {
        return dPassword;
    }

    public void setdPassword(String dPassword) {
        this.dPassword = dPassword;
    }

    public String getdHost() {
        return dHost;
    }

    public void setdHost(String dHost) {
        this.dHost = dHost;
    }

    public int getdPort() {
        return dPort;
    }

    public void setdPort(int dPort) {
        this.dPort = dPort;
    }

    public String getdDataBaseName() {
        return dDataBaseName;
    }

    public void setdDataBaseName(String dDataBaseName) {
        this.dDataBaseName = dDataBaseName;
    }

    public String getdTableName() {
        return dTableName;
    }

    public void setdTableName(String dTableName) {
        this.dTableName = dTableName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getConstraint() {
        return constraint;
    }

    public void setConstraint(String constraint) {
        this.constraint = constraint;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public LocalDate getDatelastrun() {
        return datelastrun;
    }

    public void setDatelastrun(LocalDate datelastrun) {
        this.datelastrun = datelastrun;
    }

    public LocalDate getSchDate() {
        return schDate;
    }

    public void setSchDate(LocalDate schDate) {
        this.schDate = schDate;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public int getOccurence() {
        return occurence;
    }

    public void setOccurence(int occurence) {
        this.occurence = occurence;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
    
    
}
