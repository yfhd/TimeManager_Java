import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicTabbedPaneUI.TabbedPaneLayout;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.osgi.framework.AdminPermission;

import com.ibm.icu.impl.locale.AsciiUtil.CaseInsensitiveKey;


public class TimeManagement extends JFrame implements ChangeListener{

	private int index = 0;
	static private JTabbedPane tabbedPane = null;
	private ZeitRekord jpRekord;
	private ZeitStatistic jpStatistic;
	private JPanel jpDaily;
	private ActivityAdmin jpAdmin;
	  
	private JPanel contentPane;
	
	public static void changeTab(int i){
		tabbedPane.setSelectedIndex(i);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 TimeManagement frame = new TimeManagement();
					 frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("deprecation")
	public TimeManagement() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 351);
		
		Container contentPane = this.getContentPane();
		
		tabbedPane = new JTabbedPane();
		tabbedPane.setBounds(10, 223, 155, 29);
		
		tabbedPane.addChangeListener(this);
		jpRekord = new ZeitRekord();
		jpStatistic = new ZeitStatistic();
		jpAdmin = new ActivityAdmin();
		
		jpDaily = new JPanel();
		jpDaily.setLayout(new BorderLayout());
		XYSeries series = new XYSeries("XYGraph");
	    series.add(1, 1);
	    series.add(1, 2);
	    series.add(2, 1);
	    series.add(3, 9);
	    series.add(4, 10);

	    // Add the series to your data set
	    XYSeriesCollection dataset = new XYSeriesCollection();
	    dataset.addSeries(series);

	    // Generate the graph
	    JFreeChart chart = ChartFactory.createXYLineChart(
		    "XY Chart", // Title
		    "x-axis", // x-axis Label
		    "y-axis", // y-axis Label
		    dataset, // Dataset
		    PlotOrientation.VERTICAL, // Plot Orientation
		    true, // Show Legend
		    true, // Use tooltips
		    false // Configure chart to generate URLs?
		);
		
		ChartPanel cp = new ChartPanel(chart);
		jpDaily.add(cp, BorderLayout.CENTER);
		
		jpDaily.validate();
		
		tabbedPane.addTab("Rekord", jpRekord);
	    tabbedPane.addTab("Statistics", jpStatistic);
	    tabbedPane.addTab("Weekly", jpDaily);
	    tabbedPane.addTab("Admin", jpAdmin);
		
		contentPane.add(tabbedPane);
		
	    this.show();
	    this.addWindowListener(new WindowAdapter(){
	    	public void WindowClosing(WindowEvent e){
	    		System.exit(0);
	    	}
	    });     
	    
	}
	
	public void stateChanged(ChangeEvent e){
	    if (index != tabbedPane.getSelectedIndex()){
		    if(index < tabbedPane.getTabCount()-1){
		    	tabbedPane.setEnabledAt(index+1, true);
		    	//this.pack();
		    	this.show();
		    }
	    }
	 
	    index = tabbedPane.getSelectedIndex();
	}
}
