package com.nctu

//@Grapes(
//	@Grab(group='org.apache.poi', module='poi', version='3.10.1')
//)

import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.hssf.usermodel.HSSFSheet
import org.apache.poi.hssf.usermodel.HSSFRow
import org.apache.poi.hssf.usermodel.HSSFCell
import org.apache.poi.hssf.usermodel.HSSFDateUtil


/**
 * 讀取Excel檔
 * @author Louis
 *
 */
class ExcelBuilder{

   def workbook
   def labels
   def row

   /**
    * 建置實體需傳入含路徑的檔案名稱
    * @param fileName
    */
   public ExcelBuilder(String fileName) {
	   HSSFRow.metaClass.getAt = {int idx ->
		   def cell = delegate.getCell(idx)
		   if(! cell) {
			   return null
		   }
		   def value
		   switch(cell.cellType) {
			   case HSSFCell.CELL_TYPE_NUMERIC:
				   if(HSSFDateUtil.isCellDateFormatted(cell)) {
					   value = cell.dateCellValue
				   } else {
					   value = cell.numericCellValue
				   }
				   break
			   case HSSFCell.CELL_TYPE_BOOLEAN:
				   value = cell.booleanCellValue
				   break
			   default:
				   value = cell.stringCellValue
				   break
		   }
		   return value
	   }

	   new File(fileName).withInputStream{is->
		   workbook = new HSSFWorkbook(is)
	   }
   }

   def getSheet(idx) {
	   def sheet
	   if(! idx) idx = 0
	   if(idx instanceof Number) {
		   sheet = workbook.getSheetAt(idx)
	   } else if(idx ==~ /^\d+$/) {
		   sheet = workbook.getSheetAt(Integer.valueOf(idx))
	   } else {
		   sheet = workbook.getSheet(idx)
	   }
	   return sheet
   }

   def cell(idx) {
	   if(labels && (idx instanceof String)) {
		   idx = labels.indexOf(idx.toLowerCase())
	   }
	   return row[idx]
   }

   def propertyMissing(String name) {
	   cell(name)
   }

   def eachLine(Map params = [:], Closure closure) {
	   def offset = params.offset ?: 0
	   def max = params.max ?: 9999999
	   def sheet = getSheet(params.sheet)
	   def rowIterator = sheet.rowIterator()
	   def linesRead = 0

	   if(params.labels) {
		   labels = rowIterator.next().collect{it.toString().toLowerCase()}
	   }
	   offset.times{ rowIterator.next() }

	   closure.setDelegate(this)

	   while(rowIterator.hasNext() && linesRead++ < max) {
		   row = rowIterator.next()
		   closure.call(row)
	   }
   }
}

