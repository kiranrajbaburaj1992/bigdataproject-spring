package com.bigdataproj.springboot.model;


public class TweetsUser {
	
//	public static String ID = "id";
//	public static String SCREEN_NAME = "screen_name";
//	public static String NAME = "name";
//	public static String FRIENDS_COUNT = "friends_count";
//	public static String FOLLOWERS_COUNT = "followers_count";
//	public static String STATUSES_COUNT = "statuses_count";
//	public static String VERIFIED = "verified";
//	public static String UTC_OFFSET = "utc_offset";
//	public static String TIME_ZONE = "time_zone";
	
	private String id;
    private String screen_name;
    private String name;
    private String friends_count;
    private String followers_count;
    private String statuses_count;
    private String verified;
    private String utc_offset;
    private String time_zone;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getScreen_name() {
		return screen_name;
	}
	public void setScreen_name(String screen_name) {
		this.screen_name = screen_name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFriends_count() {
		return friends_count;
	}
	public void setFriends_count(String friends_count) {
		this.friends_count = friends_count;
	}
	public String getFollowers_count() {
		return followers_count;
	}
	public void setFollowers_count(String followers_count) {
		this.followers_count = followers_count;
	}
	public String getStatuses_count() {
		return statuses_count;
	}
	public void setStatuses_count(String statuses_count) {
		this.statuses_count = statuses_count;
	}
	public String getVerified() {
		return verified;
	}
	public void setVerified(String verified) {
		this.verified = verified;
	}
	public String getUtc_offset() {
		return utc_offset;
	}
	public void setUtc_offset(String utc_offset) {
		this.utc_offset = utc_offset;
	}
	public String getTime_zone() {
		return time_zone;
	}
	public void setTime_zone(String time_zone) {
		this.time_zone = time_zone;
	}

}
