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
                    obj.quey="VARCHAR(255)";
                }
                else if(type=="BINARY_FLOAT"){
                    obj.quey="FLOAT";
                }
                else if(type=="BINARY_DOUBLE"){
                    obj.quey="DOUBLE";
                }
                else if(type=="BINARY_FLOAT UNSIGNED"){
                    obj.quey="FLOAT UNSIGNED";
                }
                else if(type=="BINARY_DOUBLE UNSIGNED"){
                    obj.quey="DOUBLE UNSIGNED";
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
                
                else if(type=="INTEGER "){
                    obj.quey="INT";
                }
                else if(type=="INTERVAL YEAR TO MONTH"||type=="INTERVAL DAY TO SECOND"){
                    obj.quey="VARCHAR(30)";
                }
                else if(type=="LONG"){
                    obj.quey="LONGTEXT";
                }
                else if(type=="LONG ROW"){
                    obj.quey="LONGBLOB";
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
                else if(type=="SMALLINT "){
                    obj.datatype="DECIMAL";
                    obj.columnsize=38;
                    obj.quey="DECIMAL(38)";
                    
                }
                 else if(type=="TIMESTAMP"){
                    
                    obj.quey="DATETIME("+obj.scale+")";
                }
                 else if(type=="TIMESTAMP WITH TIME ZONE"){
                    
                    obj.quey="DATETIME("+obj.scale+")";
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
                
                if(type=="BIGINT"||type=="BIGINT UNSIGNED"){obj.quey="NUMBER(19)";}
                else if(type=="BINARY"){
                    if(obj.columnsize>0)obj.quey="RAW("+obj.columnsize+")";
                    else obj.quey="RAW(1)";}
                else if(type=="BIT"){obj.quey="RAW("+obj.columnsize/8+")";}
                else if(type=="BLOB"){obj.quey="BLOB";}
                else if(type=="BOOLEAN"||type=="BOOL"){obj.quey="CHAR(1)";}
                else if(type=="CHAR"){obj.quey="CHAR("+obj.columnsize+")";}
                else if(type=="CHARACTER"){obj.quey="CHARACTER("+obj.columnsize+")";}
                else if(type=="CHARACTER VARYING"){obj.quey="VARCHAR2("+obj.columnsize+")";}
//                else if(type=="DATE"){
//                    obj.quey="DATE";//January 10, 2012 3:01 am
//                }
                else if("".equalsIgnoreCase(type)){obj.quey="";}
                else if("DATETIME".equalsIgnoreCase(type)){obj.quey="TIMESTAMP";}
                else if("DECIMAL".equalsIgnoreCase(type)||"DEC".equalsIgnoreCase(type)||"DECIMAL UNSIGNED".equalsIgnoreCase(type)||"DEC UNSIGNED".equalsIgnoreCase(type)){obj.quey="NUMBER("+obj.precision+","+obj.scale+")";}

                else if("DOUBLE".equalsIgnoreCase(type)||"DOUBLE UNSIGNED".equalsIgnoreCase(type)){obj.quey="BINARY_DOUBLE";}
                else if("FIXED".equalsIgnoreCase(type)||"FIXED UNSIGNED".equalsIgnoreCase(type)){obj.quey="NUMBER("+obj.precision+","+obj.scale+")";}
                else if("FLOAT".equalsIgnoreCase(type)||"FLOAT4".equalsIgnoreCase(type)||"FLOAT8".equalsIgnoreCase(type)||"FLOAT UNSIGNED".equalsIgnoreCase(type)||"FLOAT4 UNSIGNED".equalsIgnoreCase(type)||"FLOAT8 UNSIGNED".equalsIgnoreCase(type)){obj.quey="BINARY_DOUBLE";}
                else if("INT".equalsIgnoreCase(type)||"INTEGER".equalsIgnoreCase(type)||"INT UNSIGNED".equalsIgnoreCase(type)||"INTEGER UNSIGNED".equalsIgnoreCase(type)){obj.quey="NUMBER(10)";}
                else if("INT1".equalsIgnoreCase(type)||"INT1 UNSIGNED".equalsIgnoreCase(type)){obj.quey="NUMBER(3)";}
                else if("INT2".equalsIgnoreCase(type)||"INT2 UNSIGNED".equalsIgnoreCase(type)){obj.quey="NUMBER(5)";}
                else if("INT3".equalsIgnoreCase(type)||"INT3 UNSIGNED".equalsIgnoreCase(type)){obj.quey="NUMBER(7)";}
                else if("INT4".equalsIgnoreCase(type)||"INT4 UNSIGNED".equalsIgnoreCase(type)){obj.quey="NUMBER(10)";}
                else if("INT8".equalsIgnoreCase(type)||"INT8 UNSIGNED".equalsIgnoreCase(type)){obj.quey="NUMBER(19)";}
                else if("LONGBLOB".equalsIgnoreCase(type)){obj.quey="BLOB";}
                else if("LONGTEXT".equalsIgnoreCase(type)){obj.quey="CLOB";}
                else if("LONG VARBINARY".equalsIgnoreCase(type)){obj.quey="BLOB";}
                else if("LONG".equalsIgnoreCase(type)||"LONG VARCHAR".equalsIgnoreCase(type)){obj.quey="CLOB";}
                else if("MEDIUMBLOB".equalsIgnoreCase(type)){obj.quey="BLOB";}
                else if("MEDIUMINT".equalsIgnoreCase(type)||"MEDIUMINT UNSIGNED".equalsIgnoreCase(type)){obj.quey="NUMBER(7)";}
                
                else if("MEDIUMTEXT".equalsIgnoreCase(type)){obj.quey="CLOB";}
                else if("MIDDLEINT".equalsIgnoreCase(type)||"MIDDLEINT UNSIGNED".equalsIgnoreCase(type)){obj.quey="NUMBER(7)";}
                else if("NCHAR".equalsIgnoreCase(type)){obj.quey="NCHAR("+obj.columnsize+")";}
                else if("NVARCHAR".equalsIgnoreCase(type)){obj.quey="NVARCHAR2("+obj.columnsize+")";}
                else if("NUMERIC".equalsIgnoreCase(type)||"NUMERIC UNSIGNED".equalsIgnoreCase(type)){obj.quey="NUMBER("+obj.precision+","+obj.scale+") ";}
                else if("REAL".equalsIgnoreCase(type)){obj.quey="BINARY_DOUBLE";}
                else if("SMALLINT".equalsIgnoreCase(type)||"SMALLINT UNSIGNED".equalsIgnoreCase(type)){obj.quey="NUMBER(5)";}
                else if("TEXT".equalsIgnoreCase(type)){obj.quey="CLOB";}
                else if("TIME".equalsIgnoreCase(type)){obj.quey="TIMESTAMP";}
                else if("TIMESTAMP".equalsIgnoreCase(type)){obj.quey="TIMESTAMP";}
                else if("TINYBLOB".equalsIgnoreCase(type)){obj.quey="RAW(255)";}
                else if("TINYINT".equalsIgnoreCase(type)||"TINYINT UNSIGNED".equalsIgnoreCase(type)){obj.quey="NUMBER(3)";}
                else if("TINYTEXT".equalsIgnoreCase(type)){obj.quey="VARCHAR2(255)";}
                else if("VARBINARY".equalsIgnoreCase(type)){obj.quey="RAW("+obj.columnsize+")";}
                else if("VARCHAR".equalsIgnoreCase(type)){obj.quey="VARCHAR2("+obj.columnsize+")";}
                else if("YEAR".equalsIgnoreCase(type)){obj.quey="NUMBER(4)";}
            }
            
    }
    public void oracletoPostgre(ArrayList<ColumnDetails> coldetail){
            int length=coldetail.size();
            for(int i=0;i<length;i++){
               ColumnDetails obj=coldetail.get(i);
                String type =obj.datatype;
                long size=obj.columnsize;
                
                if("BFILE".equalsIgnoreCase(type)){obj.quey="VARCHAR(255)";continue;}
                else if("BINARY_FLOAT".equalsIgnoreCase(type)){obj.quey="REAL";continue;}
                else if("BINARY_DOUBLE".equalsIgnoreCase(type)){obj.quey="DOUBLE PRECISION";continue;}
                else if("BLOB".equalsIgnoreCase(type)){obj.quey="BYTEA";continue;}
                else if("CHAR".equalsIgnoreCase(type)||"CHARACTER".equalsIgnoreCase(type)){
                    if(size>255){obj.quey="VARCHAR("+obj.columnsize+")";
                    }else{obj.quey=obj.datatype+"("+obj.columnsize+")";}
                }
                else if("CLOB".equalsIgnoreCase(type)){obj.quey="TEXT";continue;}
                else if("DATE".equalsIgnoreCase(type)){obj.quey="TIMESTAMP";continue;}
                else if("DECIMAL".equalsIgnoreCase(type)||"DEC".equalsIgnoreCase(type)){obj.quey=obj.datatype+"("+obj.precision+","+obj.scale+")";continue;}
                else if("FLOAT".equalsIgnoreCase(type)){obj.quey="DOUBLE PRECISION";continue;}
                else if("INTEGER".equalsIgnoreCase(type)||"INT".equalsIgnoreCase(type)){obj.quey="DECIMAL(38)";continue;}
                else if("INTERVAL DAY TO SECOND".equalsIgnoreCase(type)){obj.quey=obj.datatype+"("+obj.scale+")";continue;}
                else if("LONG".equalsIgnoreCase(type)){obj.quey="TEXT";continue;}
                else if("LONG ROW".equalsIgnoreCase(type)){obj.quey="BYTEA";continue;}
                else if("NCHAR".equalsIgnoreCase(type)){obj.quey="CHAR"+"("+obj.columnsize+")";continue;}
                else if("NCHAR VARYING".equalsIgnoreCase(type)){obj.quey="VARCHAR("+obj.columnsize+")";continue;}
                else if("NCLOB".equalsIgnoreCase(type)){obj.quey="TEXT";continue;}
                else if("NUMBER".equalsIgnoreCase(type)){
                    if(obj.scale==0&&obj.precision!=0){
                        if(obj.precision<3)obj.quey="SMALLINT";
                        else if(obj.precision<5)obj.quey="SMALLINT";
                        else if(obj.precision<9){obj.datatype="INT";obj.quey="INT";}
                        else if(obj.precision<19)obj.quey="BIGINT";
                        else obj.quey="DECIMAL("+obj.precision+")";
                    }
                    else if(obj.scale!=0&&obj.precision!=0){obj.quey="DECIMAL("+obj.precision+","+obj.scale+")";continue;
                    }else{obj.quey="DOUBLE PRECISION";continue;}
                 }
                else if("NUMERIC".equalsIgnoreCase(type)){obj.quey=obj.datatype+"("+obj.precision+","+obj.scale+")";continue;}
                else if("NVARCHAR2".equalsIgnoreCase(type)){obj.quey="VARCHAR("+obj.columnsize+")";continue;}
                else if("RAW".equalsIgnoreCase(type)){obj.quey="BYTEA";continue;}
                else if("REAL".equalsIgnoreCase(type)){obj.quey="DOUBLE PRECISION";continue;}
                else if("ROWID".equalsIgnoreCase(type)){obj.quey="CHAR(10)";continue;}
                else if("SMALLINT ".equalsIgnoreCase(type)){obj.quey="DECIMAL(38)";continue;}
                else if("TIMESTAMP".equalsIgnoreCase(type)){;obj.quey="TIMESTAMP";continue;}
                else if("TIMESTAMP WITH TIME ZONE".equalsIgnoreCase(type)){;obj.quey="TIMESTAMP WITH TIME ZONE";continue;}
                else if("UROWID".equalsIgnoreCase(type)){obj.quey="VARCHAR("+obj.columnsize+")";continue;}
                else if("VARCHAR2".equalsIgnoreCase(type)||"VARCHAR".equalsIgnoreCase(type)){obj.datatype="VARCHAR";obj.quey="VARCHAR("+obj.columnsize+")";continue;}
                else if("XMLTYPE".equalsIgnoreCase(type)){obj.quey="XML";continue;}
                
            }
    }
    
    public void postgretoOracle(ArrayList<ColumnDetails> coldetail){
        int length=coldetail.size();
        for(int i=0;i<length;i++){
            ColumnDetails obj=coldetail.get(i);
            String type =obj.datatype;
            long size=obj.columnsize;
                
            if(type=="BIGINT"){
                obj.quey="NUMBER(19)";
            }
            else if("BIGSERIAL".equalsIgnoreCase(type)){obj.quey="";}//////sequence and trigger
            else if("BIT".equalsIgnoreCase(type)||"BIT VARYING".equalsIgnoreCase(type)){obj.quey="RAW("+obj.columnsize/8+")";continue;}
            else if("BOOLEAN".equalsIgnoreCase(type)||"BOOL".equalsIgnoreCase(type)){obj.quey="CHAR(1)";continue;}
            else if("BYTEA".equalsIgnoreCase(type)){obj.quey="BLOB";continue;}
            else if("CHARACTER".equalsIgnoreCase(type)||"CHAR".equalsIgnoreCase(type)){obj.quey=obj.datatype+"("+obj.columnsize+")";continue;}
            else if("DECIMAL".equalsIgnoreCase(type)||"DEC".equalsIgnoreCase(type)){obj.quey="NUMBER("+obj.precision+","+obj.scale+")";continue;}
            else if("DOUBLE PRECISION".equalsIgnoreCase(type)||"FLOAT8".equalsIgnoreCase(type)){obj.quey="BINARY_DOUBLE";continue;}
            else if("FLOAT4".equalsIgnoreCase(type)){obj.quey="BINARY_FLOAT";continue;}
            else if("INTEGER".equalsIgnoreCase(type)||"INT".equalsIgnoreCase(type)){obj.quey="NUMBER(10)";continue;}
            else if("INT2".equalsIgnoreCase(type)){obj.quey="NUMBER(5)";continue;}
            else if("INT4".equalsIgnoreCase(type)){obj.quey="NUMBER(10)";continue;}
            else if("INT8".equalsIgnoreCase(type)){obj.quey="NUMBER(20)";continue;}
            else if("INTERVAL".equalsIgnoreCase(type)){obj.quey="NUMBER(5)";continue;}
            else if("INTERVAL DAY TO HOUR".equalsIgnoreCase(type)){obj.quey="INTERVAL DAY(5) TO SECOND";continue;}
            else if("INTERVAL DAY TO MINUTE".equalsIgnoreCase(type)){obj.quey="INTERVAL DAY(5) TO SECOND";continue;}
            else if("INTERVAL HOUR TO MINUTE".equalsIgnoreCase(type)){obj.quey="INTERVAL DAY(5) TO SECOND";continue;}
            else if("INTERVAL HOUR TO SECOND".equalsIgnoreCase(type)){obj.quey="INTERVAL DAY(5) TO SECOND[("+obj.precision+")]";continue;}
            else if("INTERVAL MINUTE TO SECOND".equalsIgnoreCase(type)){obj.quey="INTERVAL DAY(5) TO SECOND[("+obj.precision+")]";continue;}
            else if("INTERVAL DAY TO SECOND".equalsIgnoreCase(type)){obj.quey="INTERVAL DAY(5) TO SECOND[("+obj.precision+")]";continue;}
            else if("MONEY".equalsIgnoreCase(type)){obj.quey="NUMBER(17,2)";continue;}
            else if("NUMERIC".equalsIgnoreCase(type)){obj.quey="NUMERIC("+obj.precision+","+obj.scale+")";continue;}
            else if("REAL".equalsIgnoreCase(type)){obj.quey="BINARY_FLOAT";continue;}
            //serial is rest....................................
            else if("SMALLINT".equalsIgnoreCase(type)){obj.quey="NUMBER(5)";continue;}
            else if("TEXT".equalsIgnoreCase(type)){obj.quey="CLOB";continue;}
            else if("TIME".equalsIgnoreCase(type)){obj.quey="TIMESTAMP("+obj.precision+")";continue;}
            else if("TIME WITH TIME ZONE".equalsIgnoreCase(type)||"TIMESTAMPZ".equalsIgnoreCase(type)){obj.quey="TIMESTAMP("+obj.precision+") WITH TIME ZONE";continue;}
            else if("UUID".equalsIgnoreCase(type)){obj.quey="CHAR(36)";continue;}
            else if("VARBIT".equalsIgnoreCase(type)){obj.quey="RAW("+obj.columnsize/8+")";continue;}
            else if("VARCHAR".equalsIgnoreCase(type)){obj.quey="VARCHAR2("+obj.columnsize+")";continue;}
            else if("XML".equalsIgnoreCase(type)){obj.quey="XMLTYPE";continue;}
        }
    }
    
    public void mysqltoPostgres(ArrayList<ColumnDetails> coldetail){
        int length=coldetail.size();
        for(int i=0;i<length;i++){
            ColumnDetails obj=coldetail.get(i);
            String type =obj.datatype;
            long size=obj.columnsize;
            if("BINARY".equalsIgnoreCase(type)){obj.quey="BYTEA";}
            else if("BIT".equalsIgnoreCase(type)){obj.quey="BOOLEAN";}
            else if("CHAR".equalsIgnoreCase(type)||"CHARACTER".equalsIgnoreCase(type)){obj.quey=obj.datatype+"("+obj.columnsize+")";}
            else if("DATETIME".equalsIgnoreCase(type)){obj.quey="TIMESTAMP";}
            else if("DECIMAL".equalsIgnoreCase(type)||"DEC".equalsIgnoreCase(type)){obj.quey=obj.datatype+"("+obj.precision+","+obj.scale+")";}
            else if("DOUBLE".equalsIgnoreCase(type)){obj.quey="DOUBLE PRECISION";}
            else if("FLOAT".equalsIgnoreCase(type)){obj.quey="REAL";}
            else if("MEDIUMINT".equalsIgnoreCase(type)){obj.quey="INTEGER";}
            else if("NUMERIC".equalsIgnoreCase(type)){obj.quey="NUMERIC("+obj.precision+","+obj.scale+")";}
            else if("TINYBLOB".equalsIgnoreCase(type)||"BLOB".equalsIgnoreCase(type)||"MEDIUMBLOB".equalsIgnoreCase(type)||"LONGBLOB".equalsIgnoreCase(type)){obj.quey="BYTEA";}
            else if("TINYINT".equalsIgnoreCase(type)&&!obj.autoIncrement&&obj.sign){obj.quey="SMALLINT";}
            else if("TINYTEXT".equalsIgnoreCase(type)||"TEXT".equalsIgnoreCase(type)||"MEDIUMTEXT".equalsIgnoreCase(type)||"LONGTEXT".equalsIgnoreCase(type)){obj.quey="TEXT";}
            else if("VARBINARY".equalsIgnoreCase(type)){obj.quey="BYTEA";}
            //else if("VARCHAR".equalsIgnoreCase(type)){obj.quey="VARCHAR("+obj.columnsize+")";}
            else if("VARCHAR".equalsIgnoreCase(type)){obj.quey="TEXT";}
            else if("BIGINT".equalsIgnoreCase(type)&&obj.autoIncrement){obj.quey="BIGSERIAL";}
            else if("INTEGER".equalsIgnoreCase(type)&&obj.autoIncrement){obj.quey="SERIAL";}
            else if("SMALLINT".equalsIgnoreCase(type)&&obj.autoIncrement){obj.quey="SMALLSERIAL";}
            else if("TINYINT".equalsIgnoreCase(type)&&obj.autoIncrement){obj.quey="SMALLSERIAL";}
            else if("BIGINT UNSIGNED".equalsIgnoreCase(type)&&!obj.sign){obj.quey="NUMERIC(20)";}
            else if("INT UNSIGNED".equalsIgnoreCase(type)&&!obj.sign){obj.quey="BIGINT";}
            else if("SMALLINT UNSIGNED".equalsIgnoreCase(type)&&!obj.sign){obj.quey="INTEGER";}
            else if("MEDIUMINT UNSIGNED".equalsIgnoreCase(type)&&!obj.sign){obj.quey="INTEGER";}
            else if("TINYINT UNSIGNED".equalsIgnoreCase(type)&&!obj.sign){obj.quey="INTEGER";}
            else if("ENUM".equalsIgnoreCase(type)){obj.quey="TEXT";}
            else if("YEAR".equalsIgnoreCase(type)){obj.quey="TIMESTAMP";}
            else if("SET".equalsIgnoreCase(type)){obj.quey="TEXT";}/////
            
            
        }
    }

    public void postgretoMysql(ArrayList<ColumnDetails> coldetail){
        int length=coldetail.size();
        for(int i=0;i<length;i++){
            ColumnDetails obj=coldetail.get(i);
            String type =obj.datatype;
            long size=obj.columnsize;
                
            if("BOOLEAN".equalsIgnoreCase(type)||"BOOL".equalsIgnoreCase(type)){obj.quey="TINYINT(1)";continue;}
            else if("BYTEA".equalsIgnoreCase(type)){obj.quey="LONGBLOB";continue;}
            
            else if("CHARACTER".equalsIgnoreCase(type)||"CHAR".equalsIgnoreCase(type)){obj.quey="LONGTEXT";continue;}
            else if("CIDR".equalsIgnoreCase(type)){obj.quey="VARCHAR(43)";}
            else if("DOUBLE PRECISION".equalsIgnoreCase(type)){obj.quey="DOUBLE";continue;}
            else if("INET".equalsIgnoreCase(type)){obj.quey="VARCHAR(43)";}
            else if("INTERVAL".equalsIgnoreCase(type)){obj.quey="TIME";continue;}
            else if("INTERVAL DAY TO HOUR".equalsIgnoreCase(type)){obj.quey="INTERVAL DAY(5) TO SECOND";continue;}
            else if("INTERVAL DAY TO MINUTE".equalsIgnoreCase(type)){obj.quey="INTERVAL DAY(5) TO SECOND";continue;}
            else if("INTERVAL HOUR TO MINUTE".equalsIgnoreCase(type)){obj.quey="INTERVAL DAY(5) TO SECOND";continue;}
            else if("INTERVAL HOUR TO SECOND".equalsIgnoreCase(type)){obj.quey="INTERVAL DAY(5) TO SECOND[("+obj.precision+")]";continue;}
            else if("INTERVAL MINUTE TO SECOND".equalsIgnoreCase(type)){obj.quey="INTERVAL DAY(5) TO SECOND[("+obj.precision+")]";continue;}
            else if("INTERVAL DAY TO SECOND".equalsIgnoreCase(type)){obj.quey="INTERVAL DAY(5) TO SECOND[("+obj.precision+")]";continue;}
            else if("MACADDR".equalsIgnoreCase(type)){obj.quey="VARCHAR(17)";}
            else if("MONEY".equalsIgnoreCase(type)){obj.quey="DECIMAL(19,2)";continue;}
            else if("NATIONAL CHARACTER".equalsIgnoreCase(type)){obj.quey="LONGTEXT";continue;}
            else if("NATINAL CHARACTER VARYING".equalsIgnoreCase(type)){obj.quey="LONGTEXT";continue;}
            else if("NUMERIC".equalsIgnoreCase(type)){obj.quey="DECIMAL";continue;}
            else if("REAL".equalsIgnoreCase(type)){obj.quey="FLOAT";continue;}
            //serial is rest....................................
            else if("SERIAL".equalsIgnoreCase(type)){obj.quey="INT";}//set auto increment enable}
            else if("SMALLSERIAL".equalsIgnoreCase(type)){obj.quey="SMALLINT";}//set auto increment enable}
            else if("BIGSERIAL".equalsIgnoreCase(type)){obj.quey="BIGINT";}//set auto increment enable}
            else if("TEXT".equalsIgnoreCase(type)){obj.quey="LONGTEXT";continue;}
            else if("TIMESTAMP".equalsIgnoreCase(type)){obj.quey="DATETIME";continue;}
            else if("TIME WITH TIME ZONE".equalsIgnoreCase(type)||"TIMESTAMPZ".equalsIgnoreCase(type)){obj.quey="TIMESTAMP("+obj.precision+") WITH TIME ZONE";continue;}
            else if("UUID".equalsIgnoreCase(type)){obj.quey="VARCHAR(36)";continue;}
            else if("VARCHAR".equalsIgnoreCase(type)){obj.quey="LONGTEXT";continue;}
            else if("XML".equalsIgnoreCase(type)||"JSON".equalsIgnoreCase(type)||"TSVECTOR".equalsIgnoreCase(type)||"TSQUERY".equalsIgnoreCase(type)||"ARRAY".equalsIgnoreCase(type)){obj.quey="LONGTEXT";continue;}
            else if("LINE".equalsIgnoreCase(type)){obj.quey="LINESTRING";}
            else if("LSEG".equalsIgnoreCase(type)){obj.quey="LINESTRING";}
            else if("BOX".equalsIgnoreCase(type)){obj.quey="POLYGON";}
            else if("PATH".equalsIgnoreCase(type)){obj.quey="LINESTRING";}
            else if("CIRCLE".equalsIgnoreCase(type)){obj.quey="POLYGON";}
            else if("TXID_SNAPSHOT".equalsIgnoreCase(type)){obj.quey="VARCHAR";}
        }

    }
    public void mssqltoOracle(ArrayList<ColumnDetails> coldetail){
        int length=coldetail.size();
        for(int i=0;i<length;i++){
            ColumnDetails obj=coldetail.get(i);
            String type =obj.datatype;
            long size=obj.columnsize;
                
            if("CHAR".equalsIgnoreCase(type)||"NCHAR".equalsIgnoreCase(type)){
                obj.quey=obj.datatype+"("+obj.columnsize+")";
            }
            else if("NVARCHAR".equalsIgnoreCase(type)&&obj.columnsize<=4000){obj.quey="NVARCHAR2("+obj.columnsize+")";}
            else if("NVARCHAR".equalsIgnoreCase(type)&&obj.columnsize>4000){obj.quey="NCLOB";}
            else if("VARCHAR".equalsIgnoreCase(type)&&obj.columnsize<=8000){obj.quey="VARCHAR2("+obj.columnsize+")";}
            else if("VARCHAR".equalsIgnoreCase(type)&&obj.columnsize>8000){obj.quey="CLOB";}  
            else if("BIGINT".equalsIgnoreCase(type)){obj.quey="NUMBER(19)";}
            else if("DECIMAL".equalsIgnoreCase(type)||"DEC".equalsIgnoreCase(type)){obj.quey="NUMBER("+obj.precision+","+obj.scale+")";}
            else if("FLOAT".equalsIgnoreCase(type)){obj.quey="NUMBER";}
            else if("INTEGER".equalsIgnoreCase(type)||"INT".equalsIgnoreCase(type)){obj.quey="NUMBER(10)";}
            else if("NUMERIC".equalsIgnoreCase(type)){obj.quey="NUMBER("+obj.precision+","+obj.scale+")";}
            else if("REAL".equalsIgnoreCase(type)){obj.quey="NUMBER";}
            else if("SMALLINT".equalsIgnoreCase(type)){obj.quey="NUMBER(5)";}
            else if("TINYINT".equalsIgnoreCase(type)){obj.quey="NUMBER(3)";}
            else if("DATETIME".equalsIgnoreCase(type)){obj.quey="TIMESTAMP(3)";}
            else if("TIME".equalsIgnoreCase(type)){obj.quey="TIMESTAMP";}////improvment needed
            else if("BIT".equalsIgnoreCase(type)){obj.quey="NUMBER(1)";}
            else if("MONEY".equalsIgnoreCase(type)){obj.quey="NUMBER(19,4)";}
            else if("SMALLMONEY".equalsIgnoreCase(type)){obj.quey="NUMBER(10,4)";}
            else if("UNIQUEIDENTIFIER".equalsIgnoreCase(type)){obj.quey="CHAR(36)";}
            else if("XML".equalsIgnoreCase(type)){obj.quey="XMLTYPE";}
        }
    }
    
    public void mssqltoMysql(ArrayList<ColumnDetails> coldetail){
        int length=coldetail.size();
        for(int i=0;i<length;i++){
            ColumnDetails obj=coldetail.get(i);
            String type =obj.datatype;
            long size=obj.columnsize;
                
            if("BINARY".equalsIgnoreCase(type)){
                obj.quey=obj.datatype+"("+obj.columnsize+")";
            }
            else if("BIT".equalsIgnoreCase(type)){obj.quey="TINYINT";}
            else if("CHAR".equalsIgnoreCase(type)&&obj.columnsize<256){obj.quey="CHAR("+obj.columnsize+")";}
            else if("CHAR".equalsIgnoreCase(type)&&obj.columnsize>255){obj.quey="TEXT";}
            else if("DATETIME".equalsIgnoreCase(type)){obj.quey="DATETIME(3)";}
            else if("DATETIME2".equalsIgnoreCase(type)){obj.quey="DATETIME";}////imporovment needed
            else if("DATETIMEOFFSET".equalsIgnoreCase(type)){obj.quey="DATETIME";}
            else if("DECIMAL".equalsIgnoreCase(type)){obj.quey="DECIMAL("+obj.precision+","+obj.scale+")";}
            else if("FLOAT".equalsIgnoreCase(type)){obj.quey="DOUBLE";}
            else if("IMAGE".equalsIgnoreCase(type)){obj.quey="LONGBLOB";}
            else if("MONEY".equalsIgnoreCase(type)){obj.quey="DECIMAL(15,4)";}
            else if("NCHAR".equalsIgnoreCase(type)&&obj.columnsize<256){obj.quey="NCHAR("+obj.columnsize+")";}
            else if("NCHAR".equalsIgnoreCase(type)&&obj.columnsize>255){obj.quey="TEXT";}
            else if("NTEXT".equalsIgnoreCase(type)){obj.quey="LONGTEXT";}
            else if("NUMERIC".equalsIgnoreCase(type)){obj.quey="NUMERIC("+obj.precision+","+obj.scale+")";}
            else if("NVARCHAR".equalsIgnoreCase(type)&&obj.columnsize<4001){obj.quey="NVARCHAR("+obj.columnsize+")";}
            else if("NVARCHAR".equalsIgnoreCase(type)){obj.quey="LONGTEXT";}
            else if("ROWVERSION".equalsIgnoreCase(type)){obj.quey="BINARY(8)";}
            else if("SMALLDATETIME".equalsIgnoreCase(type)){obj.quey="DATETIME";}
            else if("SMALLMONEY".equalsIgnoreCase(type)){obj.quey="DECIMAL(6,4)";}
            else if("TEXT".equalsIgnoreCase(type)){obj.quey="LONGTEXT";}
            else if("TIME".equalsIgnoreCase(type)){obj.quey="TIME";}/////
            else if("TIMESTAMP".equalsIgnoreCase(type)){obj.quey="BINARY(8)";}
            else if("UNIQUEIDENTIFIER".equalsIgnoreCase(type)){obj.quey="CHAR(16)";}
            else if("VARBINARY".equalsIgnoreCase(type)&&obj.columnsize<8001){obj.quey="VARBINARY("+obj.columnsize+")";}
            else if("VARBINARY".equalsIgnoreCase(type)){obj.quey="LONGBLOB";}
            else if("VARCHAR".equalsIgnoreCase(type)&&obj.columnsize<8001){obj.quey="VARCHAR("+obj.columnsize+")";}
            else if("VARCHAR".equalsIgnoreCase(type)){obj.quey="LONGTEXT";}
            else if("XML".equalsIgnoreCase(type)){obj.quey="LONGTEXT";}
          
            
        }
    }
    
    public void mssqltoPostgre(ArrayList<ColumnDetails> coldetail){
        int length=coldetail.size();
        for(int i=0;i<length;i++){
            ColumnDetails obj=coldetail.get(i);
            String type =obj.datatype;
            long size=obj.columnsize;
                
            if("BINARY".equalsIgnoreCase(type)){
                obj.quey="BYTEA";
            }
            else if("BIT".equalsIgnoreCase(type)){obj.quey="BOOLEAN";}
            else if("CHAR".equalsIgnoreCase(type)||"CHARACTER".equalsIgnoreCase(type)){obj.quey=obj.datatype+"("+obj.columnsize+")";}
            else if("DATETIME".equalsIgnoreCase(type)){obj.quey="TIMESTAMP(3)";}
            else if("DATETIME2".equalsIgnoreCase(type)){obj.quey="TIMESTAMP";}////imporovment needed
            else if("DATETIMEOFFSET".equalsIgnoreCase(type)){obj.quey="TIMESTAMP WITH TIME ZONE";}//////
            else if("DECIMAL".equalsIgnoreCase(type)||"DEC".equalsIgnoreCase(type)){obj.quey=obj.datatype+"("+obj.precision+","+obj.scale+")";}
            else if("FLOAT".equalsIgnoreCase(type)){obj.quey="DOUBLE PRECISION";}
            else if("IMAGE".equalsIgnoreCase(type)){obj.quey="BYTEA";}
            else if("NCHAR".equalsIgnoreCase(type)){obj.quey="CHAR("+obj.columnsize+")";}
            else if("NTEXT".equalsIgnoreCase(type)){obj.quey="TEXT";}
            else if("NUMERIC".equalsIgnoreCase(type)){obj.quey="NUMERIC("+obj.precision+","+obj.scale+")";}
            else if("NVARCHAR".equalsIgnoreCase(type)&&obj.columnsize<4001){obj.quey="VARCHAR("+obj.columnsize+")";}
            else if("NVARCHAR".equalsIgnoreCase(type)){obj.quey="TEXT";}
            else if("ROWVERSION".equalsIgnoreCase(type)){obj.quey="BYTEA";}
            else if("SMALLDATETIME".equalsIgnoreCase(type)){obj.quey="TIMESTAMP(0)";}
            else if("SMALLMONEY".equalsIgnoreCase(type)){obj.quey="MONEY";}
            else if("TEXT".equalsIgnoreCase(type)){obj.quey="TEXT";}
            else if("TIME".equalsIgnoreCase(type)){obj.quey="TIME";}/////
            else if("TIMESTAMP".equalsIgnoreCase(type)){obj.quey="BYTEA";}
            else if("TINYINT".equalsIgnoreCase(type)){obj.quey="SMALLINT";}
            else if("UNIQUEIDENTIFIER".equalsIgnoreCase(type)){obj.quey="CHAR(16)";}
            else if("VARBINARY".equalsIgnoreCase(type)){obj.quey="BYTEA";}
            else if("VARCHAR".equalsIgnoreCase(type)&&obj.columnsize<8001){obj.quey="VARCHAR("+obj.columnsize+")";}
            else if("VARCHAR".equalsIgnoreCase(type)){obj.quey="TEXT";}
            else if("XML".equalsIgnoreCase(type)){obj.quey="XML";}
          
            
        }
    }
    
    public void oracletoMssql(ArrayList<ColumnDetails> coldetail){
            int length=coldetail.size();
            for(int i=0;i<length;i++){
               ColumnDetails obj=coldetail.get(i);
                String type =obj.datatype;
                long size=obj.columnsize;
                
                if("BFILE".equalsIgnoreCase(type)){obj.quey="VARCHAR(255)";continue;}
                else if("BINARY_FLOAT".equalsIgnoreCase(type)){obj.quey="REAL";continue;}
                else if("BINARY_DOUBLE".equalsIgnoreCase(type)){obj.quey="DOUBLE PRECISION";continue;}
                else if("BLOB".equalsIgnoreCase(type)){obj.quey="VARBINARY(max)";continue;}
                else if("CHAR".equalsIgnoreCase(type)||"CHARACTER".equalsIgnoreCase(type)){obj.quey=obj.datatype+"("+obj.columnsize+")";}
                else if("CLOB".equalsIgnoreCase(type)){obj.quey="VARCHAR(max)";continue;}
                else if("DATE".equalsIgnoreCase(type)){obj.quey="DATETIME";continue;}
                else if("DECIMAL".equalsIgnoreCase(type)||"DEC".equalsIgnoreCase(type)){obj.quey=obj.datatype+"("+obj.precision+","+obj.scale+")";continue;}
                else if("DOUBLE PRECISION".equalsIgnoreCase(type)){obj.quey="FLOAT";}
                else if("INTEGER".equalsIgnoreCase(type)||"INT".equalsIgnoreCase(type)){obj.quey="DECIMAL(38)";continue;}
                else if("INTERVAL DAY TO SECOND".equalsIgnoreCase(type)){obj.quey="VARCHAR(30)";continue;}
                else if("INTERVAL YEAR TO SECOND".equalsIgnoreCase(type)){obj.quey="VARCHAR(30)";continue;}
                else if("LONG".equalsIgnoreCase(type)){obj.quey="VARCHAR(max)";continue;}
                else if("LONG ROW".equalsIgnoreCase(type)){obj.quey="VARCHAR(max)";continue;}
                else if("NCHAR".equalsIgnoreCase(type)){obj.quey="NCHAR"+"("+obj.columnsize+")";continue;}
                else if("NCHAR VARYING".equalsIgnoreCase(type)){obj.quey="NVARCHAR("+obj.columnsize+")";continue;}
                else if("NCLOB".equalsIgnoreCase(type)){obj.quey="nVARCHAR(max)";continue;}
                else if("NUMBER".equalsIgnoreCase(type)){
                    if(obj.scale==0&&obj.precision!=0){
                        if(obj.precision<3)obj.quey="TINYINT";
                        else if(obj.precision<5)obj.quey="SMALLINT";
                        else if(obj.precision<9){obj.quey="INT";}
                        else if(obj.precision<19)obj.quey="BIGINT";
                        else obj.quey="DECIMAL("+obj.precision+")";
                    }
                    else if(obj.scale!=0&&obj.precision!=0){obj.quey="DECIMAL("+obj.precision+","+obj.scale+")";continue;
                    }else{obj.quey="FLOAT";continue;}
                 }
                else if("NUMERIC".equalsIgnoreCase(type)){obj.quey="NUMERIC("+obj.precision+","+obj.scale+")";continue;}
                else if("NVARCHAR2".equalsIgnoreCase(type)){obj.quey="NVARCHAR("+obj.columnsize+")";continue;}
                else if("RAW".equalsIgnoreCase(type)){obj.quey="VARBINARY";continue;}
                else if("REAL".equalsIgnoreCase(type)){obj.quey="FLOAT";continue;}
                else if("ROWID".equalsIgnoreCase(type)){obj.quey="CHAR(18)";continue;}
                else if("SMALLINT ".equalsIgnoreCase(type)){obj.quey="DECIMAL(38)";continue;}
                else if("TIMESTAMP".equalsIgnoreCase(type)){;obj.quey="DATETIME2";continue;}////////
                else if("TIMESTAMP WITH TIME ZONE".equalsIgnoreCase(type)){;obj.quey="DATETIMEOFFDET";continue;}///////
                else if("UROWID".equalsIgnoreCase(type)){obj.quey="VARCHAR("+obj.columnsize+")";continue;}
                else if("VARCHAR2".equalsIgnoreCase(type)||"VARCHAR".equalsIgnoreCase(type)){obj.quey="VARCHAR("+obj.columnsize+")";continue;}
                else if("XMLTYPE".equalsIgnoreCase(type)){obj.quey="XML";continue;}
                
            }
    }
    
    public void mysqltoMssql(ArrayList<ColumnDetails> coldetail){
            int length=coldetail.size();
            for(int i=0;i<length;i++){
               ColumnDetails obj=coldetail.get(i);
                String type =obj.datatype;
                long size=obj.columnsize;
                
                
                if(type=="BINARY"){
                    if(obj.columnsize>0)obj.quey="BINARY("+obj.columnsize+")";
                    else obj.quey="BINARY(1)";}
                else if(type=="BIT"){obj.quey="BINARY("+obj.columnsize/8+")";}
                else if(type=="BLOB"){obj.quey="VARBINARY(max)";}
                else if(type=="BOOLEAN"||type=="BOOL"){obj.quey="BIT";}
                else if(type=="CHAR"){obj.quey="CHAR("+obj.columnsize+")";}
                else if(type=="CHARACTER"){obj.quey="CHARACTER("+obj.columnsize+")";}
                else if(type=="CHARACTER VARYING"){obj.quey="CHARACTER VARYING("+obj.columnsize+")";}
//                else if(type=="DATE"){
//                    obj.quey="DATE";//January 10, 2012 3:01 am
//                }
                else if("".equalsIgnoreCase(type)){obj.quey="";}
                else if("DATETIME".equalsIgnoreCase(type)){obj.quey="DATETIME2";}/////
                else if("DECIMAL".equalsIgnoreCase(type)||"DEC".equalsIgnoreCase(type)){obj.quey=obj.datatype+"("+obj.precision+","+obj.scale+")";}
                else if("DOUBLE".equalsIgnoreCase(type)){obj.quey="FLOAT";}
                else if("FIXED".equalsIgnoreCase(type)){obj.quey="DECIMAL("+obj.precision+","+obj.scale+")";}
                else if("FLOAT".equalsIgnoreCase(type)||"FLOAT4".equalsIgnoreCase(type)){obj.quey="FLOAT";}
                else if("FLOAT8".equalsIgnoreCase(type)){obj.quey="BINARY_DOUBLE";}
                
                else if("INT1".equalsIgnoreCase(type)){obj.quey="SMALLINT";}
                else if("INT2".equalsIgnoreCase(type)){obj.quey="SMALLINT";}
                else if("INT3".equalsIgnoreCase(type)){obj.quey="INT";}
                else if("INT4".equalsIgnoreCase(type)){obj.quey="INT";}
                else if("INT8".equalsIgnoreCase(type)){obj.quey="BIGINT";}
                else if("LONGBLOB".equalsIgnoreCase(type)){obj.quey="VARBINARY";}
                else if("LONGTEXT".equalsIgnoreCase(type)){obj.quey="VARCHAR";}
                else if("LONG VARBINARY".equalsIgnoreCase(type)){obj.quey="VARBINARY(max)";}
                else if("LONG".equalsIgnoreCase(type)||"LONG VARCHAR".equalsIgnoreCase(type)){obj.quey="VARCHAR(max)";}
                else if("MEDIUMBLOB".equalsIgnoreCase(type)){obj.quey="VARBINARY(max)";}
                else if("MEDIUMINT".equalsIgnoreCase(type)){obj.quey="INT";}
                else if("MEDIUMTEXT".equalsIgnoreCase(type)){obj.quey="VARCHAR(max)";}
                else if("MIDDLEINT".equalsIgnoreCase(type)){obj.quey="INT";}
                else if("NCHAR".equalsIgnoreCase(type)){obj.quey="NCHAR("+obj.columnsize+")";}
                else if("NVARCHAR".equalsIgnoreCase(type)){obj.quey="NVARCHAR("+obj.columnsize+")";}
                else if("NUMERIC".equalsIgnoreCase(type)){obj.quey="NUMERIC("+obj.precision+","+obj.scale+") ";}
                else if("REAL".equalsIgnoreCase(type)){obj.quey="DOUBLE PRECISION";}
                else if("SERIAL".equalsIgnoreCase(type)){obj.quey="NUMERIC(20)";}
                else if("TEXT".equalsIgnoreCase(type)){obj.quey="VARCHAR(max)";}
                else if("TIME".equalsIgnoreCase(type)){obj.quey="TIME";}//////
                else if("TIMESTAMP".equalsIgnoreCase(type)){obj.quey="DATETIME2";}//////
                else if("TINYBLOB".equalsIgnoreCase(type)){obj.quey="VARBINARY(255)";}
                else if("TINYINT".equalsIgnoreCase(type)){obj.quey="SMALLINT";}
                else if("TINYTEXT".equalsIgnoreCase(type)){obj.quey="VARCHAR(255)";}
                else if("VARBINARY".equalsIgnoreCase(type)){obj.quey="VARBINARY("+obj.columnsize+")";}
                else if("VARCHAR".equalsIgnoreCase(type)){obj.quey="VARCHAR("+obj.columnsize+")";}
                else if("YEAR".equalsIgnoreCase(type)){obj.quey="NUMERIC(4)";}
            }
            
    }
    
    public void postgretoMssql(ArrayList<ColumnDetails> coldetail){
        int length=coldetail.size();
        for(int i=0;i<length;i++){
            ColumnDetails obj=coldetail.get(i);
            String type =obj.datatype;
            long size=obj.columnsize;
                
            if(type=="SMALLDATETIME"){obj.quey="TIMESTAMP(0)";}
            else if("DATETIME".equalsIgnoreCase(type)){obj.quey="TIMESTAMP(3)";}//////sequence and trigger
            else if("DATETIME2".equalsIgnoreCase(type)){obj.quey="TIMESTAMP";}//////sequence and trigger
            else if("DATETIMEOFFSET".equalsIgnoreCase(type)){obj.quey="TIMESTAMP WITH TIME ZONE";}//////sequence and trigger
            else if("BINARY".equalsIgnoreCase(type)){obj.quey="BYTEA";}
            else if("VARBINARY".equalsIgnoreCase(type)){obj.quey="BYTEA";}
            else if("NTEXT".equalsIgnoreCase(type)){obj.quey="TEXT";}
            else if("IMAGE".equalsIgnoreCase(type)){obj.quey="BYTEA";}
            else if("VARCHAR".equalsIgnoreCase(type)){obj.quey="TEXT";}
            else if("NVARCHAR".equalsIgnoreCase(type)){obj.quey="TEXT";}
            else if("VARBINARY".equalsIgnoreCase(type)){obj.quey="BYTEA";}
            else if("UNIQUEIDENTIFIER".equalsIgnoreCase(type)){obj.quey="CHAR(16)";}
            else if("HIERARCHYID".equalsIgnoreCase(type)){obj.quey="NVARCHAR(4000)";}
            else if("GEOMETRY".equalsIgnoreCase(type)){obj.quey="GEOMETRY";}
            else if("GEOGRAPHY".equalsIgnoreCase(type)){obj.quey="GEOGRAPHY";}
            else if("ROWVERSION".equalsIgnoreCase(type)){obj.quey="TIMESTAMP";}
            else if("TINYINT".equalsIgnoreCase(type)){obj.quey="SMALLINT";}
            else if("SMALLMONEY".equalsIgnoreCase(type)){obj.quey="MONEY";}
            
        
        
        
        }
    }
    
    
    
    
    
}
