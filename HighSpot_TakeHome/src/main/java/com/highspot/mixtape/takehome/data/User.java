package com.highspot.mixtape.takehome.data;

/**
 * Data model for User.
 */
public class User {
  private String id;
  private String name;

  public String getId()
  {
    return this.id;
  }

  String getName()
  {
    return this.name;
  }

  void setUserId (String id) {
    this.id = id;
  }

  void setName (String name) {
    this.name = name;
  }
}
