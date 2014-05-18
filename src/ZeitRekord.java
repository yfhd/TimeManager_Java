import javax.lang.model.element.ExecutableElement;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;

import org.eclipse.swt.widgets.MessageBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.sql.*;

public class ZeitRekord extends JPanel {
	private JTextField tf_Date;
	private JTextField tf_StartTime;
	private JTextField tf_EndTime;
	private JTextField tf_ElapseTime;
	private JComboBox<String> cb_Activity;
	private Timer timer;
	
	public final static int ONE_SECOND = 1000;
	private final static int interval = ONE_SECOND*60;
	/**
	 * Create the panel.
	 */
	void paint(){	
	}
	
	class RemindTask extends TimerTask
	{
		public void run()
		{
			flushElapseTime();
			timer.schedule(new RemindTask(), interval);
		}
	}
	
	public void flushElapseTime()  
	{                                     
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		String strDate = simpleDateFormat.format(date);	
		
		try{
			String strTemp = tf_StartTime.getText();
		    Date date1 = simpleDateFormat.parse(strTemp);
		    long remainder;
		    
		    Date date2 = simpleDateFormat.parse(strDate);
		    remainder = (date2.getTime() - date1.getTime())/1000;
		    
		    long hh, mm, ss;
		    hh = remainder/3600;
		    mm = (remainder%3600)/60;
		    ss = remainder%60;
		    strDate = String.valueOf(hh) + ":" + String.valueOf(mm) + ":" + String.valueOf(ss);
		    
		    tf_ElapseTime.setText(strDate);
		}
		catch(ParseException pe){
			System.out.println("ParseException in actionPerformed()");
		}  
	}
	
	public void flushEndTime(){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		String strDate = simpleDateFormat.format(date);	
		tf_EndTime.setText(strDate);
		
		try{
		    Date date1 = simpleDateFormat.parse(tf_StartTime.getText());
		    long remainder;
		    
		    Date date2 = simpleDateFormat.parse(tf_EndTime.getText());
		    remainder = (date2.getTime() - date1.getTime())/1000;
		    
		    long hh, mm, ss;
		    hh = remainder/3600;
		    mm = (remainder%3600)/60;
		    ss = remainder%60;
		    strDate = String.valueOf(hh) + ":" + String.valueOf(mm) + ":" + String.valueOf(ss);
		    
		    tf_ElapseTime.setText(strDate);
		}
		catch(ParseException pe){
			System.out.println("ParseException in actionPerformed()");
		}
	}
	
	public ZeitRekord() {
		setLayout(null);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(88, 53, 54, 15);
		add(lblDate);
		
		JLabel lblStartTime = new JLabel("Start Time");
		lblStartTime.setBounds(88, 86, 66, 15);
		add(lblStartTime);
		
		JLabel lblEndTime = new JLabel("End Time");
		lblEndTime.setBounds(88, 122, 54, 15);
		add(lblEndTime);
		
		JLabel lblElapseTime = new JLabel("Elapse Time");
		lblElapseTime.setBounds(88, 162, 86, 15);
		add(lblElapseTime);
		
		JLabel lblActivity = new JLabel("Activity");
		lblActivity.setBounds(88, 196, 54, 15);
		add(lblActivity);
		
		tf_Date = new JTextField();
		tf_Date.setBounds(221, 50, 86, 21);
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		String strDate = simpleDateFormat.format(date);	
		
		tf_Date.setText(strDate);
		add(tf_Date);
		tf_Date.setColumns(10);
		
		tf_StartTime = new JTextField();
		tf_StartTime.setBounds(221, 83, 86, 21);
		add(tf_StartTime);
		tf_StartTime.setColumns(10);
		
		tf_EndTime = new JTextField();
		tf_EndTime.setBounds(221, 119, 86, 21);
		add(tf_EndTime);
		tf_EndTime.setColumns(10);
		
		tf_ElapseTime = new JTextField();
		tf_ElapseTime.setBounds(221, 159, 86, 21);
		add(tf_ElapseTime);
		tf_ElapseTime.setColumns(10);
		
		cb_Activity = new JComboBox<String>();
		cb_Activity.setBounds(221, 193, 86, 21);
		
		cb_Activity.addItem("De");
		cb_Activity.addItem("PL");
		cb_Activity.addItem("DB");
		cb_Activity.addItem("OS");
		cb_Activity.addItem("SE");
		cb_Activity.addItem("Yoga");
		cb_Activity.addItem("Badminton");
		cb_Activity.addItem("Speech");
		cb_Activity.addItem("Job");
		
		add(cb_Activity);
		
		JButton btn_Start = new JButton("Start");
		btn_Start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
				Date date = new Date();
				String strDate = simpleDateFormat.format(date);
				tf_StartTime.setText(strDate);
				
				String str = null;
				tf_ElapseTime.setText(str);
				tf_EndTime.setText(str);
				
				timer = new Timer();
				timer.schedule(new RemindTask(), interval);
			}
		});
		btn_Start.setBounds(70, 244, 66, 23);
		add(btn_Start);
		
		JButton btn_Stop = new JButton("Stop");
		btn_Stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				flushEndTime();
				timer.cancel();
			}
		});
		btn_Stop.setBounds(166, 244, 72, 23);
		add(btn_Stop);
		
		JButton btn_Record = new JButton("Record");
		btn_Record.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String url = "jdbc:mysql://192.168.198.130:3306/time_manager";
					Class.forName("com.mysql.jdbc.Driver");
					String userName = "root";
					String password = "port7d";
					Connection conn = DriverManager.getConnection(url,userName,password);
					Statement sql = conn.createStatement();
					
					String sqlStatement = "insert into activity_time values('";
					sqlStatement = sqlStatement + (String)cb_Activity.getSelectedItem() + "', '";
					sqlStatement = sqlStatement + (String)tf_Date.getText() + "', '";
					sqlStatement = sqlStatement + (String)tf_StartTime.getText() + "', '";
					sqlStatement = sqlStatement + (String)tf_EndTime.getText() + "', '";
					sqlStatement = sqlStatement + (String)tf_ElapseTime.getText() + "');";
					//JOptionPane.showMessageDialog(null,sqlStatement);
					sql.execute(sqlStatement);
					sql.close();
					conn.close();
				}
				catch(Exception exce){
					System.out.println(exce.toString());
				}
			}
				
				/*
				try{
					String strStatement = "insert into Activity_Time values('root', '";
					
					strStatement = strStatement + (String)cb_Activity.getSelectedItem() + "', '";
					strStatement = strStatement + (String)tf_StartTime.getText() + "', '";
					strStatement = strStatement + (String)tf_EndTime.getText() + "', '";
					strStatement = strStatement + (String)tf_ElapseTime.getText() + "', ";
					strStatement = strStatement + "to_date('" + (String)tf_Date.getText() + "', 'yyyy/mm/dd'))";
					dbAccess.insert2DB(strStatement);
				}
				catch(Exception errInfo)
				{
					System.err.print("errInfo.getMessage()");
				}
				*/
		});
		btn_Record.setBounds(266, 244, 80, 23);
		add(btn_Record);
		
		this.show();
	}
}
