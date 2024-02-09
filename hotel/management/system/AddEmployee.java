
package hotel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class AddEmployee extends JFrame implements ActionListener{
    
    JTextField tfname, tfage, tfsalary, tfphone, tfemail, tfadhar; //needs to be declared here globally to access it from actionPerformed.
    JRadioButton male, female; 
    JButton submit;
    JComboBox cbjob;
    
    AddEmployee(){
        setLayout(null);
        
        JLabel name = new JLabel("NAME"); //creates label - NAME
        name.setBounds(60, 30, 120, 30);
        name.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(name);
        
         tfname = new JTextField(); //creates empty box in front of NAME.
        tfname.setBounds(200, 30, 150, 30);
        add(tfname);
        
        JLabel age = new JLabel("AGE"); //creates label - AGE
        age.setBounds(60, 80, 120, 30);
        age.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(age);
        
        tfage = new JTextField(); //creates empty box in front of AGE.
        tfage.setBounds(200, 80, 150, 30);
        add(tfage);
        
        JLabel gender = new JLabel("GENDER"); //creates label - GENDER
        gender.setBounds(60, 130, 120, 30);
        gender.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(gender);
        
         male = new JRadioButton("Male"); //creates radio button for male.
        male.setBounds(200, 130, 70, 30);
        male.setFont(new Font("Tahoma", Font.PLAIN, 14));
        male.setBackground(Color.WHITE);
        add(male);
        
         female = new JRadioButton("Female"); //creates radio button for female.
        female.setBounds(280, 130, 70, 30);
        female.setFont(new Font("Tahoma", Font.PLAIN, 14));
        female.setBackground(Color.WHITE);
        add(female);
        
        ButtonGroup bg = new ButtonGroup(); // grouping radio buttons means any 1 of male/female gets selected at a time.
        bg.add(male);
        bg.add(female);
        
        JLabel job = new JLabel("JOB"); //creates label - JOB
        job.setBounds(60, 180, 120, 30);
        job.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(job);
        
        String s[] = {"Receptionist", "Porter", "Kitchen staff", "Housekeeping", "Room service", "Chef", "Waiter", "Manager", "Accountant"}; //these options should be in drop down list of 'job'. 
         cbjob = new JComboBox(s); //creates drop down list with all options from string s.
        cbjob.setBounds(200, 180, 150, 30);
        cbjob.setBackground(Color.WHITE);
        add(cbjob);
        
        JLabel salary = new JLabel("SALARY"); //creates label - SALARY
        salary.setBounds(60, 230, 120, 30);
        salary.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(salary);
        
         tfsalary = new JTextField(); //creates empty box in front of SALARY.
        tfsalary.setBounds(200, 230, 150, 30);
        add(tfsalary);
        
        JLabel phone = new JLabel("PHONE"); //creates label - PHONE
        phone.setBounds(60, 280, 120, 30);
        phone.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(phone);
        
         tfphone = new JTextField(); //creates empty box in front of PHONE.
        tfphone.setBounds(200, 280, 150, 30);
        add(tfphone);
        
        JLabel email = new JLabel("E-MAIL"); //creates label - EMAIL
        email.setBounds(60, 330, 120, 30);
        email.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(email);
        
         tfemail = new JTextField(); //creates empty box in front of EMAIL.
        tfemail.setBounds(200, 330, 150, 30);
        add(tfemail);
        
        JLabel adhar = new JLabel("AADHAR NO"); //creates label - AADHAR no
        adhar.setBounds(60, 380, 120, 30);
        adhar.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(adhar);
        
         tfadhar = new JTextField(); //creates empty box in front of AADHAR no
        tfadhar.setBounds(200, 380, 150, 30);
        add(tfadhar);
        
         submit = new JButton("SUBMIT"); //adds button - SUBMIT
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.setBounds(200, 430, 150, 30);
        submit.addActionListener(this); //to call the button on which click event has to be made.
        add(submit);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/tenth.jpg"));
        Image i2 = i1.getImage().getScaledInstance(450, 450, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(380, 60, 450, 370);
        add(image);
        
        getContentPane().setBackground(Color.WHITE); //regarding frame
        setBounds(350, 200, 850, 540);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        String name = tfname.getText();
        String age = tfage.getText();
        String salary = tfsalary.getText();
        String phone = tfphone.getText();
        String email = tfemail.getText();
        String adhar = tfadhar.getText();
        
        String gender = null;
        
        if(male.isSelected()) 
           gender = "Male"; //if admin selects 'male', then 'Male' goes in 'gender' string.
        else if(female.isSelected())
           gender = "Female"; //if admin selects 'female', then 'Female' goes in 'gender' string.
        
        String job = (String)cbjob.getSelectedItem(); //whichever option is selected from drop down list, goes in 'job'; typecasted to string as getSelectedItem() returns an object.
    
    try{
        Conn con = new Conn();
        
        String query = "insert into employee values('" + name + "', '" + age + "', '" + gender + "', '" + job + "', '" + salary + "', '" + phone + "', '" + email + "', '" + adhar + "')";
        
        con.s.executeUpdate(query); //inserting data means updating db, hence it won't return anything.
        
        JOptionPane.showMessageDialog(null, "Employee added successfully"); //confirmation msg after employee added.
        
        setVisible(false);
        
    }catch(Exception e){
        e.printStackTrace();
    }
    }
    
    
    
    public static void main(String args[]){
        new AddEmployee(); //calling cnstrctr in main fnc to execute.
    }
}
