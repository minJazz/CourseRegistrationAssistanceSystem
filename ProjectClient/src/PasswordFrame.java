import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PasswordFrame extends JFrame implements MouseListener {
	Client c;
	JButton ok = new JButton("찾기");
	JButton cancel = new JButton("취소");

	JLabel id = new JLabel("아이디(학번/사번)");
	JLabel name = new JLabel("이름");

	JTextField txtId = new JTextField();
	JTextField txtName = new JTextField();

	JPanel panel = new JPanel(new FlowLayout());
	JPanel btn = new JPanel(new FlowLayout());

	public PasswordFrame(Client _c) {
		c = _c;
		c.pf = this;

		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		c.add(panel, BorderLayout.CENTER);
		c.add(btn, BorderLayout.SOUTH);

		panel.add(id, BorderLayout.CENTER);
		panel.add(txtId, BorderLayout.CENTER);
		panel.add(name, BorderLayout.CENTER);
		panel.add(txtName, BorderLayout.CENTER);

		btn.add(ok);
		btn.add(cancel);

		ok.setBackground(new Color(0, 122, 133));
		ok.setForeground(Color.WHITE);
		cancel.setBackground(new Color(0, 122, 133));
		cancel.setForeground(Color.WHITE);

		id.setPreferredSize(new Dimension(100, 30));
		name.setPreferredSize(new Dimension(100, 30));
		txtId.setPreferredSize(new Dimension(150, 30));
		txtName.setPreferredSize(new Dimension(150, 30));

		ok.addMouseListener(this);
		cancel.addMouseListener(this);

		setTitle("비밀번호 찾기");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		setSize(300, 150);
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
		String msg = "pw@";
		switch (b.getText()) {
		case "찾기":
			if (txtId.getText().equals("") || txtName.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "빈칸이 있습니다. 모두 입력해주세요.");
				break;
			}
			msg += txtId.getText() + "@" + txtName.getText();
			c.send(msg);
			break;
		case "취소":
			yesORno = JOptionPane.showConfirmDialog(null, "종료하시겠습니까?", "Confirm", JOptionPane.YES_NO_OPTION);
			switch (yesORno) {
			case 0:

				c.pf.dispose();
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
