
package dbtransaction;


import java.sql.*;
import java.sql.Connection;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.util.converter.LocalDateStringConverter;

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
    private final String sTableName="uuemp";

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
    ArrayList<ColumnDetails> coldetail=new ArrayList<>();
    public RunningTransaction() throws SQLException {
        runner();
    }

    private void runner() throws SQLException{ 
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
       
        ResultSetMetaData d=sConnection.createStatement().executeQuery("select *from uuemp where 1=2").getMetaData();
       
        
        
for(int i=1;i<=d.getColumnCount();i++)
{
    String columnName = d.getColumnName(i);
    String type = d.getColumnTypeName(i);
    int columnsize = (d.getColumnDisplaySize(i));
    int  columnSqlType = d.getColumnType(i);
    int precision= d.getPrecision(i);
    int scale= d.getScale(i);
    //Printing results
    System.out.println(columnName+""+type+""+columnsize+""+columnSqlType+""+precision+""+scale);
    coldetail.add(new ColumnDetails(columnName,  columnsize, type,precision, scale));
}
 System.out.println("************************************************************************");
new DataTypeMapping().oracletoMysql(coldetail);


String query="";
for(int i=0;i<coldetail.size();i++){
    System.out.println(coldetail.get(i).columnName+""+coldetail.get(i).datatype+""+coldetail.get(i).columnsize+""+coldetail.get(i).precision+""+coldetail.get(i).scale);
}


       
    }
  
}
