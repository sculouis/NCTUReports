package com.nctu

/**
 * 繳費單統計表的統計結果
 * 繳費截止日期20141105
 * @author Louis
 *
 */
class Credits {

	def b09 = 0
	def b10 = 0
	def havepayed = ""
	def Cnt = 0

	def Payeds = []
	def ArrayList myPay1 = new ArrayList()
	def ArrayList myPay2 = new ArrayList()
	def Excelpayed  //已繳費明細表
	def	myExcelDes  //所有學生資料
	
	    
	public Credits(payedfilename,allfilename){
		Excelpayed = new ExcelBuilder(payedfilename) //已繳費明細表
		myExcelDes = new ExcelBuilder(allfilename) //所有學生資料
	}

	/**
	 * 取得所有已繳資料
	 * @return
	 */
	def allpayed(){
		Cnt = 0
		Excelpayed.eachLine(labels: true){
			havepayed = "$是否已繳款"
			if (havepayed == "已繳款"){
				Cnt++
				Payeds.add("${學號}")
			}

		}
	}



	/**
	 * 是否已繳
	 * @param STUID
	 * @return boolean
	 */
	private boolean findResult(STUID) {
		boolean finded = false
		Payeds.findAll(){
			if (it == STUID){
				finded = true
			}
		}
		return finded
	}

	
	/**
	 * 取得在值專班學分費大於0的學號
	 * 將這些學號放入myPay List
	 * @return 無傳回值
	 */
	def getB09GreateZero() {
		Cnt = 0
		myExcelDes.eachLine(labels: true){
			b09 = "$B09"
			if (b09 > 0 && findResult("${STUID}") == true ){
					Cnt++
					myPay1.add("${STUID},${b09},${CL_ID}")
			}
		}
	}

	
	/**
	 * 陸生GMBA學分費
	 * @return
	 */
	def getB10GreateZero() {
		Cnt = 0
		myExcelDes.eachLine(labels: true){
			b10 = "$B10"
			if (b10 > 0 && findResult("${STUID}") == true) {
					Cnt++
					myPay2.add("${STUID},${b10},${CL_ID}")
			}
		}
	}
	
	
	def run(ArrayList payeds){
		def cnt1 = 0
		def sum1 = 0
		def cnt2 = 0
		def sum2 = 0
		def cnt3 = 0
		def sum3 = 0
		def cnt4 = 0
		def sum4 = 0
		def total = 0
		//EMBA：系所代碼後兩碼為61 Ex: 261。
		//專班：系所代碼後兩碼介於62~87或90~99 Ex: 262~287, 290~299。
		//大學：系所代碼開頭為3。
		//研究所：非以上著皆是。
		payeds.each {it ->
		//    if ("$B10" == "3000") {
		//        println "First column on row ${it.rowNum} = $虛擬帳號" + ":length:" + "$虛擬帳號".length()
//				sour.add("$STUID" + "-" + "$ST_NAME" +"-" + "$B10" +"-"+ "$CL_ID")
			    def elemts = it.split(",")
				def clid = elemts[2]
				Integer tot = Integer.parseInt(elemts[1])
				total = total + tot
			switch (clid){
					case ~/[0-9]61/: //EMBA
						cnt1 = cnt1 + 1
						sum1 = sum1 + tot
						break
		//            case ~/[0-9]6[2-9]/:  //專班
		//                cnt2 = cnt2 + 1
		//                break
					case ~/[0-9]6[2-9]|[0-9]7[0-9]|[0-9]8[0-7]|[0-9]9[0-9]/:  //專班
						cnt2 = cnt2 + 1
						sum2 = sum2 + tot
						break
					case ~/3\d{2}/:  //大學
						cnt3 = cnt3 + 1
						sum3 = sum3 + tot
						break
					default:
						cnt4 = cnt4 + 1 //研究所
						sum4 = sum4 + tot
						break
				}
		//    }
		}
		
		println("\n總筆數:${Cnt} 總金額:${String.format("%,d",total)}")
		println("EMBA:筆數${cnt1} 金額:${String.format("%,d",sum1)}")
		println("專班:筆數${cnt2} 金額:${String.format("%,d",sum2)}")
		println("大學:筆數${cnt3} 金額:${String.format("%,d",sum3)}")
		println("研究所:筆數${cnt4} 金額:${String.format("%,d",sum4)}")
		//println("\n明細資料如下")
		//println("STUID-ST_NAME-B10-CL_ID")
		//println("-----------------------")
		//sour.each {x -> println(x)}
		
	}

	
	
}
