
package dbtransaction;
import java.awt.BorderLayout;
import java.awt.Font;


import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import javax.swing.JTextField;
import javax.swing.JPasswordField;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;



/**
 *
 * @author Vijay
 */
public class LoginPage extends JDialog {
    private JTextField username;
    private JPasswordField password;
    private final JPanel contentPanel = new JPanel();
    
    public LoginPage(){
      setBounds(20,20,1000,1000);
      getContentPane().setLayout(new BorderLayout());
      contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
      getContentPane().add(contentPanel, BorderLayout.CENTER);
      contentPanel.setLayout(null);
        {
      JLabel lblUsername = new JLabel("Id/Email :");
      lblUsername.setBounds(270, 220, 130, 20);
      lblUsername.setFont(new Font("Arial", Font.BOLD, 20));
      contentPanel.add(lblUsername);
        }
        {
      JLabel lblPassword = new JLabel("Password :");
      lblPassword.setBounds(270, 270, 130, 20);
      lblPassword.setFont(new Font("Arial", Font.BOLD, 20));
      contentPanel.add(lblPassword);
        }
    
      username = new JTextField();
      username.setBounds(400, 220, 200, 25);
      contentPanel.add(username);
      username.setColumns(10);
    
      password = new JPasswordField();
      password.setBounds(400, 270, 200, 25);
      contentPanel.add(password);
    
    JButton btnLogin = new JButton("Login");
    JButton btnSignUp = new JButton("SignUp");
    btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputUserName = username.getText();
                String inputPassword = username.getText();
                boolean isUserFound=false,isPasswordMatched=false;
//                for(searching in database for mail){
//                        
//            }
                if(!isUserFound){
                    JOptionPane.showMessageDialog(null, "User doesn't exist","Please Sign up",JOptionPane.WARNING_MESSAGE);
                    
                }
            }
        });
    btnLogin.setBounds(320,320,100,25);
    contentPanel.add(btnLogin);
    btnSignUp.setBounds(460,320,100,25);
    contentPanel.add(btnSignUp);
    
    JLabel lblLogin = new JLabel("Login");
    lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
    lblLogin.setFont(new Font("Tahoma", Font.BOLD, 20));
    lblLogin.setBounds(390,180,100,25);
    contentPanel.add(lblLogin);
        
    }
    
    
    
    
}
