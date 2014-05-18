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
		
		DefaultPieDataset dpd = new DefaultPieDataset(); //����һ��Ĭ�ϵı�ͼ        
		dpd.setValue("Manager", 25);  //��������        
		dpd.setValue("Marketing", 25);        
		dpd.setValue("Developer", 45);        
		dpd.setValue("Other Stuff", 10);                
		JFreeChart chart = ChartFactory.createPieChart("Organization Data Gram",dpd,true,true,false);         //���Բ�����API�ĵ�,��һ�������Ǳ��⣬�ڶ���������һ�����ݼ���������������ʾ�Ƿ���ʾLegend�����ĸ�������ʾ�Ƿ���ʾ��ʾ�������������ʾͼ���Ƿ����URL                
		
		/*
		ChartPanel chartPanel = new ChartPanel(chart);
		Container jf = this.getParent();
		jf = new ChartFrame("Organization Data Gram",chart);         //chartҪ����Java��������У�ChartFrame�̳���java��Jframe�ࡣ�õ�һ�������������Ƿ��ڴ������Ͻǵģ��������м�ı��⡣        
		((Window) jf).pack(); //�Ժ��ʵĴ�Сչ��ͼ��        
		jf.setVisible(true);//ͼ���Ƿ�ɼ�
		*/
		
		/*
		ChartFrame chartFrame = new ChartFrame("Organization Data Gram",chart);         //chartҪ����Java��������У�ChartFrame�̳���java��Jframe�ࡣ�õ�һ�������������Ƿ��ڴ������Ͻǵģ��������м�ı��⡣        
		chartFrame.pack(); //�Ժ��ʵĴ�Сչ��ͼ��        
		chartFrame.setVisible(true);//ͼ���Ƿ�ɼ�
		*/
		
		//add(chartPanel);
	}	
}
