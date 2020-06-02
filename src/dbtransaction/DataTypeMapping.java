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
                else if("".equals(type)){obj.quey="";}
                else if("DATETIME".equals(type)){obj.quey="TIMESTAMP";}
                else if("DECIMAL".equals(type)||"DEC".equals(type)||"DECIMAL UNSIGNED".equals(type)||"DEC UNSIGNED".equals(type)){obj.quey="NUMBER("+obj.precision+","+obj.scale+")";}

                else if("DOUBLE".equals(type)||"DOUBLE UNSIGNED".equals(type)){obj.quey="BINARY_DOUBLE";}
                else if("FIXED".equals(type)||"FIXED UNSIGNED".equals(type)){obj.quey="NUMBER("+obj.precision+","+obj.scale+")";}
                else if("FLOAT".equals(type)||"FLOAT4".equals(type)||"FLOAT8".equals(type)||"FLOAT UNSIGNED".equals(type)||"FLOAT4 UNSIGNED".equals(type)||"FLOAT8 UNSIGNED".equals(type)){obj.quey="BINARY_DOUBLE";}
                else if("INT".equals(type)||"INTEGER".equals(type)||"INT UNSIGNED".equals(type)||"INTEGER UNSIGNED".equals(type)){obj.quey="NUMBER(10)";}
                else if("INT1".equals(type)||"INT1 UNSIGNED".equals(type)){obj.quey="NUMBER(3)";}
                else if("INT2".equals(type)||"INT2 UNSIGNED".equals(type)){obj.quey="NUMBER(5)";}
                else if("INT3".equals(type)||"INT3 UNSIGNED".equals(type)){obj.quey="NUMBER(7)";}
                else if("INT4".equals(type)||"INT4 UNSIGNED".equals(type)){obj.quey="NUMBER(10)";}
                else if("INT8".equals(type)||"INT8 UNSIGNED".equals(type)){obj.quey="NUMBER(19)";}
                else if("LONGBLOB".equals(type)){obj.quey="BLOB";}
                else if("LONGTEXT".equals(type)){obj.quey="CLOB";}
                else if("LONG VARBINARY".equals(type)){obj.quey="BLOB";}
                else if("LONG".equals(type)||"LONG VARCHAR".equals(type)){obj.quey="CLOB";}
                else if("MEDIUMBLOB".equals(type)){obj.quey="BLOB";}
                else if("MEDIUMINT".equals(type)||"MEDIUMINT UNSIGNED".equals(type)){obj.quey="NUMBER(7)";}
                else if("MEDIUMTEXT".equals(type)){obj.quey="CLOB";}
                else if("MIDDLEINT".equals(type)||"MIDDLEINT UNSIGNED".equals(type)){obj.quey="NUMBER(7)";}
                else if("NCHAR".equals(type)){obj.quey="NCHAR("+obj.columnsize+")";}
                else if("NVARCHAR".equals(type)){obj.quey="NVARCHAR2("+obj.columnsize+")";}
                else if("NUMERIC".equals(type)||"NUMERIC UNSIGNED".equals(type)){obj.quey="NUMBER("+obj.precision+","+obj.scale+") ";}
                else if("REAL".equals(type)){obj.quey="BINARY_DOUBLE";}
                else if("SMALLINT".equals(type)||"SMALLINT UNSIGNED".equals(type)){obj.quey="NUMBER(5)";}
                else if("TEXT".equals(type)){obj.quey="CLOB";}
                else if("TIME".equals(type)){obj.quey="TIMESTAMP";}
                else if("TIMESTAMP".equals(type)){obj.quey="TIMESTAMP";}
                else if("TINYBLOB".equals(type)){obj.quey="RAW(255)";}
                else if("TINYINT".equals(type)||"TINYINT UNSIGNED".equals(type)){obj.quey="NUMBER(3)";}
                else if("TINYTEXT".equals(type)){obj.quey="VARCHAR2(255)";}
                else if("VARBINARY".equals(type)){obj.quey="RAW("+obj.columnsize+")";}
                else if("VARCHAR".equals(type)){obj.quey="VARCHAR2("+obj.columnsize+")";}
                else if("YEAR".equals(type)){obj.quey="NUMBER(4)";}
            }
            
    }
    public void oracletoPostgre(ArrayList<ColumnDetails> coldetail){
            int length=coldetail.size();
            for(int i=0;i<length;i++){
               ColumnDetails obj=coldetail.get(i);
                String type =obj.datatype;
                long size=obj.columnsize;
                
                if("BFILE".equals(type)){obj.quey="VARCHAR(255)";continue;}
                else if("BINARY_FLOAT".equals(type)){obj.quey="REAL";continue;}
                else if("BINARY_DOUBLE".equals(type)){obj.quey="DOUBLE PRECISION";continue;}
                else if("BLOB".equals(type)){obj.quey="BYTEA";continue;}
                else if("CHAR".equals(type)||"CHARACTER".equals(type)){
                    if(size>255){obj.quey="VARCHAR("+obj.columnsize+")";
                    }else{obj.quey=obj.datatype+"("+obj.columnsize+")";}
                }
                else if("CLOB".equals(type)){obj.quey="TEXT";continue;}
                else if("DATE".equals(type)){obj.quey="TIMESTAMP";continue;}
                else if("DECIMAL".equals(type)||"DEC".equals(type)){obj.quey=obj.datatype+"("+obj.precision+","+obj.scale+")";continue;}
                else if("FLOAT".equals(type)){obj.quey="DOUBLE PRECISION";continue;}
                else if("INTEGER".equals(type)||"INT".equals(type)){obj.quey="DECIMAL(38)";continue;}
                else if("INTERVAL DAY TO SECOND".equals(type)){obj.quey=obj.datatype+"("+obj.scale+")";continue;}
                else if("LONG".equals(type)){obj.quey="TEXT";continue;}
                else if("LONG ROW".equals(type)){obj.quey="BYTEA";continue;}
                else if("NCHAR".equals(type)){obj.quey="CHAR"+"("+obj.columnsize+")";continue;}
                else if("NCHAR VARYING".equals(type)){obj.quey="VARCHAR("+obj.columnsize+")";continue;}
                else if("NCLOB".equals(type)){obj.quey="TEXT";continue;}
                else if("NUMBER".equals(type)){
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
                else if("NUMERIC".equals(type)){obj.quey=obj.datatype+"("+obj.precision+","+obj.scale+")";continue;}
                else if("NVARCHAR2".equals(type)){obj.quey="VARCHAR("+obj.columnsize+")";continue;}
                else if("RAW".equals(type)){obj.quey="BYTEA";continue;}
                else if("REAL".equals(type)){obj.quey="DOUBLE PRECISION";continue;}
                else if("ROWID".equals(type)){obj.quey="CHAR(10)";continue;}
                else if("SMALLINT ".equals(type)){obj.quey="DECIMAL(38)";continue;}
                else if("TIMESTAMP".equals(type)){;obj.quey="TIMESTAMP";continue;}
                else if("TIMESTAMP WITH TIME ZONE".equals(type)){;obj.quey="TIMESTAMP WITH TIME ZONE";continue;}
                else if("UROWID".equals(type)){obj.quey="VARCHAR("+obj.columnsize+")";continue;}
                else if("VARCHAR2".equals(type)||"VARCHAR".equals(type)){obj.datatype="VARCHAR";obj.quey="VARCHAR("+obj.columnsize+")";continue;}
                else if("XMLTYPE".equals(type)){obj.quey="XML";continue;}
                
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
            else if("BIGSERIAL".equals(type)){obj.quey="";}//////sequence and trigger
            else if("BIT".equals(type)||"BIT VARYING".equals(type)){obj.quey="RAW("+obj.columnsize/8+")";continue;}
            else if("BOOLEAN".equals(type)||"BOOL".equals(type)){obj.quey="CHAR(1)";continue;}
            else if("BYTEA".equals(type)){obj.quey="BLOB";continue;}
            else if("CHARACTER".equals(type)||"CHAR".equals(type)){obj.quey=obj.datatype+"("+obj.columnsize+")";continue;}
            else if("DECIMAL".equals(type)||"DEC".equals(type)){obj.quey="NUMBER("+obj.precision+","+obj.scale+")";continue;}
            else if("DOUBLE PRECISION".equals(type)||"FLOAT8".equals(type)){obj.quey="BINARY_DOUBLE";continue;}
            else if("FLOAT4".equals(type)){obj.quey="BINARY_FLOAT";continue;}
            else if("INTEGER".equals(type)||"INT".equals(type)){obj.quey="NUMBER(10)";continue;}
            else if("INT2".equals(type)){obj.quey="NUMBER(5)";continue;}
            else if("INT4".equals(type)){obj.quey="NUMBER(10)";continue;}
            else if("INT8".equals(type)){obj.quey="NUMBER(20)";continue;}
            else if("INTERVAL".equals(type)){obj.quey="NUMBER(5)";continue;}
            else if("INTERVAL DAY TO HOUR".equals(type)){obj.quey="INTERVAL DAY(5) TO SECOND";continue;}
            else if("INTERVAL DAY TO MINUTE".equals(type)){obj.quey="INTERVAL DAY(5) TO SECOND";continue;}
            else if("INTERVAL HOUR TO MINUTE".equals(type)){obj.quey="INTERVAL DAY(5) TO SECOND";continue;}
            else if("INTERVAL HOUR TO SECOND".equals(type)){obj.quey="INTERVAL DAY(5) TO SECOND[("+obj.precision+")]";continue;}
            else if("INTERVAL MINUTE TO SECOND".equals(type)){obj.quey="INTERVAL DAY(5) TO SECOND[("+obj.precision+")]";continue;}
            else if("INTERVAL DAY TO SECOND".equals(type)){obj.quey="INTERVAL DAY(5) TO SECOND[("+obj.precision+")]";continue;}
            else if("MONEY".equals(type)){obj.quey="NUMBER(17,2)";continue;}
            else if("NUMERIC".equals(type)){obj.quey="NUMERIC("+obj.precision+","+obj.scale+")";continue;}
            else if("REAL".equals(type)){obj.quey="BINARY_FLOAT";continue;}
            //serial is rest....................................
            else if("SMALLINT".equals(type)){obj.quey="NUMBER(5)";continue;}
            else if("TEXT".equals(type)){obj.quey="CLOB";continue;}
            else if("TIME".equals(type)){obj.quey="TIMESTAMP("+obj.precision+")";continue;}
            else if("TIME WITH TIME ZONE".equals(type)||"TIMESTAMPZ".equals(type)){obj.quey="TIMESTAMP("+obj.precision+") WITH TIME ZONE";continue;}
            else if("UUID".equals(type)){obj.quey="CHAR(36)";continue;}
            else if("VARBIT".equals(type)){obj.quey="RAW("+obj.columnsize/8+")";continue;}
            else if("VARCHAR".equals(type)){obj.quey="VARCHAR2("+obj.columnsize+")";continue;}
            else if("XML".equals(type)){obj.quey="XMLTYPE";continue;}
        }
    }
    
    public void mysqltoPostgres(ArrayList<ColumnDetails> coldetail){
        int length=coldetail.size();
        for(int i=0;i<length;i++){
            ColumnDetails obj=coldetail.get(i);
            String type =obj.datatype;
            long size=obj.columnsize;
            if("BINARY".equals(type)){obj.quey="BYTEA";}
            else if("BIT".equals(type)){obj.quey="BOOLEAN";}
            else if("CHAR".equals(type)||"CHARACTER".equals(type)){obj.quey=obj.datatype+"("+obj.columnsize+")";}
            else if("DATETIME".equals(type)){obj.quey="TIMESTAMP";}
            else if("DECIMAL".equals(type)||"DEC".equals(type)){obj.quey=obj.columnName+"("+obj.precision+","+obj.scale+")";}
            else if("DOUBLE".equals(type)){obj.quey="DOUBLE PRECISION";}
            else if("FLOAT".equals(type)){obj.quey="REAL";}
            else if("MEDIUMINT".equals(type)){obj.quey="INTEGER";}
            else if("NUMERIC".equals(type)){obj.quey="NUMERIC("+obj.precision+","+obj.scale+")";}
            else if("TINYBLOB".equals(type)||"BLOB".equals(type)||"MEDIUMBLOB".equals(type)||"LONGBLOB".equals(type)){obj.quey="BYTEA";}
            else if("TINYINT".equals(type)&&!obj.autoIncrement&&obj.sign){obj.quey="SMALLINT";}
            else if("TINYTEXT".equals(type)||"TEXT".equals(type)||"MEDIUMTEXT".equals(type)||"LONGTEXT".equals(type)){obj.quey="TEXT";}
            else if("VARBINARY".equals(type)){obj.quey="BYTEA";}
            //else if("VARCHAR".equals(type)){obj.quey="VARCHAR("+obj.columnsize+")";}
            else if("VARCHAR".equals(type)){obj.quey="TEXT";}
            else if("BIGINT".equals(type)&&obj.autoIncrement){obj.quey="BIGSERIAL";}
            else if("INTEGER".equals(obj)&&obj.autoIncrement){obj.quey="SERIAL";}
            else if("SMALLINT".equals(obj)&&obj.autoIncrement){obj.quey="SMALLSERIAL";}
            else if("TINYINT".equals(obj)&&obj.autoIncrement){obj.quey="SMALLSERIAL";}
            else if("BIGINT".equals(obj)&&!obj.sign){obj.quey="NUMERIC(20)";}
            else if("INT".equals(obj)&&!obj.sign){obj.quey="BIGINT";}
            else if("SMALLINT".equals(obj)&&!obj.sign){obj.quey="INTEGER";}
            else if("TINYINT".equals(obj)&&!obj.sign){obj.quey="INTEGER";}
            
        }
    }

    public void postgretoMysql(ArrayList<ColumnDetails> coldetail){
        int length=coldetail.size();
        for(int i=0;i<length;i++){
            ColumnDetails obj=coldetail.get(i);
            String type =obj.datatype;
            long size=obj.columnsize;
                
            if(type=="BIGINT"){
                obj.quey="NUMBER(19)";
            }
            else if("BIGSERIAL".equals(type)){obj.quey="";}//////sequence and trigger
            else if("BIT".equals(type)||"BIT VARYING".equals(type)){obj.quey="RAW("+obj.columnsize/8+")";continue;}
            else if("BOOLEAN".equals(type)||"BOOL".equals(type)){obj.quey="TINYINT(1)";continue;}
            else if("BYTEA".equals(type)){obj.quey="LONGBLOB";continue;}
            
            else if("CHARACTER".equals(type)||"CHAR".equals(type)){obj.quey="LONGTEXT";continue;}
            else if("CIDR".equals(type)){obj.quey="VARCHAR(43)";}
            
            else if("DECIMAL".equals(type)||"DEC".equals(type)){obj.quey="NUMBER("+obj.precision+","+obj.scale+")";continue;}
            else if("DOUBLE PRECISION".equals(type)){obj.quey="DOUBLE";continue;}
            else if("FLOAT4".equals(type)){obj.quey="BINARY_FLOAT";continue;}
            else if("INET".equals(type)){obj.quey="VARCHAR(43)";}
            else if("INTEGER".equals(type)||"INT".equals(type)){obj.quey="NUMBER(10)";continue;}
            else if("INT2".equals(type)){obj.quey="NUMBER(5)";continue;}
            else if("INT4".equals(type)){obj.quey="NUMBER(10)";continue;}
            else if("INT8".equals(type)){obj.quey="NUMBER(20)";continue;}
            else if("INTERVAL".equals(type)){obj.quey="TIME";continue;}
            else if("INTERVAL DAY TO HOUR".equals(type)){obj.quey="INTERVAL DAY(5) TO SECOND";continue;}
            else if("INTERVAL DAY TO MINUTE".equals(type)){obj.quey="INTERVAL DAY(5) TO SECOND";continue;}
            else if("INTERVAL HOUR TO MINUTE".equals(type)){obj.quey="INTERVAL DAY(5) TO SECOND";continue;}
            else if("INTERVAL HOUR TO SECOND".equals(type)){obj.quey="INTERVAL DAY(5) TO SECOND[("+obj.precision+")]";continue;}
            else if("INTERVAL MINUTE TO SECOND".equals(type)){obj.quey="INTERVAL DAY(5) TO SECOND[("+obj.precision+")]";continue;}
            else if("INTERVAL DAY TO SECOND".equals(type)){obj.quey="INTERVAL DAY(5) TO SECOND[("+obj.precision+")]";continue;}
            else if("MACADDR".equals(type)){obj.quey="VARCHAR(17)";}
            else if("MONEY".equals(type)){obj.quey="DECIMAL(19,2)";continue;}
            else if("NATIONAL CHARACTER".equals(type)){obj.quey="LONGTEXT";continue;}
            else if("NATINAL CHARACTER VARYING".equals(type)){obj.quey="LONGTEXT";continue;}
            else if("NUMERIC".equals(type)){obj.quey="DECIMAL";continue;}
            else if("REAL".equals(type)){obj.quey="FLOAT";continue;}
            //serial is rest....................................
            else if("SERIAL".equals(type)){obj.quey="INT";}//set auto increment enable}
            else if("SMALLSERIAL".equals(type)){obj.quey="SMALLINT";}//set auto increment enable}
            else if("BIGSERIAL".equals(type)){obj.quey="BIGINT";}//set auto increment enable}
            else if("SMALLINT".equals(type)){obj.quey="NUMBER(5)";continue;}
            else if("TEXT".equals(type)){obj.quey="LONGTEXT";continue;}
            else if("TIMESTAMP".equals(type)){obj.quey="DATETIME";continue;}
            else if("TIME WITH TIME ZONE".equals(type)||"TIMESTAMPZ".equals(type)){obj.quey="TIMESTAMP("+obj.precision+") WITH TIME ZONE";continue;}
            else if("UUID".equals(type)){obj.quey="VARCHAR(36)";continue;}
            else if("VARBIT".equals(type)){obj.quey="RAW("+obj.columnsize/8+")";continue;}
            else if("VARCHAR".equals(type)){obj.quey="LONGTEXT";continue;}
            else if("XML".equals(type)||"JSON".equals(type)||"TSVECTOR".equals(type)||"TSQUERY".equals(type)||"ARRAY".equals(type)){obj.quey="LONGTEXT";continue;}
            else if("LINE".equals(type)){obj.quey="LINESTRING";}
            else if("LSEG".equals(type)){obj.quey="LINESTRING";}
            else if("BOX".equals(type)){obj.quey="POLYGON";}
            else if("PATH".equals(type)){obj.quey="LINESTRING";}
            else if("CIRCLE".equals(type)){obj.quey="POLYGON";}
            else if("TXID_SNAPSHOT".equals(type)){obj.quey="VARCHAR";}
        }
    }
    public void mssqltoOracle(ArrayList<ColumnDetails> coldetail){
        int length=coldetail.size();
        for(int i=0;i<length;i++){
            ColumnDetails obj=coldetail.get(i);
            String type =obj.datatype;
            long size=obj.columnsize;
                
            if("CHAR".equals(type)||"NCHAR".equals(type)){
                obj.quey=obj.datatype+"("+obj.columnsize+")";
            }
            else if("NVARCHAR".equals(type)&&obj.columnsize<=4000){obj.quey="NVARCHAR2("+obj.columnsize+")";}
            else if("NVARCHAR".equals(type)&&obj.columnsize>4000){obj.quey="NCLOB";}
            else if("VARCHAR".equals(type)&&obj.columnsize<=8000){obj.quey="VARCHAR2("+obj.columnsize+")";}
            else if("VARCHAR".equals(type)&&obj.columnsize>8000){obj.quey="CLOB";}  
            else if("BIGINT".equals(type)){obj.quey="NUMBER(19)";}
            else if("DECIMAL".equals(type)||"DEC".equals(type)){obj.quey="NUMBER("+obj.precision+","+obj.scale+")";}
            else if("FLOAT".equals(type)){obj.quey="NUMBER";}
            else if("INTEGER".equals(type)||"INT".equals(type)){obj.quey="NUMBER(10)";}
            else if("NUMERIC".equals(type)){obj.quey="NUMBER("+obj.precision+","+obj.scale+")";}
            else if("REAL".equals(type)){obj.quey="NUMBER";}
            else if("SMALLINT".equals(type)){obj.quey="NUMBER(5)";}
            else if("TINYINT".equals(type)){obj.quey="NUMBER(3)";}
            else if("DATETIME".equals(type)){obj.quey="TIMESTAMP(3)";}
            else if("TIME".equals(type)){obj.quey="TIMESTAMP";}////improvment needed
            else if("BIT".equals(type)){obj.quey="NUMBER(1)";}
            else if("MONEY".equals(type)){obj.quey="NUMBER(19,4)";}
            else if("SMALLMONEY".equals(type)){obj.quey="NUMBER(10,4)";}
            else if("UNIQUEIDENTIFIER".equals(type)){obj.quey="CHAR(36)";}
            else if("XML".equals(type)){obj.quey="XMLTYPE";}
        }
    }
    
    public void mssqltoMysql(ArrayList<ColumnDetails> coldetail){
        int length=coldetail.size();
        for(int i=0;i<length;i++){
            ColumnDetails obj=coldetail.get(i);
            String type =obj.datatype;
            long size=obj.columnsize;
                
            if("BINARY".equals(type)){
                obj.quey=obj.datatype+"("+obj.columnsize+")";
            }
            else if("BIT".equals(type)){obj.quey="TINYINT";}
            else if("CHAR".equals(type)&&obj.columnsize<256){obj.quey="CHAR("+obj.columnsize+")";}
            else if("CHAR".equals(type)&&obj.columnsize>255){obj.quey="TEXT";}
            else if("DATETIME".equals(type)){obj.quey="DATETIME(3)";}
            else if("DATETIME2".equals(type)){obj.quey="DATETIME";}////imporovment needed
            else if("DATETIMEOFFSET".equals(type)){obj.quey="DATETIME";}
            else if("DECIMAL".equals(type)){obj.quey="DECIMAL("+obj.precision+","+obj.scale+")";}
            else if("FLOAT".equals(type)){obj.quey="DOUBLE";}
            else if("IMAGE".equals(type)){obj.quey="LONGBLOB";}
            else if("MONEY".equals(type)){obj.quey="DECIMAL(15,4)";}
            else if("NCHAR".equals(type)&&obj.columnsize<256){obj.quey="NCHAR("+obj.columnsize+")";}
            else if("NCHAR".equals(type)&&obj.columnsize>255){obj.quey="TEXT";}
            else if("NTEXT".equals(type)){obj.quey="LONGTEXT";}
            else if("NUMERIC".equals(type)){obj.quey="NUMERIC("+obj.precision+","+obj.scale+")";}
            else if("NVARCHAR".equals(type)&&obj.columnsize<4001){obj.quey="NVARCHAR("+obj.columnsize+")";}
            else if("NVARCHAR".equals(type)){obj.quey="LONGTEXT";}
            else if("ROWVERSION".equals(type)){obj.quey="BINARY(8)";}
            else if("SMALLDATETIME".equals(type)){obj.quey="DATETIME";}
            else if("SMALLMONEY".equals(type)){obj.quey="DECIMAL(6,4)";}
            else if("TEXT".equals(type)){obj.quey="LONGTEXT";}
            else if("TIME".equals(type)){obj.quey="TIME";}/////
            else if("TIMESTAMP".equals(type)){obj.quey="BINARY(8)";}
            else if("UNIQUEIDENTIFIER".equals(type)){obj.quey="CHAR(16)";}
            else if("VARBINARY".equals(type)&&obj.columnsize<8001){obj.quey="VARBINARY("+obj.columnsize+")";}
            else if("VARBINARY".equals(type)){obj.quey="LONGBLOB";}
            else if("VARCHAR".equals(type)&&obj.columnsize<8001){obj.quey="VARCHAR("+obj.columnsize+")";}
            else if("VARCHAR".equals(type)){obj.quey="LONGTEXT";}
            else if("XML".equals(type)){obj.quey="LONGTEXT";}
          
            
        }
    }
    
    public void mssqltoPostgre(ArrayList<ColumnDetails> coldetail){
        int length=coldetail.size();
        for(int i=0;i<length;i++){
            ColumnDetails obj=coldetail.get(i);
            String type =obj.datatype;
            long size=obj.columnsize;
                
            if("BINARY".equals(type)){
                obj.quey="BYTEA";
            }
            else if("BIT".equals(type)){obj.quey="BOOLEAN";}
            else if("CHAR".equals(type)||"CHARACTER".equals(obj)){obj.quey=obj.datatype+"("+obj.columnsize+")";}
            else if("DATETIME".equals(type)){obj.quey="TIMESTAMP(3)";}
            else if("DATETIME2".equals(type)){obj.quey="TIMESTAMP";}////imporovment needed
            else if("DATETIMEOFFSET".equals(type)){obj.quey="TIMESTAMP WITH TIME ZONE";}//////
            else if("DECIMAL".equals(type)||"DEC".equals(type)){obj.quey=obj.datatype+"("+obj.precision+","+obj.scale+")";}
            else if("FLOAT".equals(type)){obj.quey="DOUBLE PRECISION";}
            else if("IMAGE".equals(type)){obj.quey="BYTEA";}
            else if("NCHAR".equals(type)){obj.quey="CHAR("+obj.columnsize+")";}
            else if("NTEXT".equals(type)){obj.quey="TEXT";}
            else if("NUMERIC".equals(type)){obj.quey="NUMERIC("+obj.precision+","+obj.scale+")";}
            else if("NVARCHAR".equals(type)&&obj.columnsize<4001){obj.quey="VARCHAR("+obj.columnsize+")";}
            else if("NVARCHAR".equals(type)){obj.quey="TEXT";}
            else if("ROWVERSION".equals(type)){obj.quey="BYTEA";}
            else if("SMALLDATETIME".equals(type)){obj.quey="TIMESTAMP(0)";}
            else if("SMALLMONEY".equals(type)){obj.quey="MONEY";}
            else if("TEXT".equals(type)){obj.quey="TEXT";}
            else if("TIME".equals(type)){obj.quey="TIME";}/////
            else if("TIMESTAMP".equals(type)){obj.quey="BYTEA";}
            else if("TINYINT".equals(type)){obj.quey="SMALLINT";}
            else if("UNIQUEIDENTIFIER".equals(type)){obj.quey="CHAR(16)";}
            else if("VARBINARY".equals(type)){obj.quey="BYTEA";}
            else if("VARCHAR".equals(type)&&obj.columnsize<8001){obj.quey="VARCHAR("+obj.columnsize+")";}
            else if("VARCHAR".equals(type)){obj.quey="TEXT";}
            else if("XML".equals(type)){obj.quey="XML";}
          
            
        }
    }
    
    public void oracletoMssql(ArrayList<ColumnDetails> coldetail){
            int length=coldetail.size();
            for(int i=0;i<length;i++){
               ColumnDetails obj=coldetail.get(i);
                String type =obj.datatype;
                long size=obj.columnsize;
                
                if("BFILE".equals(type)){obj.quey="VARCHAR(255)";continue;}
                else if("BINARY_FLOAT".equals(type)){obj.quey="REAL";continue;}
                else if("BINARY_DOUBLE".equals(type)){obj.quey="DOUBLE PRECISION";continue;}
                else if("BLOB".equals(type)){obj.quey="VARBINARY(max)";continue;}
                else if("CHAR".equals(type)||"CHARACTER".equals(type)){obj.quey=obj.datatype+"("+obj.columnsize+")";}
                else if("CLOB".equals(type)){obj.quey="VARCHAR(max)";continue;}
                else if("DATE".equals(type)){obj.quey="DATETIME";continue;}
                else if("DECIMAL".equals(type)||"DEC".equals(type)){obj.quey=obj.datatype+"("+obj.precision+","+obj.scale+")";continue;}
                else if("DOUBLE PRECISION".equals(type)){obj.quey="FLOAT";}
                else if("INTEGER".equals(type)||"INT".equals(type)){obj.quey="DECIMAL(38)";continue;}
                else if("INTERVAL DAY TO SECOND".equals(type)){obj.quey="VARCHAR(30)";continue;}
                else if("INTERVAL YEAR TO SECOND".equals(type)){obj.quey="VARCHAR(30)";continue;}
                else if("LONG".equals(type)){obj.quey="VARCHAR(max)";continue;}
                else if("LONG ROW".equals(type)){obj.quey="VARCHAR(max)";continue;}
                else if("NCHAR".equals(type)){obj.quey="NCHAR"+"("+obj.columnsize+")";continue;}
                else if("NCHAR VARYING".equals(type)){obj.quey="NVARCHAR("+obj.columnsize+")";continue;}
                else if("NCLOB".equals(type)){obj.quey="nVARCHAR(max)";continue;}
                else if("NUMBER".equals(type)){
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
                else if("NUMERIC".equals(type)){obj.quey="NUMERIC("+obj.precision+","+obj.scale+")";continue;}
                else if("NVARCHAR2".equals(type)){obj.quey="NVARCHAR("+obj.columnsize+")";continue;}
                else if("RAW".equals(type)){obj.quey="VARBINARY";continue;}
                else if("REAL".equals(type)){obj.quey="FLOAT";continue;}
                else if("ROWID".equals(type)){obj.quey="CHAR(18)";continue;}
                else if("SMALLINT ".equals(type)){obj.quey="DECIMAL(38)";continue;}
                else if("TIMESTAMP".equals(type)){;obj.quey="DATETIME2";continue;}////////
                else if("TIMESTAMP WITH TIME ZONE".equals(type)){;obj.quey="DATETIMEOFFDET";continue;}///////
                else if("UROWID".equals(type)){obj.quey="VARCHAR("+obj.columnsize+")";continue;}
                else if("VARCHAR2".equals(type)||"VARCHAR".equals(type)){obj.quey="VARCHAR("+obj.columnsize+")";continue;}
                else if("XMLTYPE".equals(type)){obj.quey="XML";continue;}
                
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
                else if("".equals(type)){obj.quey="";}
                else if("DATETIME".equals(type)){obj.quey="DATETIME2";}/////
                else if("DECIMAL".equals(type)||"DEC".equals(type)){obj.quey=obj.datatype+"("+obj.precision+","+obj.scale+")";}
                else if("DOUBLE".equals(type)){obj.quey="FLOAT";}
                else if("FIXED".equals(type)){obj.quey="DECIMAL("+obj.precision+","+obj.scale+")";}
                else if("FLOAT".equals(type)||"FLOAT4".equals(type)){obj.quey="FLOAT";}
                else if("FLOAT8".equals(type)){obj.quey="BINARY_DOUBLE";}
                
                else if("INT1".equals(type)){obj.quey="SMALLINT";}
                else if("INT2".equals(type)){obj.quey="SMALLINT";}
                else if("INT3".equals(type)){obj.quey="INT";}
                else if("INT4".equals(type)){obj.quey="INT";}
                else if("INT8".equals(type)){obj.quey="BIGINT";}
                else if("LONGBLOB".equals(type)){obj.quey="VARBINARY(max)";}
                else if("LONGTEXT".equals(type)){obj.quey="VARCHAR(max)";}
                else if("LONG VARBINARY".equals(type)){obj.quey="VARBINARY(max)";}
                else if("LONG".equals(type)||"LONG VARCHAR".equals(type)){obj.quey="VARCHAR(max)";}
                else if("MEDIUMBLOB".equals(type)){obj.quey="VARBINARY(max)";}
                else if("MEDIUMINT".equals(type)){obj.quey="INT";}
                else if("MEDIUMTEXT".equals(type)){obj.quey="VARCHAR(max)";}
                else if("MIDDLEINT".equals(type)){obj.quey="INT";}
                else if("NCHAR".equals(type)){obj.quey="NCHAR("+obj.columnsize+")";}
                else if("NVARCHAR".equals(type)){obj.quey="NVARCHAR("+obj.columnsize+")";}
                else if("NUMERIC".equals(type)){obj.quey="NUMERIC("+obj.precision+","+obj.scale+") ";}
                else if("REAL".equals(type)){obj.quey="DOUBLE PRECISION";}
                else if("SERIAL".equals(type)){obj.quey="NUMERIC(20)";}
                else if("TEXT".equals(type)){obj.quey="VARCHAR(max)";}
                else if("TIME".equals(type)){obj.quey="TIME";}//////
                else if("TIMESTAMP".equals(type)){obj.quey="DATETIME2";}//////
                else if("TINYBLOB".equals(type)){obj.quey="VARBINARY(255)";}
                else if("TINYINT".equals(type)){obj.quey="SMALLINT";}
                else if("TINYTEXT".equals(type)){obj.quey="VARCHAR(255)";}
                else if("VARBINARY".equals(type)){obj.quey="VARBINARY("+obj.columnsize+")";}
                else if("VARCHAR".equals(type)){obj.quey="VARCHAR("+obj.columnsize+")";}
                else if("YEAR".equals(type)){obj.quey="NUMERIC(4)";}
            }
            
    }
    
    public void postgretoMssql(ArrayList<ColumnDetails> coldetail){
        int length=coldetail.size();
        for(int i=0;i<length;i++){
            ColumnDetails obj=coldetail.get(i);
            String type =obj.datatype;
            long size=obj.columnsize;
                
            if(type=="BIGINT"){
                obj.quey="NUMBER(19)";
            }
            else if("BIGSERIAL".equals(type)){obj.quey="";}//////sequence and trigger
            else if("BIT".equals(type)||"BIT VARYING".equals(type)){obj.quey="RAW("+obj.columnsize/8+")";continue;}
            else if("BOOLEAN".equals(type)||"BOOL".equals(type)){obj.quey="TINYINT(1)";continue;}
            else if("BYTEA".equals(type)){obj.quey="LONGBLOB";continue;}
            
            else if("CHARACTER".equals(type)||"CHAR".equals(type)){obj.quey="LONGTEXT";continue;}
            else if("CIDR".equals(type)){obj.quey="VARCHAR(43)";}
            
            else if("DECIMAL".equals(type)||"DEC".equals(type)){obj.quey="NUMBER("+obj.precision+","+obj.scale+")";continue;}
            else if("DOUBLE PRECISION".equals(type)){obj.quey="DOUBLE";continue;}
            else if("FLOAT4".equals(type)){obj.quey="BINARY_FLOAT";continue;}
            else if("INET".equals(type)){obj.quey="VARCHAR(43)";}
            else if("INTEGER".equals(type)||"INT".equals(type)){obj.quey="NUMBER(10)";continue;}
            else if("INT2".equals(type)){obj.quey="NUMBER(5)";continue;}
            else if("INT4".equals(type)){obj.quey="NUMBER(10)";continue;}
            else if("INT8".equals(type)){obj.quey="NUMBER(20)";continue;}
            else if("INTERVAL".equals(type)){obj.quey="TIME";continue;}
            else if("INTERVAL DAY TO HOUR".equals(type)){obj.quey="INTERVAL DAY(5) TO SECOND";continue;}
            else if("INTERVAL DAY TO MINUTE".equals(type)){obj.quey="INTERVAL DAY(5) TO SECOND";continue;}
            else if("INTERVAL HOUR TO MINUTE".equals(type)){obj.quey="INTERVAL DAY(5) TO SECOND";continue;}
            else if("INTERVAL HOUR TO SECOND".equals(type)){obj.quey="INTERVAL DAY(5) TO SECOND[("+obj.precision+")]";continue;}
            else if("INTERVAL MINUTE TO SECOND".equals(type)){obj.quey="INTERVAL DAY(5) TO SECOND[("+obj.precision+")]";continue;}
            else if("INTERVAL DAY TO SECOND".equals(type)){obj.quey="INTERVAL DAY(5) TO SECOND[("+obj.precision+")]";continue;}
            else if("MACADDR".equals(type)){obj.quey="VARCHAR(17)";}
            else if("MONEY".equals(type)){obj.quey="DECIMAL(19,2)";continue;}
            else if("NATIONAL CHARACTER".equals(type)){obj.quey="LONGTEXT";continue;}
            else if("NATINAL CHARACTER VARYING".equals(type)){obj.quey="LONGTEXT";continue;}
            else if("NUMERIC".equals(type)){obj.quey="DECIMAL";continue;}
            else if("REAL".equals(type)){obj.quey="FLOAT";continue;}
            //serial is rest....................................
            else if("SERIAL".equals(type)){obj.quey="INT";}//set auto increment enable}
            else if("SMALLSERIAL".equals(type)){obj.quey="SMALLINT";}//set auto increment enable}
            else if("BIGSERIAL".equals(type)){obj.quey="BIGINT";}//set auto increment enable}
            else if("SMALLINT".equals(type)){obj.quey="NUMBER(5)";continue;}
            else if("TEXT".equals(type)){obj.quey="LONGTEXT";continue;}
            else if("TIMESTAMP".equals(type)){obj.quey="DATETIME";continue;}
            else if("TIME WITH TIME ZONE".equals(type)||"TIMESTAMPZ".equals(type)){obj.quey="TIMESTAMP("+obj.precision+") WITH TIME ZONE";continue;}
            else if("UUID".equals(type)){obj.quey="VARCHAR(36)";continue;}
            else if("VARBIT".equals(type)){obj.quey="RAW("+obj.columnsize/8+")";continue;}
            else if("VARCHAR".equals(type)){obj.quey="LONGTEXT";continue;}
            else if("XML".equals(type)||"JSON".equals(type)||"TSVECTOR".equals(type)||"TSQUERY".equals(type)||"ARRAY".equals(type)){obj.quey="LONGTEXT";continue;}
            else if("LINE".equals(type)){obj.quey="LINESTRING";}
            else if("LSEG".equals(type)){obj.quey="LINESTRING";}
            else if("BOX".equals(type)){obj.quey="POLYGON";}
            else if("PATH".equals(type)){obj.quey="LINESTRING";}
            else if("CIRCLE".equals(type)){obj.quey="POLYGON";}
            else if("TXID_SNAPSHOT".equals(type)){obj.quey="VARCHAR";}
        }
    }
    
    
    
    
    
}
