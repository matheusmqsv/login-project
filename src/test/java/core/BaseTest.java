package core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.yaml.snakeyaml.Yaml;

import consts.Constants;
import models.Manager;

public class BaseTest {
  private Map<String, String> dataset;
  protected Manager manager;
  protected String testName;
  
  public BaseTest(String testName, Map<String, String> dataset) {
    this.testName = testName;
    this.dataset = dataset;
  }
  
  public static List<Object> createExecutionList() throws FileNotFoundException {
    List<Object[]> executionList = new ArrayList<Object[]>();
    Yaml yaml = new Yaml();
    Map<String, List<LinkedHashMap<String, Map<String, String>>>> yamlMap = yaml
        .load(new FileInputStream(Constants.SCENARIOS_DATASET_PATH));
    yamlMap.get("AuthenticationTest").forEach(cenario -> {
      cenario.forEach((k, v) -> {
        if (v.get("run").equalsIgnoreCase("yes"))
          executionList.add(new Object[] { k, v });
      });
    });
    Object[] objectReturn = new Object[executionList.size()];
    objectReturn = executionList.toArray(objectReturn);
    return Arrays.asList(objectReturn);
  }
  
  @Before
  public void before() throws IOException {
    manager = new Manager(testName, dataset);
  }
  
  @After
  public void after() {
    manager.getDriver().quit();
  }
}
