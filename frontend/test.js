const { Builder, By, until } = require("selenium-webdriver");
const { getWinners } = require("./script.js");

const defaultWaitTime = 1;

async function runTest() {
  let driver = await new Builder().forBrowser("chrome").build();
  try {
    await driver.manage().window().maximize();
    await driver.get("http://127.0.0.1:8081/");

    await runA1Scenario(driver);

    // const endGameButton = await driver.findElement(By.id("endGameButton"));
    // await endGameButton.click();
    // await driver.sleep(defaultWaitTime);

    // await runTwoWinnerScenario(driver);

    // await endGameButton.click();
    // await driver.sleep(defaultWaitTime);

    // await runZeroWinnerScenario(driver);

    await driver.sleep(1000000);

    // await endGameButton.click();
    // await driver.sleep(defaultWaitTime);
  } catch (error) {
    console.error("Test encountered an error:", error);
  } finally {
    await driver.quit();
  }
}

async function runZeroWinnerScenario(driver) {
  const startZeroWinnerButton = await driver.findElement(
    By.id("start0WinnerQuestButton")
  );
  await driver.sleep(defaultWaitTime);
  await startZeroWinnerButton.click();

  const drawEventCardButton = await driver.findElement(
    By.id("drawEventCardButton")
  );
  await drawEventCardButton.click();
  await driver.sleep(defaultWaitTime);

  const gameInput = await driver.findElement(By.id("gameInput"));
  await gameInput.sendKeys("Y");
  await driver.sleep(defaultWaitTime);
  const submitButton = await driver.findElement(By.id("submitButton"));
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  const nextStageButton = await driver.findElement(By.id("nextStageButton"));
  await nextStageButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("1");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
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

  await nextStageButton.click();
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

  await gameInput.sendKeys("1");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("1");
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

  const nextPlayerButton = await driver.findElement(By.id("nextPlayerButton"));
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

  await gameInput.sendKeys("12");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("QUIT");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await nextPlayerButton.click();
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

  await gameInput.sendKeys("3");
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

  // Assertions

  const p1Shields = await driver.findElement(By.id("p1-shields"));
  const p1ShieldsText = await p1Shields.getText();
  const p1CardCount = await driver.findElement(By.id("p1-cardCount"));
  const p1CardCountText = await p1CardCount.getText();
  const p1Cards = await driver.findElement(By.id("p1-cards"));
  const p1CardsText = await p1Cards.getText();

  console.assert(p1ShieldsText === "0", "P1 should have 0 shields");
  console.assert(p1CardCountText === "12", "P1 should have 12 cards");
  console.assert(
    p1CardsText === "F15, D5, D5, D5, D5, S10, S10, S10, H10, H10, H10, H10",
    "P1 should have correct cards"
  );

  const p2Shields = await driver.findElement(By.id("p2-shields"));
  const p2ShieldsText = await p2Shields.getText();
  const p2CardCount = await driver.findElement(By.id("p2-cardCount"));
  const p2CardCountText = await p2CardCount.getText();
  const p2Cards = await driver.findElement(By.id("p2-cards"));
  const p2CardsText = await p2Cards.getText();

  console.assert(p2ShieldsText === "0", "P2 should have 0 shields");
  console.assert(p2CardCountText === "11", "P2 should have 11 cards");
  console.assert(
    p2CardsText === "F5, F5, F10, F15, F15, F20, F20, F25, F30, F30, F40",
    "P2 should have correct cards"
  );

  const p3Shields = await driver.findElement(By.id("p3-shields"));
  const p3ShieldsText = await p3Shields.getText();
  const p3CardCount = await driver.findElement(By.id("p3-cardCount"));
  const p3CardCountText = await p3CardCount.getText();
  const p3Cards = await driver.findElement(By.id("p3-cards"));
  const p3CardsText = await p3Cards.getText();

  console.assert(p3ShieldsText === "0", "P3 should have 0 shields");
  console.assert(p3CardCountText === "12", "P3 should have 12 cards");
  console.assert(
    p3CardsText === "F5, F5, F10, F15, F15, F20, F20, F25, F25, F30, F40, L20",
    "P3 should have correct cards"
  );

  const p4Shields = await driver.findElement(By.id("p4-shields"));
  const p4ShieldsText = await p4Shields.getText();
  const p4CardCount = await driver.findElement(By.id("p4-cardCount"));
  const p4CardCountText = await p4CardCount.getText();
  const p4Cards = await driver.findElement(By.id("p4-cards"));
  const p4CardsText = await p4Cards.getText();

  console.assert(p4ShieldsText === "0", "P4 should have 0 shields");
  console.assert(p4CardCountText === "12", "P4 should have 12 cards");
  console.assert(
    p4CardsText === "F5, F5, F10, F15, F15, F20, F20, F25, F25, F30, F50, E30",
    "P4 should have correct cards"
  );

  const winners = await getWinners();
  console.assert(winners === "[]", "There should be no winners");

  console.log("0_winner_quest completed successfully");

  await driver.sleep(15000);
}

async function runTwoWinnerScenario(driver) {
  const startTwoWinnerButton = await driver.findElement(
    By.id("start2WinnerGameButton")
  );
  await driver.sleep(defaultWaitTime);
  await startTwoWinnerButton.click();

  await driver.sleep(defaultWaitTime);
  const drawEventCardButton = await driver.findElement(
    By.id("drawEventCardButton")
  );
  await drawEventCardButton.click();
  await driver.sleep(defaultWaitTime);

  const gameInput = await driver.findElement(By.id("gameInput"));
  await gameInput.sendKeys("Y");
  await driver.sleep(defaultWaitTime);
  const submitButton = await driver.findElement(By.id("submitButton"));
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  const nextStageButton = await driver.findElement(By.id("nextStageButton"));
  await nextStageButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("1");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("QUIT");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
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

  await gameInput.sendKeys("QUIT");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
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

  await gameInput.sendKeys("QUIT");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
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
  const nextPlayerButton = await driver.findElement(By.id("nextPlayerButton"));
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

  await gameInput.sendKeys("1");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("QUIT");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await nextPlayerButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("1");
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

  await nextPlayerButton.click();
  await driver.sleep(defaultWaitTime);

  await drawEventCardButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("N");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await nextPlayerButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("Y");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await nextStageButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("1");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("QUIT");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await nextStageButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("1");
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

  await gameInput.sendKeys("1");
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

  await gameInput.sendKeys("N");
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

  await gameInput.sendKeys("10");
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

  await gameInput.sendKeys("10");
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

  await gameInput.sendKeys("2");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await gameInput.sendKeys("2");
  await driver.sleep(defaultWaitTime);
  await submitButton.click();
  await driver.sleep(defaultWaitTime);

  await nextPlayerButton.click();
  await driver.sleep(defaultWaitTime);

  // Assertions

  const p1Shields = await driver.findElement(By.id("p1-shields"));
  const p1ShieldsText = await p1Shields.getText();
  const p1CardCount = await driver.findElement(By.id("p1-cardCount"));
  const p1CardCountText = await p1CardCount.getText();
  const p1Cards = await driver.findElement(By.id("p1-cards"));
  const p1CardsText = await p1Cards.getText();

  console.assert(p1ShieldsText === "0", "P1 should have 0 shields");
  console.assert(p1CardCountText === "12", "P1 should have 12 cards");
  console.assert(
    p1CardsText ===
      "F15, F15, F20, F20, F20, F20, F25, F25, F30, H10, B15, L20",
    "P1 should have correct cards"
  );

  const p2Shields = await driver.findElement(By.id("p2-shields"));
  const p2ShieldsText = await p2Shields.getText();
  const p2CardCount = await driver.findElement(By.id("p2-cardCount"));
  const p2CardCountText = await p2CardCount.getText();
  const p2Cards = await driver.findElement(By.id("p2-cards"));
  const p2CardsText = await p2Cards.getText();

  console.assert(p2ShieldsText === "7", "P2 should have 7 shields");
  console.assert(p2CardCountText === "9", "P2 should have 9 cards");
  console.assert(
    p2CardsText === "F10, F15, F15, F25, F30, F40, F50, L20, L20",
    "P2 should have correct cards"
  );

  const p3Shields = await driver.findElement(By.id("p3-shields"));
  const p3ShieldsText = await p3Shields.getText();
  const p3CardCount = await driver.findElement(By.id("p3-cardCount"));
  const p3CardCountText = await p3CardCount.getText();
  const p3Cards = await driver.findElement(By.id("p3-cards"));
  const p3CardsText = await p3Cards.getText();

  console.assert(p3ShieldsText === "0", "P3 should have 0 shields");
  console.assert(p3CardCountText === "12", "P3 should have 12 cards");
  console.assert(
    p3CardsText === "F20, F40, D5, D5, S10, H10, H10, H10, H10, B15, B15, L20",
    "P3 should have correct cards"
  );

  const p4Shields = await driver.findElement(By.id("p4-shields"));
  const p4ShieldsText = await p4Shields.getText();
  const p4CardCount = await driver.findElement(By.id("p4-cardCount"));
  const p4CardCountText = await p4CardCount.getText();
  const p4Cards = await driver.findElement(By.id("p4-cards"));
  const p4CardsText = await p4Cards.getText();

  console.assert(p4ShieldsText === "7", "P4 should have 7 shields");
  console.assert(p4CardCountText === "9", "P4 should have 9 cards");
  console.assert(
    p4CardsText === "F15, F15, F20, F25, F30, F50, F70, L20, L20",
    "P4 should have correct cards"
  );

  const winners = await getWinners();
  console.assert(
    winners === "[P2 {Shields: 7}, P4 {Shields: 7}]",
    "P2 and P4 should be winners"
  );

  console.log("2winner_game_2winner_quest completed successfully");

  await driver.sleep(15000);
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

  // Assertions

  const p1Shields = await driver.findElement(By.id("p1-shields"));
  const p1ShieldsText = await p1Shields.getText();
  const p1CardCount = await driver.findElement(By.id("p1-cardCount"));
  const p1CardCountText = await p1CardCount.getText();
  const p1Cards = await driver.findElement(By.id("p1-cards"));
  const p1CardsText = await p1Cards.getText();

  console.assert(p1ShieldsText === "0", "P1 should have 0 shields");
  console.assert(p1CardCountText === "9", "P1 should have 9 cards");
  console.assert(
    p1CardsText === "F5, F10, F15, F15, F30, H10, B15, B15, L20",
    "P1 should have correct cards"
  );

  const p2Shields = await driver.findElement(By.id("p2-shields"));
  const p2ShieldsText = await p2Shields.getText();
  const p2CardCount = await driver.findElement(By.id("p2-cardCount"));
  const p2CardCountText = await p2CardCount.getText();
  const p2Cards = await driver.findElement(By.id("p2-cards"));
  const p2CardsText = await p2Cards.getText();

  console.assert(p2ShieldsText === "0", "P2 should have 0 shields");
  console.assert(p2CardCountText === "12", "P2 should have 12 cards");
  console.assert(
    p2CardsText === "F5, F5, F5, F5, F5, F10, F10, F10, F10, F10, H10, E30",
    "P2 should have correct cards"
  );

  const p3Shields = await driver.findElement(By.id("p3-shields"));
  const p3ShieldsText = await p3Shields.getText();
  const p3CardCount = await driver.findElement(By.id("p3-cardCount"));
  const p3CardCountText = await p3CardCount.getText();
  const p3Cards = await driver.findElement(By.id("p3-cards"));
  const p3CardsText = await p3Cards.getText();

  console.assert(p3ShieldsText === "0", "P3 should have 0 shields");
  console.assert(p3CardCountText === "5", "P3 should have 5 cards");
  console.assert(
    p3CardsText === "F5, F5, F15, F30, S10",
    "P3 should have correct cards"
  );

  const p4Shields = await driver.findElement(By.id("p4-shields"));
  const p4ShieldsText = await p4Shields.getText();
  const p4CardCount = await driver.findElement(By.id("p4-cardCount"));
  const p4CardCountText = await p4CardCount.getText();
  const p4Cards = await driver.findElement(By.id("p4-cards"));
  const p4CardsText = await p4Cards.getText();

  console.assert(p4ShieldsText === "4", "P4 should have 4 shields");
  console.assert(p4CardCountText === "4", "P4 should have 4 cards");
  console.assert(
    p4CardsText === "F15, F15, F40, L20",
    "P4 should have correct cards"
  );

  const winners = await getWinners();
  console.assert(winners === "[]", "There should be no winners");

  console.log("A1_scenario completed successfully");

  await driver.sleep(15000);
}

runTest();
