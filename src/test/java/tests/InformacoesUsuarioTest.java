package tests;

import static org.junit.Assert.*; // Importa todos os métodos estáticos da classe Assert

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import suporte.Generator;
import suporte.Screenshot;

import java.util.concurrent.TimeUnit;

public class InformacoesUsuarioTest {

    private WebDriver navegador;

    @Rule
    public TestName test = new TestName();

    @Before
    public void setUP(){
        //Abrindo o navegador
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\drivers\\chromedriver.exe");
        navegador = new ChromeDriver();
        // Espera 1 segundo até que todos os elementos da página estejam carregados para serem encontrados pelo script
        navegador.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

        //Navegando para a página do taskit
        navegador.get("http://www.juliodelima.com.br/taskit");

        // Clicar no link que possui o texto "Sing in"
        navegador.findElement(By.linkText("Sign in")).click();

        // Identificando o formulário de login
        WebElement formlarioSignInBox = navegador.findElement(By.id("signinbox"));

        // Digitar no campo com name "login" que está dentro do formulário de id "signinbox" o texto "julio0001"
        formlarioSignInBox.findElement(By.name("login")).sendKeys("julio0001");

        // Digitar no campo com name "password" que está dentro do formulário de id "signinbox" o texto "123456"
        formlarioSignInBox.findElement(By.name("password")).sendKeys("123456");

        // Clicar no link com o texto "SIGN IN"
        navegador.findElement(By.linkText("SIGN IN")).click();

        // Clicar no link com class "me"
        navegador.findElement(By.className("me")).click();

        // Clicar no link com o texto "MORE DATA ABOUT YOU"
        navegador.findElement(By.linkText("MORE DATA ABOUT YOU")).click();
    }

    /*@Test
    public void testAdicionarUmaInformacaoDoUsuario() {

        // Clicar no botão através do seu xpath //button[@data-target="addmoredata"]
        navegador.findElement(By.xpath("//button[@data-target=\"addmoredata\"]")).click();

        // Identificar a popup onde está o formulário de id addmoredata
        WebElement popupAddMoreData = navegador.findElement(By.id("addmoredata"));

        // No combo de name "type" escolher a opção Phone
        WebElement campoType = popupAddMoreData.findElement(By.name("type"));
        new Select(campoType).selectByVisibleText("Phone");

        // No campo de name "contact" digitar um telefone
        popupAddMoreData.findElement(By.name("contact")).sendKeys("5571999991111");

        // Clicar no link de text "SAVE" que está na popup
        popupAddMoreData.findElement(By.linkText("SAVE")).click();

        // Na mensagem de id "toast-container" validar que o texto é "Your contact has been added"
        WebElement mensagemPopup = navegador.findElement(By.id("toast-container"));
        String mensagem = mensagemPopup.getText();
        assertEquals("Your contact has been added!", mensagem);
    }

     */

        @Test
        public void removerUmContatoDeUmUsuario(){
            //Clicar no elemento (ícone de lixeira) pelo seu xpath //span[text()="+55111118888"]/following-sibling::a
            navegador.findElement(By.xpath("//span[text()=\"+55111118888\"]/following-sibling::a")).click();
            // Confirmar a janela javascript
            navegador.switchTo().alert().accept();
            // Validar que a mensagem apresentada foi Rest in peace, dear phone!
            WebElement mensagemPopup2 = navegador.findElement(By.id("toast-container"));
            String mensagem2 = mensagemPopup2.getText();
            assertEquals("Rest in peace, dear phone!", mensagem2);

            String screenshotArquivo = "/Users/luisp/IdeaProjects/report/taskit/" + Generator.dataHoraParaArquivo() + test.getMethodName()+ ".png";
            Screenshot.take(navegador, screenshotArquivo);
            // Aguardar 10 segundos para que a janela desapareça
            WebDriverWait aguardar = new WebDriverWait(navegador,10);
            aguardar.until(ExpectedConditions.stalenessOf(mensagemPopup2));
            // Clicar no link com texto "Logout"
            navegador.findElement(By.linkText("Logout")).click();
        }

    @After
    public void tearDown(){
        //Fechar o navegador
        navegador.quit();
    }


}
