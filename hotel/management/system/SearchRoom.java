
package hotel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.*; //for creating table

public class SearchRoom extends JFrame implements ActionListener{
    
    JTable table;
    JButton back, submit;
    JComboBox bedType;
    JCheckBox available; //to insert check box in our frame. 
    SearchRoom(){
        
        getContentPane().setBackground(Color.white);
        setLayout(null);
        
        JLabel text = new JLabel("Search for Rooms");
        text.setFont(new Font("Tahoma", Font.PLAIN,20));
        text.setBounds(400, 30, 200, 30);
        add(text);
        
        JLabel lblbed = new JLabel("Bed Type");
        lblbed.setBounds(50, 50, 100, 120);
        add(lblbed);
        
        bedType = new JComboBox(new String[]{"Single Bed", "Double Bed"});
        bedType.setBounds(150, 100, 150, 25);
        bedType.setBackground(Color.white);
        add(bedType);
        
        available = new JCheckBox("Only display Available"); // 'Only display Available' will be displayed with the check box.
        available.setBackground(Color.white);
        available.setBounds(650, 100, 150, 25);
        add(available);
        
        JLabel l1 = new JLabel("Room Number");
        l1.setBounds(50, 160, 100, 20);
        add(l1);
        
        JLabel l2 = new JLabel("Availability");
        l2.setBounds(270, 160, 100, 20);
        add(l2);
        
        JLabel l3 = new JLabel("Cleaning Status");
        l3.setBounds(450, 160, 100, 20);
        add(l3);
        
        JLabel l4 = new JLabel("Price");
        l4.setBounds(670, 160, 100, 20);
        add(l4);
        
        JLabel l5 = new JLabel("Bed Type");
        l5.setBounds(870, 160, 100, 20);
        add(l5);
        
        
        
        table = new JTable(); //creating a table using JTable cls.
        table.setBounds(0, 200, 1000, 300); //size of the whole table
        add(table);
        
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from room"); //all data items of room table selected & put in rs 
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
                String query1 = "select * from room where bed_type = '"+ bedType.getSelectedItem() +"'"; //whatever value is selected from drop-down list 'bedType', only such rooms will be shown in table.
                String query2 = "select * from room where availability = 'Available' AND bed_type = '"+ bedType.getSelectedItem() +"'"; //along with available beds, above condition should be taken care of too.
                
                Conn con = new Conn();
                ResultSet rs;
                if(available.isSelected()) //if check box is selected.
                    rs = con.s.executeQuery(query2);
                else ////if check box isn't selected.
                    rs = con.s.executeQuery(query1);
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
        new SearchRoom();
    }
}

