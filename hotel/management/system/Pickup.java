
package hotel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.*; //for creating table

public class Pickup extends JFrame implements ActionListener{
    
    JTable table;
    JButton back, submit;
    Choice typeofcar;
    Pickup(){
        
        getContentPane().setBackground(Color.white);
        setLayout(null);
        
        JLabel text = new JLabel("Pick-up Service");
        text.setFont(new Font("Tahoma", Font.PLAIN,20));
        text.setBounds(400, 30, 200, 30);
        add(text);
        
        JLabel lblbed = new JLabel("Type of car");
        lblbed.setBounds(50, 50, 100, 120);
        add(lblbed);
        
        JLabel l1 = new JLabel("Name");
        l1.setBounds(30, 160, 100, 20);
        add(l1);
        
        JLabel l2 = new JLabel("Age");
        l2.setBounds(200, 160, 100, 20);
        add(l2);
        
        JLabel l3 = new JLabel("Gender");
        l3.setBounds(330, 160, 100, 20);
        add(l3);
        
        JLabel l4 = new JLabel("Company");
        l4.setBounds(460, 160, 100, 20);
        add(l4);
        
        JLabel l5 = new JLabel("Brand");
        l5.setBounds(630, 160, 100, 20);
        add(l5);
        
        JLabel l6 = new JLabel("Availability");
        l6.setBounds(740, 160, 100, 20);
        add(l6);
        
        JLabel l7 = new JLabel("Location");
        l7.setBounds(890, 160, 100, 20);
        add(l7);
        
        typeofcar = new Choice();
        typeofcar.setBounds(150, 100, 200, 25);
        add(typeofcar);
        
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from driver");
            while(rs.next()){
                typeofcar.add(rs.getString("brand")); //adds value of 'brand' column(of all cars) to 'typeofcar' drop-down.
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        table = new JTable(); //creating a table using JTable cls.
        table.setBounds(0, 200, 1000, 300); //size of the whole table
        add(table);
        
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from driver"); //all data items of room table selected & put in rs 
            table.setModel(DbUtils.resultSetToTableModel(rs)); //to import all data from 'room' on frame.
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        submit = new JButton("Submit");
        submit.setBackground(Color.black);
        submit.setForeground(Color.white);
        submit.setBounds(300, 520, 120, 30);
        submit.addActionListener(this);
        add(submit);
        
        back = new JButton("Back");
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        back.setBounds(500, 520, 120, 30);
        back.addActionListener(this);
        add(back);
        
        setBounds(300, 200, 1000, 600);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==submit){
            
            try{
                String query = "select * from driver where brand = '"+ typeofcar.getSelectedItem() +"'"; //whatever value is selected from drop-down list 'bedType', only such rooms will be shown in table.
                
                Conn con = new Conn();
                ResultSet rs;
                rs = con.s.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs)); //to import all data as per the value of 'rs' from 'room' on our frame.
                
            }catch(Exception e){
                e.printStackTrace();
            }
            
        }else if(ae.getSource()==back){
        setVisible(false);
        new Reception();
        }
    }
    
    public static void main(String args[]){
        new Pickup();
    }
}

