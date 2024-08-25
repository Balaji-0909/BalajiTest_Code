package testscenario;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.google.common.io.Files;

import org.testng.annotations.Parameters;

public class LoginValidation {
	
		//Declaring Xpath using string
		By Username = By.xpath("//*[@id='username']");
		By Password = By.xpath("//*[@id='password']");
		By Email_id = By.xpath("//*[@id='email']");
		By CreateAccount = By.xpath("//button[text()='Create account']"); 
				
		WebDriver driver;
		@BeforeClass
		public void setup() {
		System.setProperty("webdriver.chrome.driver", "F:\\Browserdrivers\\chromedriver.exe");		
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
		}
		
		SoftAssert softAssert = new SoftAssert();
		 @Test
		 // Method to enter values for 2 Characters
		 @Parameters("password")
		    public void testUsernameWith2Characters(String password) throws IOException {
		        String username = generateRandomUsername(2);
		        String email = generateRandomEmail();
		        
		        executeTest(username, email, password);
		 }
		 
		 @Test
		// Method to enter values for 3 Characters
		@Parameters("password")
	    public void testUsernameWith3Characters(String password) throws IOException {
	        String username = generateRandomUsername(3);
	        String email = generateRandomEmail();
	        
	        executeTest(username, email, password);
	    }
		 
		 
		//Method validates the login, 
		// Validating login with 2 characters, Get invalid message
		 //Validate login, with 3 characters, Get logged in
		
		@Parameters("password")
		public void executeTest(String username, String email, String password) throws IOException {	
		
		//Entering Username	
		driver.get("https://try.vikunja.io/register");
		WebElement UserName_Field = driver.findElement(Username);
		UserName_Field.sendKeys(username);
		System.out.println(" User name entered " + username);
		
		//Entering Email	
		WebElement Email_Field = driver.findElement(Email_id);
		Email_Field.sendKeys(email);
		System.out.println("Email  address entered " + email);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//Entering Password
		WebElement Password_Field = driver.findElement(Password);
		Password_Field.sendKeys(password);
		System.out.println("Passwword entered " + password);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//Clicking on Create Account button
		WebElement CreateAccount_Button = driver.findElement(CreateAccount);
		CreateAccount_Button.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		
		
		// Validate that the message when we enter 2 characters 
		
		if(username.length() == 2) {
        try {
        	WebElement fieldClass = driver.findElement(By.xpath("//*[@class = 'message-wrapper mb-4']"));
            String Actual_Error_Message = fieldClass.getText();
            String Expected_Error_Message = "Invalid Data";
            System.out.println(Actual_Error_Message);
                       
         // Check if the field is highlighted in red
            String fieldColor = UserName_Field.getCssValue("border-color");
            System.out.println("Border color displayed:" + fieldColor);
         // Red color in RGB
            String expectedColor = "rgb(255, 0, 0)";           
            Assert.assertEquals(fieldColor, expectedColor, "The username field is not highlighted in red.");
		// Validate message for 2 charcters
			Assert.assertEquals(Actual_Error_Message, Expected_Error_Message);
            Assert.assertTrue(fieldClass.isDisplayed(), "The expected error message is not displayed.");			
          //Take a Screenshot
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        	Files.copy(screenshot, new File("screenshot1.png"));
        } catch (org.openqa.selenium.TimeoutException e) {
            System.out.println("Timeout waiting for error message. It might not have appeared.");
            takeScreenshot("timeout_no_error_message");
        }
        	
		}else if(username.length() == 3){   
			// Validating the login page when we enter 3 characters
        try {
        	String Tab_Name_Acutal = driver.findElement(By.xpath("//*[text() =' Overview']")).getText();
        	System.out.println(Tab_Name_Acutal);
        	String Tab_Name_Expected = "Overview";
        	Assert.assertEquals(Tab_Name_Acutal, Tab_Name_Expected);
        	//Take a Screenshot
        	File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        	Files.copy(screenshot, new File("screenshot.png"));
        } catch (NoSuchElementException ne) {
        	System.out.println("The element was not found: " + ne.getMessage());
            takeScreenshot("element_not_found");
            throw ne;
        }
		}else {
			System.out.println("Unexpected username length: " + username.length());
	        takeScreenshot("unexpected_username_length");
	        Assert.fail("Username length is not 2 or 3 characters.");
		 }
		}		
		
		private void takeScreenshot(String filename) throws IOException {
		    File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		    Files.copy(screenshot, new File(filename + ".png"));
		}


		private String generateRandomUsername(int length) {
			return UUID.randomUUID().toString().replace("-", "").substring(0, length);
	    }

	    private String generateRandomEmail() {
	        return "user" + UUID.randomUUID().toString().substring(0, 8) + "@gmail.com";
	    }
		@AfterClass
	    public void tearDown() {
	        if (driver != null) {
	            driver.quit();
	        }
    }
}
		
