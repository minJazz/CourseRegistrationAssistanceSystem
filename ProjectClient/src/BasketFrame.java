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

public class BasketFrame extends JFrame implements ActionListener {
	Client c;

	Object[][] course;
	String[] columNames = new String[] { "과목코드", "담당교수", "과목명", "시간", "인원현황", "학점" };
	DefaultTableModel tableModel;
	JTable table;
	JScrollPane sp;

	JLabel title = new JLabel("내 장바구니");

	JButton delete = new JButton("삭제하기");
	JButton exit = new JButton("닫기");
	JButton add = new JButton("추가하기");

	public BasketFrame(Client _c) {
		c = _c;
		this.course = c.course;
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.WHITE);

		tableModel = new DefaultTableModel(course, columNames);
		table = new JTable(tableModel);
		sp = new JScrollPane(table);

		getContentPane().add(sp);
		getContentPane().add(exit);
		getContentPane().add(title);
		getContentPane().add(delete);
		getContentPane().add(add);

		sp.setBounds(51, 62, 444, 289);
		title.setBounds(152, 10, 217, 42);
		delete.setBounds(215, 377, 115, 45);
		exit.setBounds(380, 377, 115, 45);
		add.setBounds(51, 377, 115, 45);

		title.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		title.setHorizontalAlignment(SwingConstants.CENTER);

		exit.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		delete.setFont(new Font("맑은 고딕", Font.PLAIN, 13));

		exit.addActionListener(this);
		delete.addActionListener(this);
		add.addActionListener(this);

		exit.setBackground(new Color(164, 37, 67));
		exit.setForeground(Color.WHITE);
		delete.setBackground(new Color(0, 122, 133));
		delete.setForeground(Color.WHITE);

		add.setForeground(Color.WHITE);
		add.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		add.setBackground(new Color(0, 122, 133));

		setSize(560, 480);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String search = "";
		int yesORno = 1;
		if (e.getSource() instanceof JButton) {
			JButton b = (JButton) e.getSource();
			switch (b.getText()) {
			case "추가하기":
				search = "storeIn@" + JOptionPane.showInputDialog("추가하고자 하는 분반을 입력해주세요.");
				if (search.equals("store@")) {
					JOptionPane.showMessageDialog(null, "분반을 입력해주세요!");
					break;
				}
				c.send(search);
				break;
			case "삭제하기":
				search = "storeOut@" + JOptionPane.showInputDialog("삭제하고자 하는 분반을 입력해주세요.");
				if (search.equals("store@")) {
					JOptionPane.showMessageDialog(null, "분반을 입력해주세요!");
					break;
				}
				c.send(search);

				break;
			case "닫기":

				dispose();
				break;
			default:
				break;

			}
		}
	}

}
