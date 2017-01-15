package com.ap.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ap.dao.UserDAOImpl;
import com.ap.model.Tweet;
import com.ap.model.User;

@Component
public class TweetServiceImpl implements TweetService {

	@Autowired
	private UserDAOImpl userDAO;

	public List<User> getFollowers(Long userId) {
		return userDAO.getFollowers(userId);
	}

	public List<User> getFollowing(Long id) {
		return userDAO.getAllFollowing(id);
	}

	public User follow(Long id, Long followUserId) {
		return userDAO.follow(id, followUserId);
	}

	public User unfollow(Long id, Long followUserId) {
		User user = userDAO.unfollow(id, followUserId);
		return user;
	}

	public List<Tweet> getUserTweets(Long id, String keyword) {
		return userDAO.getUserTweets(id, keyword);
	}

	public User findUser(long followUserId) {
		return userDAO.findUser(followUserId);
	}

}
