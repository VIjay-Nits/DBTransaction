/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbtransaction;

import javax.swing.JDialog;

/**
 *
 * @author USER
 */
public class DBTransaction {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        LoginPage dialog=new LoginPage();
//        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//        dialog.setTitle("Login Page");
//        dialog.setVisible(true);
        TransactionGUI tg=new TransactionGUI();
       java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TransactionGUI().setVisible(true);
            }
        });
//        AddTask add=new AddTask();
//       java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new AddTask().setVisible(true);
//            }
//        });
       
    }
    
}
