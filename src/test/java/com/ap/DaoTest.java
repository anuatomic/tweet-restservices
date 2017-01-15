package com.ap;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ap.model.Tweet;
import com.ap.model.User;

public class DaoTest {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Before
	public void setUp() {
		BasicDataSource dataSource = new BasicDataSource();

		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://rds-mysql-tweet.cbexxcg3z7zv.us-east-1.rds.amazonaws.com:3306/");
		dataSource.setUsername("apabla");
		dataSource.setPassword("tweettest");

		jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource);
	}

	@Test
	public void testGetFollowers() {

		String sql = "select u.id, u.first_name, u.last_name from innodb.following_table ft, innodb.user u"
				+ " where ft.u_id =" + 100 + " and ft.fu_id = u.id";
		List<User> followers = new ArrayList();
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		for (Map<String, Object> row : list) {
			System.out.println(row.get("first_name"));
			followers.add(new User(row.get("id").toString(), row.get("first_name").toString(),
					row.get("last_name").toString()));
		}
		assertNotNull(followers);
	}

	@Test
	public void testGetAllFollowing() {

		String sql = "select u.id, u.first_name, u.last_name from innodb.following_table ft, innodb.user u"
				+ " where ft.fu_id =" + 100 + " and ft.u_id = u.id";
		List<User> following = new ArrayList();
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		for (Map<String, Object> row : list) {
			System.out.println(row.get("first_name"));
			following.add(new User(row.get("id").toString(), row.get("first_name").toString(),
					row.get("last_name").toString()));
		}
		assertNotNull(following);

	}

	@Test
	public void testFollowUser() {
		boolean inserted = false;
		String sqlb = "INSERT into innodb.following_table(u_id, fu_id) VALUES (?, ?)";
		int rowsInserted = jdbcTemplate.update("insert into innodb.following_table (u_id, fu_id) values (?, ?)", 102,
				102);
		if (rowsInserted > 0) {
			inserted = true;

		}
		assertTrue(inserted);

	}

	@Test
	public void testIsFollowing() {

		String sqlb = "select COUNT(*) from innodb.following_table ft where ft.u_id = ? and ft.fu_id = ?";

		Integer count = jdbcTemplate.queryForObject(sqlb, new Object[] { 102, 102 }, Integer.class);
		if (count > 0) {
			System.out.println("rec count " + count);
		}

	}

	@Test
	public void findUser() {

		String sql = "SELECT * FROM innodb.user WHERE id = ?";

		User user = (User) jdbcTemplate.queryForObject(sql, new Object[] { 101 },
				new BeanPropertyRowMapper(User.class));
		System.out.println(user.toString());

	}

	@Test
	public void getUserTweets() {

		Long userId = 100l;
		// String keyword = "";
		String keyword = "genie";

		String sql = "select t.* from innodb.tweet as t where user_id =" + userId
				+ " union select t.* from innodb.tweet as t where t.id in (select tweet_id from innodb.retweet where user_id = "
				+ userId + " AND t.text LIKE '%" + keyword + "%')";
		System.out.println(sql);
		List<Tweet> tweets = new ArrayList();
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		for (Map<String, Object> row : list) {
			Tweet tweet = new Tweet(Long.valueOf(row.get("id").toString()), row.get("text").toString());
			System.out.println(tweet.toString());
			tweets.add(tweet);
		}

	}

}
