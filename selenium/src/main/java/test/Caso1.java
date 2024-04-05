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

public class Caso1 {

  private WebDriver driver;
  private final String driverBinary = "./src/test/resources/driver/chromedriver"; // Este driver es de Linux ya que es
                                                                                  // lo que yo uso
  private final String url = "https://petstore.octoperf.com/";

  // Parametros para la prueba
  private final String username = "j2ee";

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
    // Encontramos el boton para iniciar sesion y le damos click
    WebElement loginLink = driver.findElement(By.cssSelector("div#MenuContent > a:nth-of-type(2)"));
    loginLink.click();
    // Encontramos el espacio para el username y lo rellenamos (el espacio de clave
    // viene prerellenado)
    WebElement usernameInput = driver.findElement(By.cssSelector("input[name=username]"));
    usernameInput.sendKeys(username);
    // Encontramos el boton de submit y le damos click
    WebElement submitButton = driver.findElement(By.cssSelector("input[name=signon]"));
    submitButton.click();
    // Verificamos que se haya iniciado sesion comparando el link actual al link del
    // catalogo, si se inicio sesion la pagina tiene que redirigir el usuario ahi
    Assert.assertEquals("https://petstore.octoperf.com/actions/Catalog.action", driver.getCurrentUrl());
  }

  @After
  public void QuitNav() {
    driver.quit();
  }

}
