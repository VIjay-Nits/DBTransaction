/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbtransaction;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;


/**
 *
 * @author Vijay
 */
public class DataTypeMapping {
    
    
    public void oracletoMysql(ArrayList<ColumnDetails> coldetail){
            int length=coldetail.size();
            for(int i=0;i<length;i++){
               ColumnDetails obj=coldetail.get(i);
                String type =obj.datatype;
                long size=obj.columnsize;
                
                if(type=="BFILE"){
                    obj.datatype="VARCHAR";
                    obj.columnsize=255;
                }
                else if(type=="CHAR"||type=="CHARACTER"){
                    if(size>255){
                        obj.datatype="VARCHAR";
                    }
                    
                }
                else if(type=="CLOB"){
                    obj.datatype="LONGTEXT";
                    
                }
                else if(type=="DATE"){
                    obj.datatype="DATETIME";
                }
                else if(type=="FLOAT"){
                    obj.datatype="DOUBLE";
                }
                else if(type=="INTEGER"){
                    obj.datatype="INT";
                }
                else if(type=="LONG"){
                    obj.datatype="LONGTEXT";
                }
                else if(type=="LONG ROW"){
                    obj.datatype="LONGBLOB";
                }
                else if(type=="NCHAR"&&size>255){
                    obj.datatype="NVARCHAR";
                }
                else if(type=="NCLOB"){
                    obj.datatype="NVARCHAR";
                    obj.columnsize=(long)Math.pow(2, 31)-1;
                    
                }
                 else if(type=="NUMBER"){
                    obj.datatype="DOUBLE";
                }
                 else if(type=="NVARCHAR2"){
                    obj.datatype="NVARCHAR";
                }
                 else if(type=="RAW"&&size<256){
                    obj.datatype="BINARY";
                    
                }
                 else if(type=="RAW"&&size>255){
                    obj.datatype="VARBINARY";
                }
                 else if(type=="REAL"){
                    obj.datatype="DOUBLE";
                }
                 else if(type=="ROWID"){
                    obj.datatype="CHAR";
                    obj.columnsize=10;
                            
                }
                 else if(type=="SMALLINT"){
                    obj.datatype="DECIMAL";
                    obj.columnsize=38;
                    
                }
                 else if(type=="TIMESTAMP"){
                    obj.datatype="DATETIME";
                }
                 else if(type=="TIMESTAMP WITH TIME ZONE"){
                    obj.datatype="DATETIME";
                }
                 else if(type=="UROWID"){
                    obj.datatype="VARCHAR";
                }
                 else if(type=="VARCHAR2"){
                    obj.datatype="VARCHAR";
                }
                 else if(type=="XMLTYPE"){
                    obj.datatype="LONGTEXT";
                }
                
                
                
                
            }
    
   
  
    }
}
