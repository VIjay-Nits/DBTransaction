
package dbtransaction;

import java.sql.SQLException;
import javax.swing.JDialog;

/**
 *
 * @author Vijay
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
//     try{  new RunningTransaction();}
//     catch(SQLException e){
//        e.printStackTrace();
//     }
//        AddTask add=new AddTask();
//       java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new AddTask().setVisible(true);
//            }
//        });
       
    }
    
}
