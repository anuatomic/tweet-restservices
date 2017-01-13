package com.ap;

import java.sql.DriverManager;

import org.junit.Test;

public class TestJDBC {

	@Test
	public void test() {
		String host = "jdbc:mysql://rds-mysql-tweet.cbexxcg3z7zv.us-east-1.rds.amazonaws.com:3306/";

		String uname = "apabla";
		String db = "innodb";
		String password = "tweettest";
		String driver = "com.mysql.jdbc.Driver";
		int i = 0;
		try {
			Class.forName(driver).newInstance();
			java.sql.Connection conn = DriverManager.getConnection(host + db, uname, password);

			java.sql.Statement st = conn.createStatement();

			System.out.println("testing siva");
			// String sql = "SELECT * FROM innodb.following_table";

			String sql = "select u.id, u.first_name, u.last_name from innodb.following_table ft, innodb.user u"
					+ " where ft.u_id =" + 100 + " and ft.fu_id = u.id";

			boolean execute = st.execute(sql);

			if (execute)
				System.out.println("Sucessfully executed table");

			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}

	@Test
	public void testB() {
		String host = "jdbc:mysql://rds-mysql-tweet.cbexxcg3z7zv.us-east-1.rds.amazonaws.com:3306/";

		String uname = "apabla";
		String db = "innodb";
		String password = "tweettest";
		String driver = "com.mysql.jdbc.Driver";
		int i = 0;
		try {
			Class.forName(driver).newInstance();
			java.sql.Connection conn = DriverManager.getConnection(host + db, uname, password);

			java.sql.Statement st = conn.createStatement();

			System.out.println("testing siva");
			// String sql = "SELECT * FROM innodb.following_table";

			String sql = "select u.id, u.first_name, u.last_name from innodb.following_table ft, innodb.user u"
					+ " where ft.u_id =" + 100 + " and ft.fu_id = u.id";

			boolean execute = st.execute(sql);

			if (execute)
				System.out.println("Sucessfully executed table");

			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}

}
