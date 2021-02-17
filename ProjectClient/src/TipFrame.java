import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class TipFrame extends JFrame implements ActionListener {
	public TipFrame() {
		setTitle("Tips");
		getContentPane().setLayout(null);

		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		textArea.setText(
				"이 시스템은, 선문대 컴퓨터 공학부 학생들, 교수님들을 위해 만들었습니다.\r\n이 시스템은 컴퓨터공학, 데이터공학 1~4학년 과목을 조회할 수 있습니다.\r\n조회뿐만 아니라 장바구니에 담을 수 있고, 과목의 \r\n현재 수강희망 인원도 알 수 있습니다. \r\n학생들이 매 학기마다 수강신청을 하면서 정원초과로 인해 \r\n난처한 상황을 직면하는데, 그 부분도 해소할 수 있고, 교수님들도 \r\n실시간으로 모니터링하여 어느 분반에 사람이 몰리는지 파악할 수 있습니다. \r\n또한 학생들은 과목평가를 할 수 있어 학생들은 정보를 얻을 수 있습니다. \r\n학생분들, 교수님들 화이팅!\n\r\n\t\r\nps. 최재성교수님 한학기 배움을 주셔서 감사합니다.\r\n이번학기는 저번학기와 다르게 질문을 최소화 하고 혼자 해결하려고 노력했습니다.\r\n속도는 더디지만 이것저것 스스로 해결하니 코딩이 더 재미있네요!\r\n다음학기에 건강하게 뵙겠습니다 새해복많이받으세요!                \r\n                                                                                    made by MJ\r\n\t\t\t\t");
		textArea.setBounds(12, 10, 612, 357);
		getContentPane().add(textArea);
		textArea.setBackground(Color.LIGHT_GRAY);
		textArea.setEditable(false);

		JButton close = new JButton("닫기");
		close.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		close.setBounds(262, 380, 133, 23);
		getContentPane().add(close);

		close.addActionListener(this);
		getContentPane().setBackground(Color.WHITE);

		close.setBackground(new Color(0, 122, 133));
		close.setForeground(Color.WHITE);

		setVisible(true);
		setSize(650, 450);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) {
			JButton b = (JButton) e.getSource();
			if (b.getText().equals("닫기")) {
				dispose();
			}

		}

	}
}
