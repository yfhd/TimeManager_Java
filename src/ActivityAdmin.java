import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JComboBox;


public class ActivityAdmin extends JPanel {

	/**
	 * Create the panel.
	 */
	public ActivityAdmin() {
		System.out.println("Hello World!");
		
		setLayout(null);
		
		JButton btn_DateTrans = new JButton("Date Trans");
		btn_DateTrans.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// dbAccess.varchar2Date();
			}
		});
		btn_DateTrans.setBounds(167, 41, 102, 23);
		add(btn_DateTrans);
		
		JLabel lblNewLabel = new JLabel("Select Y&W");
		lblNewLabel.setBounds(21, 101, 72, 15);
		add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(118, 98, 64, 21);
		add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(219, 98, 64, 21);
		add(comboBox_1);
		
		JButton btnDailyReport = new JButton("Daily Report");
		btnDailyReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TimeManagement.changeTab(2);
			}
		});
		btnDailyReport.setBounds(304, 97, 112, 23);
		add(btnDailyReport);

	}
}
