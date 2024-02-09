
package hotel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.*; //for creating table

public class CustomerInfo extends JFrame implements ActionListener{
    
    JTable table;
    JButton back;
    
    CustomerInfo(){
        
        getContentPane().setBackground(Color.white);
        setLayout(null);
        
        JLabel l1 = new JLabel("Document Type");
        l1.setBounds(10, 10, 100, 20);
        add(l1);
        
        JLabel l2 = new JLabel("Document No");
        l2.setBounds(150, 10, 100, 20);
        add(l2);
        
        JLabel l3 = new JLabel("Name");
        l3.setBounds(290, 10, 100, 20);
        add(l3);
        
        JLabel l4 = new JLabel("Gender");
        l4.setBounds(400, 10, 100, 20);
        add(l4);
        
        JLabel l5 = new JLabel("Country");
        l5.setBounds(540, 10, 100, 20);
        add(l5);
        
        JLabel l6 = new JLabel("Room Number");
        l6.setBounds(640, 10, 100, 20);
        add(l6);
        
        JLabel l7 = new JLabel("Check-in Time");
        l7.setBounds(760, 10, 100, 20);
        add(l7);
        
        JLabel l8 = new JLabel("Deposit");
        l8.setBounds(900, 10, 100, 20); 
        add(l8);        
        
        table = new JTable();
        table.setBounds(0, 40, 1000, 400); 
        add(table);
        
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer"); //all data items of room table selected & put in rs 
            table.setModel(DbUtils.resultSetToTableModel(rs)); //to import all data from 'room' on frame.
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        back = new JButton("Back");
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        back.setBounds(420, 500, 120, 30);
        back.addActionListener(this);
        add(back);
        
        setBounds(300, 200, 1000, 600);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        setVisible(false);
        new Reception();
    }
    
    public static void main(String args[]){
        new CustomerInfo();
    }
}

