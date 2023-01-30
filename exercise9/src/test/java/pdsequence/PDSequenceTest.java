package pdsequence;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class PDSequenceTest {

	@Test
	public void test1() {
		// list contains only one PD(m) number
		assertEquals(Arrays.asList(62), PDSequence.getPDsequence(Arrays.asList(62), 1));
	}

	@Test
	public void test2() {
		// list contains one PD(m) number and some other numbers
		assertEquals(Arrays.asList(89), PDSequence.getPDsequence(Arrays.asList(89, 1, 74, 74, 7), 63));
	}

	@Test
	public void test3() {
		// list contains multiple PD(m) number and some other numbers
		assertEquals(Arrays.asList(17, 24, 32), PDSequence.getPDsequence(Arrays.asList(1, 100, 32, 24, 5, 17), 17));
	}

	@Test
	public void test4() {
		// list contains no PD(m) numbers
		assertEquals(Arrays.asList(), PDSequence.getPDsequence(Arrays.asList(29, 5, 6, 1, 45, 61, 67, 30), 19));
	}

	@Test
	public void test5() {
		// inList is empty
		assertEquals(Arrays.asList(), PDSequence.getPDsequence(Arrays.asList(), 5));
	}

    @Test
    public void test6() {
        // list contains multiple PD(m) number and some other numbers
        assertEquals(Arrays.asList(206, 234), PDSequence.getPDsequence(Arrays.asList(1, 100, 234, 206, -50, 170), 200));
    }
}
