
package hotel.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Dashboard extends JFrame implements ActionListener{
    
    Dashboard(){ //cnstrctr
        setBounds(0, 0, 1550, 1000);
        setLayout(null);
         
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/third.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1550, 1000, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 1550, 1000);
        add(image);
        
        JLabel text = new JLabel("THE SONAR BANGLA GROUP WELCOMES YOU");
        text.setBounds(300, 80, 1000, 50);
        text.setFont(new Font("Tahoma", Font.PLAIN, 45));
        image.add(text);
        
        JMenuBar mb = new JMenuBar(); //creates a manu bar on top off screen.
        mb.setBounds(0, 0, 1550, 30);
        image.add(mb);
        
        JMenu hotel = new JMenu("HOTEL MANAGEMENT"); // creates menu with title HOTEL MANAGEMENT
        hotel.setForeground(Color.RED);
        mb.add(hotel);
        
        JMenuItem reception = new JMenuItem("RECEPTION"); //adds RECEPTION to HOTEL MANAGEMENT menu
        reception.addActionListener(this);
        hotel.add(reception);
        
        JMenu admin = new JMenu("ADMIN"); // creates menu with title ADMIN
        admin.setForeground(Color.BLUE);
        mb.add(admin);
        
        JMenuItem addemployee = new JMenuItem("ADD EMPLOYEE"); //adds addemployee to ADMIN menu
        addemployee.addActionListener(this);
        admin.add(addemployee);
        
        JMenuItem adddrives = new JMenuItem("ADD DRIVERS"); 
        adddrives.addActionListener(this);
        admin.add(adddrives);   
        
        JMenuItem addrooms = new JMenuItem("ADD ROOMS"); 
        addrooms.addActionListener(this);
        admin.add(addrooms);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getActionCommand().equals("ADD EMPLOYEE")) 
            new AddEmployee();
        else if(ae.getActionCommand().equals("ADD ROOMS"))
            new AddRooms();
        else if(ae.getActionCommand().equals("ADD DRIVERS"))
            new AddDriver();
        else if(ae.getActionCommand().equals("RECEPTION"))
            new Reception();
    }
  
    public static void main(String args[]){
        new Dashboard();
    }
}
