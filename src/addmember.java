import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.*;
import javax.swing.*;

public class addmember extends JFrame implements ActionListener
{	JLabel l1, l2, l3, l4, l5, l6;
	JTextField tf1, tf2, tf3, tf4, tf5, tf6;
	JButton btn1;
	Connection con;
	
	addmember()
	{
		  JFrame frame = new JFrame("Add Member");
		  
		  l1 = new JLabel("Member Name :");
		  l2 = new JLabel("Member Age :");
		  l3 = new JLabel("Member ID :");
		  l4 = new JLabel("Member Type(M/F)");
		  l5= new JLabel("Member Address : ");
		  l6 = new JLabel("Member Phone : ");
		  tf1 = new JTextField();
		  tf2 = new JTextField();
		  tf3 = new JTextField();
		  tf4 = new JTextField();
		  tf5 = new JTextField();
		  tf6 = new JTextField();
		  btn1 = new JButton("Create new Member");
				  
		 
		  l1.setBounds(10, 20, 200, 30);
		  l2.setBounds(10, 70, 200, 30);
		  l3.setBounds(10, 120, 200, 30);
		  l4.setBounds(10, 170, 400, 30);
		  l5.setBounds(10, 220, 400, 30);
		  l6.setBounds(10, 270, 400, 30);
		  tf1.setBounds(400, 20, 400, 30);
		  tf2.setBounds(400, 70, 400, 30);
		  tf3.setBounds(400, 120, 400, 30);
		  tf4.setBounds(400, 170, 400, 30);
		  tf5.setBounds(400, 220, 400, 30);
		  tf6.setBounds(400, 270, 400, 30);
		  btn1.setBounds(350, 450, 100, 30);
		  
		  frame.add(l1);
		  frame.add(tf1);
		  frame.add(l2);
		  frame.add(tf2);
		  frame.add(l3);
		  frame.add(tf3);
		  frame.add(l4);
		  frame.add(tf4);
		  frame.add(l5);
		  frame.add(tf5);
		  frame.add(l6);
		  frame.add(tf6);
		  frame.add(btn1);
		  

		  frame.setSize(800, 400);
		  frame.setLayout(null);
		  frame.setVisible(true);
		  
		  try
			{
				Class.forName("oracle.jdbc.OracleDriver");
				con = DriverManager.getConnection
				("jdbc:oracle:thin:@localhost:1521:xe","system","lit");					
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}

			btn1.addActionListener(this);
		
	}
	
////////////////////////////////////////////////////////////////////////////////
	
	
	
	public void actionPerformed(ActionEvent ae)
	 {
		String text = ae.getActionCommand();
		try
		{
			
			System.out.println(text);
			if(text.equals("Create new Member"))
			{
				// Logic to insert the record
				int eid = Integer.parseInt(tf1.getText());
				String ename= tf2.getText();
				double salary = Double.parseDouble(tf3.getText());

				String sql = "Insert into lms values (?,?, ?)";
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setInt(1,eid);
				pst.setString(2,ename);
				pst.setDouble(3,salary);

				int status = pst.executeUpdate();
				if(status > 0 )
					JOptionPane.showMessageDialog (null,"Yes Inserted ..");
				else
					JOptionPane.showMessageDialog (null,"Error in insertion ..");

			}
		}	
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}

	
///////////////////////////////////////////////////////////////////////////////
	
	public static void main(String args[]) throws Exception
	{
		new addmember();
	}
}