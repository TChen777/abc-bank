package com.abc;

import org.junit.Test;
import java.util.Date;

import static org.junit.Assert.assertTrue;

public class DateProviderTest {
  
    @Test
    public void testDataProvider() {
        assertTrue(DateProvider.getInstance() instanceof DateProvider);
    }
    
    @Test
    public void testDate() {
        assertTrue(DateProvider.getInstance().now() instanceof Date);
    }
    
}
