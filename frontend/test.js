const { Builder, By, until } = require("selenium-webdriver");

const defaultWaitTime = 1;

async function runTest() {
  let driver = await new Builder().forBrowser("chrome").build();
  try {
    await driver.manage().window().maximize();
    await driver.get("http://127.0.0.1:8081/");

    await runA1Scenario(driver);

    const endGameButton = await driver.findElement(By.id("endGameButton"));
    await endGameButton.click();
    await driver.sleep(defaultWaitTime);
    await driver.sleep(15000);
  } catch (error) {
    console.error("Test encountered an error:", error);
  } finally {
    await driver.quit();
  }
}

async function runA1Scenario(driver) {
  const startA1Button = await driver.findElement(
    By.id("startA1ScenarioButton")
  );
  await driver.sleep(defaultWaitTime);
  await startA1Button.click();

  await driver.sleep(defaultWaitTime);
  const drawEventCardButton = await driver.findElement(
    By.id("drawEventCardButton")
  );
  await drawEventCardButton.click();
  await driver.sleep(defaultWaitTime);

  const gameInput = await driver.findElement(By.id("gameInput"));
  await gameInput.sendKeys("N");
  await driver.sleep(defaultWaitTime);
  const submitButton = await driver.findElement(By.id("submitButton"));
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  const nextPlayerButton = await driver.findElement(By.id("nextPlayerButton"));
  await nextPlayerButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("Y");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  const nextStageButton = await driver.findElement(By.id("nextStageButton"));
  await nextStageButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("1");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("7");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("QUIT");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await nextStageButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("2");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("5");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("QUIT");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await nextStageButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("2");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("3");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("4");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("QUIT");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await nextStageButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("2");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("3");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("QUIT");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await nextStageButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("Y");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);
  await nextPlayerButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("Y");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);
  await nextPlayerButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("Y");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);
  await nextPlayerButton.click();
  await driver.sleep(defaultWaitTime);

  await nextStageButton.click();
  await driver.sleep(defaultWaitTime);
  await gameInput.sendKeys("1");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("5");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("5");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("QUIT");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await nextPlayerButton.click();
  await driver.sleep(defaultWaitTime);

  await nextStageButton.click();
  await driver.sleep(defaultWaitTime);
  await gameInput.sendKeys("1");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("5");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("4");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("QUIT");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await nextPlayerButton.click();
  await driver.sleep(defaultWaitTime);

  await nextStageButton.click();
  await driver.sleep(defaultWaitTime);
  await gameInput.sendKeys("1");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("4");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("6");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("QUIT");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await nextPlayerButton.click();
  await driver.sleep(defaultWaitTime);

  await nextStageButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("Y");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);
  await nextPlayerButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("Y");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);
  await nextPlayerButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("Y");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);
  await nextPlayerButton.click();
  await driver.sleep(defaultWaitTime);

  await nextStageButton.click();
  await driver.sleep(defaultWaitTime);

  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("7");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("6");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("QUIT");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await nextPlayerButton.click();
  await driver.sleep(defaultWaitTime);

  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("9");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("4");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("QUIT");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await nextPlayerButton.click();
  await driver.sleep(defaultWaitTime);

  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("6");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("6");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("QUIT");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await nextPlayerButton.click();
  await driver.sleep(defaultWaitTime);

  await nextStageButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("Y");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);
  await nextPlayerButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("Y");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);
  await nextPlayerButton.click();
  await driver.sleep(defaultWaitTime);

  await nextStageButton.click();
  await driver.sleep(defaultWaitTime);

  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("9");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("6");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("4");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("QUIT");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await nextPlayerButton.click();
  await driver.sleep(defaultWaitTime);

  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("7");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("5");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("6");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("QUIT");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await nextPlayerButton.click();
  await driver.sleep(defaultWaitTime);

  await nextStageButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("Y");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);
  await nextPlayerButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("Y");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);
  await nextPlayerButton.click();
  await driver.sleep(defaultWaitTime);

  await nextStageButton.click();
  await driver.sleep(defaultWaitTime);

  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("7");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("6");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("6");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("QUIT");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await nextPlayerButton.click();
  await driver.sleep(defaultWaitTime);

  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("4");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("4");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("4");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("5");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("QUIT");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await nextPlayerButton.click();
  await driver.sleep(defaultWaitTime);

  await nextStageButton.click();
  await driver.sleep(defaultWaitTime);

  await nextPlayerButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("1");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("1");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("1");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("1");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  const p1Shields = await driver.findElement(By.id("p1-shields"));
  const p1ShieldsText = await p1Shields.getText();
  const p1CardCount = await driver.findElement(By.id("p1-cardCount"));
  const p1CardCountText = await p1CardCount.getText();

  console.assert(p1ShieldsText === "0", "P1 should have 0 shields");
  console.assert(p1CardCountText === "9", "P1 should have 9 cards");

  const p2Shields = await driver.findElement(By.id("p2-shields"));
  const p2ShieldsText = await p2Shields.getText();
  const p2CardCount = await driver.findElement(By.id("p2-cardCount"));
  const p2CardCountText = await p2CardCount.getText();

  console.assert(p2ShieldsText === "0", "P2 should have 0 shields");
  console.assert(p2CardCountText === "12", "P2 should have 12 cards");

  const p3Shields = await driver.findElement(By.id("p3-shields"));
  const p3ShieldsText = await p3Shields.getText();
  const p3CardCount = await driver.findElement(By.id("p3-cardCount"));
  const p3CardCountText = await p3CardCount.getText();

  console.assert(p3ShieldsText === "0", "P3 should have 0 shields");
  console.assert(p3CardCountText === "5", "P3 should have 5 cards");

  const p4Shields = await driver.findElement(By.id("p4-shields"));
  const p4ShieldsText = await p4Shields.getText();
  const p4CardCount = await driver.findElement(By.id("p4-cardCount"));
  const p4CardCountText = await p4CardCount.getText();

  console.assert(p4ShieldsText === "4", "P4 should have 4 shields");
  console.assert(p4CardCountText === "4", "P4 should have 4 cards");

  console.log("A1_scenario completed successfully");

  await driver.sleep(15000);
}

runTest();
