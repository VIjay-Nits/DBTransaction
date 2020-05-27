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
                    obj.quey="VARCHAR(255)";
                }
                else if(type=="CHAR"||type=="CHARACTER"){
                    if(size>255){
                        obj.datatype="VARCHAR";
                        obj.quey="VARCHAR("+obj.columnsize+")";
                    }
                    
                }
                else if(type=="CLOB"){
                    obj.datatype="LONGTEXT";
                    obj.quey="LONGTEXT";
                    
                }
                else if(type=="DATE"){
                    obj.datatype="DATETIME";
                    obj.quey=obj.datatype;
                }
                else if(type=="FLOAT"){
                    obj.datatype="DOUBLE";
                    obj.quey=obj.datatype;
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
                    obj.quey=obj.datatype+"("+obj.columnsize+")";
                }
                else if(type=="NCHAR"&&size<=255){
                    obj.datatype="NCHAR";
                    obj.quey=obj.datatype+"("+obj.columnsize+")";
                }
                else if(type=="NCHAR VARYING"){
                    obj.datatype="NCHAR VARYING";
                    obj.quey=obj.datatype+"("+obj.columnsize+")";
                }
//                else if(type=="NCLOB"){
//                    obj.datatype="NVARCHAR";
//                    obj.columnsize=(long)Math.pow(2, 31)-1;
//                    
//                }
                 else if(type=="NUMBER"){
                    obj.datatype="DOUBLE";
                    obj.quey="DOUBLE";
                }
                 else if(type=="NUMERIC"){
                     obj.quey=obj.datatype+"("+obj.precision+","+obj.scale+")";
                 }
                 else if(type=="NVARCHAR2"){
                    obj.datatype="NVARCHAR";
                    obj.quey="NVARCHAR("+obj.columnsize+")";
                }
                 else if(type=="RAW"&&size<256){
                    obj.datatype="BINARY";
                    obj.quey="BINARY("+obj.scale+")";
                    
                }
                 else if(type=="RAW"&&size>255){
                    obj.datatype="VARBINARY";
                    obj.quey="VARBINARY("+obj.columnsize+")";
                }
                 else if(type=="REAL"){
                    obj.datatype="DOUBLE";
                    obj.quey="DOUBLE";
                }
                 else if(type=="ROWID"){
                    obj.datatype="CHAR";
                    obj.columnsize=10;
                    obj.quey="CHAR(10)";
                            
                    
                            
                }
                 else if(type=="SMALLINT"){
                    obj.datatype="DECIMAL";
                    obj.columnsize=38;
                    obj.quey="DECIMAL(38)";
                    
                }
                 else if(type=="TIMESTAMP"){
                    obj.datatype="DATETIME";
                    obj.quey="DATETIME("+obj.columnsize+")";
                }
                 else if(type=="TIMESTAMP WITH TIME ZONE"){
                    obj.datatype="DATETIME";
                    obj.quey="DATETIME("+obj.columnsize+")";
                }
                 else if(type=="UROWID"){
                    obj.datatype="VARCHAR";
                    obj.quey="VARCHAR("+obj.columnsize+")";
                }
                 else if(type=="VARCHAR2"||type=="VARCHAR"){
                    obj.datatype="VARCHAR";
                    obj.quey="VARCHAR("+obj.columnsize+")";
                }
                 else if(type=="XMLTYPE"){
                    obj.datatype="LONGTEXT";
                    obj.quey="LONGTEXT";
                            
                }
                
                
                
                
            }
    
   
  
    }
}
