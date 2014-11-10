package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import com.nctu.*;

class Creditstest {

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
	public void testCredits1() {
		//103學年1學期學雜費
		def Excelpayed = "/Users/Louis/Downloads/10301/Tuition/119362.xls" //已繳費明細表
		def	myExcelDes = "/Users/Louis/Downloads/10301/Tuition/387330.xls" //所有學生資料

		
		def mycredit = new Credits(Excelpayed,myExcelDes);
		mycredit.allpayed();
		println mycredit.getPayeds();
		println mycredit.getCnt();
				
		//陸生團保費
		mycredit.getB10GreateZero();
		mycredit.getMyPay2().each {
			println it
			}
		mycredit.run(mycredit.getMyPay2());
	}

	
	@Test
	public void testCredits2() {
		//103學年1學期學分費
		def Excelpayed = "/Users/Louis/Downloads/10301/Credit/payed-1.xls" //已繳費明細表
		def	myExcelDes = "/Users/Louis/Downloads/10301/Credit/alldata2.xls" //所有學生資料

		
		def mycredit = new Credits(Excelpayed,myExcelDes);
		mycredit.allpayed();
		println mycredit.getPayeds();
		println mycredit.getCnt();
		
		
		//在職專班學分費
		mycredit.getB09GreateZero();
		mycredit.getMyPay1().each {
		println it	
		}
	    mycredit.run(mycredit.getMyPay1());
		
				
        //陸生GMBA學分費
		mycredit.getB10GreateZero();
		mycredit.getMyPay2().each {
			println it
			}
		mycredit.run(mycredit.getMyPay2());
	}
	


}
