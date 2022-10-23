package util;

import enums.NavegadoresEnum;
import enums.UnidadesMedidaTempoEnum;
import exceptions.DriverException;
import exceptions.FecharNavegadorException;
import exceptions.NavegadorNaoIdentificadoException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;

public class WebdriverUtil {
    public static WebDriver getWebDriver(String navegador, String pathWebDriver, String pathBrowserExe, Integer porta, String pathProfile) throws NavegadorNaoIdentificadoException, DriverException {
        WebDriver webDriver;
        NavegadoresEnum navegadorEnum = converterNavegador(navegador);

        switch (navegadorEnum) {
            case CHROME:
                webDriver = getChromeWebDriver(pathBrowserExe, porta, pathProfile, pathWebDriver);
                break;

            default:
                throw new NavegadorNaoIdentificadoException(navegador);
        }

        webDriver.manage().window().maximize();
        return webDriver;
    }

    public static WebDriver getWebDriver(String navegador, String pathWebDriver) throws NavegadorNaoIdentificadoException, DriverException {
        WebDriver webDriver;
        NavegadoresEnum navegadorEnum = converterNavegador(navegador);

        switch (navegadorEnum) {
            case CHROME:
                webDriver = getChromeWebDriver(pathWebDriver);
                break;

            case FIREFOX:
                webDriver = getFirefoxWebDriver(pathWebDriver);
                break;

            default:
                throw new NavegadorNaoIdentificadoException(navegador);
        }

        webDriver.manage().window().maximize();
        return webDriver;
    }

    private static NavegadoresEnum converterNavegador(String navegador) throws NavegadorNaoIdentificadoException {
        try {
            return NavegadoresEnum.valueOf(navegador.toUpperCase());
        }
        catch (IllegalArgumentException e) {
            throw new NavegadorNaoIdentificadoException(navegador);
        }
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

    private static WebDriver getChromeWebDriver(String pathChromeExe, int porta, String pathProfile, String pathChromeDriver) throws DriverException {
        try {
            Runtime.getRuntime().exec(String.format("%schrome.exe --remote-debugging-port=%d --user-data-dir=%s", pathChromeExe, porta, pathProfile), null, new File(pathChromeExe));

            System.setProperty("webdriver.chrome.driver", pathChromeDriver);
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("debuggerAddress", "127.0.0.1:" + porta);

            return new ChromeDriver(options);
        } catch (Exception var2) {
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

    public static void fecharNavegador(WebDriver webDriver) throws FecharNavegadorException {
        try {
            TimerUtil.aguardar(UnidadesMedidaTempoEnum.SEGUNDOS, 2);
            webDriver.close();
        }
        catch (Exception e) {
            throw new FecharNavegadorException();
        }
    }
}
