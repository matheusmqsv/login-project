package core;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import consts.Constants;
import enums.LogType;
import models.Manager;

public class BasePage {
  protected Manager manager;
  protected WebDriverWait wait;
  
  public BasePage(Manager manager) {
    this.manager = manager;
    wait = new WebDriverWait(manager.getDriver(), Constants.TEMPO_ESPERA_MAX);
  }
  
  public void esperarPaginaCarregar() {
    wait.until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
  }
  
  /**
   * Navega ate determinado endereco
   * 
   * @param url
   *          Endereco a ser acessado
   */
  public void navegar(String url) {
    manager.sendLog("Navegando para o endereco: " + url, LogType.INFO);
    manager.getDriver().navigate().to(url);
    esperarPaginaCarregar();
  }
  
  /**
   * Metodo generico para clicar em um elemento com verificacao de existencia na
   * pagina
   * 
   * @param elemento
   *          Elemento a ser clicado
   */
  protected void clicarElemento(WebElement elemento) {
    esperaElementoAparecer(elemento);
    elemento.click();
  }
  
  /**
   * Metodo generico para preencher um elemento com verificacao de existencia na
   * pagina
   * 
   * @param elemento
   *          Elemento a ser preenchido
   * @param valor
   *          Valor de preenchimento
   */
  protected void preencherCampo(WebElement elemento, String valor) {
    manager.sendLog("Inserindo o valor: " + valor, LogType.INFO);
    esperarPaginaCarregar();
    esperaElementoAparecer(elemento);
    elemento.sendKeys(valor);
  }
  
  /**
   * Metodo generico para preencher um elemento pausadamento com verificacao de
   * existencia na pagina
   * 
   * @param elemento
   *          Elemento a ser preenchido
   * @param valor
   *          Valor de preenchimento
   * @throws InterruptedException
   */
  protected void preencherCampoPausa(WebElement elemento, String valor) throws InterruptedException {
    for (char c : valor.toCharArray()) {
      esperaElementoAparecer(elemento);
      elemento.sendKeys(String.valueOf(c));
      Thread.sleep(250);
    }
  }
  
  /**
   * Aguarda um elemento aparecer na pagina utilizando o tempo de espera padrao
   * 
   * @param elemento
   *          Elemento a ser esperado
   * @return true se o elemento aparecer ou false de o elemento nao aparecer
   */
  protected Boolean esperaElementoAparecer(WebElement elemento) {
    try {
      wait.until(ExpectedConditions.visibilityOf(elemento));
      return true;
    } catch (Exception e) {
      return false;
    }
  }
  
  /**
   * Aguarda um elemento aparecer na pagina utilizando o tempo de espera
   * parametrizado
   * 
   * @param elemento
   *          Elemento a ser esperado
   * @param tempoEspera
   *          Tempo de esperado elemento
   * @return
   */
  protected Boolean esperaElementoAparecer(WebElement elemento, Integer tempoEspera) {
    WebDriverWait wait = new WebDriverWait(manager.getDriver(), tempoEspera);
    try {
      wait.until(ExpectedConditions.visibilityOf(elemento));
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}