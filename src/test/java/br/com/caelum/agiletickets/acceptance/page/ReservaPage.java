package br.com.caelum.agiletickets.acceptance.page;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ReservaPage {

	private static final String BASE_URL = "http://localhost:8080";
	private final WebDriver driver;

	public ReservaPage(WebDriver driver) {
		this.driver = driver;
	}

	public void abreListagem() {
		driver.get(BASE_URL + "/sessao");
	}

	private void abreLink(String uri){
		driver.get(BASE_URL + uri);
	}

	private WebElement lista() {
		return driver.findElement(By.id("sessoes"));
	}

	private WebElement linhaCom5Vagas() {
		List<WebElement> linhas = driver.findElements(By.tagName("li"));
		for( WebElement l : linhas ){
			String[] split = l.getText().split(" ");
			Integer vagas = Integer.valueOf( split[split.length-1] );
			if ( vagas  == 5 ){
				return l;
			}
		}
		return null;
	}
	
	public void abrePaginaCom5Vagas(){
		abreListagem();
		WebElement linha = linhaCom5Vagas();
		if ( linha != null){
			WebElement ancora = linha.findElement(By.tagName("a"));
			abreLink( ancora.getAttribute("href") );
		}
	}
	

}
