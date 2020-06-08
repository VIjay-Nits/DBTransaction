
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
    private Connection sConnection,dConnection;
    
    int rowCount=0;
    ArrayList<ColumnDetails> coldetail=new ArrayList<>();
    
    
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
        
        ArrayList<ResultSet>oobj=new ArrayList<>();
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
            //runTaskAl.clear();
            int i=0;
            while(result.next()){
             // runTaskAl.add(new RunningTaskList(result));
              oobj.add(result);
              this.source=oobj.get(i).getString("ssource");
                this.sUserName=oobj.get(i).getString("susername");
                this.sPassword=oobj.get(i).getString("spassword");
                this.sHost=oobj.get(i).getString("shost");
                this.sPort=oobj.get(i).getInt("sport");
                this.sDataBaseName=oobj.get(i).getString("sdatabasename");
                this.sTableName=oobj.get(i).getString("stablename");
        
                this.destination=oobj.get(i).getString("destination");
                this.dUserName=oobj.get(i).getString("dusername");
                this.dPassword=oobj.get(i).getString("dpassword");
                this.dHost=oobj.get(i).getString("dhost");
                this.dPort=oobj.get(i).getInt("dport");
                this.dDataBaseName=oobj.get(i).getString("ddatabasename");
                this.dTableName=oobj.get(i).getString("dtablename");
                
                this.taskName=oobj.get(i).getString("taskname");
                this.creationDate=oobj.get(i).getDate("dateofcreation").toLocalDate();
                this.schDate=oobj.get(i).getDate("datenextrun").toLocalDate();
                this.datelastrun=oobj.get(i).getDate("datelastrun").toLocalDate();
                this.occurence=oobj.get(i).getInt("occurrence");
                this.count=oobj.get(i).getInt("mycount");
                this.columnName=oobj.get(i).getString("constraintcolumnname");
                if(count==0){
                    destinationTablegenerator();
                }else{
                    datatransfer();
                }
                i++;
            }
            //result.close();
            st.close();
             
         }catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
            
         }
//        for(int i=0;i<oobj.size();i++){
//            try{
//               // new RunningTaskList().runTaskAl.get(i).setStatus("RUNNING");
//                
//               // new RunningTaskList().runTaskAl.get(i).setStatus("COMPLETED");
//                
//                
//            
//            }catch(SQLException e){
//                JOptionPane.showMessageDialog(null,e);
//            }
        //}
    }

   public void destinationTablegenerator() throws SQLException{ 
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
       
        ResultSet rss= sConnection.getMetaData().getColumns(null, null, sTableName, null);
       
        //rss.first();
        for(int i=1;i<=d.getColumnCount();i++)
        {
//            String columnName = d.getColumnName(i);
//            String type = d.getColumnTypeName(i);
//            int columnsize = d.getColumnDisplaySize(i);
//            int  columnSqlType = d.getColumnType(i);
//            int precision= d.getPrecision(i);
//            int scale= d.getScale(i);
            //boolean sign=d.isSigned(i);
            boolean autoIncrement=d.isAutoIncrement(i);
            rss.next();
            String columnName = rss.getString("COLUMN_NAME");
            String type = rss.getString("TYPE_NAME");
            int columnsize = rss.getInt("COLUMN_SIZE");
            int  columnSqlType = d.getColumnType(i);
            int precision= d.getPrecision(i);
            int scale= d.getScale(i);
            boolean sign=d.isSigned(i);
//            //Printing results
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
        int count=0;
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
            for(int j=1;j<=smetadata.getColumnCount();j++){
                if(coldetail.get(j-1).datatype=="TIMESTAMP"/*&&source=="Oracle"&&destination=="PostgreSQL"*/){
                   
                    dppstmt.setTimestamp(j,srs.getTimestamp(j));   
                }else{
                    //System.out.println(srs.getObject(j).getClass());
                    dppstmt.setObject(j,(srs.getObject(j)));
                }
            }
          
                dppstmt.addBatch();
                
                count++;
            if(count==1000){
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
                count++;
                datelastrun=schDate;
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
    

}
