import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class ReviewFrame extends JFrame implements ActionListener {
	Client c;
	
	JComboBox cnameBox = new JComboBox();
	JComboBox scoreBox = new JComboBox();
	JComboBox nameBox = new JComboBox();
	JLabel cName = new JLabel("교수 이름");
	JLabel lScore = new JLabel("평점");
	JLabel Lcname = new JLabel("강의명");
	JButton cancel = new JButton("취소");
	private final JButton signup = new JButton("등록");
	
	

	public ReviewFrame(Client _c) {
		c = _c;
		cnameBox.setModel(new DefaultComboBoxModel(c.cname));
		nameBox.setModel(new DefaultComboBoxModel(c.pname));
		scoreBox.setModel(new DefaultComboBoxModel(new String[] {"5", "4", "3", "2", "1"}));
				
		setTitle("강의평가서");
		getContentPane().setLayout(null);
		getContentPane().add(cName);
		getContentPane().add(cnameBox);
		getContentPane().add(scoreBox);
		getContentPane().add(nameBox);
		getContentPane().add(lScore);
		getContentPane().add(Lcname);
		getContentPane().add(signup);
		getContentPane().add(cancel);
		getContentPane().setBackground(Color.WHITE);
		
		cName.setBounds(12, 83, 151, 43);
		cnameBox.setBounds(165, 36, 171, 23);
		scoreBox.setBounds(165, 149, 76, 23);
		nameBox.setBounds(165, 96, 171, 23);
		lScore.setBounds(12, 136, 151, 43);
		Lcname.setBounds(12, 23, 151, 43);
		cancel.setBounds(183, 218, 98, 28);
		signup.setBounds(65, 218, 98, 28);
		
		cName.setHorizontalAlignment(SwingConstants.CENTER);
		cName.setFont(new Font("맑은 고딕", Font.PLAIN, 17));
		lScore.setHorizontalAlignment(SwingConstants.CENTER);
		lScore.setFont(new Font("맑은 고딕", Font.PLAIN, 17));
		Lcname.setHorizontalAlignment(SwingConstants.CENTER);
		Lcname.setFont(new Font("맑은 고딕", Font.PLAIN, 17));
	
		getContentPane().add(signup);
	
		signup.addActionListener(this); 
		cancel.addActionListener(this); 
		
		signup.setBackground(new Color(164, 37, 67));
		signup.setForeground(Color.WHITE);
		cancel.setBackground(new Color(0, 122, 133));
		cancel.setForeground(Color.WHITE);
		
		
		setSize(365,300);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int yesORno = 1;
		String result = "reviewSignup@";
		if (e.getSource() instanceof JButton) {
			JButton b = (JButton) e.getSource();
			switch (b.getText()) {
			case "등록":
				result += cnameBox.getSelectedItem()+ "@" + nameBox.getSelectedItem() +"@"+ scoreBox.getSelectedItem(); 
				c.send(result);
				break;
			case "취소":
				yesORno = JOptionPane.showConfirmDialog(null, "종료하시겠습니까?", "Confirm", JOptionPane.YES_NO_OPTION);
				switch (yesORno) {
					case 0:
						dispose();
						break;
					case 1:
						break;
					}
					break;
			default:
				break;

			}
		}
	}
}
