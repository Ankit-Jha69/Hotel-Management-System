package hotel.management.system;
import javax.swing.*; //x means extended; has JFrame.
import java.awt.*; //has img & clr fncn.
import java.awt.event.*;//has ActionListerner intrface.

public class HotelManagementSystem extends JFrame implements ActionListener{ // we extend our cls to JFrame cls which is present under swing package.
                                                                             //ActionListener interface helps to catch click on a button.
    HotelManagementSystem(){   //constructor
        setSize(1366, 565); //frame dimensions (l, b)
        setLocation(100, 100); //100 from top(x) & 100 from left(y) of screen, so that frame opens in mid of screeen.
        setLayout(null);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/first.jpg")); //imported image from icons folder and stored it in object i1.
        JLabel image = new JLabel(i1); //to place icon i1 on our empty frame.
        image.setBounds(0, 0, 1366, 565); //sets the layout of img on frame- dis. from x(top) of frame, dis. from y(left) of frame, length, breadth.
        add(image); //places image on our empty frame.
        
        //JLabel used to add anything on frame.
        JLabel text = new JLabel("HOTEL MANAGEMENT SYSTEM"); //this text has to be displayed on frame.
        text.setBounds(20, 430, 1000, 90); //set layout & dimnsions of text.
        text.setForeground(Color.WHITE); //sets font clr to white.
        text.setFont(new Font("serif", Font.PLAIN, 50)); //sets font name, style & size to text.
        image.add(text); //adds text on top of img in the frame & does not hides it below the img.
        
        JButton next = new JButton("Next"); //creates a button 'next' on the img.
        next.setBounds(1150, 450, 150, 50);
        next.setBackground(Color.WHITE); //sets bg clr of button to white.
        next.setForeground(Color.BLACK); //sets text(Next) clr in button to black.
        next.setFont(new Font("serif", Font.PLAIN, 24));
        next.addActionListener(this); //to catch the click event on next button.
        image.add(next);
        setVisible(true); //makes frame visible(hidden by default).
        
        while(true){ //infinite loop to keep text flickering
            text.setVisible(false); //turns off the text
            try{
                Thread.sleep(500); //wait time 500ms
            }catch(Exception e){
                e.printStackTrace();
            }
            text.setVisible(true); //turns on the text
            try{
                Thread.sleep(500);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        
    }
    
    public void actionPerformed(ActionEvent ae){ //method overriding; here, we write actions to be performed after clicking next button.
        setVisible(false); //closes currrent frame.
        new Login(); //calls login frame, which in turns calls Login cnstrctr. 
        //hence, on clicking next, login page opens up.
    }
    public static void main(String[] args) {
        new HotelManagementSystem();
    }
    
}
