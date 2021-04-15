package com.highspot.mixtape.takehome.data;

import java.util.ArrayList;
import java.util.List;

public  class TestUtil {

    public static MixTape getTestMixTape()
    {
        Song song1 = new Song();
        Song song2 = new Song();
        Song song3 = new Song();

        song1.setId("1");
        song1.setArtist("artist1");
        song1.setTitle("title1");

        song2.setId("2");
        song2.setArtist("artist2");
        song2.setTitle("title2");

        song3.setId("3");
        song3.setArtist("artist3");
        song3.setTitle("title3");

        List<String> songIds1 = new ArrayList();
        List<String> songIds2 = new ArrayList();

        songIds1.add(song1.getId());
        songIds1.add(song2.getId());

        songIds2.add(song2.getId());
        songIds2.add(song3.getId());

        PlayList playList1 = new PlayList();
        PlayList playList2 = new PlayList();

        playList1.setId("1");
        playList1.setUserId("1");
        playList1.setSongIds(songIds1);

        playList2.setId("2");
        playList2.setUserId("2");
        playList2.setSongIds(songIds2);

        User user1 = new User();
        user1.setUserId("1");
        user1.setName("name1");

        User user2 = new User();
        user2.setUserId("2");
        user2.setName("name2");

        List<PlayList> playListList = new ArrayList<>();
        playListList.add(playList1);
        playListList.add(playList2);

        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);

        List<Song> songsList = new ArrayList<>();
        songsList.add(song1);
        songsList.add(song2);
        songsList.add(song3);

        MixTape mixTape = new MixTape();
        mixTape.setUsers(userList);
        mixTape.setPlayLists(playListList);
        mixTape.setSongs(songsList);

        return mixTape;
    }

    public static PlayList getEmptyPlayList() {
        PlayList pl = new PlayList();
        List<String> emptySongs = new ArrayList<>(0);
        pl.setSongIds(emptySongs);
        return pl;
    }

    public static PlayList getvalidExistingPlayList() {
        Song song1 = new Song();

        song1.setId("1");
        song1.setArtist("artist1");
        song1.setTitle("title1");

        List<String> songIds1 = new ArrayList();

        songIds1.add(song1.getId());

        PlayList playList = new PlayList();

        playList.setId("1");
        playList.setUserId("1");
        playList.setSongIds(songIds1);

        return playList;
    }

    public static PlayList getInvaliduserIdPlayList() {
        PlayList playList = getvalidExistingPlayList();
        playList.setUserId("-1");
        return playList;
    }

    public static PlayList getInvalidSongIdPlayList() {
        Song song1 = new Song();

        song1.setId("-1");
        song1.setArtist("artist1");
        song1.setTitle("title1");

        List<String> songIds1 = new ArrayList();
        songIds1.add(song1.getId());
        PlayList playList = new PlayList();

        playList.setId("101");
        playList.setUserId("1");
        playList.setSongIds(songIds1);

        return playList;
    }
}
