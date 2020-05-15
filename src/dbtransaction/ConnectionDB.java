/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbtransaction;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class ConnectionDB  {
    String user;
    String password;
     ConnectionDB(String user,String password){
         this.user=user;
         this.password=password;
         
    }
    public boolean isConnectioncreated(String connName){
        if(connName.equals("Oracle"))return isOracleConnected();
        else if(connName.equals("MySQL"))return isMySQLConnected();
        else if(connName.equals("MsSQL"))return isMsSQLConnected();
        else return isPostgreSQLConnected();
    }
     
    private boolean isOracleConnected(){
         try {
             Class.forName("oracle.jdbc.driver.OracleDriver");
         } catch (ClassNotFoundException ex) {
             
             Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
             return false;
         }
       
         try {
             DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",user,password);
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
            DriverManager.getConnection("jdbc:mysql://localhost:3306/sonoo",user,password);
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
            DriverManager.getConnection(url);
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
            DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",user,password);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
       
       return true;
   }
  
   
   

}
  
    

