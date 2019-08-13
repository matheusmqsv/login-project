package pages;

import org.junit.Assert;
import org.openqa.selenium.support.PageFactory;

import core.BasePage;
import models.Manager;
import uimaps.LoginMap;

public class LoginPage extends BasePage {
  private LoginMap loginMap = PageFactory.initElements(manager.getDriver(), LoginMap.class);
  
  public LoginPage(Manager manager) {
    super(manager);
  }
  
  public void login(String usuario, String senha) {
    preencherCampo(loginMap.usernameInput, usuario);
    preencherCampo(loginMap.passwordInput, senha);
    manager.getScreeenshot();
    clicarElemento(loginMap.loginButton);
    manager.getScreeenshot();
  }
  
  public void validarLogin(String resultadoEsperado) {
    Assert.assertTrue(loginMap.alertMessage.getText().trim().contains(resultadoEsperado));
  }
}
