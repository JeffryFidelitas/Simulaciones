package test;
//JOSE LEON

import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Caso3 {

  private WebDriver driver;
  private final String driverBinary = "./src/test/resources/driver/chromedriver"; // Este driver es de Linux ya que es
                                                                                  // lo que yo uso
  private final String url = "https://petstore.octoperf.com/";

  // Parametros para la prueba
  private final String busqueda = "fish";

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
    // Encontramos la barra de busqueda y buscamos lo que esta puesto en el
    // parametro
    WebElement barraBusqueda = driver.findElement(By.cssSelector("div#SearchContent > form > input[type=text]"));
    barraBusqueda.sendKeys(busqueda + Keys.ENTER);
    // Verificamos que la tabla de resultados no este vacia contando cuantos links
    // hay en la tabla, si hay uno o más entonces hay resultados
    Assert.assertFalse(driver.findElements(By.cssSelector("div#Catalog > table > tbody > tr a")).isEmpty());
  }

  @After
  public void tearDown() {
    driver.quit();
  }

}
