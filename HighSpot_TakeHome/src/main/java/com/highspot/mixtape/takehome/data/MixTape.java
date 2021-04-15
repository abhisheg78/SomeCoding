package com.highspot.mixtape.takehome.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Data model for MixTape file.
 */
public class MixTape {
  @JsonProperty("users")
  private List<User> users;
  @JsonProperty("playlists")
  private List<PlayList> playLists;
  @JsonProperty("songs")
  private List<Song> songs;

  public List<User> getUsers()
  {
    return this.users;
  }

  public List<PlayList> getPlayLists()
  {
    return this.playLists;
  }

  public List<Song> getSongs()
  {
    return this.songs;
  }

  void setUsers(List<User> users)
  {
    this.users = users;
  }

  void setPlayLists(List<PlayList> playLists)
  {
    this.playLists = playLists;
  }

  void setSongs(List<Song> songs)
  {
    this.songs = songs;
  }

  /**
   * find Song by song id.
   * @param songId song id.
   * @return a Song object or null.
   */
  public Song getSongById(final String songId) {
    return findObjectFromList(this.songs, song -> song.getId().equals(songId));
  }

  /**
   * find PlayList by play list id.
   * @param playListId play list id.
   * @return a PlayList object or null
   */
  public PlayList getPlayListById(final String playListId) {
    return findObjectFromList(this.playLists,
                              playList -> playList.getId().equals(playListId));
  }

  /**
   * find user by user id.
   * @param userId user id.
   * @return a user object or null.
   */
  public User getUserById(final String userId) {
    return findObjectFromList(this.users,
                              user -> user.getId().equals(userId));
  }

  private static <T> T findObjectFromList(final List<T> list,
                          final Predicate<? super T> predicate) {
   final Optional<T> optObj =
     list.stream()
     .filter(predicate)
     .findFirst();

   return optObj.orElse(null);
  }
}
