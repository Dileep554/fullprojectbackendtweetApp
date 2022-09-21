package com.tweetapp.api.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tweets")
public class Tweet {
	
	@Id
	private String id;
	private String tweetName;
	@CreatedDate
	private LocalDateTime postDate;
	private long likes;
	private User user;
	private List<Tweet> replies;
	private String tweetTag;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTweetName() {
		return tweetName;
	}
	public void setTweetName(String tweetName) {
		this.tweetName = tweetName;
	}
	public LocalDateTime getPostDate() {
		return postDate;
	}
	public void setPostDate(LocalDateTime postDate) {
		this.postDate = postDate;
	}
	public long getLikes() {
		return likes;
	}
	public void setLikes(long likes) {
		this.likes = likes;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Tweet [id=" + id + ", tweet=" + tweetName + ", postDate=" + postDate + ", likes=" + likes + ", user=" + user
				+ ", replies=" + replies + "]";
	}
	public List<Tweet> getReplies() {
		return replies;
	}
	public void setReplies(List<Tweet> replies) {
		this.replies = replies;
	}
	public String getTweetTag() {
		return tweetTag;
	}
	public void setTweetTag(String tweetTag) {
		this.tweetTag = tweetTag;
	}

}
