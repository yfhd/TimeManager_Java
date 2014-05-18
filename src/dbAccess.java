
import java.sql.*;
import java.util.Iterator;

import javax.swing.JOptionPane;

import org.eclipse.swt.widgets.MessageBox;

public class dbAccess {
	private static String strConnect =
			"jdbc:odbc:DRIVER=Microsoft Access Driver (*.mdb, *.accdb);DBQ=d:\\application\\GeekerRecord.accdb;PWD=1L0v3Acce55;";
	
	public static long getTotalTime(String strItem){
		long time = 0;
		
		try{
			String driverName="oracle.jdbc.driver.OracleDriver";
			String url="jdbc:oracle:thin:@192.168.178.31:1521/ge01";
			String strTime;
			
			Class.forName(driverName);
			Connection conn = DriverManager.getConnection(url,"scott","agit1234");
			 
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("Select * from Activity_Time where Activity_Name = '" + strItem +"'");
			
			while(rs.next()){
				strTime = rs.getString("Elapse_Time");
				String[] arr = strTime.split(":");
				
				time += Long.parseLong(arr[0])*3600 + Long.parseLong(arr[1])*60 + Long.parseLong(arr[2]);
			}
			
			// JOptionPane.showMessageDialog(null, "Insert to DB successfully");
			System.out.println("Succes!");
		}
		catch(Exception e){
			System.out.println(e.toString());
			System.out.println("Fail!");
			// JOptionPane.showMessageDialog(null, "Fail to insert to DB: " + e.toString());
		}
		
		return time;
	}
	
	public  static boolean insert2DB(String stat){
		try{
			String driverName="oracle.jdbc.driver.OracleDriver";
			String url="jdbc:oracle:thin:@192.168.178.31:1521/ge01";
			Class.forName(driverName);
			Connection conn = DriverManager.getConnection(url,"scott","agit1234");
			 
			Statement stmt = conn.createStatement();
			System.out.println(stat);
			stmt.executeQuery(stat);
			JOptionPane.showMessageDialog(null, "Insert to DB successfully");
			System.out.println("Succes!");
		}
		catch(Exception e){
			System.out.println(e.toString());
			System.out.println("Fail!");
			JOptionPane.showMessageDialog(null, "Fail to insert to DB: " + e.toString());
			
			return false;
		}
		
		return true;
	}
	
	public static boolean connOracle(){
		try{
			String driverName="oracle.jdbc.driver.OracleDriver";
			String url="jdbc:oracle:thin:@192.168.178.31:1521/ge01";
			Class.forName(driverName);
			Connection conn = DriverManager.getConnection(url,"scott","agit1234");
			System.out.println("Succes!");
		 
			String sql="select * from emp";
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			  
			while(rs.next())
			{
				System.out.println(rs.getString("eName") + " " + rs.getString("job") + " " + rs.getString("sal"));
			}
			 
			System.out.println("Success!");  
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		    System.out.println("Fail!");
		}
		 
		return true;
	}
	
	public static boolean insertRecord2DB(String strStatement){
		String strInfo = "";
		boolean flag;
		
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con = DriverManager.getConnection(strConnect, "","");
			
			if (null == con) {
			   System.out.println("Unable to connect to data source " + strConnect);
			   return false;
			}
				   
			System.out.println("Successfully connected to database. Data source name:\n  " 
			+ con.getMetaData().getURL());
				   
            Statement s = con.createStatement();
            
            s.execute(strStatement);
            s.close();
            con.close();
            strInfo = "Successfully insert record into table Activity_Time!";
            flag = true;
        }
        catch(Exception ex){
        	strInfo = "Failed to insert record into table Activity_Time!";
            ex.printStackTrace();
            flag = false;
        }
        
		JOptionPane.showMessageDialog(null, strInfo, 
                "Information Message ",JOptionPane.INFORMATION_MESSAGE); 
        return flag;
	}
	
	public long inquiry(String str){
		String strInfo = "";
		ResultSet rs = null;
		long totalSeconds = 0;
		
		try{
			Driver d = (Driver)Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
            Connection conn = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=E:\\Career\\Computer Science\\Database\\Access\\GeekerRecord.accdb");
            Statement s = conn.createStatement();
            String str_time;
            long seconds = 0;
            
            rs = s.executeQuery(str);
           
            if(rs != null){
				while(rs.next()){	
					str_time = rs.getString("Elapse_Time");
					seconds = Integer.parseInt(str_time.substring(12, 13))*3600;
					totalSeconds += seconds;
					seconds = Integer.parseInt(str_time.substring(15, 16))*60;
					totalSeconds += seconds;
					seconds = Integer.parseInt(str_time.substring(18, 19));
					totalSeconds += seconds;
				}
			}
			
            s.close();
            conn.close();
            strInfo = "Successfully inquery records from table Activity_Time!";
        }
        catch(Exception ex){
        	strInfo = "Failed to inquery records from table Activity_Time!";
            ex.printStackTrace();
        }
        
		JOptionPane.showMessageDialog(null, strInfo, 
                "Information Message ",JOptionPane.INFORMATION_MESSAGE); 
        return totalSeconds;
	}
}
