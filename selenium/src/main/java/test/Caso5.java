package test;
//JOSE LEON

import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Caso5 {

  private WebDriver driver;
  private final String driverBinary = "./src/test/resources/driver/chromedriver"; // Este driver es de Linux ya que es
                                                                                  // lo que yo uso
  private final String url = "https://petstore.octoperf.com/";

  // Parametros para la prueba
  private final String idProducto = "FI-SW-01"; // (Especie de pez)
  private final String idItem = "EST-1";

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
    // Encontramos el link para la categoria de peces y le damos click
    WebElement pezLink = driver.findElement(By.cssSelector("div#QuickLinks > a:nth-of-type(1)"));
    pezLink.click();
    // Se elige segun el id de producto
    WebElement productoLink = driver.findElement(By.xpath("//a[text() = '" + idProducto + "']"));
    productoLink.click();
    // Lo mismo pero con el item
    WebElement itemLink = driver.findElement(By.xpath("//a[text() = '" + idItem + "']"));
    itemLink.click();
    // Verificamos que nos haya llevado al link correcto
    Assert.assertTrue(driver.getCurrentUrl().contains("&itemId=" + idItem));
  }

  @After
  public void QuitNav() {
    driver.quit();
  }

}
