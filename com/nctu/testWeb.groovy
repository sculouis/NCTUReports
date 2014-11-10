/**
 * Created by Louis on 2014/9/25.
 */
    @Grapes([
            @Grab('org.gebish:geb-core:0.9.3'),
            @Grab('org.seleniumhq.selenium:selenium-firefox-driver:2.43.1'),
            @Grab('org.seleniumhq.selenium:selenium-support:2.43.1')
    ])
	
    import geb.Browser

    Browser.drive {
        config.reportsDir = new File('/Users/Louis/tmp')
        go 'https://192.168.102.33:6607/'

        waitFor() {
            $("a[href='/school/paycaweb/login.action']").click()
        }
//          $("span.selection unselect").click()

        $("#Mbr").click()
        String theText = '國立交通大學'
        $('input',name:'orgOidInput').click()
        $('input',name:'orgOidInput').value(theText)

         String id = '0010010'
         String password = '20787'
        $('input', name: 'mbrId').value(id)
         $('input', name: 'password').first().value(password)
         sid = new BufferedReader(new InputStreamReader(System.in)).readLine()
         $('input', name: 'captcha').first().value(sid)
         $('img.linkPointer').click()
            report 'test-login'
//          $('input.btn.btn_g_2').click()
//          $('div.homeLink').find('img.linkPointer').click()
//        quit()
    }
