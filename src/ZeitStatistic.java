import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;


public class ZeitStatistic extends JPanel {
	private JComboBox<String> cb_Activity;
	private JLabel lblNewLabel;
	private JLabel lbl_Ziel;

	/**
	 * Create the panel.
	 */
	public ZeitStatistic() {
		setLayout(null);
		
		JLabel lblPleaseSelectAn = new JLabel("Please select an Item");
		lblPleaseSelectAn.setBounds(40, 61, 135, 15);
		add(lblPleaseSelectAn);
		
		cb_Activity = new JComboBox<String>();
		
		
		cb_Activity.setBounds(226, 58, 81, 21);
		
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
		
		lblNewLabel = new JLabel("Alles Gut!");
		lblNewLabel.setBounds(40, 122, 287, 28);
		add(lblNewLabel);
		
		lbl_Ziel = new JLabel("Uebungen macht den Meister");
		lbl_Ziel.setBounds(40, 186, 353, 21);
		add(lbl_Ziel);
		
		cb_Activity.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				long time = 0, timeRemain;
				String strInfo = null;
				String strTemp;
				
				time = dbAccess.getTotalTime((String)cb_Activity.getSelectedItem());
				strTemp = Long.toString(time/3600) + "h:";
				strTemp += Long.toString((time % 3600)/60) + "m:";
				strTemp += Long.toString(time%60) + "s";
				strInfo = "Sie habe ueber " + (String)cb_Activity.getSelectedItem() + " " + strTemp + " gearbeitet";
				lblNewLabel.setText(strInfo);
				
				timeRemain = 10000*3600 - time;
				strTemp = Long.toString(timeRemain/3600) + "h:";
				strTemp += Long.toString((timeRemain % 3600)/60) + "m:";
				strTemp += Long.toString(timeRemain%60) + "s";
				strInfo = "After " + strTemp + ", you will be the expert in " + (String)cb_Activity.getSelectedItem() + "!";
				lbl_Ziel.setText(strInfo);
			}
		});
		
		this.show();
	}
}
