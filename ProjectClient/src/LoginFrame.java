import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginFrame extends JFrame implements MouseListener, KeyListener {

	Client c;
	JPanel panel = new JPanel(new FlowLayout());

	JButton signUp = new JButton("회원가입");
	JButton login = new JButton("로그인");
	JButton pw = new JButton("비밀번호찾기");

	JTextField txtID = new JTextField();
	JPasswordField txtPass = new JPasswordField();

	JLabel id = new JLabel("I  D");
	JLabel password = new JLabel("Password");

	public LoginFrame(Client _c) {
		c = _c;
		c.lf = this;
		setTitle("선문대학교 컴퓨터공학부 수강신청 도우미");

		id.setPreferredSize(new Dimension(70, 30));
		txtID.setPreferredSize(new Dimension(300, 30));
		password.setPreferredSize(new Dimension(70, 30));
		txtPass.setPreferredSize(new Dimension(300, 30));
		signUp.setPreferredSize(new Dimension(125, 30));
		login.setPreferredSize(new Dimension(125, 30));
		pw.setPreferredSize(new Dimension(125, 30));

		panel.add(id);
		panel.add(txtID);
		panel.add(password);
		panel.add(txtPass);
		panel.add(signUp);
		panel.add(login);
		panel.add(pw);
		setContentPane(panel);
		panel.setBackground(Color.WHITE);

		signUp.setBackground(new Color(0, 122, 133));
		signUp.setForeground(Color.WHITE);
		signUp.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		login.setBackground(new Color(0, 122, 133));
		login.setForeground(Color.WHITE);
		login.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		pw.setBackground(new Color(164, 37, 67));
		pw.setForeground(Color.WHITE);
		pw.setFont(new Font("맑은 고딕", Font.PLAIN, 15));

		signUp.addMouseListener(this);
		login.addMouseListener(this);
		pw.addMouseListener(this);

		txtPass.addKeyListener(this);
		signUp.addKeyListener(this);
		login.addKeyListener(this);

		setResizable(false);
		setSize(450, 150);

		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyReleased(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			String id = txtID.getText();
			String passwd = "";
			for (char pass : txtPass.getPassword()) {
				passwd += "" + pass;
			}

			String info = "login@" + id + "@" + passwd + "@";
			c.send(info);
			return;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {

		JButton b = (JButton) e.getSource();

		switch (b.getText()) {
		case "회원가입":
			new SignUpFrame(c);
			break;
		case "로그인":

			String id = txtID.getText();
			String passwd = "";
			for (char pass : txtPass.getPassword()) {
				passwd += "" + pass;
			}

			if (id.equals("") || passwd.equals("")) {
				JOptionPane.showMessageDialog(null, "빈칸이 있습니다. 정보를 입력해주세요.");
				break;
			}
			String info = "login@" + id + "@" + passwd + "@";

			c.send(info);
			break;
		case "비밀번호찾기":
			new PasswordFrame(c);
			break;
		default:
			break;

		}
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
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
