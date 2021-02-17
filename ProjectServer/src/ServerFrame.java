import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class ServerFrame extends JFrame implements ActionListener {
	Server s;
	ConnectedClient c;
	JButton end = new JButton("종 료");
	JTextArea ta;

	JPanel centerPanel = new JPanel(new BorderLayout());
	JPanel southPanel = new JPanel(new BorderLayout());
	JPanel eastPanel = new JPanel(new BorderLayout());
	JPanel northPanel = new JPanel(new BorderLayout());

	JMenuBar mb = new JMenuBar();
	JMenu homeMenu = new JMenu("HOME");
	JMenuItem exit = new JMenuItem("EXIT");
	JMenuItem clear = new JMenuItem("CLEAR");
	JLabel port = new JLabel("port : 1788");

	public static void main(String[] args) {
		Server s = new Server();
		ServerFrame sf = new ServerFrame(s);

	}

	public ServerFrame(Server _s) {

		s = _s;
		Container c = getContentPane();

		c.setLayout(new BorderLayout());

		c.add(centerPanel, BorderLayout.CENTER);
		c.add(southPanel, BorderLayout.SOUTH);
		c.add(eastPanel, BorderLayout.EAST);
		c.add(northPanel, BorderLayout.NORTH);

		homeMenu.add(exit);
		homeMenu.addSeparator();
		homeMenu.add(clear);
		mb.add(homeMenu);
		northPanel.add(mb, BorderLayout.CENTER);
		northPanel.add(port, BorderLayout.EAST);

		port.setFont(new Font("고딕", Font.BOLD, 12));

		ta = new JTextArea(7, 20);
		JScrollPane scroll = new JScrollPane(ta);
		centerPanel.add(scroll);
		ta.setEditable(false);
		ta.setLineWrap(true); // 자동줄넘김
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		southPanel.add(end, BorderLayout.WEST);

		exit.addActionListener(this);
		clear.addActionListener(this);
		homeMenu.addActionListener(this);
		end.addActionListener(this);

		ta.append("Server > Server Socket is Created Complete!!!");

		setSize(400, 500);
		setTitle("선문대학교 컴퓨터공학부 수강신청 도우미 시스템 서버");
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) {
			int yesORno = JOptionPane.showConfirmDialog(null, "종료하시겠습니까?", "Confirm", JOptionPane.YES_NO_OPTION);

			switch (yesORno) {
			case 0:
				try {
					FileWriter fw = new FileWriter("./serverLog.txt");
					fw.write(ta.getText());
					fw.close();
					System.out.println("끝");

				} catch (Exception e1) {
					e1.printStackTrace();
				}

				System.exit(0);
				break;
			case 1:

			}
			return;
		}

		if (e.getSource() instanceof JMenuItem) {
			JMenuItem menu1 = (JMenuItem) e.getSource();
			switch (menu1.getText()) {
			case "EXIT":
				try {
					FileWriter fw = new FileWriter("./serverLog.txt");
					fw.write(ta.getText());
					fw.close();
					System.out.println("끝");

				} catch (Exception e1) {
					e1.printStackTrace();
				}

				System.exit(0);
				break;
			case "CLEAR":
				ta.setText("");
				break;

			default:
				break;
			}

		}
	}

}
