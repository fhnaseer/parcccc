package de.tum.in.repobruegge.pom17aprilsnow.flightapp;

import org.junit.Test;

import java.util.Date;

import de.tum.in.repobruegge.pom17aprilsnow.flightapp.Util.Utils;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Vincent Bode on 05.07.2017.
 */

public class UtilsTest {
    @Test
    public void testDateFormatting() {
        assertTrue("Time formatting", Utils.formatTime(new Date(0)).matches("\\d\\d:\\d\\d"));
        assertTrue("Date formatting", Utils.formatDate(new Date(0)).matches("\\d\\d\\.\\d\\d"));

        assertNotNull("Date parsing", Utils.parseTime("2017-07-13T12:45+02:00"));
    }
}
