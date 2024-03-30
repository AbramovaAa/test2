import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Optional;

public class Test1 {

    private WebDriver driver;

    @Before
    public void pc() {
        System.setProperty("webdriver.chrome.driver", "/Users/alexandraabramova/Downloads/chromedriver-mac-x64/chromeDriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @Test()
    //Проверка открытия существующего варианта
    public void checkOpenVar() {
        //1) Открыть сайт РешуОГЭ
        driver.get("https://math-oge.sdamgia.ru");
        //2) Нажать на предмет "Математика"
        WebElement matematika = (new WebDriverWait(driver, Duration.ofSeconds(100))).until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[1]/div/div[2]/nav[2]/a[1]/div")));
        //3) Нажать на вариант 1
        WebElement var1 = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/div/main/section[1]/div[2]/a[1]"));
        var1.click();
        //4) Получить номер текущего варианта
        WebElement number = driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[2]/b"));
        //5) Сравнить полученный номер с ожидаемым
        String num = number.getText().split("№ ")[1];
        Integer n1 = Integer.valueOf(num.split(" ")[0]);
        int expectedNumber = 58715383;
        Assert.assertEquals(Optional.of(n1), Optional.of(expectedNumber));
    }

    @Test()
    //Проверка создания собственного варианта
    public void checkAddVar() {
        //1) Открыть сайт РешуОГЭ
        driver.get("https://math-oge.sdamgia.ru");
        //2) Нажать на предмет "Математика"
        //WebElement matematika = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/nav[2]/a[1]/div"));
        WebElement matematika = (new WebDriverWait(driver, Duration.ofSeconds(100))).until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[1]/div/div[2]/nav[2]/a[1]/div")));
        //matematika.click();
        //3) Нажать на знак "+" в задании №1
        WebElement var1 = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/div/main/section[3]/form/div/div[1]/div[2]/div[2]/div[1]/button[2]"));
        var1.click();
        //4) Нажать на кнопку «Составить вариант из одного задания»
        WebElement add = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/div/main/section[3]/form/div/div[2]/div/button[1]/span"));
        add.click();
        //5) Сравнить полученный номер варианта с ожидаемым
        WebElement number = driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[4]/div[2]/div/div[1]/div[1]/div[1]/span"));
        String num = number.getText().split(" ")[1];
        //System.out.println(num);
        Integer n1 = Integer.valueOf(num.split(" ")[0]);
        int expectedNumber = 1;
        Assert.assertEquals(Optional.of(n1), Optional.of(expectedNumber));
    }

    @Test()
    public void checkResult() {
        //1) Открыть сайт РешуОГЭ
        driver.get("https://math-oge.sdamgia.ru");
        //2) Нажать на предмет "Математика"
        //WebElement matematika = (new WebDriverWait(driver, Duration.ofSeconds(100))).until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[1]/div/div[2]/nav[2]/a[1]/div")));
        WebElement matematika = (new WebDriverWait(driver, Duration.ofSeconds(100))).until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[1]/div/div[2]/nav[2]/a[1]/div")));
        //WebElement matematika = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/nav[2]/a[1]/div"));
        //3) Нажать на вариант 1
        WebElement var1 = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/div/main/section[1]/div[2]/a[1]"));
        var1.click();
        //4) Ввести ответ на первое задание «3174»
        WebElement otvet = driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[6]/div[2]/input"));
        otvet.sendKeys("3174");
        //5) Нажать на кнопку «Сохранить»
        WebElement s = driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[8]/input"));
        s.click();
        //6) Нажать на кнопку «Подвести итоги»
        WebElement itog = driver.findElement(By.xpath("/html/body/div[1]/div[5]/center[2]/input"));
        itog.click();
        //7) Нажать на кнопку «Перейти к результатам»
        WebElement res = driver.findElement(By.xpath("/html/body/div[7]/div[2]/div/input"));
        res.click();
        //8) Сравнить введенный ответ с правильным ответом
        WebElement ball = driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[4]/div[1]/table/tbody/tr[2]/td[3]"));
        WebElement ball1 = driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[4]/div[1]/table/tbody/tr[2]/td[4]"));
        String b = ball.getText().split(" ")[0];
        String b1 = ball1.getText().split(" ")[0];
        //System.out.println(b);
        //System.out.println(b1);
        Assert.assertEquals(b, b1);
    }
    @After
    public void close() {
        driver.close();
    }
}
