const apiBaseUrl = "http://localhost:8080";

async function startGame() {
  try {
    const response = await fetch(`${apiBaseUrl}/start`);
    const result = await response.text();
    console.log(result);
  } catch (error) {
    console.error("Error starting game:", error);
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
