package test;
//JEAN POL SOTO

import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Caso2 {

  private WebDriver driver;
  private final String driverBinary = "./src/test/resources/driver/chromedriver"; // Este driver es de Linux ya que es
                                                                                  // lo que yo uso
  private final String url = "https://petstore.octoperf.com/";

  @Before
  public void serUp() {
    ChromeOptions co = new ChromeOptions();
    co.addArguments("--remote-allow-origins=*");
    co.addArguments("--no-sandbox");
    System.setProperty("webdriver.chrome.driver", driverBinary);
    driver = new ChromeDriver(co);
    driver.manage().window().maximize();
    driver.get(url);
  }

  @Test
  public void testSelenium() {
    // Encontramos el link para ingresar a la tienda y le damos click
    WebElement linkTienda = driver.findElement(By.cssSelector("div#Content > p > a"));
    linkTienda.click();
    // Encontramos el link para la categoria de perros y le damos click
    WebElement perroLink = driver.findElement(By.cssSelector("div#QuickLinks > a:nth-of-type(2)"));
    perroLink.click();
    // Nos aseguramos de que estamos en el link correcto, para esto vemos que el
    // categoryID en el link sea DOGS
    Assert.assertTrue(driver.getCurrentUrl().contains("&categoryId=DOGS"));
    // Encontramos todos los links en la tabla y verificamos que sean mayor a 0
    Assert.assertFalse(driver.findElements(By.cssSelector("div#Catalog > table > tbody > tr a")).isEmpty());
  }

  @After
  public void QuitNav() {
    driver.quit();
  }

}
