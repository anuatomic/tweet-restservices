package com.ap;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = { AppConfig.class })
public class UserDAOTest extends JdbcDaoSupport {

	private List followers = new ArrayList();
	private JdbcTemplate jdbcTemplate;
	@Autowired
	@Qualifier("dataSource")
	private DataSource testDataSource;

	@Test
	public void testUserDAO() {
		System.out.println("****************** get Followers");
		/*
		 * String sql =
		 * "select u.id, u.first_name, u.last_name from innodb.following_table ft, innodb.user u"
		 * + " where ft.u_id =" + 100 + " and ft.fu_id = u.id";
		 * 
		 * followers = new ArrayList<User>();
		 * 
		 * JdbcTemplate jdbcTemplate2 = this.getJdbcTemplate(); List<Map<String,
		 * Object>> list = this.getJdbcTemplate().queryForList(sql); for
		 * (Map<String, Object> row : list) {
		 * System.out.println(row.get("first_name")); followers.add(new
		 * User(row.get("id").toString(), row.get("first_name").toString(),
		 * row.get("last_name").toString())); }
		 */

		try {
			String host = "jdbc:rds-mysql-tweet.cbexxcg3z7zv.us-east-1.rds.amazonaws.com:3306";
			String uName = "apabla";
			String uPass = "tweettest";
			java.sql.Connection con = DriverManager.getConnection(host, uName, uPass);
			java.sql.Statement stmt = con.createStatement();
			/*
			 * String sql =
			 * "select u.id, u.first_name, u.last_name from innodb.following_table ft, innodb.user u"
			 * + " where ft.u_id =" + 100 + " and ft.fu_id = u.id";
			 */
			String sql = "select * from innodb.following_table";
			ResultSet rs = stmt.executeQuery(sql);

			rs.next();
			int id_col = rs.getInt("id");
		} catch (SQLException err) {
			System.out.println(err.getMessage());
		}
	}

	@Test
	public void testUserDAOB() {
		String host = "jdbc:rds-mysql-tweet.cbexxcg3z7zv.us-east-1.rds.amazonaws.com:3306";
		String uname = "apabla";
		String db = "innodb";
		String password = "tweettest";
		String driver = "com.mysql.jdbc.Driver";
		int i = 0;
		try {
			Class.forName(driver).newInstance();
			java.sql.Connection conn = DriverManager.getConnection(host + db, uname, password);

			java.sql.Statement st = conn.createStatement();
			String sql = "select u.id, u.first_name, u.last_name from innodb.following_table ft, innodb.user u"
					+ " where ft.u_id =" + 100 + " and ft.fu_id = u.id";
			boolean execute = st.execute(sql);

			if (execute)
				System.out.println("Sucessfully Updated table");

			conn.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}

	@Test
	public void testInsert() {
		long userId = 100;
		long followerId = 104;

		String sql = "insert into innodb.following_table(u_id, fu_id)" + " values(" + userId + "," + followerId + ")";
		System.out.println("sql " + sql);

		System.out.println("before inserted");

		/*
		 * Map<String, Long> parameters = new HashMap<String, Long>();
		 * parameters.put("u_id", userId); parameters.put("fu_id", followerId);
		 * 
		 * try { int numRowsInserted = this.getJdbcTemplate().update(sql,
		 * parameters); } catch (Exception e) { // TODO Auto-generated catch
		 * block System.out.println(e.toString()); e.printStackTrace(); }
		 */

		// System.out.println(numRowsInserted + " row inserted.");

		String sqlb = "INSERT into innodb.following_table(u_id, fu_id) VALUES (?, ?)";

		this.jdbcTemplate.update("insert into innodb.following_table (u_id, fu_id) values (?, ?)", userId, followerId);

	}

}
