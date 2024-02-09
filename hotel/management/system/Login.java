
package hotel.management.system;
import javax.swing.*;
import java.awt.*; 
import java.awt.event.*; //for click event
import java.sql.*; //has ResultSet function.

public class Login extends JFrame implements ActionListener{ 
    
    JTextField username; //needs to be declared here globally to access it from actionPerformed.
    JPasswordField password;  //special object just for PWs, so that they ain't visible while typing.
    JButton login, cancel;
    
    Login(){ //cnstrctr
        getContentPane().setBackground(Color.WHITE); //sets clr of whole frame to white.
        setLayout(null);
        JLabel user = new JLabel("Username");
        user.setBounds(40, 20, 100, 30); // margin & dimensions of 'user'.
        add(user);
        
         username = new JTextField(); //creates empty text box for username to be entered.
        username.setBounds(150, 20, 150, 30);
        add(username);
        
        JLabel pass = new JLabel("Password");
        pass.setBounds(40, 70, 100, 30); // margin & dimensions of 'user'.
        add(pass);
        
         password = new JPasswordField(); //creates empty text box for password to be entered.
        password.setBounds(150, 70, 150, 30);
        add(password);
        
         login = new JButton("Login"); // creates login button.
        login.setBounds(40, 150, 120, 30);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        login.addActionListener(this); //to call the button on which click event has to be made.
        add(login);
        
         cancel = new JButton("Cancel"); // creates login button.
        cancel.setBounds(180, 150, 120, 30);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        add(cancel);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg")); 
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT); //crops i1 and puts cropped img in i2.
        ImageIcon i3 = new ImageIcon(i2); //create img icon i3 & put i2 in it, so that we can i2 in below line.
        JLabel image = new JLabel(i3);  //i2 always has to be img icon type.
        image.setBounds(350, 10, 200, 200); 
        add(image);
        
        setBounds(500, 200, 600, 300); // margin & dimensions of login frame.
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){ //method override done.
        if(ae.getSource() == login){ //if login button clicked
            String user = username.getText(); //stores the input of username in user string.
            String pass = password.getText(); //stores the input of password in pass string.
            
            try{
                Conn c = new Conn(); //make object c of connection class(c).
                
                String query = "select * from login where username = '" + user + "' and password = '" + pass + "'"; //wrote sql query.
                
                ResultSet rs = c.s.executeQuery(query); //Query 'query' gets executed with help of statement s & result(data) stored in rs; returns null if no data found.
                
                if(rs.next()){ //if entered username & pw match with db
                    setVisible(false); //closes login frame.
                    new Dashboard(); //calls cnstrctr of Dashboard cls and hence dashboard frame opens.
                }else{
                    JOptionPane.showMessageDialog(null, "Invalid username or password"); //displays this dialog box with message if invalid input.
                    setVisible(false); //closes login frame.
                }
                
            }catch(Exception e){
                e.printStackTrace();
            }
            
        }else if(ae.getSource() == cancel){ //if cancel button clicked
            setVisible(false);
        }
    }
    public static void main(String[] args){
    new Login();
}
}

