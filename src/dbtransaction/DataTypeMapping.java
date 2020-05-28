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
                else if(type=="BINARY_FLOAT"){
                    obj.quey="FLOAT";
                }
                else if(type=="BINARY_DOUBLE"){
                    obj.quey="DOUBLE";
                }
                else if(type=="BLOB"){
                    obj.quey="LONGBLOB";
                }
                
                else if(type=="CHAR"||type=="CHARACTER"){
                    if(size>255){
                        obj.datatype="VARCHAR";
                        obj.quey="VARCHAR("+obj.columnsize+")";
                    }
                    else{
                        obj.quey=obj.datatype+"("+obj.columnsize+")";
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
                else if(type=="DECIMAL"||type=="DEC"){
                    obj.quey=obj.datatype+"("+obj.precision+","+obj.scale+")";
                }
                
                else if(type=="FLOAT"){
                    obj.datatype="DOUBLE";
                    obj.quey=obj.datatype;
                }
                else if(type=="INTEGER"){
                    obj.datatype="INT";
                }
                else if(type=="INTERVAL YEAR TO MONTH"||type=="INTERVAL DAY TO SECOND"){
                    obj.quey="VARCHAR(30)";
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
                else if(type=="NCLOB"){
                    obj.datatype="NVARCHAR";
                    obj.columnsize=(long)Math.pow(2, 31)-1;
                    obj.quey="NVARCHAR(MAX)";
                }
                 else if(type=="NUMBER"){
                    if(obj.scale==0&&obj.precision!=0){
                        if(obj.precision<3)obj.quey="TINYINT";
                        else if(obj.precision<5)obj.quey="SMALLINT";
                        else if(obj.precision<9)obj.quey="INT";
                        else if(obj.precision<19)obj.quey="BIGINT";
                        else obj.quey="DECIMAL("+obj.precision+")";
                    }
                    
                    else if(obj.scale!=0&&obj.precision!=0){
                        obj.quey="DECIMAL("+obj.precision+","+obj.scale+")";
                    }
                    else{
                        obj.quey="DOUBLE";
                    }
                    
                    
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
                    obj.quey="BINARY("+obj.columnsize+")";
                    
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
    
    
    public void mysqltoOracle(ArrayList<ColumnDetails> coldetail){
            int length=coldetail.size();
            for(int i=0;i<length;i++){
               ColumnDetails obj=coldetail.get(i);
                String type =obj.datatype;
                long size=obj.columnsize;
                
                if(type=="BIGINT"){
                    obj.quey="NUMBER(19)";
                }
                else if(type=="BINARY"){
                    if(obj.columnsize>0)
                    obj.quey="RAW("+obj.columnsize+")";
                    else obj.quey="RAW(1)";
                }
                else if(type=="BIT"){
                    obj.quey="RAW("+obj.columnsize/8+")";
                }
                else if(type=="BLOB"){
                    obj.quey="BLOB";
                }
                else if(type=="BOOLEAN"||type=="BOOL"){
                    obj.quey="CHAR(1)";
                }
                else if(type=="CHAR"){
                    obj.quey="CHAR("+obj.columnsize+")";
                }
                else if(type=="CHARACTER"){
                    obj.quey="CHARACTER("+obj.columnsize+")";
                }
                else if(type=="CHARACTER VARYING"){
                    obj.quey="VARCHAR2("+obj.columnsize+")";
                    
                }
//                else if(type=="DATE"){
//                    obj.quey="DATE";//January 10, 2012 3:01 am
//                }
                else if(type==""){
                    obj.quey="";
                }
                else if(type=="DATETIME"){
                    obj.quey="TIMESTAMP("+obj.precision+")";
                }
                else if(type=="DECIMAL"||type=="DEC"){
                    obj.quey="NUMBER("+obj.precision+","+obj.scale+")";
                }
                else if(type=="DOUBLE"){
                    obj.quey="BINARY_DOUBLE";
                }
                else if(type=="FIXED"){
                    obj.quey="NUMBER("+obj.precision+","+obj.scale+")";
                }
                else if(type=="FLOAT"||type=="FLOAT4"||type=="FLOAT8"){
                    obj.quey="BINARY_DOUBLE";
                }
                else if(type=="INT"||type=="INTEGER"){
                    obj.quey="NUMBER(10)";
                }
                else if(type=="INT1"){
                    obj.quey="NUMBER(3)";
                }
                else if(type=="INT2"){
                    obj.quey="NUMBER(5)";
                }
                else if(type=="INT3"){
                    obj.quey="NUMBER(7)";
                }
                else if(type=="INT4"){
                    obj.quey="NUMBER(10)";
                }
                else if(type=="INT8"){
                    obj.quey="NUMBER(19)";
                }
                else if(type=="LONGBLOB"){
                    obj.quey="BLOB";
                }
                else if(type=="LONGTEXT"){
                    obj.quey="CLOB";
                }
                else if(type=="LONG VARBINARY"){
                    obj.quey="BLOB";
                }
                else if(type=="LONG"||type=="LONG VARCHAR"){
                    obj.quey="CLOB";
                }
                else if(type=="MEDIUMBLOB"){
                    obj.quey="BLOB";
                }
                else if(type=="MEDIUMINT"){
                    obj.quey="NUMBER(7)";
                }
                else if(type=="MEDIUMTEXT"){
                    obj.quey="CLOB";
                }
                else if(type=="MIDDLEINT"){
                    obj.quey="NUMBER(7)";
                }
                else if(type=="NCHAR"){
                    obj.quey="NCHAR("+obj.columnsize+")";
                }
                else if(type=="NVARCHAR"){
                    obj.quey="NVARCHAR2("+obj.columnsize+")";
                }
                else if(type=="NUMERIC"){
                    obj.quey="NUMBER("+obj.precision+","+obj.scale+")";
                }
                else if(type=="REAL"){
                    obj.quey="BINARY_DOUBLE";
                }
                else if(type=="SMALLINT"){
                    obj.quey="NUMBER(5)";
                }
                else if(type=="TEXT"){
                    obj.quey="CLOB";
                }
                else if(type=="TIME"){
                    obj.quey="TIMESTAMP("+obj.precision+")";
                }
                else if(type=="TIMESTAMP"){
                    obj.quey="TIMESTAMP("+obj.precision+")";
                }
                else if(type=="TINYBLOB"){
                    obj.quey="RAW(255)";
                }
                else if(type=="TINYINT"){
                    obj.quey="NUMBER(3)";
                }
                else if(type=="TINYTEXT"){
                    obj.quey="VARCHAR2(255)";
                }
                else if(type=="VARBINARY"){
                    obj.quey="RAW("+obj.columnsize+")";
                }
                else if(type=="VARCHAR"){
                    obj.quey="VARCHAR2("+obj.columnsize+")";
                }
                else if(type=="YEAR"){
                    obj.quey="NUMBER(4)";
                }
                
            }
    }
    
    
    
}
