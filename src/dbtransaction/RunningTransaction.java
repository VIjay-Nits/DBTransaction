
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


/**
 *
 * @author Vijay
 */
public class RunningTransaction {
  
    private final String source="Oracle";
    private final String sUserName="system";
    private final String sPassword="manager";
    private final String sHost="";
    private final String sPort="";
    private final String sDataBaseName="";
    private final String sTableName="aap";

    private final String destination="MySQL";
    private final String dUserName="root";
    private final String dPassword="mymanager";
    private final String dHost="";
    private final String dPort="";
    private final String dDataBaseName="";
    private final String dTableName="destinationtable";

    private final String email="vijaygupta131999@gmail.com";
    private final String taskName="firstTask";
    private final String constraint="2020-05-23";
    private final String columnName="Ddddd";
    private final LocalDate schDate= LocalDate.of(2020,5, 23);
    private final LocalDate creationDate= LocalDate.of(2020,5, 23);
    private final int occurence=7;
    private final int count=0;
    private Connection sConnection,dConnection;
    
    int rowCount=0;
    ArrayList<ColumnDetails> coldetail=new ArrayList<>();
    
    public RunningTransaction() throws SQLException {
        datatransfer();//destinationTablegenerator();
    }

    private void destinationTablegenerator() throws SQLException{ 
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
       
        
        
        for(int i=1;i<=d.getColumnCount();i++)
        {
            String columnName = d.getColumnName(i);
            String type = d.getColumnTypeName(i);
            int columnsize = d.getColumnDisplaySize(i);
            int  columnSqlType = d.getColumnType(i);
            int precision= d.getPrecision(i);
            int scale= d.getScale(i);
            //Printing results
           // System.out.println(columnName+""+type+""+columnsize+""+columnSqlType+""+precision+""+scale);
            coldetail.add(new ColumnDetails(columnName,  columnsize, type,precision, scale));
        }
        System.out.println("************************************************************************");
        if(source=="Oracle"&&destination=="MySQL"){
        new DataTypeMapping().oracletoMysql(coldetail);}


        String query="create table "+dTableName+"(";
        for(int i=0;i<coldetail.size();i++){
            query+=" "+coldetail.get(i).columnName+" "+coldetail.get(i).quey;
            if(i<coldetail.size()-1)query+=",";
            else query+=");";
            //System.out.println(coldetail.get(i).columnName+""+coldetail.get(i).datatype+""+coldetail.get(i).columnsize+""+coldetail.get(i).precision+""+coldetail.get(i).scale);
        }
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
        
        
//        srs.last();
//        rowCount= srs.last() ? srs.getRow() : 0; 
  //      srs.beforeFirst();
        int counter=0;
        ArrayList<ResultSet> datalist=new ArrayList<>();
        Statement dstmt=dConnection.createStatement();
        dConnection.setAutoCommit(false);
        String insert= "INSERT INTO "+dTableName+" VALUES(";
        for(int i=0;i<setadata.getColumnCount()-1;i++){
            insert+="?,";
                    }
        insert+="?)";
        System.out.println(insert);
        PreparedStatement dppstmt = dConnection.prepareStatement(insert);
        ResultSet srs=sConnection.createStatement().executeQuery(query);
        while(srs.next()){
            ResultSetMetaData smetadata=srs.getMetaData();
          for(int j=1;j<=smetadata.getColumnCount();j++){
                        dppstmt.setObject(j,srs.getObject(j));
                    }
                    dppstmt.addBatch();
            
            counter++;
            if(counter==1000){
                dppstmt.executeBatch();
                dppstmt.clearBatch();
                dConnection.commit();
            }
        
        }
        dppstmt.executeBatch();
        dppstmt.clearBatch();
        dConnection.commit();
        
                
        
        
       
        
        
        
        
        
        
    
    }

}
