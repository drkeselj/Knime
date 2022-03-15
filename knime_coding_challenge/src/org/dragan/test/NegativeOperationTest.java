/**
 * 
 */
package org.dragan.test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.dragan.model.NegativeOperation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Kesa
 *
 */
class NegativeOperationTest {
	
	private NegativeOperation operation = new NegativeOperation();
	private double diff = 0.00001d;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		operation = new NegativeOperation();
	}

	/**
	 * Test method for {@link org.dragan.model.NegativeOperation#execute(int)}.
	 */
	@Test
	void testExecuteInt() {
		assertEquals(-123, operation.execute(123));
		assertEquals(123, operation.execute(-123));
		assertEquals(0, operation.execute(0));
	}

	/**
	 * Test method for {@link org.dragan.model.NegativeOperation#execute(double)}.
	 */
	@Test
	void testExecuteDouble() {
		assertTrue(isEqual(-123.45, operation.execute(123.45)));
		assertTrue(isEqual(123.45, operation.execute(-123.45)));
		assertTrue(isEqual(0d, operation.execute(0d)));
	}

	/**
	 * Test method for {@link org.dragan.model.AbstractOperation#execute(java.lang.String)}.
	 */
	@Test
	void testExecuteString() {
		UnsupportedOperationException thrown = assertThrows(
				UnsupportedOperationException.class,
				() -> operation.execute("abc"),
		        "Exception should be thrown"
		    );
		assertTrue(thrown.getMessage().contains("not supported"));
	}
	
	
	private boolean isEqual(double d1, double d2) {
		return Math.abs(Math.abs(d1) - Math.abs(d2)) < diff;
	}
}
