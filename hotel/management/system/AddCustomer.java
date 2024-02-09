package hotel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

public class AddCustomer extends JFrame implements ActionListener{
    
    JComboBox comboid;
    JTextField tfno, tfname, tfcountry, tfdeposit; 
    JRadioButton rmale, rfemale; 
    Choice croom; //no J with choice cls.
    JLabel checkintime;
    JButton add, back;
    
    AddCustomer(){
        
        getContentPane().setBackground(Color.white);
        setLayout(null);
        
        JLabel text = new JLabel("NEW CUSTOMER FORM");
        text.setBounds(100, 20, 300, 30);
        text.setFont(new Font("Raleway", Font.PLAIN, 20));
        add(text);
        
        JLabel lblid = new JLabel("ID");
        lblid.setBounds(35, 80, 100, 20);
        lblid.setFont(new Font("Raleway", Font.PLAIN, 20));
        add(lblid);
        
        String options[] = {"Aadhar card", "Passport", "Driving License", "Voter ID card"};
        comboid = new JComboBox(options);
        comboid.setBounds(200, 80, 150, 25);
        comboid.setBackground(Color.white);
        add(comboid);
        
        JLabel lblno = new JLabel("ID Number");
        lblno.setBounds(35, 120, 150, 20);
        lblno.setFont(new Font("Raleway", Font.PLAIN, 20));
        add(lblno);
        
        tfno = new JTextField();
        tfno.setBounds(200, 120, 150, 25);
        add(tfno);
        
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(35, 160, 100, 20);
        lblname.setFont(new Font("Raleway", Font.PLAIN, 20));
        add(lblname);
        
        tfname = new JTextField();
        tfname.setBounds(200, 160, 150, 25);
        add(tfname);
        
        JLabel lblgender = new JLabel("Gender");
        lblgender.setBounds(35, 200, 100, 20);
        lblgender.setFont(new Font("Raleway", Font.PLAIN, 20));
        add(lblgender);
        
        rmale = new JRadioButton("Male"); //creates radio button for male.
        rmale.setBounds(200, 200, 60, 25);
        rmale.setFont(new Font("Tahoma", Font.PLAIN, 14));
        rmale.setBackground(Color.WHITE);
        add(rmale);
        
         rfemale = new JRadioButton("Female"); //creates radio button for female.
        rfemale.setBounds(270, 200, 70, 25);
        rfemale.setFont(new Font("Tahoma", Font.PLAIN, 14));
        rfemale.setBackground(Color.WHITE);
        add(rfemale);
        
        ButtonGroup bg = new ButtonGroup(); // grouping radio buttons means any 1 of male/female gets selected at a time.
        bg.add(rmale);
        bg.add(rfemale);
        
        JLabel country = new JLabel("Country");
        country.setBounds(35, 240, 100, 20);
        country.setFont(new Font("Raleway", Font.PLAIN, 20));
        add(country);
        
        tfcountry = new JTextField();
        tfcountry.setBounds(200, 240, 150, 25);
        add(tfcountry);
        
        JLabel lblroom = new JLabel("Room number");
        lblroom.setBounds(35, 280, 150, 20);
        lblroom.setFont(new Font("Raleway", Font.PLAIN, 20));
        add(lblroom);
        
        //now, we need to add all available rooms from db(under 'room' table) to 'croom' drop-down list.
        croom = new Choice(); //similar to JComboBox but here we can add elements to drop down list by using add() function.
        
        try{ //the drop-down list should display all rooms from 'room' table in db.
            Conn con = new Conn();
            String query = "select * from room where availability = 'available'"; //sql query to select all rooms which ain't occupied.
            ResultSet rs =  con.s.executeQuery(query); //result returned by 'query' gets stored in rs.
            while(rs.next()){
                croom.add(rs.getString("roomno")); //only 'roomno' will be added to drop-down list.
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        croom.setBounds(200, 280, 150, 25);
        add(croom);
        
        JLabel lbltime = new JLabel("Check-in date");
        lbltime.setBounds(35, 320, 150, 20);
        lbltime.setFont(new Font("Raleway", Font.PLAIN, 20));
        add(lbltime);   
        
        Date date = new Date();
        
        checkintime = new JLabel("" + date); //it has to be string & not directly 'date' obj.
        checkintime.setBounds(200, 320, 180, 25);
        checkintime.setFont(new Font("Raleway", Font.PLAIN, 17));
        add(checkintime); 
        
        JLabel lbldeposit = new JLabel("Deposit");
        lbldeposit.setBounds(35, 360, 100, 20);
        lbldeposit.setFont(new Font("Raleway", Font.PLAIN, 20));
        add(lbldeposit);
        
        tfdeposit = new JTextField();
        tfdeposit.setBounds(200, 360, 150, 25);
        add(tfdeposit);
        
        add = new JButton("add");
        add.setBackground(Color.black);
        add.setForeground(Color.white);
        add.setBounds(50, 410, 120, 30);
        add.addActionListener(this);
        add(add);
        
        back = new JButton("Back");
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        back.setBounds(200, 410, 120, 30);
        back.addActionListener(this);
        add(back);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/fifth.png"));
        Image i2 = i1.getImage().getScaledInstance(300, 400, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400, 40, 300, 400);
        add(image);
        
        setBounds(350, 200, 800, 550);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==add){
            String id = (String)comboid.getSelectedItem();
            String no = tfno.getText();
            String name = tfname.getText();
            String gender = null;
            
            if(rmale.isSelected())
                    gender = "male";
            else
                    gender = "female";
            
        String country = tfcountry.getText();
        String room = croom.getSelectedItem(); //no typecast into string for Choice.
        String time = checkintime.getText();
        String deposit = tfdeposit.getText();
        
        try{
            Conn con = new Conn();
            String query = "insert into customer values('"+ id + "', '"+ no + "', '"+ name + "', '"+ gender + "', '"+ country + "', '"+ room + "', '"+ time + "', '"+ deposit + "')";
            //now after inserting vustomer details, that room needs to be updated to 'occupied' status.
            String query2 = "update room set availability = 'Occupied' where roomno = '"+ room +"'";
            con.s.executeUpdate(query); //as DML commands, we did executeUpdate()
            con.s.executeUpdate(query2);
            
            JOptionPane.showMessageDialog(null, "New customer added successfully");
            
            setVisible(false); //close 'NEW CUSTOMER FORM' after all details of new customer added.
            new Reception();   //redirects back to 'Reception'
            
        }catch(Exception e){
            e.printStackTrace();
        }
        }
        else if(ae.getSource()==back){
            setVisible(false);
            new Reception();
        }
    }
        
    public static void main(String[] args){
        new AddCustomer();
    }
}
