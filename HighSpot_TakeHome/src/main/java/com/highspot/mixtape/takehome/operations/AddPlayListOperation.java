package com.highspot.mixtape.takehome.operations;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.highspot.mixtape.takehome.data.MixTape;
import com.highspot.mixtape.takehome.data.PlayList;
import com.highspot.mixtape.takehome.exceptions.AlreadyExistException;
import com.highspot.mixtape.takehome.exceptions.EmptyPlayListException;
import com.highspot.mixtape.takehome.exceptions.NotFoundException;

/**
 * add a new playlist, the playlist should contain atleast one song.
 */
@JsonTypeName("add_playlist")
public class AddPlayListOperation implements IOperation {
  @JsonProperty("playlist")
  private PlayList playList;

  void setPlayList(PlayList playList) {
    this.playList = playList;
  }

  /**
   * Add a new play list to MixTape file.
   * It will fail in the following situation:
   *  1. the playlist doesn't have any songs.
   *  2. the playlist id already exists.
   *  3. the userId in the playlist does not exist in MixTape.
   *  4. some song id does not exist in MixTape.
   * @param mixTape a MixTape object representing content of MixTape object.
   * @throws Exception it will throw three type of exceptions:
   *  1. EmptyPlayListException the playlist does not have any songs.
   *  2. AlreadyExistException the playlist already exists.
   *  3. NotFoundException the user id or some song id does not exist in MixTape object.
   */
  public void execute(final MixTape mixTape) throws Exception {
    validateChangeRequest(mixTape);
    mixTape.getPlayLists().add(playList);
  }

  private void validateChangeRequest(final MixTape mixTape) throws Exception {
    //new playlist should contain songs
    if(playList.getSongIds().size() == 0) {
      throw new EmptyPlayListException("playlist is empty");
    }

    //playlist id already exists
    if (mixTape.getPlayListById(playList.getId()) != null) {
      throw new AlreadyExistException("playlist already exists");
    }

    //the userId doesn't exist in mixTape
    if (mixTape.getUserById(playList.getUserId()) == null) {
      throw new NotFoundException("user id does not exist");
    }

    //check if all the song id exist
    for (String songId : playList.getSongIds()) {
      //songId doesn't exist in mixTape
      if (mixTape.getSongById(songId) == null) {
        throw new NotFoundException("song id does not exist");
      }
    }
  }
}
