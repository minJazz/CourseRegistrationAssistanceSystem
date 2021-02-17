/*
 *    프로그램명 : 선문대학교 컴퓨터공학부 수강신청 도우미 시스템
 *    개발자 : 컴퓨터공학과 2016244115 김민재
 *    개발 시작일자 : 2020년 11월 29일
 *    개발 완료일자 : 2020년 12월 20일
 *    
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Server {
	ServerSocket ss = null;
	DBmanager db = new DBmanager();

	SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
	Calendar time = Calendar.getInstance();
	String formatTime = format.format(time.getTime());

	public static void main(String[] args) {
		Server server = new Server();
		ServerFrame sf = new ServerFrame(server);
		try {
			server.ss = new ServerSocket(1788); // 포트번호 1788의 서버소켓 생성

			while (true) {
				Socket socket = server.ss.accept();
				ConnectedClient c = new ConnectedClient(socket, server, sf);
				c.start();
			}
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				server.ss.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

class ConnectedClient extends Thread {
	Socket socket;
	Server server;
	ServerFrame sf;

	String msg;

	OutputStream outStream;
	DataOutputStream dataOutStream;
	InputStream inStream;
	DataInputStream dataInStream;

	public ConnectedClient(Socket _s, Server _ss, ServerFrame sf) {
		this.socket = _s;
		this.server = _ss;
		this.sf = sf;
	}

	@Override
	public void run() {

		try {
			outStream = this.socket.getOutputStream();
			dataOutStream = new DataOutputStream(outStream);
			inStream = this.socket.getInputStream();
			dataInStream = new DataInputStream(inStream);
			ObjectOutputStream oos = new ObjectOutputStream(outStream);

			while (true) {
				msg = dataInStream.readUTF();

				switch (msg.split("@")[0]) {
				case "logout":
					appendText("Server > " + msg.split("@")[1] + "#" + this.socket.getPort() + "에서의 접속이 종료되었습니다.");
					dataOutStream.writeUTF("logout#");
					break;
				case "login":
					if (!(server.db.LoginCheck(msg.split("@")[1], msg.split("@")[2]))) {
						dataOutStream.writeUTF("login#err");
						break;
					}
					appendText("Server > " + msg.split("@")[1] + "#" + this.socket.getPort() + "에서의 접속이 연결되었습니다.");
					dataOutStream.writeUTF("login#ok#" + server.db.search(msg));
					oos.writeObject(server.db.courseInit());

					break;

				case "signUp":
					if (!(server.db.SignUp(msg))) {
						dataOutStream.writeUTF("signUp#err");
						break;
					}
					;
					dataOutStream.writeUTF("signUp#ok");
					break;

				case "signUpCheck":
					if (!(server.db.signUpCheck(msg.split("@")[1]))) {
						dataOutStream.writeUTF("signUpCheck#err");
						break;
					}
					dataOutStream.writeUTF("signUpCheck#ok");
					break;

				case "pw":
					dataOutStream.writeUTF(server.db.search(msg));
					break;

				case "search":
					dataOutStream.writeUTF("search#");
					oos.writeObject(server.db.search2(msg));
					break;
				case "log":
					dataOutStream.writeUTF("log#");
					oos.writeObject(server.db.boardInit());
					break;
				case "review":
					dataOutStream.writeUTF("review#prof#" + server.db.reviewInit());
					Thread.sleep(100);
					dataOutStream.writeUTF("review#cname#" + server.db.reviewInit2());
					break;
				case "reviewSignup":
					dataOutStream.writeUTF("reviewSignup#");
					oos.writeObject(server.db.review(msg));
					break;
				case "treeSelect":
					dataOutStream.writeUTF("tree#");
					oos.writeObject(server.db.tree(msg));
					break;
				case "store":
					dataOutStream.writeUTF("store#");
					oos.writeObject(server.db.basketInit());
					break;
				case "storeIn":
					dataOutStream.writeUTF("storeIn#");
					oos.writeObject(server.db.code(msg));
					break;
				case "storeOut":
					dataOutStream.writeUTF("storeOut#");
					oos.writeObject(server.db.delete(msg));
					break;
				case "Reset":
					dataOutStream.writeUTF("Reset#");
					oos.writeObject(server.db.courseInit());
					break;
				default:
					break;

				}
			}

		} catch (EOFException e) {
		} catch (SocketException se) {
			appendText("Server > 비정상적인 종료가 감지되었습니다.");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void appendText(String text) {
		sf.ta.append("[" + server.formatTime + "]" + text + "\n");
	}
}