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
    String deafultvalue;
    String isNullable;
    String is_autoIncrment;

    public ColumnDetails(String columnName, String datatype, long columnsize, String deafultvalue, String isNullable, String is_autoIncrment) {
        this.columnName = columnName;
        this.datatype = datatype;
        this.columnsize = columnsize;
        this.deafultvalue = deafultvalue;
        this.isNullable = isNullable;
        this.is_autoIncrment = is_autoIncrment;
    }
    
    
}
