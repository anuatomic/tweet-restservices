package com.ap.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;

import com.ap.model.Tweet;
import com.ap.model.User;

@Component
public class UserDAOImpl extends JdbcDaoSupport implements UserDAO {

	static Logger log = Logger.getLogger(UserDAOImpl.class.getName());

	// return followers for a given user
	public List<User> getFollowers(Long userId) {
		log.debug("UserDAOImpl.getFollowers() - Executing");
		String sql = "select u.id, u.first_name, u.last_name from innodb.following_table ft, innodb.user u"
				+ " where ft.u_id =" + userId + " and ft.fu_id = u.id";
		List<User> followers = new ArrayList();
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql);
		for (Map<String, Object> row : list) {
			System.out.println(row.get("first_name"));
			followers.add(new User(row.get("id").toString(), row.get("first_name").toString(),
					row.get("last_name").toString()));
		}

		return followers;
	}

	// return all users one particular user is following
	public List<User> getAllFollowing(Long userId) {
		log.debug("UserDAOImpl.getAllFollowing() - Executing");
		String sql = "select u.id, u.first_name, u.last_name from innodb.following_table ft, innodb.user u"
				+ " where ft.fu_id =" + userId + " and ft.u_id = u.id";
		List<User> following = new ArrayList();
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql);
		for (Map<String, Object> row : list) {
			System.out.println(row.get("first_name"));
			following.add(new User(row.get("id").toString(), row.get("first_name").toString(),
					row.get("last_name").toString()));
		}

		return following;

	}

	// return user being followed
	public boolean follow(Long userId, Long followerId) {
		// Add logic to make sure user is not already being followed
		log.debug("UserDAOImpl.follow() - Executing");
		String sqlb = "INSERT into innodb.following_table(u_id, fu_id) VALUES (?, ?)";
		int rowsInserted = this.getJdbcTemplate()
				.update("insert into innodb.following_table (u_id, fu_id) values (?, ?)", userId, followerId);
		if (rowsInserted > 0) {
			return true;

		}
		return false;

	}

	// return user being unfollowed
	public boolean unfollow(Long userId, Long followerId) {
		log.debug("UserDAOImpl.unfollow() - Executing");
		String sqlb = "DELETE from innodb.following_table WHERE u_id = ? and fu_id = ?";
		int rowsDeleted = this.getJdbcTemplate().update(sqlb, userId, followerId);
		if (rowsDeleted > 0) {
			return true;

		}
		return false;

	}

	public List<Tweet> getUserTweets(Long userId, String keyword) {

		String sql = "select t.* from innodb.tweet as t where user_id =" + userId
				+ " union select t.* from innodb.tweet as t where t.id in (select tweet_id from innodb.retweet where user_id = "
				+ userId + ")";
		log.debug("UserDAOImpl.getUserTweets() - Executing");
		List<Tweet> tweets = new ArrayList();
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql);
		for (Map<String, Object> row : list) {
			log.debug(row.get("text"));
			tweets.add(new Tweet(Long.valueOf(row.get("id").toString()), row.get("text").toString()));
		}
		return tweets;

	}

	@Autowired
	public void QueryForListReturnListDAO(DataSource dataSource) {
		this.setDataSource(dataSource);
	}
}
