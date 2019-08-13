package consts;

import java.io.File;

public class Constants {
  public static final String PROJECT_PATH = System.getProperty("user.dir");
  public static final String CHROME_DRIVER_PATH = PROJECT_PATH + File.separator + "drivers" + File.separator + "chromedriver.exe";
  public static final String FIREFOX_DRIVER_PATH = PROJECT_PATH + File.separator + "drivers" + File.separator + "geckodriver.exe";
  public static final String IE_DRIVER_PATH = PROJECT_PATH + File.separator + "drivers" + File.separator + "ie.exe";
  public static final String EVIDENCES_PATH = PROJECT_PATH + File.separator + "evidences" + File.separator;
  public static final String SCENARIOS_DATASET_PATH = PROJECT_PATH + File.separator + "scenariosDataset.yaml";
  public static final Integer TEMPO_ESPERA_MIN = 5;
  public static final Integer TEMPO_ESPERA_MAX = 10;
  public static final String REMOTE_DRIVER_URL = "http://localhost:4444/wd/hub";
}
