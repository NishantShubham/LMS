import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;


public class rstest 
{
	public static void main(String args[]) throws Exception
	{
		Connection con = null;
		try 
		{
			Class.forName("oracle.jdbc.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
					"system","lit");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String idi = "1";
		Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String sql = "select MEMBERNAME,MEMBERAGE,MEMBERID,MEMBERGENDER,MEMBERADDRESS,MEMBERPHONE from lmsmembertable where memberid=";
		String sqlstr = sql + idi;
		System.out.println(sqlstr);
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
	      table.setBounds(100, 100, 100, 100);
	      JScrollPane sp = new JScrollPane(table);
		
		/*while(rs.next())
		{	
			String name = rs.getString(1);
			int age = rs.getInt(2);
			int id = rs.getInt(3);
			String gender = rs.getString(4);
			String address = rs.getString(5);
			String phone = rs.getString(6);
			//String phone1 = Double.toString(phone);
			System.out.println("\n"+name+"\t" +age+"\t" +id+"\t" +gender+"\t" +address+"\t" +phone);
		}*/
	}
}
