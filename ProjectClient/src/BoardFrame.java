import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class BoardFrame extends JFrame implements ActionListener {
	Client c;
	Object[][] course;
	String[] columNames = new String[] { "과목명", "담당교수", "평점" };
	DefaultTableModel tableModel;
	JTable table;
	JScrollPane sp;

	JLabel title = new JLabel("최근 강의평");

	JButton review = new JButton("평가하기");
	JButton exit = new JButton("종료");

	public BoardFrame(Client _c) {
		c = _c;
		this.course = c.course;
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.WHITE);

		tableModel = new DefaultTableModel(course, columNames);
		table = new JTable(tableModel);
		sp = new JScrollPane(table);
		sp.setBounds(67, 93, 399, 229);
		getContentPane().add(sp);

		title.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(152, 10, 217, 42);

		exit.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		review.setFont(new Font("맑은 고딕", Font.PLAIN, 13));

		getContentPane().add(title);

		review.setBounds(138, 377, 115, 45);
		getContentPane().add(review);

		exit.addActionListener(this);
		review.addActionListener(this);

		exit.setBackground(new Color(164, 37, 67));
		exit.setForeground(Color.WHITE);
		review.setBackground(new Color(0, 122, 133));
		review.setForeground(Color.WHITE);

		exit.setBounds(279, 377, 115, 45);
		getContentPane().add(exit);

		setSize(560, 480);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int yesORno = 1;
		if (e.getSource() instanceof JButton) {
			JButton b = (JButton) e.getSource();
			switch (b.getText()) {
			case "평가하기":
				c.send("review@");
				c.send("review2@");
				break;
			case "종료":
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
