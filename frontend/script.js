const apiBaseUrl = "http://localhost:8080";

async function startGame() {
  const response = await fetch(`${apiBaseUrl}/start`);
  const data = await response.text();
  alert(data);
}
