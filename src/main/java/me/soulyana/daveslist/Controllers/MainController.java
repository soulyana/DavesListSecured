package me.soulyana.daveslist.Controllers;

import me.soulyana.daveslist.Models.Room;
import me.soulyana.daveslist.Repositories.RoomRepository;
import me.soulyana.daveslist.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class MainController {

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/")
    public String showlistings(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "index";
    }

    @RequestMapping("/addroom")
    public String addRoom(Model model) {
        model.addAttribute("aRoom", new Room());
        return "roomform";
    }

    @PostMapping("/addroom")
    public String processRoomForm(@Valid @ModelAttribute("aRoom") Room room, BindingResult result) {
        if(result.hasErrors()) {
            return "roomform";
        }
        roomRepository.save(room);
        return "redirect:/index";
    }

}
