import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Client {
	Socket socket = null;
	LoginFrame lf;
	MainFrame mf;
	PasswordFrame pf;
	SignUpFrame sf;
	BoardFrame bf;
	ReviewFrame rf;
	BasketFrame bsf;

	String id = "";
	public String info = "";

	Object[][] course;

	String[] cname; // 2
	String[] pname;

	MessageListener msgListener = null;
	OutputStream outStream = null;
	DataOutputStream dataOutStream = null;

	void send(String msg) {
		try {
			OutputStream outStream = socket.getOutputStream();
			DataOutputStream dataOutStream = new DataOutputStream(outStream);

			dataOutStream.writeUTF(msg);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "입력 오류 > 다시접속하세요.");
		}
	}

	public static void main(String[] args) {
		new Client();
	}

	Client() {

		try {
			this.socket = new Socket("localhost", 1788);
			msgListener = new MessageListener(this.socket, this);
			msgListener.start();

			lf = new LoginFrame(this);

			outStream = this.socket.getOutputStream();
			dataOutStream = new DataOutputStream(outStream);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class MessageListener extends Thread {
	Client c;
	Socket socket;
	InputStream inStream;
	DataInputStream dataInStream;
	ObjectInputStream ois;

	int yesORno = 1;

	MessageListener(Socket _s, Client _c) {
		socket = _s;
		c = _c;
	}

	@Override
	public void run() {
		receive();
	}

	void receive() {
		try {
			inStream = this.socket.getInputStream();
			dataInStream = new DataInputStream(inStream);
			ois = new ObjectInputStream(inStream);
			while (true) {
				String msg = dataInStream.readUTF();

				switch (msg.split("#")[0]) {
				case "Reset":
					c.course = (Object[][]) ois.readObject();
					c.mf.tableModel = new DefaultTableModel(c.course, c.mf.columNames);
					c.mf.table.setModel(c.mf.tableModel);
					break;
				case "storeIn":
					c.course = (Object[][]) ois.readObject();
					if (c.course == null) {
						JOptionPane.showMessageDialog(null, "없는 정보입니다! 정보를 다시 입력해주세요.");
						break;
					}
					c.bsf.tableModel = new DefaultTableModel(c.course, c.bsf.columNames);
					c.bsf.table.setModel(c.bsf.tableModel);
					break;
				case "storeOut":
					c.course = (Object[][]) ois.readObject();
					if (c.course == null) {
						JOptionPane.showMessageDialog(null, "없는 정보입니다! 정보를 다시 입력해주세요.");
						break;
					}
					c.bsf.tableModel = new DefaultTableModel(c.course, c.bsf.columNames);
					c.bsf.table.setModel(c.bsf.tableModel);
					break;
				case "store":
					c.course = (Object[][]) ois.readObject();
					c.bsf = new BasketFrame(c);
					break;
				case "search":
					c.course = (Object[][]) ois.readObject();
					if (c.course == null) {
						JOptionPane.showMessageDialog(null, "없는 정보입니다! 정보를 다시 입력해주세요.");
						break;
					}
					c.mf.tableModel = new DefaultTableModel(c.course, c.mf.columNames);
					c.mf.table.setModel(c.mf.tableModel);
					break;
				case "review":
					String temp;

					if (msg.split("#")[1].equals("prof")) {
						temp = msg.split("#")[2];
						c.pname = temp.split("//");
					}
					if (msg.split("#")[1].equals("cname")) {
						temp = msg.split("#")[2];
						c.cname = temp.split("//");
					}

					if (c.cname != null && c.pname != null) {
						c.rf = new ReviewFrame(c);
					}
					break;
				case "tree":
					c.course = (Object[][]) ois.readObject();
					c.mf.tableModel = new DefaultTableModel(c.course, c.mf.columNames);
					c.mf.table.setModel(c.mf.tableModel);
					break;
				case "reviewSignup":
					c.course = (Object[][]) ois.readObject();
					c.bf.course = c.course;
					c.bf.tableModel = new DefaultTableModel(c.course, c.bf.columNames);
					c.bf.table.setModel(c.bf.tableModel);
					JOptionPane.showMessageDialog(null, "등록완료!");
					c.rf.dispose();
					break;
				case "log":
					c.course = (Object[][]) ois.readObject();
					c.bf = new BoardFrame(c);
					break;
				case "logout":
					socket.close();
					inStream.close();
					dataInStream.close();
					ois.close();
					System.exit(0);
					break;

				case "login":
					if (msg.split("#")[1].equals("err")) {
						JOptionPane.showMessageDialog(null, "로그인 실패. 정보를 다시 입력해주세요.");
						break;
					}
					c.id = c.lf.txtID.getText();

					c.course = (Object[][]) ois.readObject();
					c.lf.dispose();
					c.info = msg;
					c.mf = new MainFrame(c);
					new TipFrame();
					break;

				case "signUp":
					if (msg.split("#")[1].equals("err")) {
						JOptionPane.showMessageDialog(null, "실패했습니다. 입력된 정보를 수정해주세요.");
						break;
					}
					JOptionPane.showMessageDialog(null, "축하합니다 회원가입을 성공했습니다~");
					c.sf.dispose();
					break;

				case "signUpCheck":
					if (msg.split("#")[1].equals("err")) {
						JOptionPane.showMessageDialog(null, "이미 존재하는 아이디입니다! 다시 입력해주세요.");
						break;
					}
					yesORno = JOptionPane.showConfirmDialog(null, "사용가능한 아이디입니다. 사용하시겠습니까?", "Confirm",
							JOptionPane.YES_NO_OPTION);
					switch (yesORno) {
					case 0:
						c.sf.txtId.disable();
						break;
					case 1:
						break;
					}
					break;
				case "pw":
					if (msg.split("#")[1].equals("none")) {
						JOptionPane.showMessageDialog(null, "정보를 잘못 입력했습니다.");
						break;
					}

					JOptionPane.showMessageDialog(null, "비밀번호는 " + msg.split("#")[1] + " 입니다.");
					c.pf.dispose();
					break;
				default:
					break;
				}

			}

		} catch (SocketException se) {
			JOptionPane.showMessageDialog(null, "서버가 종료되었습니다 다시 접속해주세요.");
			System.exit(0);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
				inStream.close();
				dataInStream.close();
				ois.close();
			} catch (IOException e) {
			}
		}
	}

}