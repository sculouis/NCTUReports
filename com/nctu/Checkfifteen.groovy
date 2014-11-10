package com.nctu

class Checkfifteen {
	def sour = []
	def des = []
	def myExcel = new ExcelBuilder("/Users/Louis/tmp/test.xls")
	def myExcelDes = new ExcelBuilder("/Users/Louis/tmp/372803.xls")

	def getData(){	
		myExcel.eachLine(labels: true) {
			if ("$虛擬帳號".length() == 15) {
				sour.add("$虛擬帳號")
			}
		}
			myExcelDes.eachLine(labels: true){
					des.add("$虛擬帳號")
				}
	}

	def getResult(){
				println("15碼".padRight(15," ") + " - " + "16碼".padRight(16," "))
			println("".padRight(15,"-") + " - " + "".padRight(16,"-"))
			sour.findAll { x ->
			des.each { y ->
				if (x == y.substring(0, 15)) {
					println("${x} - ${y}")
				}
			}
		}
		println("source count:${sour.size()}")
	}
}
