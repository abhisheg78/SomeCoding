package com.highspot.mixtape.takehome.data;

import org.junit.Assert;
import org.junit.Test;

public class MixTapeDataModelTests {

    @Test
    public void testMixTapeDataModel() {
         MixTape mixTape = TestUtil.getTestMixTape();

        Assert.assertEquals(2, mixTape.getPlayLists().size());
        Assert.assertEquals(3, mixTape.getSongs().size());
        Assert.assertEquals(2, mixTape.getUsers().size());

        Assert.assertEquals("artist1", mixTape.getSongById("1").getArtist());
        Assert.assertEquals("title2", mixTape.getSongById("2").getTitle());
        Assert.assertEquals("3", mixTape.getSongById("3").getId());

        Assert.assertEquals("1", mixTape.getPlayListById("1").getId());
        Assert.assertEquals(2, mixTape.getPlayListById("1").getSongIds().size());
        Assert.assertEquals("2", mixTape.getPlayListById("2").getUserId());

        Assert.assertEquals("1", mixTape.getUserById("1").getId());
        Assert.assertEquals("name2", mixTape.getUserById("2").getName());
    }
}
