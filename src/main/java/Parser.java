import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergey Bondarenko
 */
public class Parser {
    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Invalid argument. HELP: Example: Google_Result_Parser [query]");
            System.exit(1);
        }

        WebDriver ffDriver = new FirefoxDriver();
        ffDriver.get("http://www.google.com");

        WebElement searchInput = ffDriver.findElement(By.name("q"));
        searchInput.sendKeys(args[0]);
        searchInput.submit();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<WebElement> allResults = ffDriver.findElements(By.xpath("//h3[@class='r']/a"));

        List<String> urlResults = new ArrayList<String>();

        for (WebElement result : allResults) {
            urlResults.add(result.getAttribute("href"));
        }

        for (String url : urlResults) {
            ffDriver.get(url);
            System.out.println(ffDriver.getTitle());
        }
    }
}
