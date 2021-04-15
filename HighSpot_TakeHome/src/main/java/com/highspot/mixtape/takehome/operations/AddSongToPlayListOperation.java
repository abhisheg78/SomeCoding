package com.highspot.mixtape.takehome.operations;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.highspot.mixtape.takehome.data.MixTape;
import com.highspot.mixtape.takehome.data.PlayList;
import com.highspot.mixtape.takehome.data.Song;
import com.highspot.mixtape.takehome.exceptions.AlreadyExistException;
import com.highspot.mixtape.takehome.exceptions.NotFoundException;

/**
 * Add an existing song to an existing play list.
 */
@JsonTypeName("add_song")
public class AddSongToPlayListOperation implements IOperation {
  @JsonProperty("song_id")
  private String songId;

  @JsonProperty("playlist_id")
  private String playlistId;

  void setSongId(String songId) {
    this.songId = songId;
  }

  void setPlaylistId(String playlistId) {
    this.playlistId = playlistId;
  }

  /**
   * Add an existing song to an existing play list.
   * It will fail in the following situation:
   *  1. The song does not exist.
   *  2. The play list does not exist.
   *  3. The play list already contains the song.
   * @param mixTape a MixTape object representing content of MixTape.json.
   * @throws Exception
   *  1. NotFoundException the song or the play list does not exist.
   *  2. AlreadyExistException the song already belongs to the play list.
   */

  public void execute(final MixTape mixTape) throws Exception {

    final Song song = mixTape.getSongById(songId);
    final PlayList playList = mixTape.getPlayListById(playlistId);

    validateChangeRequest(song, playList);
    playList.getSongIds().add(songId);
  }

  private void validateChangeRequest(Song song, PlayList playList) throws Exception{
    //song doesn't exist in mixTape
    if (song == null) {
      throw new NotFoundException("song does not exist");
    }

    //playList doesn't exist in mixTape
    if (playList == null) {
      throw new NotFoundException("play list does not exist");
    }

    //playList already contains the song
    if (playList.getSongIds().contains(songId)) {
      throw new AlreadyExistException(String.format("song already exist in playlist", songId));
    }
  }
}
