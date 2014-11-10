package com.nctu

class ChinaPay {
	def b10 = 0
	def i = 0
	def myPay = []
	def SourcePay = []
    def cnt = 0
	
	/**
	 * 取得繳費明細表的資料
	 * 放到SourcePay List
	 * @return 無傳回值
	 */
	def getSoureData(){
		def myExcel = new ExcelBuilder('/Users/Louis/Downloads/payed.xls')
		myExcel.eachLine(labels: true) {
			if ("$學院" != "========"){
				def A繳款金額 = check("$繳款金額")
				def A就貸可貸金額 = check("$就貸可貸金額")
				def A學費 = check("$學費")
				def A雜費 = check("$雜費")
				def	A學雜費基數 = check("$學雜費基數")
				def A平安保險 = check("$平安保險")
				def A急難救助 = check("$急難救助")
				def A僑生保險 = check("$僑生保險")
				def A宿舍費 = check("$宿舍費")
				def A暑期住宿費 = check("$暑期住宿費")
				def A體育場使用費 = check("$體育場使用費")
				def A語言實習費 = check("$語言實習費")
				def A網路與軟體費 = check("$網路與軟體費")
				SourcePay.add("$學院,$學號,$姓名,$A繳款金額,$撥款日期,$A就貸可貸金額,$A學費,$A雜費,$A學雜費基數,$A平安保險,$A急難救助,$A僑生保險,$A宿舍費,$A暑期住宿費,$A體育場使用費,$A語言實習費,$A網路與軟體費,0")
			}
		}
	}

	/**
	 * 判斷內容不是null且不是空白則整數傳回
	 * @param value 傳入cell的內容
	 * @return 無傳回值
	 */
	private check(value){
		def rtnvalue = value
		if(value != null && !value.isEmpty()) {
			rtnvalue = Float.valueOf(value).intValue()
		}

		return rtnvalue
	}

	/**
	 * 取得陸生團保費大於0的學號
	 * 將這些學號放入myPay List
	 * @return 無傳回值
	 */
	def getGreateZero() {
		def	myExcelDes = new ExcelBuilder("/Users/Louis/Downloads/alldata.xls")
		myExcelDes.eachLine(labels: true){
			b10 = "$B10"
			if (b10 > 0){
				cnt++
				myPay.add("${STUID}")
			}
		}
	}
	
	/**
	 * 寫入Excel檔
	 * @return 無傳回值
	 */
	def writeData(){
			def workbook = new HSSFWorkbookBuilder().workbook {
				sheet("Data") {
					// sheet1
					row([
						"學院",
						"學號",
						"姓名",
						"繳款金額",
						"撥款日期",
						"就貸可貸金額",
						"學費",
						"雜費",
						"學雜費基數",
						"平安保險",
						"急難救助",
						"僑生保險",
						"宿舍費",
						"暑期住宿費",
						"體育場使用費",
						"語言實習費",
						"網路與軟體費",
						"陸生團保費"
					])
					row([
						"========",
						"========",
						"========",
						"========",
						"========",
						"========",
						"========",
						"========",
						"========",
						"========",
						"========",
						"========",
						"========",
						"========",
						"========",
						"========",
						"========",
						"========"
					])
		
					SourcePay.each{x ->
						def y = x.split(",")
						//尋找是否為陸生
						myPay.findAll {
							if (it == y[1]){
								y[17] = "3,000"
							}
						}
						row(y)
					}
				}
				save("/Users/Louis/Downloads/result.xls")
			}
		}
		
}
