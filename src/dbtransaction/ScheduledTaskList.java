/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbtransaction;

/**
 *
 * @author USER
 */
public class ScheduledTaskList {
    private String taskName,sourceName,destinationName,tableName,schTime,deatils;

    public ScheduledTaskList(String taskName, String sourceName, String destinationName, String tableName, String schTime, String deatils) {
        this.taskName = taskName;
        this.sourceName = sourceName;
        this.destinationName = destinationName;
        this.tableName = tableName;
        this.schTime = schTime;
        this.deatils = deatils;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getSchTime() {
        return schTime;
    }

    public void setSchTime(String schTime) {
        this.schTime = schTime;
    }

    public String getDeatils() {
        return deatils;
    }

    public void setDeatils(String deatils) {
        this.deatils = deatils;
    }
    
    
}
