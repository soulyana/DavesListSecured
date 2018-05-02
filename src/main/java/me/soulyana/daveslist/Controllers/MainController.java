package me.soulyana.daveslist.Controllers;

import me.soulyana.daveslist.Models.Room;
import me.soulyana.daveslist.Repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class MainController {

    @Autowired
    RoomRepository roomRepository;


    @RequestMapping("/")
    public String showHomepage(Model model) {
        model.addAttribute("menuoption", "homepage");
        return "homepage";
    }

    @RequestMapping("/showrooms")
    public String showlistings(Model model) {
        model.addAttribute("menuoption", "rooms");
        model.addAttribute("rooms", roomRepository.findAll());
        return "index";
    }

    @RequestMapping("/addroom")
    public String addRoom(Model model) {
        model.addAttribute("menuoption", "addroom");
        model.addAttribute("aRoom", new Room());
        return "roomform";
    }

    @RequestMapping("/updateroom")
    public String addRoom(HttpServletRequest request, Model model) {
        long roomID = new Long(request.getParameter("id"));
        model.addAttribute("aRoom", roomRepository.findById(roomID).get());
        model.addAttribute("menuoption", "updateroom");
        return "roomform";
    }

    @PostMapping("/addroom")
    public String processRoomForm(@Valid @ModelAttribute("aRoom") Room room, BindingResult result) {
        if(result.hasErrors()) {
            return "roomform";
        }
        roomRepository.save(room);
        return "redirect:/";
    }

    //show available rooms
    @RequestMapping("/showavailablerooms")
    public String showAvailableRooms(Model model) {
        model.addAttribute("menuoption", "showavailablerooms");
        model.addAttribute("roomList", roomRepository.findAllByIsRented(false));
        return "index";
    }

    //change the status of a room to rented or available
    @PostMapping("/toggle")
    public String toggleRoomStatus(HttpServletRequest request) {
        long roomID = new Long(request.getParameter("id"));
        Room thisRoom = roomRepository.findById(roomID).get();
        roomRepository.save(thisRoom);

        return "redirect:/";
    }




}
