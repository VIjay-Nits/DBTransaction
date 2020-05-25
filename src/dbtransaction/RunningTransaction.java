
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
  
    private final String source="PostgreSql";
    private final String sUserName="postgres";
    private final String sPassword="postmanager";
    private final String sHost="";
    private final String sPort="";
    private final String sDataBaseName="";
    private final String sTableName="users";

    private final String destination="Oracle";
    private final String dUserName="system";
    private final String dPassword="manager";
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
        DatabaseMetaData d=sConnection.getMetaData();
        ResultSet columns = d.getColumns(null,null, sTableName, null);
        
        
while(columns.next())
{
    String columnName = columns.getString("COLUMN_NAME");
    String type = columns.getString("TYPE_NAME");
    int columnsize = Integer.parseInt(columns.getString("COLUMN_SIZE"));
    String defaultvalue = columns.getString("COLUMN_DEF");
    String isNullable = columns.getString("IS_NULLABLE");
    String is_autoIncrment = columns.getString("IS_AUTOINCREMENT");
    //Printing results
    System.out.println(columnName+""+type+""+columnsize+""+defaultvalue+""+isNullable+""+is_autoIncrment);
    coldetail.add(new ColumnDetails(columnName, type, columnsize, defaultvalue, isNullable, is_autoIncrment));
}

        System.out.println("");
    }
  
}
