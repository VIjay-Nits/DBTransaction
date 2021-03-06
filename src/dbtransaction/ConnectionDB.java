
package dbtransaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vijay
 */
public class ConnectionDB  {
    String user;
    String password;
    private Connection connection;
     ConnectionDB(String user,String password){
         this.user=user;
         this.password=password;
         
    }
    public boolean isConnectionCreated(String connName){
        if(connName.equals("Oracle"))return isOracleConnected();
        else if(connName.equals("MySQL"))return isMySQLConnected();
        else if(connName.equals("MsSQL"))return isMsSQLConnected();
        else return isPostgreSQLConnected();
    }
    public Connection connection(){return connection;}
     
    private boolean isOracleConnected(){
         try {
             Class.forName("oracle.jdbc.driver.OracleDriver");
         } catch (ClassNotFoundException ex) {
             
             Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
             return false;
         }
       
         try {
             connection=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",user,password);
         } catch (SQLException ex) {
             Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
             return false;
         }
         return true;
    }
   private boolean isMySQLConnected(){
        try {  
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        try {
           
            connection=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sakila?useSSL=false",user,password);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
   }
   private boolean isMsSQLConnected(){
        try {  
            Class.forName("com.microsoft.sqlserver.jdbc.SQLserverDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        try {
            String url= "jdbc:sqlserver://localhost:1433;databaseName = testdb;user="+user+";password = "+password;
            
                        
                        
            connection=DriverManager.getConnection(url);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
   }
   
   private boolean isPostgreSQLConnected(){
       try {  
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        try {
            connection=DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",user,password);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
       
       return true;
   }
  
   
   

}
  
    

