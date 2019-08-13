package core;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import consts.Constants;

public class DriverFactory {
  /**
   * Gerencia uma instancia singular do driver.
   * 
   * @return WebDriver
   * @throws IOException
   */
  public static WebDriver getDriver(String driverName) {
    WebDriver driver = null;
    try {
      switch (driverName.toUpperCase()) {
        case "IE":
        case "INTERNET EXPLORER":
          System.setProperty("webdriver.ie.driver", Constants.IE_DRIVER_PATH);
          driver = new InternetExplorerDriver();
        case "FIREFOX":
          System.setProperty("webdriver.gecko.driver", Constants.FIREFOX_DRIVER_PATH);
          driver = new FirefoxDriver();
          break;
        default:
          System.setProperty("webdriver.chrome.driver", Constants.CHROME_DRIVER_PATH);
          driver = new ChromeDriver();
          break;
      }
      driver.manage().window().maximize();
      driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
    } catch (Exception e) {
      Assert.fail("Não foi possivel iniciar a instancia do driver");
    }
    return driver;
  }
  
  public static WebDriver getRemoteDriver(String url, String driverName) {
    WebDriver driver = null;
    try {
      DesiredCapabilities cap = new DesiredCapabilities();
      cap.setBrowserName(driverName);
      driver = new RemoteWebDriver(new URL(url), cap);
      driver.manage().window().maximize();
      driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
    } catch (MalformedURLException e) {
      Assert.fail("Não foi possivel iniciar a instancia do driver");
    }
    return driver;
  }
}