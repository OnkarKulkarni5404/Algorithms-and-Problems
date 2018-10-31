__author__="Onkar Kulkarni"

from selenium import webdriver

count=0;
path="./ScreenShots/Screen_shot"
type=".png"

passeduid=["onkar@gmail.com","rakno@gmail.com"]
passpass=["1234567891","1234567891"]

faileduid=["xyz","pqrs"]
faieldpass=["xyz","pqrs"]

#pass login
for x in (0,len(passeduid)-1):
    # using webdrive to work with desired Browser
    driver = webdriver.Chrome("D:\\Lib\\chromedriver_win32\\chromedriver.exe")
    # if page doesnt load within 30 seconds then let tester know
    driver.set_page_load_timeout(30)
    # Go to this given url
    driver.get("http://127.0.0.1/exp/")
    # maximize the window
    driver.maximize_window()
    # Stay there for 5 mseconds
    driver.implicitly_wait(5)
    # take screen_shot
    ans = path + repr(count)
    ans += type
    driver.get_screenshot_as_file(ans)
    count += 1
    # find element with id as given
    driver.find_element_by_id('id1').send_keys(passeduid[x])
    # same as above
    driver.find_element_by_id('password').send_keys(passpass[x])
    driver.implicitly_wait(5)
    # Click() for submitting the form
    ans = path + repr(count)
    ans += type
    driver.get_screenshot_as_file(ans)
    count += 1
    driver.find_element_by_name('submitrebaba').click()
    ans = path + repr(count)
    ans += type
    driver.get_screenshot_as_file(ans)
    count += 1
    driver.implicitly_wait(5)
    driver.quit()

count=0
path2="./ScreenShots/Failed_Screen_shot"

#fail login
for x in range(0,len(faieldpass)):
    driver = webdriver.Chrome("D:\\Lib\\chromedriver_win32\\chromedriver.exe")
    driver.set_page_load_timeout(30)
    driver.get("http://127.0.0.1/exp/")
    driver.maximize_window()
    driver.implicitly_wait(5)
    failedone = path2 + repr(count)
    failedone += type
    driver.get_screenshot_as_file(failedone)
    count += 1
    driver.find_element_by_id('id1').send_keys(faileduid[x])
    driver.find_element_by_id('password').send_keys(faieldpass[x])
    driver.implicitly_wait(5)

    failedone = path2 + repr(count)
    failedone += type
    driver.get_screenshot_as_file(failedone)
    count += 1

    driver.find_element_by_name('submitrebaba').click()
    driver.implicitly_wait(5)

    # failedone = path2 + repr(count)
    # failedone += type
    # driver.get_screenshot_as_file(failedone)
    # count += 1

    alert = driver.switch_to_alert()
    alert.accept()

    driver.implicitly_wait(30)
    driver.quit()

#pass registration

pname=["jai@gmail.com","anmol@gmail.com"]
ppword=["123456789","10111121314"]
pmobile=["9423954112","9423954442"]
count=0
path3="./ScreenShots/Pass_Registration_Screen_shot"

for x in (0,len(pname)-1):
    driver = webdriver.Chrome("D:\\Lib\\chromedriver_win32\\chromedriver.exe")
    driver.set_page_load_timeout(30)
    driver.get("http://127.0.0.1/exp/")
    driver.maximize_window()
    driver.implicitly_wait(5)
    reg = path3 + repr(count)
    reg += type
    driver.get_screenshot_as_file(reg)
    count += 1
    driver.find_element_by_id('tt1').click()
    driver.implicitly_wait(5)

    reg = path3 + repr(count)
    reg += type
    driver.get_screenshot_as_file(reg)
    count += 1

    driver.find_element_by_id('fname').send_keys(pname[x])
    driver.find_element_by_id('pword2').send_keys(ppword[x])
    driver.find_element_by_id('mno').send_keys(pmobile[x])
    driver.implicitly_wait(30)

    reg = path3 + repr(count)
    reg += type
    driver.get_screenshot_as_file(reg)
    count += 1

    driver.find_element_by_name('oncemore').click()
    driver.quit()

#failed registration name

fname=["jai","anmol"]
count=0
path4="./ScreenShots/Failed_Registration_Screen_shot"

for x in (0,len(pname)-1):
    driver = webdriver.Chrome("D:\\Lib\\chromedriver_win32\\chromedriver.exe")
    driver.set_page_load_timeout(30)
    driver.get("http://127.0.0.1/exp/")
    driver.maximize_window()
    driver.implicitly_wait(5)
    reg = path4 + repr(count)
    reg += type
    driver.get_screenshot_as_file(reg)
    count += 1
    driver.find_element_by_id('tt1').click()
    driver.implicitly_wait(5)

    reg = path4 + repr(count)
    reg += type
    driver.get_screenshot_as_file(reg)
    count += 1

    driver.find_element_by_id('fname').send_keys(fname[x])
    driver.find_element_by_id('pword2').send_keys(ppword[x])
    driver.find_element_by_id('mno').send_keys(pmobile[x])
    driver.implicitly_wait(30)

    reg = path3 + repr(count)
    reg += type
    driver.get_screenshot_as_file(reg)
    count += 1

    driver.find_element_by_name('oncemore').click()
    alert = driver.switch_to_alert()
    alert.accept()
    driver.quit()

#failed registration password

fpword=["12","34"]

for x in (0,len(pname)-1):
    driver = webdriver.Chrome("D:\\Lib\\chromedriver_win32\\chromedriver.exe")
    driver.set_page_load_timeout(30)
    driver.get("http://127.0.0.1/exp/")
    driver.maximize_window()
    driver.implicitly_wait(5)
    reg = path4 + repr(count)
    reg += type
    driver.get_screenshot_as_file(reg)
    count += 1
    driver.find_element_by_id('tt1').click()
    driver.implicitly_wait(5)

    reg = path4 + repr(count)
    reg += type
    driver.get_screenshot_as_file(reg)
    count += 1

    driver.find_element_by_id('fname').send_keys(pname[x])
    driver.find_element_by_id('pword2').send_keys(fpword[x])
    driver.find_element_by_id('mno').send_keys(pmobile[x])
    driver.implicitly_wait(30)

    reg = path3 + repr(count)
    reg += type
    driver.get_screenshot_as_file(reg)
    count += 1

    driver.find_element_by_name('oncemore').click()
    alert = driver.switch_to_alert()
    alert.accept()
    driver.quit()

#failed registration mobile

fmobile=["123456789","123456"]

for x in (0,len(pname)-1):
    driver = webdriver.Chrome("D:\\Lib\\chromedriver_win32\\chromedriver.exe")
    driver.set_page_load_timeout(30)
    driver.get("http://127.0.0.1/exp/")
    driver.maximize_window()
    driver.implicitly_wait(5)
    reg = path4 + repr(count)
    reg += type
    driver.get_screenshot_as_file(reg)
    count += 1
    driver.find_element_by_id('tt1').click()
    driver.implicitly_wait(5)

    reg = path4 + repr(count)
    reg += type
    driver.get_screenshot_as_file(reg)
    count += 1

    driver.find_element_by_id('fname').send_keys(pname[x])
    driver.find_element_by_id('pword2').send_keys(ppword[x])
    driver.find_element_by_id('mno').send_keys(fmobile[x])
    driver.implicitly_wait(30)

    reg = path3 + repr(count)
    reg += type
    driver.get_screenshot_as_file(reg)
    count += 1

    driver.find_element_by_name('oncemore').click()
    alert = driver.switch_to_alert()
    alert.accept()
    driver.quit()







