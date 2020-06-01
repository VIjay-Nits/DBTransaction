
package dbtransaction;

import static dbtransaction.DateValidator.isValidDate;
import java.sql.*;
import java.time.LocalDate;
import java.time.Month;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Vijay
 */
public class AddTask extends javax.swing.JFrame {

    /**
     * Creates new form AddTask
     */
    public AddTask() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        sUserNameFE = new javax.swing.JTextField();
        sPasswordFE = new javax.swing.JPasswordField();
        jLabel7 = new javax.swing.JLabel();
        sHostFE = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        sPortFE = new javax.swing.JTextField();
        sDatabaseNameFE = new javax.swing.JTextField();
        sourceFE = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        sTableNameFE = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        destinationFE = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        dUserNameFE = new javax.swing.JTextField();
        dPasswordFE = new javax.swing.JPasswordField();
        jLabel8 = new javax.swing.JLabel();
        dHostFE = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        dPortFE = new javax.swing.JTextField();
        dDatabaseNameFE = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        dTableNameFE = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        saveButton = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        taskNameFE = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        columnNameFE = new javax.swing.JComboBox<>();
        constraintFE = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        occurenceFE = new javax.swing.JComboBox<>();
        schDayFE = new javax.swing.JSpinner();
        schMonthFE = new javax.swing.JSpinner();
        schYearFE = new javax.swing.JSpinner();
        jLabel19 = new javax.swing.JLabel();
        verify = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add New Task");
        setResizable(false);

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Source:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.BELOW_TOP, new java.awt.Font("Tahoma", 1, 24))); // NOI18N

        jLabel1.setText("Database:");

        jLabel3.setText("Username:");

        jLabel4.setText("Password:");

        sUserNameFE.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                sUserNameFEFocusLost(evt);
            }
        });

        jLabel7.setText("Host:");

        jLabel11.setText("Port:");

        jLabel12.setText("Database Name:");

        sDatabaseNameFE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sDatabaseNameFEActionPerformed(evt);
            }
        });

        sourceFE.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Oracle", "MySQL", "MsSQL", "PostgreSQL" }));
        sourceFE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sourceFEActionPerformed(evt);
            }
        });

        jLabel20.setText("Table Name:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel20)
                    .addComponent(jLabel12)
                    .addComponent(jLabel11)
                    .addComponent(jLabel7)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(sUserNameFE)
                    .addComponent(sPasswordFE)
                    .addComponent(sHostFE)
                    .addComponent(sPortFE)
                    .addComponent(sDatabaseNameFE)
                    .addComponent(sourceFE, 0, 318, Short.MAX_VALUE)
                    .addComponent(sTableNameFE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(sourceFE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(sUserNameFE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(sPasswordFE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(sHostFE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(sPortFE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(sDatabaseNameFE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(sTableNameFE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Destination:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.BELOW_TOP, new java.awt.Font("Tahoma", 1, 24))); // NOI18N

        destinationFE.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Oracle", "MySQL", "MsSQL", "PostgreSQL" }));
        destinationFE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                destinationFEActionPerformed(evt);
            }
        });

        jLabel2.setText("DataBase:");

        jLabel5.setText("Username:");

        jLabel6.setText("Password:");

        jLabel8.setText("Host:");

        jLabel14.setText("Port:");

        jLabel13.setText("Database Name:");

        jLabel21.setText("Table Name:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel21)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8)
                    .addComponent(jLabel5)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(destinationFE, 0, 295, Short.MAX_VALUE)
                    .addComponent(dUserNameFE)
                    .addComponent(dPasswordFE)
                    .addComponent(dHostFE)
                    .addComponent(dPortFE)
                    .addComponent(dDatabaseNameFE)
                    .addComponent(dTableNameFE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(destinationFE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(dUserNameFE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(dPasswordFE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(dHostFE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(dPortFE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(dDatabaseNameFE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(dTableNameFE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6))
        );

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));

        saveButton.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        saveButton.setText("Save Task");
        saveButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        jLabel9.setText("Scheduled Date:");

        jLabel10.setText("Task Name:");

        jLabel15.setText("Filter:");

        jLabel16.setText("Column Name:");

        jLabel17.setText("Constraint:");

        columnNameFE.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                columnNameFEFocusGained(evt);
            }
        });
        columnNameFE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                columnNameFEActionPerformed(evt);
            }
        });

        constraintFE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                constraintFEActionPerformed(evt);
            }
        });

        jLabel18.setText("Occurrence of Transfer:");

        occurenceFE.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "daily", "weekly", "monthly", "yearly" }));

        schYearFE.setName(""); // NOI18N

        jLabel19.setText("(dd-mm-yyyy)");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(taskNameFE, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(columnNameFE, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(constraintFE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel18)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(occurenceFE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(99, 99, 99)
                        .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(schDayFE)
                        .addGap(18, 18, 18)
                        .addComponent(schMonthFE, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(schYearFE, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel19)
                        .addGap(36, 36, 36))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(taskNameFE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)
                        .addComponent(schDayFE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel19)
                        .addComponent(schYearFE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(schMonthFE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(columnNameFE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(constraintFE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(occurenceFE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18))))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        verify.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        verify.setText("Connection Authentication");
        verify.setActionCommand("Connection");
        verify.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        verify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verifyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(verify, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(367, 367, 367))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(verify, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

     String userNameSource,userNameDestination;
     String passwordSource,passwordDestination;
     String source,destination;
     String sTable,dTable;
     int scheduledDay,scheduledMonth,scheduledYear;
     String scheduledDate;
     boolean flagVerify=false,flagDate=false;
    
    
    private void sUserNameFEFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_sUserNameFEFocusLost

    }//GEN-LAST:event_sUserNameFEFocusLost

    private void destinationFEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_destinationFEActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_destinationFEActionPerformed
    
    
    
    private void verifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verifyActionPerformed
        userNameSource=sUserNameFE.getText().trim();
        passwordSource=sPasswordFE.getText().trim();
        userNameDestination=dUserNameFE.getText().trim();
        passwordDestination=dPasswordFE.getText().trim();
        source=(String)sourceFE.getItemAt(sourceFE.getSelectedIndex());
        destination=(String)destinationFE.getItemAt(destinationFE.getSelectedIndex());
        sTable=sTableNameFE.getText().trim();
        dTable=dTableNameFE.getText().trim();
        
        ConnectionDB connSource=new ConnectionDB(userNameSource, passwordSource);
        ConnectionDB connDestination=new ConnectionDB(userNameDestination, passwordDestination);
        if(connSource.isConnectionCreated(source)&&connDestination.isConnectionCreated(destination)){
            JOptionPane.showMessageDialog(null, "Connection Authenticated");
            
            flagVerify=true;
             try{
                 connSource.connection().close();
            connDestination.connection().close();
                 new RunningTransaction(this);
             }
        catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
        }
        }else {
            JOptionPane.showMessageDialog(null, "Connection Authentication Failed");
            flagVerify=false;
        }
        
       
        
        
    }//GEN-LAST:event_verifyActionPerformed

    private void sDatabaseNameFEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sDatabaseNameFEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sDatabaseNameFEActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        if(flagVerify){
            scheduledDay=(int)schDayFE.getValue();
            scheduledMonth=(int)schMonthFE.getValue();
            scheduledYear=(int)schYearFE.getValue();
            flagDate=false;
            
            DateValidator date=new DateValidator(scheduledDay,scheduledMonth, scheduledYear);
            LocalDate currentdate=java.time.LocalDate.now();
            LocalDate myDate=(LocalDate)LocalDate.of(scheduledYear, scheduledMonth, scheduledDay);
            if(isValidDate(scheduledDay, scheduledMonth,scheduledYear)&&currentdate.isBefore(myDate)){
                flagDate=true;
                scheduledDate=myDate.toString();
            }else{
                JOptionPane.showMessageDialog(null, "Problem with scheduled Date");
           
            }
            if(!flagDate){ 
                JOptionPane.showMessageDialog(null, "Problem with scheduled Date");
                return;
            }
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
             
            }
            try {
                Connection conn=DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","postmanager");
                System.out.println("postgres connected");
                String query="insert into users values('"+"vijaygupta13199@gmail.com','"
                        +source+"','"
                        +userNameSource+"','"
                        +passwordSource+"','"
                        +"shost"+"','"
                        +"sort"+"','"
                        +"sdatabasename"+"','"
                        +destination+"','"
                        +userNameDestination+"','"
                        +passwordDestination+"','"
                        +"dhost"+"','"
                        +"dort"+"','"
                        +"ddatabasename"+"','"
                        +taskNameFE.getText().trim()+"','"
                        +sTableNameFE.getText().trim()+"','"
                        +"constraint"+"','"
                        +"2016-4-4"+"','"
                        +"2001-5-26"+"',"
                        +7+","
                        +25+")";
                                                                                
                System.out.println(query);
                Statement st=conn.createStatement();
                int retrn=st.executeUpdate(query);

                conn.close();
                st.close();
                if(retrn==1){
                    JOptionPane.showMessageDialog(null, "Task Saved Successfully");
                    dispose();
                    ScheduledTaskList task;
                    task=new ScheduledTaskList( 
                                    taskNameFE.getText().trim(),
                                    source,
                                    destination,
                                    sTableNameFE.getText().trim(),
                                    scheduledDate,
                                       "I Will add deatils");


                    DefaultTableModel model=(DefaultTableModel)(new TransactionGUI().taskTable).getModel();

                    Object[]row=new Object[6];
                    row[0]=task.getTaskName();
                    row[1]=task.getSourceName();
                    row[2]=task.getDestinationName();
                    row[3]=task.getTableName();
                    row[4]=task.getSchTime();
                    row[5]=task.getDeatils();
                    model.addRow(row);
                }else{
                   JOptionPane.showMessageDialog(null, "Check The Details");   
                }
            } catch (SQLException ex) {
                Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Please Authenticate Connection First");
            
        }
    }//GEN-LAST:event_saveButtonActionPerformed

    private void constraintFEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_constraintFEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_constraintFEActionPerformed

    private void sourceFEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sourceFEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sourceFEActionPerformed

    private void columnNameFEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_columnNameFEActionPerformed
       
    }//GEN-LAST:event_columnNameFEActionPerformed

    private void columnNameFEFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_columnNameFEFocusGained
        
    }//GEN-LAST:event_columnNameFEFocusGained
      Vector<String>colnames=new Vector<>();
    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(AddTask.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(AddTask.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(AddTask.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(AddTask.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> columnNameFE;
    private javax.swing.JTextField constraintFE;
    private javax.swing.JTextField dDatabaseNameFE;
    private javax.swing.JTextField dHostFE;
    private javax.swing.JPasswordField dPasswordFE;
    private javax.swing.JTextField dPortFE;
    private javax.swing.JTextField dTableNameFE;
    private javax.swing.JTextField dUserNameFE;
    private javax.swing.JComboBox<String> destinationFE;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JComboBox<String> occurenceFE;
    private javax.swing.JTextField sDatabaseNameFE;
    private javax.swing.JTextField sHostFE;
    private javax.swing.JPasswordField sPasswordFE;
    private javax.swing.JTextField sPortFE;
    private javax.swing.JTextField sTableNameFE;
    private javax.swing.JTextField sUserNameFE;
    private javax.swing.JButton saveButton;
    private javax.swing.JSpinner schDayFE;
    private javax.swing.JSpinner schMonthFE;
    private javax.swing.JSpinner schYearFE;
    private javax.swing.JComboBox<String> sourceFE;
    private javax.swing.JTextField taskNameFE;
    private javax.swing.JButton verify;
    // End of variables declaration//GEN-END:variables
}
