package com.example.springtp.view;

import com.example.springtp.dto.PlayerDto;
import com.example.springtp.service.PlayersService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/view/player")
@PreAuthorize("hasRole('TEACHER')")
public class PlayerHtmlController {
    private final PlayersService playersService;

    public PlayerHtmlController(PlayersService playersService) {
        this.playersService = playersService;
    }

    @GetMapping
    public String listPlayers(Model model) {
        model.addAttribute("players", playersService.findAll());
        return "allPlayers";
    }

    @GetMapping("/form")
    public String showPlayerForm() {
        return "playerForm";
    }

    @PostMapping("/save")
    public String savePlayer(@RequestParam String name,
                             @RequestParam String role,
                             @RequestParam String email) {
        PlayerDto player = new PlayerDto();
        player.setName(name);
        player.setRole(role);
        player.setEmail(email);
        playersService.save(player);
        return "redirect:/view/player";
    }

    @PostMapping("/delete")
    public String deletePlayer(@RequestParam Long id) {
        playersService.deleteById(id);
        return "redirect:/view/player";
    }
}
