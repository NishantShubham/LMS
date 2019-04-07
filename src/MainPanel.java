import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.text.StyledEditorKit.AlignmentAction;

import java.util.*;


class MainPanel implements Runnable
{	JTabbedPane tabbedPane;
	JPanel panelClock;
	JPanel panel1;
	JPanel panel2;
	JPanel panel3;
	JPanel panel4;
	JPanel panel5;
	JPanel panel6;
	JPanel panel7;
	JPanel panel8;
	JPanel panel9;
	Connection con;
	JLabel labelClock = new JLabel();
	
	
	public final void run()
	{
		while(true)
		{
			labelClock.setText("\t\tThe Date and Time is : " + new java.util.Date());
		}
	}
	
	
	 /*Date dNow = new Date( );
     SimpleDateFormat currentDateIs = new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a zzz");*/
	
	MainPanel() 
	{
		JFrame frame = new JFrame("Library Management System");
		frame.setSize(800, 440);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout( new BorderLayout() );
		frame.add( topPanel );
		
		
		labelClock.setBounds(1,1,1,1);
		
		
		/////////////////////Function declarations for creating the pages
		createPage1();
		createPage2();
		createPage3();
		createPage4();
		createPage5();
		createPage6();
		createPage7();
		createPage8();
		createPage9();
		//createClock();

		 try
			{
				Class.forName("oracle.jdbc.OracleDriver");
				con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","lit");					
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		 
		 Thread th1 = new Thread(this);
			th1.start();
		
		
		////////////////Creating a tabbed pane
		tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Borrowing Books", panel1);				//Done
		tabbedPane.addTab("Transanction Records", panel2);			//Done
		tabbedPane.addTab("Designed By", panel3);					//Done
		tabbedPane.addTab("Important Notes", panel4);				//Done
		tabbedPane.addTab("More Inquiry", panel5);					//Done
		tabbedPane.addTab("Add Members", panel6);					//Done
		tabbedPane.addTab("Add Books", panel7);						//Done
		tabbedPane.addTab("Search", panel8);						//Done
		tabbedPane.addTab("Returning Books", panel9);				//Done
		//panelClock.add(labelClock);
		topPanel.add(tabbedPane, BorderLayout.CENTER);
		topPanel.add(labelClock, BorderLayout.SOUTH);
	}
	
	/*public void createClock()
	{
		panelClock = new JPanel();
		panelClock.setLayout(null);
		JLabel labelClock = new JLabel("The current time is : "+ currentDateIs.format(dNow) );
		labelClock.setBounds(10,15,120,100);
		panelClock.add(labelClock);
	}*/
	
	///////////////////////////////////////////////-------------------Borrowing Books
	
	public void createPage1()
	{
		  panel1 = new JPanel();
		  panel1.setLayout(null);
		  JLabel l1, l2, l3;
		  JTextField tf1, tf2, tf3;
		  JButton btn1;
		  l1 = new JLabel("Enter Borrow Date(dd-MMM-yyyy) :");
		  l2 = new JLabel("Enter Book ID :");
		  l3 = new JLabel("Enter Member ID :");
		  tf1 = new JTextField();
		  tf2 = new JTextField();
		  tf3 = new JTextField();
		  btn1 = new JButton("Borrow Book");
				  
		 
		  l1.setBounds(10, 50, 250, 30);
		  l2.setBounds(10, 140, 200, 30);
		  l3.setBounds(10, 230, 200, 30);
		  tf1.setBounds(400, 5, 380, 100);
		  tf2.setBounds(400, 100, 380, 100);
		  tf3.setBounds(400, 200, 380, 100);
		  btn1.setBounds(1, 310, 779, 30);
		  
		  panel1.add(l1);
		  panel1.add(tf1);
		  panel1.add(l2);
		  panel1.add(tf2);
		  panel1.add(l3);
		  panel1.add(tf3);
		  panel1.add(btn1);
		  
		  btn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ae) 
			{
				String text = ae.getActionCommand();
				try 
				{
					if(text.equals("Borrow Book"))
					{
						//Logic
						String date = (tf1.getText());
						SimpleDateFormat utildate = new SimpleDateFormat("dd-MMM-yyyy");
						java.util.Date date1 = utildate.parse(date);
						java.sql.Date sqlDate = new java.sql.Date(date1.getTime());
						int bookid = Integer.parseInt(tf2.getText());
						int memid = Integer.parseInt(tf3.getText());

						String sql = "insert into lmstransactiontable values (?,?,?)";
						PreparedStatement pst = con.prepareStatement(sql);
						pst.setInt(1,bookid);
						pst.setInt(2,memid);
						pst.setDate(3, sqlDate);

						int status = pst.executeUpdate();
						if(status > 0 )
							JOptionPane.showMessageDialog (null,"Borrow Successful");
						else
							JOptionPane.showMessageDialog (null,"Error! \n Try Again");
						tf1.setText(null);
						tf2.setText(null);
						tf3.setText(null);
						
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
			}
		});
	}

	
	///////////////////////--------------------------Transaction records Panel
	
	public void createPage2()
	{
		panel2 = new JPanel();
		panel2.setLayout(null);
		JLabel label = new JLabel("Enter Member ID :");
		JTextField tf = new JTextField();
		JButton btn = new JButton("Check Transaction");
		

		label.setBounds(1,15,120,20);
		tf.setBounds(1, 45, 779, 200);
		btn.setBounds(1, 250, 779, 30);
		panel2.add(label);
		panel2.add(tf);
		panel2.add(btn);
		
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ae) {
				String text = ae.getActionCommand();
				try
				{	
					if(text.equals("Check Transaction"))
					{
						Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
						String memid = tf.getText();
						String sql = "select bookid, transactiondate from lmstransactiontable where memberid=";
						String sqlstr = sql.concat(memid);
						ResultSet rs = st.executeQuery(sqlstr);
						java.sql.ResultSetMetaData metaData = rs.getMetaData();

					    // names of columns
					    Vector<String> columnNames = new Vector<String>();
					    int columnCount = metaData.getColumnCount();
					    for (int column = 1; column <= columnCount; column++) {
					        columnNames.add(metaData.getColumnName(column));
					    }

					    // data of the table
					    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
					    while (rs.next()) 
					    {
					        Vector<Object> vector = new Vector<Object>();
					        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
					            vector.add(rs.getObject(columnIndex));
					        }
					        data.add(vector);
					    }
					        
					      System.out.println(columnNames);
					      System.out.println("\n\n" + data);
					      
					      JTable table = new JTable(data, columnNames);
					      table.setBounds(150, 150, 250, 100);
					    JOptionPane.showMessageDialog(null, new JScrollPane(table));
						tf.setText(null);
					}
					else 
					{
						JOptionPane.showMessageDialog(null, "Error!"+"\n"+"Try Again.");
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
				
			}
		});
		
		
		
	}
	
	public void createPage3()
	{
		panel3 = new JPanel();
		panel3.setLayout(null);
		JLabel label1 = new JLabel("Designed By : ");
		label1.setBounds(50,20,300,30);
		label1.setFont(new Font("Serif", Font.BOLD, 20));
		JLabel label2 = new JLabel("<html><ul>" + "<li>Nishant Shubham</li>" + "<li>B.Tech ETC, KIIT, 2016-2020</li>" + "<li>Under the guidance of Bishnu Barik Sir</li>" + "</ul></html>");
		label2.setBounds(10, 30, 1200, 100);
		panel3.add(label1);
		panel3.add(label2);
		
	}
	
	public void createPage4()
	{
		panel4 = new JPanel();
		panel4.setLayout(null);
		JLabel label1 = new JLabel("Important Notes : ");
		label1.setBounds(50,20,300,30);
		label1.setFont(new Font("Serif", Font.BOLD, 20));
		JLabel label2 = new JLabel("Below are the point to be taken care of while using this system.");
		label2.setBounds(50, 50, 1200, 30);
		JLabel label3 = new JLabel("<html><ul>" + "<li>If the book was borrowed previous month, for entering the return date please calculate and enter the number of days</li>"
				+ " the book has been with the member" + "<li>Ex. Borrowed date - 25th February</li>" + "<li>Return Date - 15th March</li>" + "<li>Number of days in between - (28-25) + 15 = 18 Days</li>" + "</ul></html>");
		label3.setBounds(10, 70, 1200, 100);
		panel4.add(label1);
		panel4.add(label2);
		panel4.add(label3);
	}
	
	////////////-----------------More Inquiry
	
	public void createPage5()
	{
		panel5 = new JPanel();
		panel5.setLayout(null);
		JLabel label1 = new JLabel("Book Details :");
		JLabel label2 = new JLabel("Member Details :");
		JButton btn1, btn2;
		btn1 = new JButton("Book Details");
		btn2 = new JButton("Member Details");
		
				
		label1.setBounds(2,5,120,20);
		label2.setBounds(2,180,120,20);
		btn1.setBounds(1, 35, 779, 30);
		btn2.setBounds(1, 210, 779, 30);
		panel5.add(label1);
		panel5.add(label2);
		panel5.add(btn1);
		panel5.add(btn2);
		
		
		btn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ae)
			{ String text = ae.getActionCommand();
				try
				{
					if(text.equals("Book Details"))
						{
							Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
							String sql = "select bookname, bookauthor,bookid,bookpublisher, booktype, bookyear from lmsbooktable";
							ResultSet rs = st.executeQuery(sql);

							java.sql.ResultSetMetaData metaData = rs.getMetaData();

						    // names of columns
						    Vector<String> columnNames = new Vector<String>();
						    int columnCount = metaData.getColumnCount();
						    for (int column = 1; column <= columnCount; column++) {
						        columnNames.add(metaData.getColumnName(column));
						    }

						    // data of the table
						    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
						    while (rs.next()) 
						    {
						        Vector<Object> vector = new Vector<Object>();
						        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
						            vector.add(rs.getObject(columnIndex));
						        }
						        data.add(vector);
						    }
						        
						      System.out.println(columnNames);
						      System.out.println("\n\n" + data);
						      
						      JTable table = new JTable(data, columnNames);
						      table.setBounds(150, 150, 250, 100);
						    JOptionPane.showMessageDialog(null, new JScrollPane(table));

							
						}
					else
					{
						JOptionPane.showMessageDialog(null, "Error"+"\n"+"Try again.");
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
				
			}

		});
		
		btn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ae) {
				String text = ae.getActionCommand();
				try
				{
					if(text.equals("Member Details"))
					{
						Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
						String sql = "select membername, memberage, memberid , membergender , memberaddress, memberphone from lmsmembertable";
						ResultSet rs = st.executeQuery(sql);
						java.sql.ResultSetMetaData metaData = rs.getMetaData();

					    // names of columns
					    Vector<String> columnNames = new Vector<String>();
					    int columnCount = metaData.getColumnCount();
					    for (int column = 1; column <= columnCount; column++) {
					        columnNames.add(metaData.getColumnName(column));
					    }

					    // data of the table
					    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
					    while (rs.next()) 
					    {
					        Vector<Object> vector = new Vector<Object>();
					        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
					            vector.add(rs.getObject(columnIndex));
					        }
					        data.add(vector);
					    }
					        
					      System.out.println(columnNames);
					      System.out.println("\n\n" + data);
					      
					      JTable table = new JTable(data, columnNames);
					      table.setBounds(100, 100, 150, 100);
					    JOptionPane.showMessageDialog(null, new JScrollPane(table));
						
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Error"+"\n"+"Try again.");
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});
		
	}
	
	
	//////////////////////////////////----------Adding Member Function
	
	public void createPage6()
	{	
		  panel6 = new JPanel();
		  panel6.setLayout(null);
		  JLabel l1, l2, l3, l4, l5, l6;
		  JTextField tf1, tf2, tf3, tf4, tf5, tf6;
		  JButton btn1;
		  l1 = new JLabel("Member Name :");
		  l2 = new JLabel("Member Age :");
		  l3 = new JLabel("Member ID :");
		  l4 = new JLabel("Member Gender(M/F) :");
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
		  tf1.setBounds(400, 20, 380, 30);
		  tf2.setBounds(400, 70, 380, 30);
		  tf3.setBounds(400, 120, 380, 30);
		  tf4.setBounds(400, 170, 380, 30);
		  tf5.setBounds(400, 220, 380, 30);
		  tf6.setBounds(400, 270, 380, 30);
		  btn1.setBounds(1, 310, 779, 30);
		  
		  panel6.add(l1);
		  panel6.add(tf1);
		  panel6.add(l2);
		  panel6.add(tf2);
		  panel6.add(l3);
		  panel6.add(tf3);
		  panel6.add(l4);
		  panel6.add(tf4);
		  panel6.add(l5);
		  panel6.add(tf5);
		  panel6.add(l6);
		  panel6.add(tf6);
		  panel6.add(btn1);
		  
		  btn1.addActionListener(new ActionListener() {

			    @Override
			    public void actionPerformed(ActionEvent ae) 
			    {
			    	String text = ae.getActionCommand();
			    	
					try
					{
						
						System.out.println(text);
						if(text.equals("Create new Member"))
						{
							// Logic to insert the record
							String mname= tf1.getText();
							int mage = Integer.parseInt(tf2.getText());
							int mid = Integer.parseInt(tf3.getText());
							String mgender = tf4.getText();
							String maddress = tf5.getText();
							Double mphone = Double.parseDouble(tf6.getText());

							String sql = "Insert into lmsmembertable values (?,?,?,?,?,?)";
							PreparedStatement pst = con.prepareStatement(sql);
							pst.setString(1,mname);
							pst.setInt(2,mage);
							pst.setInt(3,mid);
							pst.setString(4,mgender);
							pst.setString(5,maddress);
							pst.setDouble(6,mphone);

							int status = pst.executeUpdate();
							if(status > 0 )
								JOptionPane.showMessageDialog (null,"Member Added Succesfully.");
							else
								JOptionPane.showMessageDialog (null,"Error!");
							
							tf1.setText(null);
							tf2.setText(null);
							tf3.setText(null);
							tf4.setText(null);
							tf5.setText(null);
							tf6.setText(null);

						}
					}	
						catch(Exception e)
						{
							e.printStackTrace();
						}
			    }
			});
		  
		  
	}
	
	
	////////////////////////////////----------Adding Books Function
	
	
	public void createPage7()
	{
		panel7 = new JPanel();
		  panel7.setLayout(null);
		  JLabel l1, l2, l3, l4, l5, l6;
		  JTextField tf1, tf2, tf3, tf4, tf5, tf6;
		  JButton btn1;
		  l1 = new JLabel("Book Name :");
		  l2 = new JLabel("Book Author :");
		  l3 = new JLabel("Book ID :");
		  l4 = new JLabel("Publisher Name :");
		  l5= new JLabel("Book Type(Genre) : ");
		  l6 = new JLabel("Year : ");
		  tf1 = new JTextField();
		  tf2 = new JTextField();
		  tf3 = new JTextField();
		  tf4 = new JTextField();
		  tf5 = new JTextField();
		  tf6 = new JTextField();
		  btn1 = new JButton("Add new Book");
				  
		 
		  l1.setBounds(10, 20, 200, 30);
		  l2.setBounds(10, 70, 200, 30);
		  l3.setBounds(10, 120, 200, 30);
		  l4.setBounds(10, 170, 400, 30);
		  l5.setBounds(10, 220, 400, 30);
		  l6.setBounds(10, 270, 400, 30);
		  tf1.setBounds(400, 20, 380, 30);
		  tf2.setBounds(400, 70, 380, 30);
		  tf3.setBounds(400, 120, 380, 30);
		  tf4.setBounds(400, 170, 380, 30);
		  tf5.setBounds(400, 220, 380, 30);
		  tf6.setBounds(400, 270, 380, 30);
		  btn1.setBounds(1, 310, 779, 30);
		  
		  panel7.add(l1);
		  panel7.add(tf1);
		  panel7.add(l2);
		  panel7.add(tf2);
		  panel7.add(l3);
		  panel7.add(tf3);
		  panel7.add(l4);
		  panel7.add(tf4);
		  panel7.add(l5);
		  panel7.add(tf5);
		  panel7.add(l6);
		  panel7.add(tf6);
		  panel7.add(btn1);
		  
		  btn1.addActionListener(new ActionListener() {

			    @Override
			    public void actionPerformed(ActionEvent ae) 
			    {
			    	String text = ae.getActionCommand();
					try
					{
						
						System.out.println(text);
						if(text.equals("Add new Book"))
						{
							// Logic to insert the record
							String bname= tf1.getText();
							String bauthor = tf2.getText();
							int bid = Integer.parseInt(tf3.getText());
							String bpublisher = tf4.getText();
							String btype = tf5.getText();
							int byear = Integer.parseInt(tf6.getText());

							String sql = "Insert into lmsbooktable values (?,?,?,?,?,?)";
							PreparedStatement pst = con.prepareStatement(sql);
							pst.setString(1,bname);
							pst.setString(2,bauthor);
							pst.setInt(3,bid);
							pst.setString(4,bpublisher);
							pst.setString(5,btype);
							pst.setInt(6,byear);

							int status = pst.executeUpdate();
							if(status > 0 )
								JOptionPane.showMessageDialog (null,"Book Added Succesfully.");
							else
								JOptionPane.showMessageDialog (null,"Error!");
							
							tf1.setText(null);
							tf2.setText(null);
							tf3.setText(null);
							tf4.setText(null);
							tf5.setText(null);
							tf6.setText(null);

						}
					}	
						catch(Exception e)
						{
							e.printStackTrace();
						}
			    }
			});
	}
	
	/////////////////////////////---------------Search Tab 
	
	public void createPage8()
	{
		  panel8 = new JPanel();
		  panel8.setLayout(null);
		  JLabel l1, l2, l3, l4;
		  JTextField tf1, tf2, tf3, tf4;
		  JButton btn1, btn2, btn3, btn4;
		  l1 = new JLabel("Enter Book ID :");
		  l2 = new JLabel("Enter Book Name :");
		  l3 = new JLabel("Enter Member ID :");
		  l4 = new JLabel("Enter Member Name :");
		  tf1 = new JTextField();
		  tf2 = new JTextField();
		  tf3 = new JTextField();
		  tf4 = new JTextField();
		  btn1 = new JButton("Search for Book using ID");
		  btn2 = new JButton("Search for Book using Name");
		  btn3 = new JButton("Search for Member using ID");
		  btn4 = new JButton("Search for Member using Name");
				  
		 
		  l1.setBounds(5, 20, 200, 30);
		  tf1.setBounds(5, 60-15, 380, 30);
		  btn1.setBounds(5, 90-15, 380, 30);
		  l2.setBounds(400-15, 20, 200, 30);
		  tf2.setBounds(400-15, 60-15, 395, 30);
		  btn2.setBounds(400-15, 90-15, 395, 30);
		  l3.setBounds(5, 175, 200, 30);
		  tf3.setBounds(5, 200, 380, 30);
		  btn3.setBounds(5, 230, 380, 30);
		  l4.setBounds(400-15, 175, 200, 30);
		  tf4.setBounds(400-15, 200, 400-5, 30);
		  btn4.setBounds(400-15, 230, 395, 30);
		  
		  panel8.add(l1);
		  panel8.add(tf1);
		  panel8.add(btn1);
		  panel8.add(l2);
		  panel8.add(tf2);
		  panel8.add(btn2);
		  panel8.add(l3);
		  panel8.add(tf3);
		  panel8.add(btn3);
		  panel8.add(l4);
		  panel8.add(tf4);
		  panel8.add(btn4);
		  
	
		  btn1.addActionListener(new ActionListener() {

			    @Override
			    public void actionPerformed(ActionEvent ae) 
			    {
			    	String text = ae.getActionCommand();
					try
					{
						//-----------------Search Logic
						System.out.println(text);
						if(text.equals("Search for Book using ID"))
						{
							Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
							String bid = tf1.getText();
							String sql = "select bookname,bookauthor,bookid,bookpublisher,booktype,bookyear from lmsbooktable where bookid=";
							String sqlstr = sql.concat(bid);
							ResultSet rs = st.executeQuery(sqlstr);
							while(rs.next())
							{	
								String name = rs.getString(1);
								String author = rs.getString(2);
								String id = rs.getString(3);
								String publisher = rs.getString(4);
								String type = rs.getString(5);
								String year = rs.getString(6);
								JOptionPane.showMessageDialog (null,"The details are :"+"\n"+"Book Name : "+name+"\n" + "Book Author : "+author+"\n"+"Book ID : "+id+"\n"+"Publisher : "+publisher+"\n"+"Type(Genre) : "+type+"\n"+"Year : "+year);
							}	

						}
						else
						{
							JOptionPane.showMessageDialog(null, "Error! Try again.");
						}
						
						tf1.setText(null);
						tf2.setText(null);
						tf3.setText(null);
						tf4.setText(null);
					}	
						catch(Exception e)
						{
							e.printStackTrace();
						}
			    }
			});
		  btn2.addActionListener(new ActionListener() {

			    @Override
			    public void actionPerformed(ActionEvent ae) 
			    {
			    	String text = ae.getActionCommand();
					try
					{
						//-----------------Search Logic
						System.out.println(text);
						
						if(text.equals("Search for Book using Name"))
						{
							Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
							String bname = tf2.getText();
							bname = "'"+bname+"'";
							String sql = "select bookname,bookauthor,bookid,bookpublisher,booktype,bookyear from lmsbooktable where bookname=";
							String sqlstr = sql+bname;
							System.out.println(sqlstr);
							ResultSet rs = st.executeQuery(sql);
							while(rs.next())
							{	
								String name = rs.getString(1);
								String author = rs.getString(2);
								String id = rs.getString(3);
								String publisher = rs.getString(4);
								String type = rs.getString(5);
								String year = rs.getString(6);
								JOptionPane.showMessageDialog (null,"The details are :"+"\n"+"Book Name : "+name+"\n" + "Book Author : "+author+"\n"+"Book ID : "+id+"\n"+"Publisher : "+publisher+"\n"+"Type(Genre) : "+type+"\n"+"Year : "+year);
							}	
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Error! Try again.");
						}
						
						tf1.setText(null);
						tf2.setText(null);
						tf3.setText(null);
						tf4.setText(null);
					}	
						catch(Exception e)
						{
							e.printStackTrace();
						}
			    }
			});
		  btn3.addActionListener(new ActionListener() {

			    @Override
			    public void actionPerformed(ActionEvent ae) 
			    {
			    	String text = ae.getActionCommand();
					try
					{
						//-----------------Search Logic
						System.out.println(text);
						
						if(text.equals("Search for Member using ID"))
						{
							String mid = tf3.getText();
							Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
							String sql = "select MEMBERNAME,MEMBERAGE,MEMBERID,MEMBERGENDER,MEMBERADDRESS,MEMBERPHONE from lmsmembertable where memberid=";
							String sqlstr = sql + mid;
							ResultSet rs = st.executeQuery(sqlstr);
							while(rs.next())
							{	
								String name = rs.getString(1);
								int age = rs.getInt(2);
								int id = rs.getInt(3);
								String gender = rs.getString(4);
								String address = rs.getString(5);
								String phone = rs.getString(6);
								JOptionPane.showMessageDialog (null,"The details are :"+"\n"+"Name : "+name+"\n" + "Age : "+age+"\n"+"ID : "+id+"\n"+"Gender : "+gender+"\n"+"Address : "+address+"\n"+"Phone : "+phone);
							}	
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Error! Try again.");
						}
						
						tf1.setText(null);
						tf2.setText(null);
						tf3.setText(null);
						tf4.setText(null);
					}	
						catch(Exception e)
						{
							e.printStackTrace();
						}
			    }
			});
		  btn4.addActionListener(new ActionListener() {

			    @Override
			    public void actionPerformed(ActionEvent ae) 
			    {
			    	String text = ae.getActionCommand();
					try
					{
						//-----------------Search Logic
						System.out.println(text);
						
						if(text.equals("Search for Member using Name"))
						{
							Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
							String mname = tf4.getText();
							mname = "'"+mname+"'";
							String sql = "select MEMBERNAME,MEMBERAGE,MEMBERID,MEMBERGENDER,MEMBERADDRESS,MEMBERPHONE from lmsmembertable where membername=";
							String sqlstr = sql + mname;
							ResultSet rs = st.executeQuery(sqlstr);
							while(rs.next())
							{	
								String name = rs.getString(1);
								int age = rs.getInt(2);
								int id = rs.getInt(3);
								String gender = rs.getString(4);
								String address = rs.getString(5);
								String phone = rs.getString(6);
								JOptionPane.showMessageDialog (null,"The details are :"+"\n"+"Name : "+name+"\n" + "Age : "+age+"\n"+"ID : "+id+"\n"+"Gender : "+gender+"\n"+"Address : "+address+"\n"+"Phone : "+phone);
							}	

						}
						else
						{
							JOptionPane.showMessageDialog(null, "Error! Try again.");
						}
						
						tf1.setText(null);
						tf2.setText(null);
						tf3.setText(null);
						tf4.setText(null);
						
					}	
						catch(Exception e)
						{
							e.printStackTrace();
						}
			    }
			});
		  
	}
	
	///////////////////------------------Returning Books
	
	public void createPage9()
	{
		panel9 = new JPanel();
		panel9.setLayout(null);
		JLabel l1, l2, l3;
		  JTextField tf1, tf2;
		  JButton btn1;
		  l1 = new JLabel("Please enter the date correctly to calculate fees. See Important Notes for more details :");
		  l2 = new JLabel("Enter Return Date(dd-MMM-yyyy) :");
		  l3 = new JLabel("Enter Book ID :");
		  tf1 = new JTextField();
		  tf2 = new JTextField(); 
		  btn1 = new JButton("Return Book");
				  
		 
		  l1.setBounds(5, 1, 1200, 30);
		  l2.setBounds(10, 100, 250, 30);
		  l3.setBounds(10, 215, 200, 30);
		  tf1.setBounds(400,35, 380, 135);
		  tf2.setBounds(400, 170, 380, 140);
		  btn1.setBounds(1, 310, 779, 30);
		  
		  panel9.add(l1);
		  panel9.add(l2);
		  panel9.add(tf1);
		  panel9.add(l3);
		  panel9.add(tf2);
		  panel9.add(btn1);
		  
		  btn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ae) 
			{
				String text = ae.getActionCommand();
				try 
				{
					if(text.equals("Return Book"))
					{
						//Logic
						long fine=0;
						String date = (tf1.getText());
						SimpleDateFormat utildate = new SimpleDateFormat("dd-MMM-yyyy");
						java.util.Date date1 = utildate.parse(date);
						java.sql.Date sqlDate = new java.sql.Date(date1.getTime());
						String bookid = tf2.getText();
						Date present = new Date();
						long datediff = ((present.getTime()) - (date1.getTime()))/(60*60*24*1000);
						if(datediff>30)
							fine = (datediff-30)*5;
						String sql = "delete from lmstransactiontable where transactiondate=(?) and bookid=(?)";
						PreparedStatement pst = con.prepareStatement(sql);
						pst.setDate(1, sqlDate);
						pst.setString(2,bookid);
						int status = pst.executeUpdate();
						if(status > 0 )
							{JOptionPane.showMessageDialog (null,"Book Returned Succesfully!");
								if(fine > 0)
									JOptionPane.showMessageDialog(null, "The fine is : " + fine);
							}
						
						
						
						else
							JOptionPane.showMessageDialog (null,"Error! \n Try Again");
						
						tf1.setText(null);
						tf2.setText(null);
						
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
			}
		});
	}
	
	public static void main(String args[])
	{
		
		new MainPanel();
		
	}
}
