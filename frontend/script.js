const apiBaseUrl = "http://localhost:8080";

async function startGame() {
  try {
    const response = await fetch(`${apiBaseUrl}/startGame`);
    const result = await response.json();
    console.log(result);

    result.players.forEach((player) => {
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
      result.currentGameTurn;
    document.getElementById("gameMessage").innerHTML = result.message;

    // Set button states
    setButtonState("startGameButton", false);
    setButtonState("endGameButton", true);
    setButtonState("drawEventCardButton", true);
  } catch (error) {
    console.error("Error starting game:", error);
  }
}

async function endGame() {
  try {
    const response = await fetch(`${apiBaseUrl}/endGame`);
    const result = await response.text();
    console.log(result);

    for (let i = 1; i <= 4; i++) {
      document.getElementById(`p${i}-shields`).textContent = "-";
      document.getElementById(`p${i}-cardCount`).textContent = "-";
      document.getElementById(`p${i}-cards`).textContent = "-";
    }

    document.getElementById("currentGameTurn").textContent = "-";
    document.getElementById("currentEventCard").textContent = "-";

    document.getElementById("questNumStages").textContent = "-";
    document.getElementById("questSponsor").textContent = "-";
    document.getElementById("questCurrentStage").textContent = "-";
    document.getElementById("questAttackTurn").textContent = "-";

    document.getElementById("gameMessage").innerHTML =
      "<em>Waiting to start...</em>";

    setButtonState("startGameButton", true);
    setButtonState("drawEventCardButton", false);
    setButtonState("nextPlayerButton", false);
    setButtonState("nextStageButton", false);
    setButtonState("endGameButton", false);

    disableGameInput();
  } catch (error) {
    console.error("Error ending game:", error);
  }
}

async function drawEventCard() {
  try {
    const response = await fetch(`${apiBaseUrl}/drawEventCard`);
    const result = await response.json();

    document.getElementById("gameMessage").innerHTML = `
      <p><strong>Drawn Event Card: </strong>${result.eventCardType} - ${result.eventCardDescription}</p>
      <p>${result.message}</p>
    `;

    document.getElementById("currentEventCard").textContent =
      result.eventCardType;

    isQuest = result.isQuest;

    if (isQuest) {
      enableGameInput("Enter Y or N...", submitSponsorChoice);
      document.getElementById("questNumStages").textContent =
        result.questNumStages;
    }

    setButtonState("drawEventCardButton", false);
  } catch (error) {
    console.error("Error drawing event card:", error);
  }
}

async function submitSponsorChoice() {
  try {
    const inputValue = document.getElementById("gameInput").value;
    const response = await fetch(
      `${apiBaseUrl}/submitSponsorChoice?sponsorChoice=${inputValue}`,
      {
        method: "POST",
      }
    );
    const result = await response.json();

    document.getElementById("gameMessage").innerHTML = result.message;
    if (result.sponsor) {
      // GO TO NEXT STEP HERE
      document.getElementById("questSponsor").textContent = result.sponsor;
      enableNextStageButton(nextStageSetup);
    } else {
      enableNextPlayerButton(promptNextPotentialSponsor);
      if (result.noSponsor) {
        enableNextPlayerButton(startNextPlayerTurn);
      }
    }

    disableGameInput();
  } catch (error) {
    console.error("Error submitting sponsor choice:", error);
  }
}

async function promptNextPotentialSponsor() {
  try {
    const response = await fetch(`${apiBaseUrl}/nextPotentialSponsor`);
    const result = await response.json();
    console.log(result);

    document.getElementById("gameMessage").innerHTML = result.message;
    enableGameInput("Enter Y or N...", submitSponsorChoice);
    setButtonState("nextPlayerButton", false);
  } catch (error) {
    console.error("Error getting next player:", error);
  }
}

async function startNextPlayerTurn() {
  try {
    const response = await fetch(`${apiBaseUrl}/nextPlayerTurn`);
    const result = await response.json();
    console.log(result);

    document.getElementById("gameMessage").innerHTML = result.message;
    document.getElementById("currentEventCard").textContent = "-";
    document.getElementById("questNumStages").textContent = "-";
    setButtonState("drawEventCardButton", true);
    document.getElementById("currentGameTurn").textContent =
      result.currentGameTurn;
    setButtonState("nextPlayerButton", false);
  } catch (error) {
    console.error("Error getting next player turn:", error);
  }
}

async function submitStageSetupChoice() {
  try {
    const inputValue = document.getElementById("gameInput").value;
    if (inputValue === "") {
      return;
    }
    const response = await fetch(
      `${apiBaseUrl}/submitStageSetupChoice?cardPosition=${inputValue}`,
      {
        method: "POST",
      }
    );
    const result = await response.json();
    console.log(result);

    document.getElementById("gameMessage").innerHTML = result.message;
    document.getElementById("gameMessage").style.whiteSpace = "pre-line";

    if (result.stageSetupComplete) {
      disableGameInput();
      result.questSetupComplete
        ? enableNextStageButton(nextStage)
        : enableNextStageButton(nextStageSetup);
    } else if (result.validCardAdded) {
      document.getElementById("gameInput").value = "";
    } else {
      document.getElementById("gameInput").value = "";
    }
  } catch (error) {
    console.error("Error submitting stage setup choice:", error);
  }
}

async function nextStageSetup() {
  try {
    const response = await fetch(`${apiBaseUrl}/nextStageSetup`);
    const result = await response.json();
    console.log(result);

    document.getElementById("gameMessage").innerHTML = result.message;
    document.getElementById("gameMessage").style.whiteSpace = "pre-line";
    enableGameInput("Choose a card position...", submitStageSetupChoice);
    document.getElementById("questCurrentStage").textContent =
      result.currentStage;
    setButtonState("nextStageButton", false);
  } catch (error) {
    console.error("Error getting next stage:", error);
  }
}

async function nextStage() {
  try {
    const response = await fetch(`${apiBaseUrl}/nextStage`);
    const result = await response.json();
    console.log(result);

    document.getElementById("questCurrentStage").textContent =
      result.currentStage;
    document.getElementById("questAttackTurn").textContent =
      result.currentAttackTurn;

    document.getElementById("gameMessage").innerHTML = result.message;
    document.getElementById("gameMessage").style.whiteSpace = "pre-line";

    enableGameInput("Enter Y or N...", submitParticipationChoice);
    setButtonState("nextStageButton", false);
  } catch (error) {
    console.error("Error getting next stage:", error);
  }
}

async function submitParticipationChoice() {
  try {
    const inputValue = document.getElementById("gameInput").value;
    const response = await fetch(
      `${apiBaseUrl}/submitParticipationChoice?participationChoice=${inputValue}`,
      {
        method: "POST",
      }
    );
    const result = await response.json();
    console.log(result);

    document.getElementById("gameMessage").innerHTML = result.message;
  } catch (error) {
    console.error("Error submitting participation choice:", error);
  }
}

// Helper functions

function setButtonState(id, enabled) {
  document.getElementById(id).disabled = !enabled;
}

function enableNextPlayerButton(action) {
  setButtonState("nextPlayerButton", true);
  document.getElementById("nextPlayerButton").onclick = action;
}

function enableNextStageButton(action) {
  setButtonState("nextStageButton", true);
  document.getElementById("nextStageButton").onclick = action;
}

function enableGameInput(message, submitAction) {
  document.getElementById("gameInput").placeholder = message;
  document.getElementById("gameInput").disabled = false;
  setButtonState("submitButton", true);
  document.getElementById("submitButton").onclick = submitAction;
}

function disableGameInput() {
  document.getElementById("gameInput").value = "";
  document.getElementById("gameInput").placeholder = "";
  document.getElementById("gameInput").disabled = true;
  setButtonState("submitButton", false);
}

document.getElementById("gameInput").onkeydown = function (event) {
  if (event.key === "Enter") {
    const submitButton = document.getElementById("submitButton");
    if (!submitButton.disabled && submitButton.onclick) {
      submitButton.onclick();
    }
  }
};
