import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class MainFrame extends JFrame {
	Client c;
	MultiListener m;

	boolean mode = false;

	Object[][] course;
	String[] columNames = new String[] { "담당교수", "과목코드", "과목명", "강의시간", "수강가능인원", "학년", "학기", "전공", "년도", "학점" };
	DefaultTableModel tableModel;
	JTable table;
	JScrollPane sp;

	// 패널영역
	JPanel panel = new JPanel();
	JPanel titlePanel = new JPanel();
	JPanel infoPanel = new JPanel();
	JPanel backPanel = new JPanel();

	JLabel title = new JLabel("수강신청 도우미 시스템 made by MJ");

	SimpleDateFormat days = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat times = new SimpleDateFormat("a hh:mm:ss");
	// 시계
	Date today = new Date();
	JLabel y = new JLabel();
	JLabel t = new JLabel();

	// 정보
	JLabel 학부 = new JLabel();
	JLabel 소속학과학년 = new JLabel();
	JLabel 성명 = new JLabel();
	JLabel 상태 = new JLabel();

	// 메뉴바
	JMenuBar mb = new JMenuBar();

	JMenu 메뉴 = new JMenu("메뉴");
	JMenuItem tip = new JMenuItem("사용법");
	JMenuItem logout = new JMenuItem("로그아웃");

	JMenu 상세조회 = new JMenu("상세조회");
	JMenuItem 과목명조회 = new JMenuItem("과목명조회");
	JMenuItem 교수명조회 = new JMenuItem("교수명조회");

	JMenu option = new JMenu("옵션");
	JMenuItem darkMode = new JMenuItem("다크모드");
	JMenu 추가 = new JMenu("추가");
	JMenuItem 장바구니열기 = new JMenuItem("장바구니열기");

	JMenuItem 강의평 = new JMenuItem("강의평");

	JButton Reset = new JButton("Reset");
	// 트리
	JTree tree = new JTree();

	void darkMode() {
		if ((mode)) {
			mode = false;
			backPanel.setBackground(Color.white);
			panel.setBackground(new Color(164, 37, 67));
			infoPanel.setBackground(new Color(0, 122, 133));
			titlePanel.setBackground(new Color(0, 122, 133));
			tree.setBackground(new Color(0, 122, 133));
			repaint();
			return;
		}
		mode = true;
		backPanel.setBackground(new Color(000, 000, 000));
		panel.setBackground(Color.DARK_GRAY);
		infoPanel.setBackground(Color.DARK_GRAY);
		titlePanel.setBackground(Color.DARK_GRAY);
		tree.setBackground(Color.DARK_GRAY);
		repaint();
		return;
	}

	public MainFrame(Client _c) {

		c = _c;
		c.mf = this;
		m = new MultiListener(this);

		course = c.course;
		System.out.println(c.info);
		
		if (c.info.split("#")[2].equals("stu")) {
			학부.setText("학부 : " + c.info.split("#")[3]);
			소속학과학년.setText(c.info.split("#")[3] + " " + c.info.split("#")[4] + "학년");
			성명.setText("성명 : " + c.info.split("#")[5]);
			상태.setText("상태 : " + c.info.split("#")[6]);
		}
		if (c.info.split("#")[2].equals("prof")) {
			학부.setText("학부 :" + c.info.split("#")[3]);
			성명.setText(c.info.split("#")[4]);
			상태.setText("성명 : " + c.info.split("#")[5]);
		}

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		backPanel.setBounds(0, 21, 831, 452);
		getContentPane().add(backPanel);
		backPanel.setLayout(null);
		backPanel.setBackground(Color.WHITE);

		tableModel = new DefaultTableModel(course, columNames);
		table = new JTable(tableModel);
		sp = new JScrollPane(table);

		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(42);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(62);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(95);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(90);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(4).setPreferredWidth(58);
		table.getColumnModel().getColumn(5).setResizable(false);
		table.getColumnModel().getColumn(5).setPreferredWidth(40);
		table.getColumnModel().getColumn(6).setResizable(false);
		table.getColumnModel().getColumn(6).setPreferredWidth(40);
		table.getColumnModel().getColumn(7).setResizable(false);
		table.getColumnModel().getColumn(7).setPreferredWidth(90);
		table.getColumnModel().getColumn(8).setResizable(false);
		table.getColumnModel().getColumn(8).setPreferredWidth(34);
		table.getColumnModel().getColumn(9).setResizable(false);
		table.getColumnModel().getColumn(9).setPreferredWidth(40);
		sp.setBounds(161, 70, 658, 373);
		backPanel.add(sp);

		panel.setBackground(new Color(164, 37, 67));
		panel.setBounds(702, 10, 117, 50);
		backPanel.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		// 시계
		t.setForeground(Color.WHITE);
		t.setHorizontalAlignment(SwingConstants.CENTER);
		t.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		y.setForeground(Color.WHITE);
		y.setHorizontalAlignment(SwingConstants.CENTER);
		y.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		y.setText(days.format(today));
		t.setText(times.format(today));

		tree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("컴퓨터공학부") {
			{
				DefaultMutableTreeNode node_1;

				node_1 = new DefaultMutableTreeNode("공통");
				node_1.add(new DefaultMutableTreeNode("1학년"));
				node_1.add(new DefaultMutableTreeNode("2학년"));
				node_1.add(new DefaultMutableTreeNode("3학년"));
				node_1.add(new DefaultMutableTreeNode("4학년"));
				add(node_1);
				node_1 = new DefaultMutableTreeNode("컴퓨터공학");
				node_1.add(new DefaultMutableTreeNode("1학년"));
				node_1.add(new DefaultMutableTreeNode("2학년"));
				node_1.add(new DefaultMutableTreeNode("3학년"));
				node_1.add(new DefaultMutableTreeNode("4학년"));
				add(node_1);
				node_1 = new DefaultMutableTreeNode("데이터공학");
				node_1.add(new DefaultMutableTreeNode("1학년"));
				node_1.add(new DefaultMutableTreeNode("2학년"));
				node_1.add(new DefaultMutableTreeNode("3학년"));
				node_1.add(new DefaultMutableTreeNode("4학년"));
				add(node_1);
			}
		}));
		tree.setBounds(12, 100, 137, 306);
		tree.setBackground(new Color(0, 122, 133));

		titlePanel.setBackground(new Color(0, 122, 133));
		titlePanel.setBounds(161, 10, 529, 50);
		titlePanel.setLayout(new BorderLayout(0, 0));

		titlePanel.add(title);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("맑은 고딕", Font.PLAIN, 26));
		title.setForeground(Color.WHITE);

		infoPanel.setBackground(new Color(0, 122, 133));
		infoPanel.setBounds(12, 10, 137, 80);
		infoPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		학부.setHorizontalAlignment(SwingConstants.CENTER);
		학부.setFont(new Font("맑은 고딕", Font.BOLD, 11));
		성명.setHorizontalAlignment(SwingConstants.CENTER);
		성명.setFont(new Font("맑은 고딕", Font.BOLD, 11));
		상태.setHorizontalAlignment(SwingConstants.CENTER);
		상태.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		소속학과학년.setHorizontalAlignment(SwingConstants.CENTER);
		소속학과학년.setFont(new Font("맑은 고딕", Font.BOLD, 11));

		Reset.setBackground(new Color(164, 37, 67));
		Reset.setForeground(Color.WHITE);

		성명.setForeground(Color.WHITE);
		학부.setForeground(Color.WHITE);
		상태.setForeground(Color.WHITE);
		소속학과학년.setForeground(Color.WHITE);

		backPanel.add(tree);
		backPanel.add(titlePanel);
		backPanel.add(infoPanel);

		infoPanel.add(학부);
		infoPanel.add(소속학과학년);
		infoPanel.add(성명);
		infoPanel.add(상태);

		mb.setBounds(0, 0, 225, 23);
		getContentPane().add(mb);

		mb.add(메뉴);
		메뉴.add(tip);
		메뉴.addSeparator();
		메뉴.add(logout);

		mb.add(상세조회);
		상세조회.add(과목명조회);
		상세조회.addSeparator();
		상세조회.add(교수명조회);

		mb.add(option);
		option.add(darkMode);

		mb.add(추가);
		추가.add(장바구니열기);
		추가.addSeparator();
		추가.add(강의평);

		panel.add(y);
		panel.add(t);
		Reset.setBounds(12, 416, 137, 23);

		backPanel.add(Reset);

		메뉴.addActionListener(m);
		tip.addActionListener(m);
		logout.addActionListener(m);
		상세조회.addActionListener(m);
		과목명조회.addActionListener(m);
		교수명조회.addActionListener(m);
		option.addActionListener(m);
		darkMode.addActionListener(m);
		추가.addActionListener(m);
		장바구니열기.addActionListener(m);
		강의평.addActionListener(m);
		tree.addTreeSelectionListener(m);
		Reset.addActionListener(m);

		setSize(845, 510);
		setVisible(true);
		setLocationRelativeTo(null);

	}

	class MultiListener implements ActionListener, ItemListener, TreeSelectionListener {
		int yesORno = 0;
		MainFrame mf;
		String search = "";

		public MultiListener(MainFrame _mf) {
			mf = _mf;
		}

		@Override

		public void valueChanged(TreeSelectionEvent evt1) {
			if (tree.getSelectionPath() == null) {
				return;
			}
			String tp = new String(tree.getSelectionPath().toString());
			DefaultMutableTreeNode treeEvt = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
			tp = tp.substring(1, tp.length() - 1);
			c.send("treeSelect@" + tp);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() instanceof JButton) {
				JButton b = (JButton) e.getSource();
				switch (b.getText()) {
				case "Reset":
					c.send("Reset@");
					break;
				}
			}

			if (e.getSource() instanceof JMenuItem) {
				JMenuItem menu1 = (JMenuItem) e.getSource();
				switch (menu1.getText()) {
				case "사용법":
					new TipFrame();
					break;
				case "로그아웃":
					yesORno = JOptionPane.showConfirmDialog(null, "종료하시겠습니까?", "Confirm", JOptionPane.YES_NO_OPTION);
					switch (yesORno) {
					case 0:
						c.send("logout@" + c.id);
						break;
					case 1:
						break;
					}
					break;

				case "과목명조회":
					search = "search@cname@" + JOptionPane.showInputDialog("검색할 과목명을 입력하시오.");
					if (search.equals("search@cname@")) {
						dispose();
					}
					c.send(search);
					break;
				case "교수명조회":
					search = "search@prof@" + JOptionPane.showInputDialog("선호하는 교수명을 입력하시오.");
					c.send(search);
					break;
				case "장바구니열기":
					c.send("store@");
					break;
				case "강의평":
					c.send("log@");
					break;
				case "다크모드":
					mf.darkMode();
					break;
				default:
					break;
				}
			}
		}

		@Override
		public void itemStateChanged(ItemEvent e) {
		}

	}

}
