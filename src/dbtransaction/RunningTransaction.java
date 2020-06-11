
package dbtransaction;


import java.sql.*;
import java.sql.Connection;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import org.apache.poi.*;
import org.apache.poi.*;
import java.util.ArrayList;
import javafx.util.converter.LocalDateStringConverter;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import java.sql.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.sql.Types.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 *
 * @author Vijay
 */
public class RunningTransaction {
    
     private String source;
    private  String sUserName;
    private  String sPassword;
    private  String sHost;
    private  int sPort;
    private  String sDataBaseName;
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
    private  int occurence;
    private  int count;
    private Connection sConnection,dConnection;
    
    
   
    
    
    public RunningTransaction(AddTask oobj ) throws SQLException {
      //  AddTask oobj=new AddTask();
        this.source=oobj.source;
        this.sUserName=oobj.userNameSource;
        this.sPassword=oobj.passwordSource;
        this.sHost=oobj.sHost;
        this.sPort=oobj.sPort;
        this.sDataBaseName=oobj.sDatabaseName;
        this.sTableName=oobj.sTable;
        
        this.destination=oobj.destination;
        this.dUserName=oobj.userNameDestination;
        this.dPassword=oobj.passwordDestination;
        this.dHost=oobj.dHost;
        this.dPort=oobj.dPort;
        this.dDataBaseName=oobj.dDatabaseName;
        this.dTableName=oobj.dTable;
        this.taskName=oobj.taskName;
     // datatransfer();
      //  destinationTablegenerator();
    }
    public RunningTransaction(){
        ArrayList<RunningTaskList> runTaskAL=new RunningTaskList().runTaskAl;
        
//        try {
//             Class.forName("org.postgresql.Driver");
//         } catch (ClassNotFoundException ex) {
//             
//             Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
//             
//         }
//       
//         try {
//            Connection connection=DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","postmanager");
//            System.out.println("postgres connected");
//            LocalDate currentdate= LocalDate.now();
//            currentdate=currentdate.plusDays(2);
//            
//            System.out.println(currentdate);
//            String query="SELECT * FROM usersdb where datenextrun='"+currentdate+"'";
//            Statement st=connection.createStatement();
//            ResultSet result=st.executeQuery(query);
//            DatabaseMetaData u= connection.getMetaData();
//            u.supportsRefCursors();
//            
//            
//            while(result.next()){
//              runTaskAL.add(new RunningTaskList(result));
//              }
//            connection.close();
//            result.close();
//            st.close();
//            
//             
//         }catch (SQLException ex) {
//            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
//            
//         }
        for(int i=0;i<runTaskAL.size();i++){
            try{
                new RunningTaskList().runTaskAl.get(i).setStatus("RUNNING");
               // new RunningTaskList().displayRunTask(new TransactionGUI().getRunTableFE());
               
                
                this.source=runTaskAL.get(i).getSource();
                this.sUserName=runTaskAL.get(i).getsUserName();
                this.sPassword=runTaskAL.get(i).getsPassword();
                this.sHost=runTaskAL.get(i).getsHost();
                this.sPort=runTaskAL.get(i).getsPort();
                this.sDataBaseName=runTaskAL.get(i).getsDataBaseName();
                this.sTableName=runTaskAL.get(i).getsTableName();
        
                this.destination=runTaskAL.get(i).getDestination();
                this.dUserName=runTaskAL.get(i).getdUserName();
                this.dPassword=runTaskAL.get(i).getdPassword();
                this.dHost=runTaskAL.get(i).getdHost();
                this.dPort=runTaskAL.get(i).getdPort();
                this.dDataBaseName=runTaskAL.get(i).getdDataBaseName();
                this.dTableName=runTaskAL.get(i).getdTableName();
                
                this.taskName=runTaskAL.get(i).getTaskName();
                this.creationDate=runTaskAL.get(i).getCreationDate();
                this.schDate=runTaskAL.get(i).getSchDate();
                this.datelastrun=runTaskAL.get(i).getDatelastrun();
                this.occurence=runTaskAL.get(i).getOccurence();
                this.count=runTaskAL.get(i).getCount();
                this.columnName=runTaskAL.get(i).getColumnName();
                
                    int nn=runTaskAL.get(i).getCount();
                
               if(runTaskAL.get(i).getCount()==0){
                    destinationTablegenerator();
                }else{
                    datatransfer();
                }
              
             new RunningTaskList().runTaskAl.get(i).setStatus("COMPLETED");
            // new RunningTaskList().displayRunTask(new TransactionGUI().getRunTableFE());
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null,e);
            }
           
        }
    }

   public void destinationTablegenerator() throws SQLException{ 
        int rowCount;
        ArrayList<ColumnDetails> coldetail=new ArrayList<>();
        //start connection with source
        ConnectionDB sconn= new ConnectionDB(sUserName,sPassword);
        if(sconn.isConnectionCreated(source)){
            sConnection=sconn.connection();
        }else{
            System.out.println("problem with source connection");
            return;
        }
        ConnectionDB dconn= new ConnectionDB(dUserName,dPassword);
        if(dconn.isConnectionCreated(destination)){
            dConnection=dconn.connection();
        }else{
            System.out.println("problem with destination connection");
            return;
        }
       // System.out.println(1+""+sConnection.getSchema());
       
        ResultSetMetaData d=sConnection.createStatement().executeQuery("select *from "+sTableName+" where 1=2").getMetaData();
       
        DatabaseMetaData dm= sConnection.getMetaData();
        dm.supportsRefCursors();
           ResultSet rss=dm.getColumns(null, null, sTableName, null);
       
        //rss.first();
        for(int i=1;i<=d.getColumnCount();i++)
        {
            String columnName = d.getColumnName(i);
            String type = d.getColumnTypeName(i);
            int columnsize = d.getColumnDisplaySize(i);
            int  columnSqlType = d.getColumnType(i);
            int precision= d.getPrecision(i);
            int scale= d.getScale(i);
            boolean sign=d.isSigned(i);
            boolean autoIncrement=d.isAutoIncrement(i);
//            rss.next();
//            String columnName = rss.getString("COLUMN_NAME");
//            String type = rss.getString("TYPE_NAME");
//            int columnsize = rss.getInt("COLUMN_SIZE");
//            int  columnSqlType = d.getColumnType(i);
//           int precision= d.getPrecision(i);
//            int scale= d.getScale(i);
//            boolean sign=d.isSigned(i);
            //Printing results
           System.out.println(columnName+""+type+""+columnsize+""+columnSqlType+""+precision+""+scale+""+sign+" "+autoIncrement);
            coldetail.add(new ColumnDetails(columnName,  columnsize, type,precision, scale,sign,autoIncrement));
        }
        System.out.println("************************************************************************");
        if("Oracle".equals(source)&&"MySQL".equals(destination)){
            new DataTypeMapping().oracletoMysql(coldetail);
        }
        else if("MySQL".equals(source)&&"Oracle".equals(destination)){
            new DataTypeMapping().mysqltoOracle(coldetail);
        }
        else if("PostgreSQL".equals(source)&&"Oracle".equals(destination)){
            new DataTypeMapping().postgretoOracle(coldetail);
        }
        else if("Oracle".equals(source)&&"PostgreSQL".equals(destination)){
            new DataTypeMapping().oracletoPostgre(coldetail);
        }
        else if("Oracle".equals(source)&&"MsSQL".equals(destination)){
            new DataTypeMapping().oracletoMssql(coldetail);
        }
        else if("MySQL".equals(source)&&"PostgreSQL".equals(destination)){
            new DataTypeMapping().mysqltoPostgres(coldetail);
        }
        else if("MySQL".equals(source)&&"MsSQL".equals(destination)){
            new DataTypeMapping().mysqltoMssql(coldetail);
        }
        else if("PostgreSQL".equals(source)&&"MySQL".equals(destination)){
            new DataTypeMapping().postgretoMysql(coldetail);
        }
        else if("PostgreSQL".equals(source)&&"MsSQL".equals(destination)){
            new DataTypeMapping().postgretoMssql(coldetail);
        }
        else if("MsSQL".equals(source)&&"MySQL".equals(destination)){
            new DataTypeMapping().mssqltoMysql(coldetail);
        }
        else if("MsSQL".equals(source)&&"Oracle".equals(destination)){
            new DataTypeMapping().mssqltoOracle(coldetail);
        }
        else if("MsSQL".equals(source)&&"PostgreSQL".equals(destination)){
            new DataTypeMapping().mssqltoPostgre(coldetail);
        }

        String query="create table "+dTableName+"(";
        for(int i=0;i<coldetail.size();i++){
            query+=" "+coldetail.get(i).columnName+" "+coldetail.get(i).quey;
            if(i<coldetail.size()-1) {
                query+=",";
            }
             
            //System.out.println(coldetail.get(i).columnName+""+coldetail.get(i).datatype+""+coldetail.get(i).columnsize+""+coldetail.get(i).precision+""+coldetail.get(i).scale);
        }
        query+=")";
         System.out.println(query);
        int success=dConnection.createStatement().executeUpdate(query);
        if(success==0){
            System.out.println("TABLE GENERATED SUCCESSFULLY");
        }
        else{
            System.out.println("PROBLEM IN TABLE CREATION");
        }
        coldetail.clear();
        System.out.println(success);
        dConnection.close();
        sConnection.close();
         datatransfer();
    
    }
    
    public void datatransfer() throws SQLException{
       ConnectionDB sconn= new ConnectionDB(sUserName,sPassword);
        if(sconn.isConnectionCreated(source)){
            sConnection=sconn.connection();
        }else{
            System.out.println("problem with source connection");
            return;
        }
        ConnectionDB dconn= new ConnectionDB(dUserName,dPassword);
        if(dconn.isConnectionCreated(destination)){
            dConnection=dconn.connection();
        }else{
            System.out.println("problem with destination connection");
            return;
        }
        
        String query ="select *from "+sTableName;//constraint will add later
        ResultSet ssrs=sConnection.createStatement().executeQuery(query);
        ResultSetMetaData setadata=ssrs.getMetaData();
        
        
//        ssrs.last();
//        rowCount= ssrs.isLast() ? ssrs.getRow() : 0; 
//        ssrs.beforeFirst();
        int cout=0;
        Statement dstmt=dConnection.createStatement();
        dConnection.setAutoCommit(false);
        String insert= "INSERT INTO "+dTableName+" VALUES(";
        for(int i=0;i<setadata.getColumnCount()-1;i++){
            insert+="?,";
                    }
        insert+="?)";
        System.out.println(insert);
        
        PreparedStatement dppstmt = dConnection.prepareStatement(insert);
        ssrs.close();
        ResultSet srs=sConnection.createStatement().executeQuery(query);
        while(srs.next()){
            ResultSetMetaData smetadata=srs.getMetaData();
          //  ArrayList<ResultSetMetaData> tablemeta=new ArrayList<>();
            for(int j=1;j<=smetadata.getColumnCount();j++){
                
//                if(coldetail.get(j-1).datatype=="TIMESTAMP"/*&&source=="Oracle"&&destination=="PostgreSQL"*/){
//                   
//                    dppstmt.setTimestamp(j,srs.getTimestamp(j));   
//                }else{
                    //System.out.println(srs.getObject(j).getClass());
                    
                    String datatype=smetadata.getColumnTypeName(j);
                    if("TIMESTAMP".equals(datatype)){
                        dppstmt.setTimestamp(j, srs.getTimestamp(j));
                    }else{
            
                    dppstmt.setObject(j,(srs.getObject(j)));
                    }
               // }
            }
          
                dppstmt.addBatch();
                
                cout++;
            if(cout==1000){
               dppstmt.executeBatch();
               // System.out.println(out.toString());
                dppstmt.clearBatch();
                dConnection.commit();
            }
        
        }
        dppstmt.executeBatch();
        
       // System.out.println(out.toString());
        dppstmt.clearBatch();
        dConnection.commit();
        
        try {
                Connection conn=DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","postmanager");
                System.out.println("postgres connected");
                
                datelastrun=schDate;
                count++;
                if(occurence==1){schDate=schDate.plusDays(1);}
                else if(occurence==7){schDate=schDate.plusWeeks(1);}
                else if(occurence==30){schDate=schDate.plusMonths(1);}
                else if(occurence==365){schDate=schDate.plusYears(1);}
        
                String update="update usersdb SET "
                                +"mycount="+count+","
                                +"datenextrun='"+schDate.toString()+"',"
                                +"datelastrun='"+datelastrun.toString()+"'"
                                +"WHERE taskname='"+taskName+"'";
                                
                System.out.println(update);
                Statement st=conn.createStatement();
                int retrn=st.executeUpdate(update);
                
                conn.close();
                st.close();
                if(retrn==1){
                    JOptionPane.showMessageDialog(null, "Task Saved Successfully");
                }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
            
        
                
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

    public Connection getsConnection() {
        return sConnection;
    }

    public void setsConnection(Connection sConnection) {
        this.sConnection = sConnection;
    }

    public Connection getdConnection() {
        return dConnection;
    }

    public void setdConnection(Connection dConnection) {
        this.dConnection = dConnection;
    }
    
    
    

}
