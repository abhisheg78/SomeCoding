package com.highspot.mixtape.takehome.operations;

import com.highspot.mixtape.takehome.data.MixTape;
import com.highspot.mixtape.takehome.data.PlayList;
import com.highspot.mixtape.takehome.data.TestUtil;
import com.highspot.mixtape.takehome.exceptions.AlreadyExistException;
import com.highspot.mixtape.takehome.exceptions.EmptyPlayListException;
import com.highspot.mixtape.takehome.exceptions.NotFoundException;
import com.highspot.mixtape.takehome.operations.RemovePlayListOperation;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MixTapeChangeOperationTests {

    @Test
    public void RemovePLayListTest() throws Exception {
        MixTape mixTape = TestUtil.getTestMixTape();
        RemovePlayListOperation operation = new RemovePlayListOperation();

        // check successful removal
        operation.setPlayListId("1");
        operation.execute(mixTape);
        assertEquals(null, mixTape.getPlayListById("1"));

        // not present play list
        operation.setPlayListId("-1");
        NotFoundException ne = assertThrows(NotFoundException.class, () -> operation.execute(mixTape));
        assertEquals("playlist does not exist", ne.getMessage());
    }

    @Test
    public void AddSongToPlayListTest() throws Exception {
        MixTape mixTape = TestUtil.getTestMixTape();
        AddSongToPlayListOperation operation = new AddSongToPlayListOperation();

        //successful addition
        operation.setPlaylistId("1");
        operation.setSongId("3");
        operation.execute(mixTape);
        assertTrue(mixTape.getPlayListById("1").getSongIds().contains("3"));

        // song do not exist
        operation.setPlaylistId("1");
        operation.setSongId("-1");
        NotFoundException ne1 = assertThrows(NotFoundException.class, () -> operation.execute(mixTape));
        assertEquals("song does not exist", ne1.getMessage());

        // play list do not exist
        operation.setPlaylistId("-1");
        operation.setSongId("1");
        NotFoundException ne2 = assertThrows(NotFoundException.class, () -> operation.execute(mixTape));
        assertEquals("play list does not exist", ne2.getMessage());

        // song already exist
        operation.setPlaylistId("2");
        operation.setSongId("3");
        AlreadyExistException ae = assertThrows(AlreadyExistException.class, () -> operation.execute(mixTape));
        assertEquals("song already exist in playlist", ae.getMessage());
    }

    @Test
    public void AddPlayListTests() throws Exception {
        MixTape mixTape = TestUtil.getTestMixTape();
        AddPlayListOperation operation = new AddPlayListOperation();

        // empty play list
        operation.setPlayList(TestUtil.getEmptyPlayList());
        EmptyPlayListException ep = assertThrows(EmptyPlayListException.class, () -> operation.execute(mixTape));
        assertEquals("playlist is empty", ep.getMessage());

        // existing play list
        operation.setPlayList(TestUtil.getvalidExistingPlayList());
        AlreadyExistException ae = assertThrows(AlreadyExistException.class, () -> operation.execute(mixTape));
        assertEquals("playlist already exists", ae.getMessage());

        // existing play list
        operation.setPlayList(TestUtil.getInvalidSongIdPlayList());
        NotFoundException ne2 = assertThrows(NotFoundException.class, () -> operation.execute(mixTape));
        assertEquals("song id does not exist", ne2.getMessage());
    }
}
