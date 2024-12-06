const apiBaseUrl = "http://localhost:8080";

async function startGame() {
  try {
    const response = await fetch(`${apiBaseUrl}/startGame`);
    const data = await response.json();
    console.log(data);

    data.players.forEach((player) => {
      const playerNum = player.playerName.substring(1);
      document.getElementById(`p${playerNum}-shields`).textContent =
        player.shields;
      document.getElementById(`p${playerNum}-cardCount`).textContent =
        player.cardCount;
      document.getElementById(`p${playerNum}-cards`).textContent = player.cards
        .replace("[", "")
        .replace("]", "");
    });

    document.getElementById("currentGameTurn").textContent =
      data.currentGameTurn;

    // Set button states
    setButtonState("startGame", false);
    setButtonState("endGame", true);
    setButtonState("drawEventCard", true);
  } catch (error) {
    console.error("Error starting game:", error);
  }
}

async function endGame() {
  try {
    const response = await fetch(`${apiBaseUrl}/endGame`);
    const result = await response.text();
    console.log(result);

    // Reset all player information
    for (let i = 1; i <= 4; i++) {
      document.getElementById(`p${i}-shields`).textContent = "-";
      document.getElementById(`p${i}-cardCount`).textContent = "-";
      document.getElementById(`p${i}-cards`).textContent = "-";
    }

    // Reset game info
    document.getElementById("currentGameTurn").textContent = "-";
    document.getElementById("currentEventCard").textContent = "-";
    document.getElementById("currentEventTurn").textContent = "-";

    // Reset game message
    document.getElementById("gameMessage").innerHTML =
      "<em>Waiting to start...</em>";

    // Reset button states
    setButtonState("startGame", true);
    setButtonState("drawEventCard", false);
    setButtonState("nextPlayer", false);
    setButtonState("endGame", false);
  } catch (error) {
    console.error("Error ending game:", error);
  }
}

async function drawEventCard() {
  try {
    const response = await fetch(`${apiBaseUrl}/drawEventCard`);
    const result = await response.json();

    document.getElementById("gameMessage").innerHTML =
      "<strong>Drawn Event Card: </strong>" +
      result.eventCardType +
      " - " +
      result.eventCardDescription;

    document.getElementById("currentEventCard").textContent =
      result.eventCardType;

    setButtonState("drawEventCard", false);
  } catch (error) {
    console.error("Error drawing event card:", error);
  }
}

async function nextPlayer() {
  try {
    const response = await fetch(`${apiBaseUrl}/next`);
    const result = await response.text();
    console.log(result);
  } catch (error) {
    console.error("Error getting next player:", error);
  }
}

function setButtonState(action, enabled) {
  document.querySelector(`button[onclick="${action}()"]`).disabled = !enabled;
}
