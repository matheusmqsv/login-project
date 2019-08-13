package models;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import consts.Constants;
import core.DriverFactory;
import enums.LogType;

public class Manager {
  private Integer evidenceIndex;
  private WebDriver driver;
  private String evidenceFolderPath;
  private Map<String, String> scenarioDataset;
  private Logger logger;
  
  public Manager(String testName, Map<String, String> dataset) throws FileNotFoundException, MalformedURLException {
    SimpleDateFormat dateFormat = new SimpleDateFormat("HHmmssSSS_yyyyMMdd");
    evidenceFolderPath = Constants.EVIDENCES_PATH + dateFormat.format(Calendar.getInstance().getTime()) + File.separator + testName;
    File evidenceFolder = new File(evidenceFolderPath);
    evidenceFolder.mkdir();
    evidenceIndex = 0;
    scenarioDataset = dataset;
    // driver = DriverFactory.getDriver(scenarioDataset.get("browser"));
    driver = DriverFactory.getRemoteDriver(Constants.REMOTE_DRIVER_URL, scenarioDataset.get("browser"));
    logger = Logger.getLogger(testName);
  }
  
  public String getValue(String key) {
    return scenarioDataset.get(key);
  }
  
  public WebDriver getDriver() {
    return driver;
  }
  
  public void getScreeenshot() {
    try {
      File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
      FileUtils.copyFile(src, new File(evidenceFolderPath + File.separator + evidenceIndex + ".png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    evidenceIndex++;
  }
  
  public void sendLog(String message, LogType type) {
    switch (type) {
      case ERROR:
        logger.error(message);
        break;
      case INFO:
        logger.info(message);
        break;
      case WARN:
        logger.warn(message);
        break;
      case FATAL:
        logger.fatal(message);
        break;
      case DEBUG:
        logger.debug(message);
        break;
      case TRACE:
        logger.trace(message);
        break;
    }
  }
}
