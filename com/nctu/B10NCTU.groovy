package com.nctu
import java.text.FieldPosition
import java.text.Format
import java.text.ParsePosition
import com.nctu.ExcelBuilder

/**
 * 繳費狀況明細表
 * @author Louis
 *
 */
class B10NCTU{
	def sour = []
	def des = []
	def myExcel = new ExcelBuilder("/Users/Louis/tmp/380173.xls") //取得繳費狀況明細表

	def run(){
		cnt1 = 0
		sum1 = 0
		cnt2 = 0
		sum2 = 0
		cnt3 = 0
		sum3 = 0
		cnt4 = 0
		sum4 = 0
		total = 0
		//EMBA：系所代碼後兩碼為61 Ex: 261。
		//專班：系所代碼後兩碼介於62~87或90~99 Ex: 262~287, 290~299。
		//大學：系所代碼開頭為3。
		//研究所：非以上著皆是。
		myExcel.eachLine(labels: true) {
		//    if ("$B10" == "3000") {
		//        println "First column on row ${it.rowNum} = $虛擬帳號" + ":length:" + "$虛擬帳號".length()
				sour.add("$STUID" + "-" + "$ST_NAME" +"-" + "$B10" +"-"+ "$CL_ID")
				clid = "$CL_ID"
				Integer tot = Integer.parseInt("$TOT")
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
		
		println("\n總筆數:${sour.size()} 總金額:${String.format("%,d",total)}")
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


