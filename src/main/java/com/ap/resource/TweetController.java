package com.ap.resource;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ap.model.Tweet;
import com.ap.model.User;
import com.ap.service.TweetServiceImpl;

@Controller
public class TweetController {

	private static final Logger logger = Logger.getLogger(TweetController.class);

	// @Autowired
	// private UserDAO userDAO;

	@Autowired
	private TweetServiceImpl tweetService;

	// All users that following a particular user
	@GetMapping("/getfollowers/{id}")
	public ResponseEntity getFollowers(@PathVariable("id") Long id) {
		List<User> followers = tweetService.getFollowers(id);
		// List<User> followers = userDAO.getFollowers(id);
		return new ResponseEntity(followers, HttpStatus.OK);
	}

	// All users being followed by user
	@GetMapping("/getfollowing/{id}")
	public ResponseEntity getFollowing(@PathVariable("id") Long id) {

		// List<User> following = userDAO.getAllFollowing(id);
		List<User> following = tweetService.getFollowing(id);
		return new ResponseEntity(following, HttpStatus.OK);
	}

	// Follow user - returns details on the user being followed
	@RequestMapping(value = "/follow/{id}/followUserId/{followUserId}", method = RequestMethod.POST)
	public ResponseEntity followUser(@PathVariable("id") Long id, @PathVariable("followUserId") Long followUserId) {

		// Boolean executed = userDAO.follow(id, followUserId);
		Boolean executed = tweetService.follow(id, followUserId);
		return new ResponseEntity(executed, HttpStatus.OK);
	}

	// UnFollow user - returns details on the user being unfollowed
	@RequestMapping(value = "/unfollow/{id}/followUserId/{followUserId}", method = RequestMethod.POST)
	public ResponseEntity unfollowUser(@PathVariable("id") Long id, @PathVariable("followUserId") Long followUserId) {

		// boolean executed = userDAO.unfollow(id, followUserId);
		boolean executed = tweetService.unfollow(id, followUserId);
		return new ResponseEntity(executed, HttpStatus.OK);
	}

	// All users tweets and users following tweet
	@GetMapping("/getUserTweets/{id}/keyword/{keyword}")
	public ResponseEntity getUserTweets(@PathVariable("id") Long id, @PathVariable("keyword") String keyword) {

		// List<Tweet> userTweets = userDAO.getUserTweets(id, keyword);
		List<Tweet> userTweets = tweetService.getUserTweets(id, keyword);
		return new ResponseEntity(userTweets, HttpStatus.OK);
	}

}
