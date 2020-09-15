package ru.netology;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidationCheckTest {

    private WebDriver driver;
    ChromeOptions options = new ChromeOptions();


    @BeforeAll
    static void setUpAll() {
        //System.setProperty("webdriver.chrome.driver", "./driver/linux/chromedriver");
        WebDriverManager.chromedriver().setup();

    }

    @BeforeEach
    void setUp() {
        options.addArguments("--headless");
        driver = new ChromeDriver(options);

    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }


    @Test
    void shouldTestPositive() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[type='text']")).sendKeys("123");
        driver.findElement(By.cssSelector("[type='tel']")).sendKeys("adc");
        driver.findElement(By.cssSelector("[class='checkbox__box']")).click();
        driver.findElement(By.cssSelector("[type='button']")).click();
        String actualMessage = driver.findElement(By.cssSelector("[class=\"input__sub\"]")).getText();
        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", actualMessage.trim());
    }
}
