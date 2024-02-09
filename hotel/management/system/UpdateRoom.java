
package hotel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class UpdateRoom extends JFrame implements ActionListener{
    
    Choice ccustomer;
    JTextField tfroom, tfavailable, tfclean;
    JButton check, update, back;
    
    UpdateRoom(){
        
        getContentPane().setBackground(Color.white);
        setLayout(null);
        
        JLabel text = new JLabel("Update Room Status");
        text.setFont(new Font("Tahome", Font.PLAIN, 25));
        text.setBounds(30, 20, 250, 30);
        text.setForeground(Color.blue);
        add(text);
        
        JLabel lblid = new JLabel("Customer ID");
        lblid.setBounds(30, 80, 100, 20);
        add(lblid);
        
        ccustomer = new Choice(); //for 'Customer ID'
        ccustomer.setBounds(200, 80, 150, 25);
        add(ccustomer);
        
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer");
            while(rs.next()){ //selecting from multiple rows of 'customer'.
                ccustomer.add(rs.getString("number"));  ////selecting 'number' colmn only from multiple rows of customers & adding it to drop-down list 'ccustomer'.
            } 
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        JLabel lblroom = new JLabel("Room Number");
        lblroom.setBounds(30, 130, 100, 20);
        add(lblroom);
        
        tfroom = new JTextField();
        tfroom.setBounds(200, 130, 150, 25);
        add(tfroom);
        
        JLabel lblavailable = new JLabel("Availability");
        lblavailable.setBounds(30, 180, 100, 20);
        add(lblavailable);
        
        tfavailable = new JTextField();
        tfavailable.setBounds(200, 180, 150, 25);
        add(tfavailable);
        
        JLabel lblclean = new JLabel("Cleaning Status");
        lblclean.setBounds(30, 230, 100, 20);
        add(lblclean);
        
        tfclean = new JTextField();
        tfclean.setBounds(200, 230, 150, 25);
        add(tfclean);
        
        check = new JButton("Check");
        check.setBackground(Color.black);
        check.setForeground(Color.white);
        check.setBounds(30, 300, 100, 30);
        check.addActionListener(this);
        add(check);
        
        update = new JButton("Update");
        update.setBackground(Color.black);
        update.setForeground(Color.white);
        update.setBounds(150, 300, 100, 30);
        update.addActionListener(this);
        add(update);
        
        back = new JButton("Back");
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        back.setBounds(270, 300, 100, 30);
        back.addActionListener(this);
        add(back);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/seventh.jpg"));
        Image i2 = i1.getImage().getScaledInstance(500, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400, 50, 500, 300);
        add(image);
        
        setBounds(300, 200, 980, 450);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==check){
            String id = ccustomer.getSelectedItem();
            String query = "select * from customer where number = '"+ id +"'";
            
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery(query);
            
            while(rs.next()){ 
                tfroom.setText(rs.getString("room")); //room no from 'room' col of 'customer' table is stored in 'tfroom' text field now.
            }
            
            ResultSet rs2 = c.s.executeQuery("select * from room where roomno = '"+ tfroom.getText() +"'"); //selects the whole row with 'tfroom' roomno.
            while(rs2.next()){
                tfavailable.setText(rs2.getString("availability")); //extracts 'availablity' col from 'room' table of that specific roomno stored in 'tfroom' and puts in 'tfavailable' text field.  
                tfclean.setText(rs2.getString("cleaning_status"));
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        }else if(ae.getSource()==update){
            String room = tfroom.getText();
            String available = tfavailable.getText();
            String status = tfclean.getText();
            
            try{
                Conn c = new Conn();
                c.s.executeUpdate("update room set availability = '"+ available +"', cleaning_status = '"+ status +"' where roomno = '"+ room +"'");
                //now we have updated 'room', 'name', 'checkintime' and 'deposit' wrt the 'number' selected from 'ccustomer'.
                
                JOptionPane.showMessageDialog(null, "Data Updated successfully");
                setVisible(false);
                new Reception();
                
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{ //for 'back'
            setVisible(false);
            new Reception();
        }
                
    }
    
public static void main(String args[]){
        new UpdateRoom();
    }    
}
