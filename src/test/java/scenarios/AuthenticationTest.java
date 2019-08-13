package scenarios;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameters;

import core.BaseTest;
import core.ParallelParameterized;
import pages.LoginPage;

@RunWith(ParallelParameterized.class)
public class AuthenticationTest extends BaseTest {
  private LoginPage loginPage;
  
  public AuthenticationTest(String testName, Map<String, String> dataset) {
    super(testName, dataset);
  }
  
  @Parameters(name = "{0}")
  public static List<Object> loadTestData() throws FileNotFoundException {
    return createExecutionList();
  }
  
  @Before
  public void before() throws IOException {
    super.before();
    loginPage = new LoginPage(manager);
  }
  
  @Test
  public void test() {
    loginPage.navegar(manager.getValue("url"));
    loginPage.login(manager.getValue("username"), manager.getValue("password"));
    loginPage.validarLogin(manager.getValue("expectedResult"));
  }
}
