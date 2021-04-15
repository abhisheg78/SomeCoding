package com.highspot.mixtape.takehome.operations;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.highspot.mixtape.takehome.data.MixTape;

/**
 * interface for Operation which will be applied on MixTape object.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
  include = JsonTypeInfo.As.PROPERTY,
  property = "operation")
@JsonSubTypes({
  @JsonSubTypes.Type(value = AddPlayListOperation.class, name = "add_playlist"),
  @JsonSubTypes.Type(value = com.highspot.mixtape.takehome.operations.RemovePlayListOperation.class, name = "remove_playlist"),
  @JsonSubTypes.Type(value = com.highspot.mixtape.takehome.operations.AddSongToPlayListOperation.class, name = "add_song")
})
public interface IOperation {
  /**
   * execute an operation on mixTape object OR the input.
   * @param mixTape a MixTape object representing content of MixTape.json.
   * @throws Exception exception.
   */
  void execute(final MixTape mixTape) throws Exception;
}
