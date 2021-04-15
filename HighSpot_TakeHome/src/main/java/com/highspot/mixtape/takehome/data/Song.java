package com.highspot.mixtape.takehome.data;

/**
 * Data model for Song.
 */
public class Song {
  private String id;
  private String artist;
  private String title;

  public String getId()
  {
    return this.id;
  }

  public String getArtist()
  {
    return this.artist;
  }

  public String getTitle()
  {
    return this.title;
  }

  void setId(String id)
  {
    this.id = id;
  }

  void setArtist(String artist)
  {
    this.artist = artist;
  }

  void setTitle(String title)
  {
    this.title = title;
  }
}
