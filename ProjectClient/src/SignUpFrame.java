import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class SignUpFrame extends JFrame implements MouseListener, ActionListener {
	Client c;
	JTextField txtId = new JTextField();
	JPasswordField txtPd = new JPasswordField(20);
	JTextField txtName = new JTextField();

	boolean IDcheck = false;
	boolean typeCheck = false;

	String[] years = { "선택", "1학년", "2학년", "3학년", "4학년" };
	JComboBox year = new JComboBox(years);
	String[] terms = { "선택", "1학기", "2학기" };
	JComboBox term = new JComboBox(terms);
	String[] deps = { "선택", "컴퓨터공학", "데이터공학" };
	JComboBox dep = new JComboBox(deps);

	JLabel Lid = new JLabel("아이디");
	JLabel Lpasswd = new JLabel("비밀번호");
	JLabel Lyear = new JLabel("학년");
	JLabel Lterm = new JLabel("학기");
	JLabel LDep = new JLabel("전공");
	JLabel Ltype = new JLabel("구분");
	JLabel Lname = new JLabel("이름");

	JComboBox type = new JComboBox(new Object[] {});

	JButton Ok = new JButton("가입");
	JButton cancel = new JButton("취소");
	JButton idCheck = new JButton("ID중복확인");

	SignUpFrame(Client _c) {
		c = _c;
		c.sf = this;
		setTitle("회원가입");
		Container c = getContentPane();
		JPanel info = new JPanel();
		info.setBackground(Color.WHITE);
		JPanel btn = new JPanel(new FlowLayout());
		c.setLayout(new BorderLayout());

		btn.setBackground(Color.WHITE);
		c.add(info, BorderLayout.CENTER);
		c.add(btn, BorderLayout.SOUTH);
		txtPd.setBounds(75, 40, 266, 30);

		txtPd.setText("");
		info.setLayout(null);
		idCheck.setBounds(243, 8, 98, 23);

		info.add(idCheck);
		Lid.setBounds(17, 4, 42, 30);

		info.add(Lid);
		txtId.setBounds(75, 5, 160, 30);
		info.add(txtId);
		info.add(idCheck);
		Lpasswd.setBounds(17, 39, 50, 30);
		info.add(Lpasswd);
		info.add(txtPd);
		Lyear.setBounds(27, 147, 31, 30);
		info.add(Lyear);
		year.setBackground(Color.white);
		year.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		year.setBounds(59, 146, 110, 30);
		info.add(year);
		Lterm.setBounds(192, 147, 50, 30);
		info.add(Lterm);
		term.setBounds(224, 146, 110, 30);
		term.setBackground(Color.white);
		term.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		info.add(term);
		LDep.setBounds(192, 106, 42, 30);
		info.add(LDep);
		dep.setBounds(224, 109, 110, 30);
		dep.setBackground(Color.white);
		dep.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		info.add(dep);

		idCheck.setBackground(new Color(164, 37, 67));
		idCheck.setForeground(Color.WHITE);
		idCheck.setFont(new Font("맑은 고딕", Font.PLAIN, 12));

		Ok.setBackground(new Color(0, 122, 133));
		Ok.setForeground(Color.WHITE);
		Ok.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		cancel.setBackground(new Color(0, 122, 133));
		cancel.setForeground(Color.WHITE);
		cancel.setFont(new Font("맑은 고딕", Font.PLAIN, 12));

		btn.add(Ok);
		btn.add(cancel);

		year.setPreferredSize(new Dimension(100, 30));
		term.setPreferredSize(new Dimension(100, 30));
		dep.setPreferredSize(new Dimension(220, 30));

		txtId.setPreferredSize(new Dimension(160, 30));
		txtPd.setPreferredSize(new Dimension(300, 30));

		Lid.setPreferredSize(new Dimension(60, 30));
		Lpasswd.setPreferredSize(new Dimension(100, 30));
		Lyear.setPreferredSize(new Dimension(50, 30));
		Lterm.setPreferredSize(new Dimension(50, 30));
		LDep.setPreferredSize(new Dimension(90, 30));

		Ltype.setPreferredSize(new Dimension(50, 30));
		Ltype.setBounds(27, 108, 50, 30);
		info.add(Ltype);

		type.setModel(new DefaultComboBoxModel(new String[] { "선택", "학부생", "교수" }));
		type.setPreferredSize(new Dimension(100, 30));
		type.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		type.setBackground(Color.WHITE);
		type.setBounds(59, 109, 110, 30);
		info.add(type);

		txtId.setText("ID는 학번으로 사용해주세요.");

		Lname.setPreferredSize(new Dimension(100, 30));
		Lname.setBounds(17, 72, 50, 30);
		info.add(Lname);

		txtName = new JTextField();
		txtName.setPreferredSize(new Dimension(160, 30));
		txtName.setBounds(75, 72, 266, 30);
		info.add(txtName);

		Ok.addMouseListener(this);
		cancel.addMouseListener(this);
		idCheck.addMouseListener(this);
		type.addActionListener(this);

		setSize(370, 250);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {

		JButton b = (JButton) e.getSource();
		int yesORno = 0;
		String msg = "";
		switch (b.getText()) {
		case "가입":
			if (type.getSelectedItem().toString().equals("교수")) {
				msg = "signUp@prof@" + txtId.getText() + "@" + new String(txtPd.getPassword()) + "@" + txtName.getText()
						+ "@" + type.getSelectedItem().toString();
				c.send(msg);
				break;
			}

			if (txtId.getText().equals("") || txtPd.getPassword().length == 0
					|| type.getSelectedItem().toString().equals("선택") || year.getSelectedItem().toString().equals("선택")
					|| term.getSelectedItem().toString().equals("선택")) {
				JOptionPane.showMessageDialog(null, "빈칸이 있습니다. 모두 입력해주세요.");
				break;
			}

			if (txtId.isEnabled()) {
				JOptionPane.showMessageDialog(null, "ID중복확인을해주세요.");
				break;
			}
			if (type.getSelectedItem().toString().equals("학부생")) {
				msg = "signUp@" + txtId.getText() + "@" + new String(txtPd.getPassword()) + "@" + txtName.getText()
						+ "@" + type.getSelectedItem().toString() + "@" + dep.getSelectedItem().toString() + "@"
						+ year.getSelectedItem().toString() + "@" + term.getSelectedItem().toString();
			}

			c.send(msg);
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
		case "ID중복확인":
			if (txtId.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "빈칸입니다. 아이디를 입력해주세요");
				break;
			}
			c.send("signUpCheck@" + txtId.getText());
			break;
		default:
			break;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JComboBox) {
			if (type.getSelectedItem().toString().equals("교수")) {
				year.disable();
				term.disable();
				dep.disable();
			}
			if (type.getSelectedItem().toString().equals("학부생") || type.getSelectedItem().toString().equals("선택")) {
				year.enable();
				term.enable();
				dep.enable();
			}

		}
	}
}