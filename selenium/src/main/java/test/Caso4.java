package test;
//JEFFRY EDUARTE

import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Caso4 {

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
    WebElement perroCategoriaLink = driver.findElement(By.cssSelector("div#QuickLinks > a:nth-of-type(2)"));
    perroCategoriaLink.click();
    // Encontramos la primera linea de la tabla de razas, el link y le hacemos click
    WebElement razaLink = driver
        .findElement(By.cssSelector("div#Catalog > table > tbody > tr:nth-of-type(2) > td > a"));
    razaLink.click();
    // Encontramos el primer perro en la tabla y le damos click
    WebElement perroLink = driver
        .findElement(By.cssSelector("div#Catalog > table > tbody > tr:nth-of-type(2) > td > a"));
    perroLink.click();
    // Encontramos el boton para agregarlo al carrito y le damos click
    WebElement addButton = driver.findElement(By.cssSelector("div#Catalog > table > tbody > tr > td > a"));
    addButton.click();
    // Para este punto ahora estamos en el carrito, verificaremos que no este vacio,
    // para eso intentaremos encontrar el indicador de que el carrito esta vacio, si
    // no encuentra el indicador entonces no esta vacio
    Assert.assertTrue(
        driver.findElements(By.cssSelector("div#Catalog > table > tbody > tr:nth-of-type(2) > td > b")).isEmpty());
  }

  @After
  public void QuitNav() {
    driver.quit();
  }

}
