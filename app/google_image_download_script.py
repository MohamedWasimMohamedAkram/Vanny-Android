from selenium import webdriver
import urllib
import urllib.request
from selenium.webdriver.common.keys import Keys
import time

driver = webdriver.Chrome()
word="baby"
url="http://images.google.com/search?q="+word+"&tbm=isch&sout=1"
driver.get(url)

#Will keep scrolling down the webpage until it cannot scroll no more
last_height = driver.execute_script('return document.body.scrollHeight')
while True:
    driver.execute_script('window.scrollTo(0,document.body.scrollHeight)')
    time.sleep(2)
    new_height = driver.execute_script('return document.body.scrollHeight')
    try:
        driver.find_element("xpath", '//*[@id="islmp"]/div/div/div/div/div[5]/input').click()
        time.sleep(2)
    except:
        pass
    if new_height == last_height:
        break
    last_height = new_height

for i in range(1, 700):
    try:
        imageXpathSelector=f'//*[@id="islrg"]/div[1]/div[{i}]/a[1]/div[1]/img'
        img=driver.find_element("xpath", imageXpathSelector)
        src=(img.get_attribute('src'))
        urllib.request.urlretrieve(src, f'/Users/personal/Desktop/Senior Design Project/datasets/{word}/{word} ('+str(i)+').jpg')
    except Exception:
        pass
    
driver.close()