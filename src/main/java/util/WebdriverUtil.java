package util;

import enums.NavegadoresEnum;
import enums.UnidadesMedidaTempoEnum;
import exceptions.DriverException;
import exceptions.FecharNavegadorException;
import exceptions.NavegadorNaoIdentificadoException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebdriverUtil {
    public static WebDriver getWebDriver(String navegador, String pathChromeDriver) throws NavegadorNaoIdentificadoException, DriverException {
        WebDriver webDriver;
        NavegadoresEnum navegadorEnum;
        try {
            navegadorEnum = NavegadoresEnum.valueOf(navegador.toUpperCase());
        }
        catch (IllegalArgumentException e) {
            throw new NavegadorNaoIdentificadoException(navegador);
        }

        switch (navegadorEnum) {
            case CHROME:
                webDriver = getChromeWebDriver(pathChromeDriver);
                break;

            case FIREFOX:
                webDriver = getFirefoxWebDriver(pathChromeDriver);
                break;

            default:
                throw new NavegadorNaoIdentificadoException(navegador);
        }

        webDriver.manage().window().maximize();
        return webDriver;
    }

    private static WebDriver getChromeWebDriver(String pathChromeDriver) throws DriverException {
        try {
            System.setProperty("webdriver.chrome.driver", pathChromeDriver);
            return new ChromeDriver();
        }
        catch (Exception e) {
            throw new DriverException("chrome");
        }
    }

    private static WebDriver getFirefoxWebDriver(String pathChromeDriver) throws DriverException {
        try {
            System.setProperty("webdriver.gecko.driver", pathChromeDriver);
            return new FirefoxDriver();
        }
        catch (Exception e) {
            throw new DriverException("firefox");
        }
    }

    public void fecharNavegador(WebDriver webDriver) throws FecharNavegadorException {
        try {
            TimerUtil.aguardar(UnidadesMedidaTempoEnum.SEGUNDOS, 2);
            webDriver.quit();
        }
        catch (Exception e) {
            throw new FecharNavegadorException();
        }
    }
}
