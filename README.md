# tweet-restservices
rest tweet services

URLs to use when testing services from local environment running on tomcat.  

401 will be returned for unauthenticated users.

1. Get - Return all users following userId 100
http://localhost:8080/tweetservice/getfollowing/100

2. Post - User(id=100) will follow UserId(104)
http://localhost:8080/tweetservice/follow/100/followUserId/104

3. Post - User(id=100) will unfollow UserId(104)
http://localhost:8080/tweetservice/unfollow/100/followUserId/104

4. Get -  Return all users that UserId(100) is following
http://localhost:8080/tweetservice/getfollowers/100

5. Get - Get all user tweets(userId 100) and all users following tweet
http://localhost:8080/tweetservice/getUserTweets/100/keyword/test


Tables

1. User Table

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `string_id` varchar(5) DEFAULT NULL,
  `first_name` varchar(100) DEFAULT NULL,
  `last_name` varchar(100) DEFAULT NULL,
  `screen_name` varchar(100) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1

2. Following Table

CREATE TABLE `following_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `u_id` int(11) NOT NULL,
  `fu_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_idx` (`u_id`),
  CONSTRAINT `id` FOREIGN KEY (`u_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1

3. Tweet table

CREATE TABLE `tweet` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `text` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1

4. Retweet Table

CREATE TABLE `retweet` (
  `id` int(11) NOT NULL,
  `tweet_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1




