package com.ap.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ap.model.Tweet;
import com.ap.model.User;

@Component
public interface TweetService {

	List<User> getFollowers(Long userId);

	List<User> getFollowing(Long id);

	User follow(Long id, Long followUserId);

	String unfollow(Long id, Long followUserId);

	List<Tweet> getUserTweets(Long id, String keyword);
}
