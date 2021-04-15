package com.highspot.mixtape.takehome.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Data model for Playlist.
 */
public class PlayList {
  private String id;
  @JsonProperty("user_id")
  private String userId;
  @JsonProperty("song_ids")
  private List<String> songIds;

  public String getId() {
    return this.id;
  }

  public String getUserId() {
    return this.userId;
  }

  public List<String> getSongIds() {
    return this.songIds;
  }

  void setId(String id) {
    this.id = id;
  }

  void setUserId(String userId) {
    this.userId = userId;
  }

  void setSongIds(List<String> songIds) {
    this.songIds = songIds;
  }
}
