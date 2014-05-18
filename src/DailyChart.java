import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Canvas;
import java.awt.Container;
import java.awt.Window;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.Font;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.ApplicationFrame;

public class DailyChart extends JPanel {
	
	/**
	 * Create the panel.
	 */
	public DailyChart() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Select a Week");
		lblNewLabel.setBounds(115, 10, 88, 15);
		add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(256, 7, 76, 21);
		
		for(int i=1; i<=52; i++){
			comboBox.addItem(i);
		}
		
		add(comboBox);
		
		DefaultPieDataset dpd = new DefaultPieDataset(); //建立一个默认的饼图        
		dpd.setValue("Manager", 25);  //输入数据        
		dpd.setValue("Marketing", 25);        
		dpd.setValue("Developer", 45);        
		dpd.setValue("Other Stuff", 10);                
		JFreeChart chart = ChartFactory.createPieChart("Organization Data Gram",dpd,true,true,false);         //可以查具体的API文档,第一个参数是标题，第二个参数是一个数据集，第三个参数表示是否显示Legend，第四个参数表示是否显示提示，第五个参数表示图中是否存在URL                
		
		/*
		ChartPanel chartPanel = new ChartPanel(chart);
		Container jf = this.getParent();
		jf = new ChartFrame("Organization Data Gram",chart);         //chart要放在Java容器组件中，ChartFrame继承自java的Jframe类。该第一个参数的数据是放在窗口左上角的，不是正中间的标题。        
		((Window) jf).pack(); //以合适的大小展现图形        
		jf.setVisible(true);//图形是否可见
		*/
		
		/*
		ChartFrame chartFrame = new ChartFrame("Organization Data Gram",chart);         //chart要放在Java容器组件中，ChartFrame继承自java的Jframe类。该第一个参数的数据是放在窗口左上角的，不是正中间的标题。        
		chartFrame.pack(); //以合适的大小展现图形        
		chartFrame.setVisible(true);//图形是否可见
		*/
		
		//add(chartPanel);
	}	
}
