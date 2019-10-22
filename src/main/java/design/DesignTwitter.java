package design;

import java.util.*;

class Twitter {

  private static int timestamp = 0;
  private Map<Integer, User> mp;

  private static class TwitterItem {
    int tweetId;
    TwitterItem next;
    int timestamp = 0;
    TwitterItem(int tweetId, TwitterItem next) {
      this.tweetId = tweetId;
      this.next = next;
      this.timestamp = Twitter.timestamp++;
    }
    TwitterItem(int tweetId) {
      this(tweetId, null);
    }
  }

  private static class User {
    int userId;
    TwitterItem head;
    Set<User> followers;
    private User(int userId) {
      this.userId = userId;
      head = null;
      followers = new HashSet<>();
      this.follow(this);
    }

    public void follow(User user) {
      followers.add(user);
    }
    void addTwitterItem(int tweetId) {
      TwitterItem item = new TwitterItem(tweetId, head);
      head = item;
    }
    public void unfollow(User user) {
      if (user == this) {
        return;
      }
      followers.remove(user);
    }
  }

  /** Initialize your data structure here. */
  public Twitter() {
    mp = new HashMap<>();
  }

  /** Compose a new tweet. */
  public void postTweet(int userId, int tweetId) {
    if (!mp.containsKey(userId)) {
      mp.put(userId, new User(userId));
    }
    mp.get(userId).addTwitterItem(tweetId);
  }

  /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
  public List<Integer> getNewsFeed(int userId) {
    List<Integer> ans = new ArrayList<>();
    PriorityQueue<TwitterItem> queue = new PriorityQueue<>((a, b) -> b.timestamp - a.timestamp);
    if (!mp.containsKey(userId)) {
      return ans;
    }
    for (User user : mp.get(userId).followers) {
      TwitterItem item = user.head;
      while (item != null) {
        queue.add(item);
        item = item.next;
      }
    }
    while (ans.size() < 10 && !queue.isEmpty()) {
      ans.add(queue.poll().tweetId);
    }
    return ans;
  }

  /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
  public void follow(int followerId, int followeeId) {
    if (!mp.containsKey(followerId)) {
      mp.put(followerId, new User(followerId));
    }
    if (!mp.containsKey(followeeId)) {
      mp.put(followeeId, new User(followeeId));
    }
    mp.get(followerId).follow(mp.get(followeeId));
  }

  /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
  public void unfollow(int followerId, int followeeId) {
    if (!mp.containsKey(followeeId) || !mp.containsKey(followerId)) {
      return;
    }
    if (followerId == followeeId) {
      return;
    }
    mp.get(followerId).unfollow(mp.get(followeeId));
  }
}

// solution 2
//class Twitter {
//  private static class TwitterItem {
//    int useId;
//    int tweetId;
//    TwitterItem(int useId, int tweetId) {
//      this.useId = useId;
//      this.tweetId = tweetId;
//    }
//  }
//
//  private ArrayList<TwitterItem> twitterItems;
//  private Map<Integer, HashSet<Integer>> mp;
//
//  /** Initialize your data structure here. */
//  public Twitter() {
//    twitterItems = new ArrayList<>();
//    mp = new HashMap<>();
//  }
//
//  /** Compose a new tweet. */
//  public void postTweet(int userId, int tweetId) {
//    addNewUse(userId);
//    twitterItems.add(new TwitterItem(userId, tweetId));
//  }
//
//  /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
//  public List<Integer> getNewsFeed(int userId) {
//    List<Integer> ans = new ArrayList<>(10);
//    if (!mp.containsKey(userId)) {
//      return ans;
//    }
//    int index = twitterItems.size() - 1;
//    while (index >= 0 && ans.size() < 10) {
//      if (mp.get(userId).contains(twitterItems.get(index).useId)) {
//        ans.add(twitterItems.get(index).tweetId);
//      }
//      index--;
//    }
//    return ans;
//  }
//
//  /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
//  public void follow(int followerId, int followeeId) {
//    if (!mp.containsKey(followerId)) {
//      addNewUse(followerId);
//    }
//    mp.get(followerId).add(followeeId);
//  }
//
//  /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
//  public void unfollow(int followerId, int followeeId) {
//    if (followeeId == followerId) {
//      return;
//    }
//    if (!mp.containsKey(followerId)) {
//      return;
//    }
//    mp.get(followerId).remove(followeeId);
//  }
//
//  private void addNewUse(int userId) {
//    if (mp.containsKey(userId)) {
//      return;
//    }
//    mp.put(userId, new HashSet<>(Arrays.asList(userId)));
//  }
//}

public class DesignTwitter {
  public static void main(String[] args) {
    Twitter t = new Twitter();
    t.postTweet(1, 5);
    System.out.println(t.getNewsFeed(1));
  }
}
