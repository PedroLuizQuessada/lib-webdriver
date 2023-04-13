import enums.NavegadoresEnum;
import exceptions.DriverException;
import exceptions.NavegadorNaoIdentificadoException;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import util.WebdriverUtil;

public class WebDriverTeste {

    private final NavegadoresEnum NAVEGADOR = NavegadoresEnum.CHROME;
    private static final String WEBDRIVER_PATH = "C:\\Users\\pedroquessada\\IdeaProjects\\.pedro\\rpa-whatsapp\\RPAWhatsapp\\chromedriver.exe";
    private static final String BROWSER_EXEC_PATH = "C:\\Users\\pedroquessada\\Downloads\\Win_x64_1084030_chrome-win\\chrome-win\\";
    private static final Integer PORTA = 9222;
    private static final String PROFILE_PATH = "C:\\Users\\pedroquessada\\IdeaProjects\\.pedro\\rpa-whatsapp\\RPAWhatsapp\\profile\\";

    @Test
    public void testeRecuperarPendencias() throws NavegadorNaoIdentificadoException, DriverException {
        WebDriver driver = WebdriverUtil.getWebDriver(NAVEGADOR.toString(), WEBDRIVER_PATH, BROWSER_EXEC_PATH, PORTA, PROFILE_PATH);
        Assert.assertNotNull(driver);
    }
}
