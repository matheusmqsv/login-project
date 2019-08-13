package uimaps;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginMap {
  @FindBy(how = How.ID, using = "username")
  public WebElement usernameInput;
  @FindBy(how = How.ID, using = "password")
  public WebElement passwordInput;
  @FindBy(how = How.XPATH, using = "//button//i[normalize-space(text())='Login']")
  public WebElement loginButton;
  @FindBy(how = How.ID, using = "flash")
  public WebElement alertMessage;
}
