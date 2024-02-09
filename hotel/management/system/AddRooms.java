package hotel.management.system;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class AddRooms extends JFrame implements ActionListener{
    
    JButton add, cancel;
    JTextField tfroom, tfprice;
    JComboBox availableCombo, cleancombo, typecombo;
    AddRooms(){
        
        getContentPane().setBackground(Color.white);
        setLayout(null);
        
        JLabel heading = new JLabel("Add Rooms");
        heading.setFont(new Font("Tahoma", Font.BOLD, 18));
        heading.setBounds(150, 20, 200, 20);
        add(heading);
        
        JLabel roomno = new JLabel("Room number");
        roomno.setFont(new Font("Tahoma", Font.PLAIN, 16));
        roomno.setBounds(60, 80, 120, 30);
        add(roomno);
        
         tfroom = new JTextField();
        tfroom.setBounds(200, 80, 150, 30);
        add(tfroom);
        
        JLabel available = new JLabel("Available");
        available.setFont(new Font("Tahoma", Font.PLAIN, 16));
        available.setBounds(60, 130, 120, 30);
        add(available);
        
        String availableOptions[] = {"Available", "Occupied"};
         availableCombo = new JComboBox(availableOptions);
        availableCombo.setBounds(200, 130, 150, 30);
        availableCombo.setBackground(Color.white);
        add(availableCombo);
        
        JLabel clean = new JLabel("Cleaning status");
        clean.setFont(new Font("Tahoma", Font.PLAIN, 16));
        clean.setBounds(60, 180, 120, 30);
        add(clean);
        
        String cleanOptions[] = {"Cleaned", "Uncleaned"};
         cleancombo = new JComboBox(cleanOptions);
        cleancombo.setBounds(200, 180, 150, 30);
        cleancombo.setBackground(Color.WHITE);
        add(cleancombo);
        
        JLabel price = new JLabel("Price");
        price.setFont(new Font("Tahoma", Font.PLAIN, 16));
        price.setBounds(60, 230, 120, 30);
        add(price);
        
         tfprice = new JTextField();
        tfprice.setBounds(200, 230, 150, 30);
        add(tfprice);
        
        JLabel type = new JLabel("Bed Type");
        type.setFont(new Font("Tahoma", Font.PLAIN, 16));
        type.setBounds(60, 280, 120, 30);
        add(type);
        
        String typeOptions[] = {"Single Bed", "Double Bed"};
         typecombo = new JComboBox(typeOptions);
        typecombo.setBounds(200, 280, 150, 30);
        typecombo.setBackground(Color.WHITE);
        add(typecombo);
        
         add = new JButton("Add Room");
        add.setBackground(Color.black);
        add.setForeground(Color.white);
        add.setBounds(60, 350, 130, 30);
        add.addActionListener(this);
        add(add);
        
         cancel = new JButton("Cancel");
        cancel.setBackground(Color.black);
        cancel.setForeground(Color.white);
        cancel.setBounds(220, 350, 130, 30);
        cancel.addActionListener(this);
        add(cancel);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/twelve.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(400, 30, 500, 300);
        add(image);
        
        setBounds(330, 200, 940, 470);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==add){ //add button clicked
            String roomno = tfroom.getText();
            String availability = (String)availableCombo.getSelectedItem();
            String status = (String)cleancombo.getSelectedItem();
            String price = tfprice.getText();
            String type = (String)typecombo.getSelectedItem();
            
            try{
                Conn con = new Conn();
                String str = "insert into room values('"+ roomno +"', '"+ availability +"', '"+ status +"', '"+ price +"', '"+ type +"')";
                
                con.s.executeUpdate(str);
                
                JOptionPane.showMessageDialog(null, "New room added successfully");
                setVisible(false);
                
            }catch(Exception e){
                e.printStackTrace();
            }
            
        }else{ //cancel button clicked
            setVisible(false);
        }
    }
    
    public static void main(String args[]){
        new AddRooms();
    }
}
