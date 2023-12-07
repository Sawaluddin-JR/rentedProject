package com.rented.demouasfadillah.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.rented.demouasfadillah.models.Rented;
import com.rented.demouasfadillah.repositories.CarRepository;
import com.rented.demouasfadillah.repositories.CustomerRepository;
import com.rented.demouasfadillah.repositories.RentalRepository;


@Controller
public class RentedController {
    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CarRepository carRepository;

    @GetMapping("/rented")
    public String showDataRented(Model model) {
        model.addAttribute("rented", rentalRepository.findAll());
        return "show-rented";
    }

    @GetMapping("/add-rented")
    public String addRented(Model model) {
        Rented rented = new Rented();
        model.addAttribute("rented", rented);
        model.addAttribute("car", carRepository.findAll());
        model.addAttribute("customer", customerRepository.findAll());
        return "add-rented";
    }

    @PostMapping("/save-rented")
    public String saveRented(@ModelAttribute("rented") @Validated Rented rented, BindingResult result, Model model) {
        // if (rented.getRentalDate() == null) {
        // rented.setRentalDate(new Date()); // Jika tanggal sewa belum diatur, gunakan
        // waktu saat ini
        // }

        if (result.hasErrors()) {
            // Jika terdapat kesalahan validasi, tampilkan kembali form
            model.addAttribute("car", carRepository.findAll());
            model.addAttribute("customer", customerRepository.findAll());
            return "add-rented";
        }
        rentalRepository.save(rented);
        return "redirect:/rented";
    }

    @GetMapping("/up-rented/{idRented}")
    public String updateRented(@PathVariable(value = "idRented") Integer idRented, Model model) {
        Rented rented = rentalRepository.getReferenceById(idRented);
        model.addAttribute("rented", rented);
        model.addAttribute("car", carRepository.findAll());
        model.addAttribute("customer", customerRepository.findAll());
        return "update-rented";
    }

    @GetMapping("/del-rented/{idRented}")
    public String deleteRented(@PathVariable(value = "idRented") Integer idRented) {
        rentalRepository.deleteById(idRented);
        return "redirect:/rented";
    }
}
