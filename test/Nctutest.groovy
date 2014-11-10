package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import com.nctu.*

class Nctutest {

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		def myhello = new ChinaPay()
		myhello.getSoureData()
		myhello.getGreateZero()
		myhello.writeData()
       assert myhello.getCnt() == 113
	}

	@Test
	public void testCheckfifteen() {
		def myhello = new Checkfifteen()
		myhello.getData()
		myhello.getResult()
	}

	
}
