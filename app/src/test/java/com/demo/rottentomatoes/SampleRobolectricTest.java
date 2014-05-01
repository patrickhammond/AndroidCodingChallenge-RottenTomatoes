package com.demo.rottentomatoes;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class SampleRobolectricTest {
    @Test
    public void testApplicationNameIsCorrect() throws Exception {
        Assert.assertEquals("Rotten Tomatoes", Robolectric.application.getString(R.string.app_name));
    }
}
