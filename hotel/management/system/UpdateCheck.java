
package hotel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class UpdateCheck extends JFrame implements ActionListener{
    
    Choice ccustomer;
    JTextField tfroom, tfname, tfcheckin, tfpaid, tfpending;
    JButton check, update, back;
    
    UpdateCheck(){
        
        getContentPane().setBackground(Color.white);
        setLayout(null);
        
        JLabel text = new JLabel("Update Status");
        text.setFont(new Font("Tahome", Font.PLAIN, 20));
        text.setBounds(90, 20, 200, 30);
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
        lblroom.setBounds(30, 120, 100, 20);
        add(lblroom);
        
        tfroom = new JTextField();
        tfroom.setBounds(200, 120, 150, 25);
        add(tfroom);
        
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(30, 160, 100, 20);
        add(lblname);
        
        tfname = new JTextField();
        tfname.setBounds(200, 160, 150, 25);
        add(tfname);
        
        JLabel lblcheckin = new JLabel("Check-in Time");
        lblcheckin.setBounds(30, 200, 100, 20);
        add(lblcheckin);
        
        tfcheckin = new JTextField();
        tfcheckin.setBounds(200, 200, 150, 25);
        add(tfcheckin);
        
        JLabel lblpaid = new JLabel("Amount Paid");
        lblpaid.setBounds(30, 240, 100, 20);
        add(lblpaid);
        
        tfpaid = new JTextField();
        tfpaid.setBounds(200, 240, 150, 25);
        add(tfpaid);
        
        JLabel lblpending = new JLabel("Pending Amount");
        lblpending.setBounds(30, 280, 100, 20);
        add(lblpending);
        
        tfpending = new JTextField();
        tfpending.setBounds(200, 280, 150, 25);
        add(tfpending);
        
        check = new JButton("Check");
        check.setBackground(Color.black);
        check.setForeground(Color.white);
        check.setBounds(30, 340, 100, 30);
        check.addActionListener(this);
        add(check);
        
        update = new JButton("Update");
        update.setBackground(Color.black);
        update.setForeground(Color.white);
        update.setBounds(150, 340, 100, 30);
        update.addActionListener(this);
        add(update);
        
        back = new JButton("Back");
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        back.setBounds(270, 340, 100, 30);
        back.addActionListener(this);
        add(back);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/nine.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(400, 50, 500, 300);
        add(image);
        
        setBounds(300, 200, 980, 500);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==check){ 
            String id = ccustomer.getSelectedItem(); //selected option out of drop-down is fed into 'id'
            String query = "select * from customer where number = '"+ id +"'"; //all columns of the customer with number = 'id' selected.
            
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery(query);
            
            while(rs.next()){ //use this for looping all the customers' data multiple times; rs.next() points to next customer row in 'customer' table. 
                tfroom.setText(rs.getString("room")); //room no from 'room' col of 'customer' table is stored in 'tfroom' text field now.
                tfname.setText(rs.getString("name")); 
                tfcheckin.setText(rs.getString("checkintime"));
                tfpaid.setText(rs.getString("deposit"));
            }
            
            //now, for 'pending amount'
            ResultSet rs2 = c.s.executeQuery("select * from room where roomno = '"+ tfroom.getText() +"'"); //selects the whole row with 'tfroom' roomno.
            while(rs2.next()){
                String price = rs2.getString("price"); //selects only 'price' column from 'room' table against the 'tfroom' roomno.
                int amountPaid = Integer.parseInt(price) - Integer.parseInt(tfpaid.getText()); //convert str to int; amount due = room price - paid amount.
                tfpending.setText("" + amountPaid); //cnvert int to str & put it in tfpending text field.
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        }else if(ae.getSource()==update){
            String number = ccustomer.getSelectedItem();
            String room = tfroom.getText();
            String name = tfname.getText();
            String checkin = tfcheckin.getText();
            String deposit = tfpaid.getText();
            
            try{
                Conn c = new Conn();
                c.s.executeUpdate("update customer set room = '"+ room +"', name = '"+ name +"', checkintime = '"+ checkin +"', deposit = '"+ deposit +"' where number = '"+ number +"'");
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
        new UpdateCheck();
    }    
}
