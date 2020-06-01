/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbtransaction;

/**
 *
 * @author Vijay
 */
public class ColumnDetails {
    String columnName;
    String datatype;
    long columnsize;
    long precision;
    long scale;
    String quey;
    boolean sign;
    boolean autoIncrement;
    public ColumnDetails(String columnName, long columnsize,String datatype , long precision, long scale,boolean sign,boolean autoIncrement) {
        this.columnName = columnName;
        this.datatype = datatype;
        this.columnsize = columnsize;
        this.precision = precision;
        this.scale = scale;
        this.quey=datatype;
        this.sign=sign;
        this.autoIncrement=autoIncrement;
    }

    
    
}
