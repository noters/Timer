package com.timer;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by ccc on 16/6/11.
 */
public class ProfleUnitTest {

    @Test
    public void show() throws Exception {
        Profile p = new Profile();
        assertEquals("周二 周三 周六", p.showWeek("1,2,5"));
        assertEquals("1,2,5", p.useWeek("周二 周三 周六"));
    }
}
