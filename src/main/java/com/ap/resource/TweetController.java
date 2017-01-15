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
import org.springframework.web.bind.annotation.RequestParam;

import com.ap.model.Tweet;
import com.ap.model.User;
import com.ap.service.TweetServiceImpl;

@Controller
public class TweetController {

	private static final Logger logger = Logger.getLogger(TweetController.class);

	@Autowired
	private TweetServiceImpl tweetService;

	private String errorMessage = null;

	// All users that following a particular user
	@GetMapping("/getfollowers/{id}")
	public ResponseEntity getFollowers(@PathVariable("id") Long id) {
		List<User> followers = tweetService.getFollowers(id);
		if (followers == null) {
			errorMessage = "No followers found for User Id " + id;
			return new ResponseEntity(errorMessage, HttpStatus.OK);
		}
		return new ResponseEntity(followers, HttpStatus.OK);
	}

	// All users being followed by user
	@GetMapping("/getfollowing/{id}")
	public ResponseEntity getFollowing(@PathVariable("id") Long id) {

		List<User> following = tweetService.getFollowing(id);
		if (following == null) {
			errorMessage = "User Id " + id + " Is not following other users";
			return new ResponseEntity(errorMessage, HttpStatus.OK);
		}
		return new ResponseEntity(following, HttpStatus.OK);
	}

	// Follow user - returns details on the user being followed
	@RequestMapping(value = "/follow/{id}/followUserId/{followUserId}", method = RequestMethod.POST)
	public ResponseEntity followUser(@PathVariable("id") Long id, @PathVariable("followUserId") Long followUserId) {

		User userFollower = tweetService.follow(id, followUserId);
		return new ResponseEntity(userFollower, HttpStatus.OK);
	}

	// UnFollow user - returns details on the user being unfollowed
	@RequestMapping(value = "/unfollow/{id}/followUserId/{followUserId}", method = RequestMethod.POST)
	public ResponseEntity unfollowUser(@PathVariable("id") Long id, @PathVariable("followUserId") Long followUserId) {
		String response = null;
		User user = tweetService.unfollow(id, followUserId);
		if (user != null) {
			response = "No longer following " + user.getFirstName() + "-" + user.getId();
		}
		user = tweetService.findUser(followUserId);
		response = "User " + user.getFirstName() + "-" + user.getId() + " is not being followed";
		return new ResponseEntity(response, HttpStatus.OK);
	}

	// Get User tweets for userID=?. If keyword value present filter tweets by
	// keyword
	@RequestMapping(value = "/getUserTweets", method = RequestMethod.GET)
	public ResponseEntity getUserTweets(@RequestParam(value = "id", required = true) Long id,
			@RequestParam(value = "keyword", required = false) String keyword) {
		List<Tweet> userTweets = tweetService.getUserTweets(id, keyword);
		return new ResponseEntity(userTweets, HttpStatus.OK);

	}
}
