package com.nctu
def node = new XmlParser().parse(new File("/Users/Louis/tmp/PAY-TableMeta.xml"))

def svctables = node.SvcTable
svctables.each {
	getAttr(it)
	println "----------------------" 
		it.SvcAttr.each {
		getAttr(it)
	}
}

//if (node.children().size() > 0){
//	getTags(node.children()) //取得屬性及值
//}

private getTags(it){
	it.each {
		println it.name()
		if (it.attributes().size() > 0) {
			getAttr(it)
		}else{
//		    println it.text()
		}
		if (it.children().size() > 0){
			getTags(it.children())
		}else{
		println it.text()
		}
	}
}

/**
 * 取得標籤的屬性值
 * @param it
 * @return
 */
private getAttr(it) {
	it.attributes().each {
		println it.key +'-'+ it.getValue()
	}
}



//private getCs(it) {
//	if (it.text().trim() == ''){
//		println it.text()
//	}
//
//	if (it.children().size() > 0){
//		it.children().each {
//			println it.name()
//			getAttr(it)
//			if (it.children().size() > 0){
//				it.children().each {
//					println it.name()
//					getAttr(it)
//					println it.text()
//				}
//			}
//		}
//	}
//}
