package com.highspot.mixtape.takehome.operations;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.highspot.mixtape.takehome.data.MixTape;
import com.highspot.mixtape.takehome.data.PlayList;
import com.highspot.mixtape.takehome.exceptions.NotFoundException;

/**
 * Remove an existing play list from MixTape object.
 */
@JsonTypeName("remove_playlist")
public class RemovePlayListOperation implements IOperation {
  @JsonProperty("playlist_id")
  private String playListId;

  void setPlayListId(String playListId) {
    this.playListId = playListId;
  }

  /**
   * Remove an existing play list from MixTape object.
   * @param mixTape a MixTape object representing content of MixTape.json.
   * @throws Exception NotFoundException when the play list does not exist.
   */
  public void execute(final MixTape mixTape) throws Exception {
    final PlayList playList = mixTape.getPlayListById(playListId);

    validateChangeRequest(playList);
    mixTape.getPlayLists().remove(playList);
  }

  private void validateChangeRequest(PlayList playList) throws Exception{
    //play list doesn't exist
    if (playList == null) {
      throw new NotFoundException("playlist does not exist");
    }

  }
}
