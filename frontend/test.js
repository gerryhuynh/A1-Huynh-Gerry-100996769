const { Builder, By, until } = require("selenium-webdriver");

async function runTest() {
  let driver = await new Builder().forBrowser("chrome").build();
  try {
    await driver.get("http://127.0.0.1:8081/");

    const startButton = await driver.findElement(
      By.xpath("//button[@onclick='startGame()']")
    );
    await startButton.click();

    await driver.wait(until.alertIsPresent(), 5000);

    const alert = await driver.switchTo().alert();
    const alertText = await alert.getText();

    await alert.accept();

    console.log("Alert text was:", alertText);
    console.assert(
      alertText === "Test successful",
      'Alert text should be "Test successful"'
    );
  } catch (error) {
    console.error("Test encountered an error:", error);
  } finally {
    await driver.quit();
  }
}

runTest();
