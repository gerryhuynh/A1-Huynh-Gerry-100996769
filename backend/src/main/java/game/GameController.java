package game;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:8081")
public class GameController {
  @GetMapping("/start")
    public String startGame() {
        return "Test successful";
    }
}
