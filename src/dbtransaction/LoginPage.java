/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * @author USER
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
      JLabel lblUsername = new JLabel("UserName");
      lblUsername.setBounds(389, 376, 63, 20);
      contentPanel.add(lblUsername);
        }
        {
      JLabel lblPassword = new JLabel("Password");
      lblPassword.setBounds(389, 419, 63, 20);
      contentPanel.add(lblPassword);
        }
    
        username = new JTextField();
       username.setBounds(473, 376, 152, 20);
    contentPanel.add(username);
    username.setColumns(10);
    
    password = new JPasswordField();
    password.setBounds(473, 419, 152, 20);
    contentPanel.add(password);
    
    JButton btnLogin = new JButton("Login");
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
    btnLogin.setBounds();
    contentPanel.add(btnLogin);
    
    JLabel lblLogin = new JLabel("Login");
    lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
    lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 20));
    lblLogin.setBounds();
    contentPanel.add(lblLogin);
        
    }
    
    
    
    
}
