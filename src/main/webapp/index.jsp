<html>
<body>
<h1>Tweet Rest Services</h1>
<br>
<h2>Example Requests</h2>
<b>Get - Return all users following userId 100</b><br> 
http://localhost:8080/tweetservice/getfollowing/100<br>
http://default-environment.igitfp4kwr.us-east-1.elasticbeanstalk.com/getfollowing/100<br>
<br>
<br>
<b>Post - User(id=100) will follow UserId(104) </b><br>
http://localhost:8080/tweetservice/follow/100/followUserId/104<br>
http://default-environment.igitfp4kwr.us-east-1.elasticbeanstalk.com/follow/100/followUserId/104<br>
<br>
<br>
<b>Post - User(id=100) will unfollow UserId(104) </b><br>
http://localhost:8080/tweetservice/unfollow/100/followUserId/104<br>
http://default-environment.igitfp4kwr.us-east-1.elasticbeanstalk.com/unfollow/100/followUserId/104<br>
<br>
<br>
<b>Get - Return all users that UserId(100) is following </b><br>
http://localhost:8080/tweetservice/getfollowers/100<br>
http://default-environment.igitfp4kwr.us-east-1.elasticbeanstalk.com/getfollowers/100<br>
<br>
<br>
<b>Get - Get all user tweets(userId 100) and all users following tweet </b><br>
http://localhost:8080/tweetservice/getUserTweets/100/keyword/test<br>
http://default-environment.igitfp4kwr.us-east-1.elasticbeanstalk.com/getUserTweets/100/keyword/test<br>
<br>
</body>
</html>
