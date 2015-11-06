package com.abc;

import org.junit.Test;
import java.util.Date;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class TransactionTest {
    
    private static final double DOUBLE_DELTA = 1e-15;
  
    @Test
    public void transaction() {
        Transaction t = new Transaction(5.0);
        assertTrue(t instanceof Transaction);
    }
    
    @Test
    public void tetsgetTransDate() {
        Transaction t = new Transaction(5.0);
        assertTrue(t.getTransDate() instanceof Date);
    }
    
    @Test
    public void testgetAmount() {
        Transaction t = new Transaction(5.0);
        assertEquals(5.0, t.getAmount(), DOUBLE_DELTA);
    }
}
