package app;

import org.junit.Test;

import model.calendar.Calendar;

import static org.junit.Assert.assertEquals;

public class MainTest {
    @Test
    public void testMethod() {
        Calendar calendar = Calendar.getInstance();
        assertEquals(1, calendar.getDay());
        calendar.inc();
        assertEquals(2, calendar.getDay());
    }
}
