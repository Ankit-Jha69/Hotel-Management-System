package hotel.management.system;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class AddDriver extends JFrame implements ActionListener{
    
    JButton add, cancel;
    JTextField tfname, tfage, tfcompany, tfmodel, tflocation;
    JComboBox gendercombo, availablecombo;
    AddDriver(){
        
        getContentPane().setBackground(Color.white);
        setLayout(null);
        
        JLabel heading = new JLabel("Add Driver");
        heading.setFont(new Font("Tahoma", Font.BOLD, 18));
        heading.setBounds(150, 10, 200, 20);
        add(heading);
        
        JLabel name = new JLabel("Name");
        name.setFont(new Font("Tahoma", Font.PLAIN, 16));
        name.setBounds(60, 70, 120, 30);
        add(name);
        
         tfname = new JTextField();
        tfname.setBounds(200, 70, 150, 30);
        add(tfname);       
        
        JLabel gender = new JLabel("Gender");
        gender.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gender.setBounds(60, 110, 120, 30);
        add(gender);
        
        String genderOptions[] = {"Male", "Female"};
         gendercombo = new JComboBox(genderOptions);
        gendercombo.setBounds(200, 110, 150, 30);
        gendercombo.setBackground(Color.WHITE);
        add(gendercombo);
        
        JLabel lblage = new JLabel("Age");
        lblage.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblage.setBounds(60, 150, 120, 30);
        add(lblage);
        
        tfage = new JTextField();
        tfage.setBounds(200, 150, 150, 30);
        add(tfage);
        
        JLabel company = new JLabel("Car Company");
        company.setFont(new Font("Tahoma", Font.PLAIN, 16));
        company.setBounds(60, 190, 120, 30);
        add(company);
        
         tfcompany = new JTextField();
        tfcompany.setBounds(200, 190, 150, 30);
        add(tfcompany);
        
        JLabel type = new JLabel("Car Model");
        type.setFont(new Font("Tahoma", Font.PLAIN, 16));
        type.setBounds(60, 230, 120, 30);
        add(type);
        
        tfmodel = new JTextField();
        tfmodel.setBounds(200, 230, 150, 30);
        add(tfmodel);
        
        JLabel lblavailable = new JLabel("Availability");
        lblavailable.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblavailable.setBounds(60, 270, 120, 30);
        add(lblavailable);
        
        String drierOptions[] = {"Available", "busy"};
         availablecombo = new JComboBox(drierOptions);
        availablecombo.setBounds(200, 270, 150, 30);
        availablecombo.setBackground(Color.WHITE);
        add(availablecombo);
        
        JLabel location = new JLabel("Location");
        location.setFont(new Font("Tahoma", Font.PLAIN, 16));
        location.setBounds(60, 310, 120, 30);
        add(location);
        
        tflocation = new JTextField();
        tflocation.setBounds(200, 310, 150, 30);
        add(tflocation);
        
         add = new JButton("Add Driver");
        add.setBackground(Color.black);
        add.setForeground(Color.white);
        add.setBounds(60, 360, 130, 30);
        add.addActionListener(this);
        add(add);
        
         cancel = new JButton("Cancel");
        cancel.setBackground(Color.black);
        cancel.setForeground(Color.white);
        cancel.setBounds(220, 360, 130, 30);
        cancel.addActionListener(this);
        add(cancel);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/eleven.jpg"));
        Image i2 = i1.getImage().getScaledInstance(500, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400, 30, 500, 300);
        add(image);
        
        setBounds(300, 200, 980, 470);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==add){ //add button clicked
            String name = tfname.getText();
            String gender = (String)gendercombo.getSelectedItem();
            String age = tfage.getText();
            String company = tfcompany.getText();
            String brand = tfmodel.getText();
            String available = (String)availablecombo.getSelectedItem();
            String location = tflocation.getText();
            
            try{
                Conn con = new Conn();
                String str = "insert into driver values('"+ name +"', '"+ gender +"', '"+ age +"', '"+ company +"', '"+ brand +"', '"+ available +"', '"+ location +"')";
                
                con.s.executeUpdate(str);
                
                JOptionPane.showMessageDialog(null, "New driver added successfully");
                setVisible(false);
                
            }catch(Exception e){
                e.printStackTrace();
            }
            
        }else{ //cancel button clicked
            setVisible(false);
        }
    }
    
    public static void main(String args[]){
        new AddDriver();
    }
}
