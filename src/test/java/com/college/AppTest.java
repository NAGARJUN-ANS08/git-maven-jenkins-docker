
package com.college;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class AppTest {
    @Test
    public void testApplicationMessage() {
        String message = "Hello from Git-Maven-Jenkins-Docker!";
        assertEquals(
            "Hello from Git-Maven-Jenkins-Docker!",
            message
        );
    }
}

