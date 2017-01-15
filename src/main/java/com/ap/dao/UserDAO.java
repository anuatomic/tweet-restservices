package com.ap.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ap.model.Tweet;
import com.ap.model.User;

@Component
public interface UserDAO {

	List<User> getFollowers(Long userId);

	List<User> getAllFollowing(Long userId);

	User follow(Long userId, Long followerId);

	User unfollow(Long userId, Long followerId);

	List<Tweet> getUserTweets(Long userId, String keyword);

}
