import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBmanager {
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;

	private String url = " ";
	private String user = " ";
	private String pw = " ";

	String prof = "";
	String code = "";
	String name = "";
	String time = "";
	int slimit = 0;
	int year = 0;
	int term = 0;
	String dep = "";
	String cyear = "";
	int score = 0;

	public DBmanager() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}

	public Object[][] delete(String info) {
		try {
			con = DriverManager.getConnection(url, user, pw);
			stmt = con.createStatement();
			String SQL = "SELECT * FROM course WHERE cnum = '" + info.split("@")[1] + "'";
			rs = stmt.executeQuery(SQL);
			if (!(rs.next())) {
				return null;
			}

			SQL = "DELETE from storelist WHERE cnum = '" + info.split("@")[1] + "'";
			stmt.executeUpdate(SQL);

			SQL = "UPDATE storestate SET num = num + 1 WHERE cnum = '" + info.split("@")[1] + "'";
			stmt.executeUpdate(SQL);

			SQL = "UPDATE course SET slimit = slimit + 1 WHERE cnum = '" + info.split("@")[1] + "'";
			stmt.executeUpdate(SQL);

			rs.close();
			stmt.close();
			con.close();
			return basketInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public Object[][] code(String info) {
		try {
			con = DriverManager.getConnection(url, user, pw);
			stmt = con.createStatement();
			String SQL = "SELECT * FROM course WHERE cnum = '" + info.split("@")[1] + "'";
			rs = stmt.executeQuery(SQL);
			if (!(rs.next())) {
				return null;
			}
			prof = rs.getString("prof");
			code = rs.getString("cnum");
			name = rs.getString("cname");
			time = rs.getString("time");
			slimit = rs.getInt("slimit");
			dep = rs.getString("department");
			score = rs.getInt("cscore");

			SQL = "SELECT cname FROM storelist WHERE cnum = '" + name + "'";
			rs = stmt.executeQuery(SQL);

			if ((rs.next())) {
				return null;
			}

			SQL = "INSERT INTO storelist VALUE ( '" + code + "','" + prof + "','" + name + "','" + time + "'," + slimit
					+ "," + score + ")";
			stmt.executeUpdate(SQL);

			SQL = "UPDATE storestate SET num = num - 1 WHERE cnum = '" + info.split("@")[1] + "'";
			stmt.executeUpdate(SQL);

			SQL = "UPDATE course SET slimit = slimit - 1 WHERE cnum = '" + info.split("@")[1] + "'";
			stmt.executeUpdate(SQL);

			rs.close();
			stmt.close();
			con.close();
			return basketInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public Object[][] search2(String info) {
		try {
			con = DriverManager.getConnection(url, user, pw);
			stmt = con.createStatement();
			if (info.split("@")[1].equals("cname")) {
				rs = stmt.executeQuery("SELECT * FROM course WHERE cname = '" + info.split("@")[2] + "'");
			}
			if (info.split("@")[1].equals("prof")) {
				rs = stmt.executeQuery("SELECT * FROM course WHERE prof = '" + info.split("@")[2] + "'");
			}
			if (!(rs.next())) {
				return null;
			}

			ArrayList<Object[]> list = new ArrayList<>();
			while (rs.next()) {
				prof = rs.getString("prof");
				code = rs.getString("cnum");
				name = rs.getString("cname");
				time = rs.getString("time");
				slimit = rs.getInt("slimit");
				year = rs.getInt("year");
				term = rs.getInt("term");
				dep = rs.getString("department");
				cyear = rs.getString("cyear");
				score = rs.getInt("cscore");

				list.add(new Object[] { prof, code, name, time, slimit, year, term, dep, cyear, score });
			}

			Object[][] lst = new Object[list.size()][];
			for (int i = 0; i < list.size(); i++) {
				lst[i] = list.get(i);
			}

			rs.close();
			stmt.close();
			con.close();
			return lst;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	String search(String info) {
		String search = "";
		String dep = "";
		int year = 0;
		String name = "";
		String state = "";
		try {
			con = DriverManager.getConnection(url, user, pw);
			stmt = con.createStatement();
			String result = "";
			if (info.split("@")[0].equals("pw")) {
				search = "SELECT password FROM member WHERE id = '" + info.split("@")[1] + "' and name ='"
						+ info.split("@")[2] + "'";
			}

			if (info.split("@")[0].equals("login")) {
				search = "SELECT * FROM member WHERE id = '" + info.split("@")[1] + "' and password ='"
						+ info.split("@")[2] + "'";
				rs = stmt.executeQuery(search);

				while (rs.next()) {

					if (rs.getString("type").equals("학부생")) {
						search = "SELECT * FROM student WHERE id = '" + info.split("@")[1] + "'";
						rs = stmt.executeQuery(search);
						while (rs.next()) {
							dep = rs.getString("department");
							year = rs.getInt("year");
							name = rs.getString("name");
							state = rs.getString("state");
						}
						result = "stu#" + dep + "#" + year + "#" + name + "#" + state;
						return result;
					}

					if (rs.getString("type").equals("교수")) {
						rs = stmt.executeQuery(search);
						while (rs.next()) {
							result += "prof#컴퓨터공학부#";
							result += rs.getString("type") + '#';
							result += rs.getString("name") + '#';
							result += "";
						}
					}
					return result;
				}
			}

			rs = stmt.executeQuery(search);

			if (rs.next()) {
				result = "pw#" + rs.getString("password");
				return result;
			}

			stmt.close();
			con.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "pw#none";
	}

	boolean LoginCheck(String _id, String _pw) {
		String pass = _pw;
		String sql = "SELECT password FROM member WHERE id ='" + _id + "'";

		try {
			con = DriverManager.getConnection(url, user, pw);
			stmt = con.createStatement();

			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				if (pass.equals(rs.getString("password"))) {
					return true;
				}
			}
			stmt.close();
			con.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	boolean signUpCheck(String id) {
		String selectSql = "SELECT * FROM member WHERE id ='" + id + "'";

		try {
			con = DriverManager.getConnection(url, user, pw);
			stmt = con.createStatement();
			rs = stmt.executeQuery(selectSql);

			if ((rs.next())) {
				return false;
			}

			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	boolean SignUp(String info) {
		String checkSQL = "SELECT * FROM member WHERE id = '" + info.split("@")[1] + "'";
		String insertSQL = "INSERT INTO member VALUE";
		String stdSQL;

		try {
			con = DriverManager.getConnection(url, user, pw);
			stmt = con.createStatement();

			if (info.split("@")[1].equals("prof")) {
				checkSQL = "SELECT * FROM member WHERE id ='" + info.split("@")[2] + "'";
			}
			rs = stmt.executeQuery(checkSQL);
			if (rs.next()) {
				return false;
			}

			if (info.split("@")[1].equals("prof")) {
				insertSQL += "(";
				for (int i = 2; i <= 5; i++) {
					insertSQL += "'" + info.split("@")[i] + "',";
				}
				insertSQL = insertSQL.substring(0, insertSQL.length() - 1);
				insertSQL += ")";
			}
			if (!(info.split("@")[1].equals("prof"))) {
				insertSQL += "(";
				for (int i = 1; i <= 4; i++) {
					insertSQL += "'" + info.split("@")[i] + "',";
				}
				insertSQL = insertSQL.substring(0, insertSQL.length() - 1);
				insertSQL += ")";

				stdSQL = "INSERT INTO student VALUE ( '" + info.split("@")[3] + "','" + info.split("@")[1] + "','"
						+ info.split("@")[6].charAt(0) + "','" + info.split("@")[7].charAt(0) + "','"
						+ info.split("@")[5] + "', '재학', 0 )";
				stmt.executeUpdate(stdSQL);
			}
			stmt.executeUpdate(insertSQL);

			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return true;

	}

	public Object[][] tree(String info) {
		try {
			con = DriverManager.getConnection(url, user, pw);
			stmt = con.createStatement();
			String SQL = "";
			String temp = info.split("@")[1];
			try {
				if (temp.split(", ")[2].length() < 4) {
					SQL = "SELECT * FROM course WHERE department='" + info.split("@")[1].split(", ")[1] + "'and year="
							+ info.split("@")[1].split(", ")[2].charAt(0) + "";
				}
			} catch (Exception e) {
				SQL = "SELECT * FROM course WHERE department='" + info.split("@")[1].split(", ")[1] + "'";
			}

			rs = stmt.executeQuery(SQL);

			ArrayList<Object[]> list = new ArrayList<>();
			while (rs.next()) {
				prof = rs.getString("prof");
				code = rs.getString("cnum");
				name = rs.getString("cname");
				time = rs.getString("time");
				slimit = rs.getInt("slimit");
				year = rs.getInt("year");
				term = rs.getInt("term");
				dep = rs.getString("department");
				cyear = rs.getString("cyear");
				score = rs.getInt("cscore");

				list.add(new Object[] { prof, code, name, time, slimit, year, term, dep, cyear, score });
			}

			Object[][] lst = new Object[list.size()][];
			for (int i = 0; i < list.size(); i++) {
				lst[i] = list.get(i);
			}

			rs.close();
			stmt.close();
			con.close();
			return lst;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Object[][] courseInit() {
		try {
			con = DriverManager.getConnection(url, user, pw);
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM course");

			ArrayList<Object[]> list = new ArrayList<>();
			while (rs.next()) {
				prof = rs.getString("prof");
				code = rs.getString("cnum");
				name = rs.getString("cname");
				time = rs.getString("time");
				slimit = rs.getInt("slimit");
				year = rs.getInt("year");
				term = rs.getInt("term");
				dep = rs.getString("department");
				cyear = rs.getString("cyear");
				score = rs.getInt("cscore");

				list.add(new Object[] { prof, code, name, time, slimit, year, term, dep, cyear, score });
			}

			Object[][] lst = new Object[list.size()][];
			for (int i = 0; i < list.size(); i++) {
				lst[i] = list.get(i);
			}

			rs.close();
			stmt.close();
			con.close();
			return lst;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Object[][] boardInit() {
		try {
			con = DriverManager.getConnection(url, user, pw);
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM board");

			ArrayList<Object[]> list = new ArrayList<>();
			while (rs.next()) {
				name = rs.getString("name");
				prof = rs.getString("prof");
				score = rs.getInt("score");
				list.add(new Object[] { name, prof, score });
			}

			Object[][] lst = new Object[list.size()][];
			for (int i = 0; i < list.size(); i++) {
				lst[i] = list.get(i);
			}

			rs.close();
			stmt.close();
			con.close();
			return lst;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Object[][] basketInit() {
		try {
			con = DriverManager.getConnection(url, user, pw);
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM storelist");

			ArrayList<Object[]> list = new ArrayList<>();
			while (rs.next()) {
				prof = rs.getString("prof");
				code = rs.getString("cnum");
				name = rs.getString("cname");
				time = rs.getString("time");
				slimit = rs.getInt("slimit");
				score = rs.getInt("cscore");
				list.add(new Object[] { code, prof, name, time, slimit, score });
			}

			Object[][] lst = new Object[list.size()][];
			for (int i = 0; i < list.size(); i++) {
				lst[i] = list.get(i);
			}

			rs.close();
			stmt.close();
			con.close();
			return lst;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Object[][] review(String info) {

		String SQL = "";

		try {
			con = DriverManager.getConnection(url, user, pw);
			stmt = con.createStatement();
			SQL += "INSERT INTO board VALUE ( '" + info.split("@")[1] + "','" + info.split("@")[2] + "',"
					+ info.split("@")[3] + ")";
			stmt.executeUpdate(SQL);

			rs.close();
			stmt.close();
			con.close();
			return boardInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	String reviewInit() {
		ArrayList<String> elements = new ArrayList<>();
		String result = "";
		try {
			con = DriverManager.getConnection(url, user, pw);
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT DISTINCT prof FROM course");

			while (rs.next()) {
				elements.add(rs.getString("prof"));
			}

			for (int i = 0; i < elements.size(); i++) {
				result += elements.get(i) + "//";
			}

			rs.close();
			stmt.close();
			con.close();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("비어있어?");
		return null;
	}

	String reviewInit2() {
		ArrayList<String> elements = new ArrayList<>();
		String result = "";
		try {
			con = DriverManager.getConnection(url, user, pw);
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT DISTINCT cname FROM course");

			while (rs.next()) {
				elements.add(rs.getString("cname"));
			}

			for (int i = 0; i < elements.size(); i++) {
				result += elements.get(i) + "//";
			}

			rs.close();
			stmt.close();
			con.close();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
