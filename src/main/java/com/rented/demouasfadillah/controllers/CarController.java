package com.rented.demouasfadillah.controllers;

import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.rented.demouasfadillah.models.Car;
import com.rented.demouasfadillah.repositories.CarRepository;

@Controller
public class CarController {
    @Autowired
    private CarRepository carRepository;

    @GetMapping("/search-car")
    public String searchByName(@RequestParam(name = "search") String brand, Model model) {
        List<Car> cars = carRepository.findByBrandContainingIgnoreCase(brand);
        model.addAttribute("cars", cars);
        return "show-car";
    }

    @GetMapping("/sort-by-brand-asc")
    public String sortingAsc(Model model) {
        List<Car> cars = carRepository.findAll(Sort.by(Sort.Direction.ASC, "brand"));
        model.addAttribute("cars", cars);
        // model.addAttribute("cars", carRepository.findAllByOrderByBrandAsc());
        return "show-car";
    }

    @GetMapping("/sort-by-brand-desc")
    public String sortingDesc(Model model) {
        List<Car> cars = carRepository.findAll(Sort.by(Sort.Direction.DESC, "brand"));
        model.addAttribute("cars", cars);
        // model.addAttribute("cars", carRepository.findAllByOrderByBrandDesc());
        return "show-car";
    }

    // ============================================= //
    @GetMapping("/list-car")
    public String listCar(Model model) {
        model.addAttribute("cars", carRepository.findAll());
        return "show-car";
    }

    @GetMapping("/add-car")
    public String addCar(Model model) {
        Car car = new Car();
        model.addAttribute("car", car);
        return "add-car";
    }

    @PostMapping("/add-save-car")
    public String saveCar(
            @RequestParam("brand") String brand,
            @RequestParam("typeCar") String typeCar,
            @RequestParam("productionYear") Integer productionYear,
            @RequestParam("price") Long price,
            @RequestParam("seats") Integer seats,
            @RequestParam("carTrunk") Integer carTrunk,
            @RequestParam("stock") Integer stock,
            @RequestParam("driver") String driver,
            @RequestParam("image") MultipartFile multipartFile,
            Model model) {

        try {
            Car car = new Car();
            Path targetPath = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "static",
                    multipartFile.getOriginalFilename());
            multipartFile.transferTo(targetPath.toFile());
            String url = "http://localhost:8080/" + multipartFile.getOriginalFilename();

            car.setBrand(brand);
            car.setTypeCar(typeCar);
            car.setProductionYear(productionYear);
            car.setPrice(price);
            car.setSeats(seats);
            car.setCarTrunk(carTrunk);
            car.setStock(stock);
            car.setDriver(driver);
            car.setImage(url);

            carRepository.save(car);
        } catch (IOException e) {
            e.printStackTrace();
            // return "redirect:/error";
        }
        return "redirect:/list-car";
    }

    // @PostMapping("/add-save-car")
    // public String saveCar(@ModelAttribute("car") Car car) {
    // carRepository.save(car);
    // return "redirect:/list-car";
    // }

    @GetMapping("/edit-car/{idCar}")
    public String editCar(@PathVariable(value = "idCar") Integer idCar, Model model) {
        Car car = carRepository.getReferenceById(idCar);
        model.addAttribute("car", car);
        return "update-car";
    }

    // @PostMapping("/update-car")
    // public String saveUpdatedRoom(
    // @RequestParam("idCar") Integer idCar,
    // @RequestParam("brand") String brand,
    // @RequestParam("price") Long price,
    // @RequestParam("seats") Integer seats,
    // @RequestParam("carTrunk") Integer carTrunk,
    // @RequestParam("stock") Integer stock,
    // @RequestParam("driver") String driver,
    // @RequestParam("image") MultipartFile multipartFile, Model model) {
    // Car existingCar = carRepository.findById(idCar).orElse(null);

    // if (existingCar != null) {
    // existingCar.setBrand(brand);
    // existingCar.setPrice(price);
    // existingCar.setSeats(seats);
    // existingCar.setCarTrunk(carTrunk);
    // existingCar.setStock(stock);
    // existingCar.setDriver(driver);

    // try {
    // if (!multipartFile.isEmpty()) {
    // Path targetPath = Paths.get(System.getProperty("user.dir"), "src", "main",
    // "resources", "static",
    // multipartFile.getOriginalFilename());
    // multipartFile.transferTo(targetPath.toFile());
    // String url = "http://localhost:8080/" + multipartFile.getOriginalFilename();
    // existingCar.setImage(url);
    // }

    // carRepository.save(existingCar);
    // } catch (IOException e) {
    // e.printStackTrace();
    // return "redirect:/error";
    // }
    // }
    // return "redirect:/";
    // }

    @GetMapping("/delete-car/{idCar}")
    public String deleteCar(@PathVariable(value = "idCar") Integer idCar) {
        carRepository.deleteById(idCar);
        return "redirect:/list-car";
    }

    // image
    // @GetMapping("/upload-form")
    // public String uploadImage() {
    // return "upload-car";
    // }

    // @PostMapping("/upload")
    // public String handleFileUpload(@RequestPart("file") MultipartFile file, Model
    // model) {
    // if (file.isEmpty()) {
    // model.addAttribute("message", "Pilih berkas untuk diunggah");
    // return "uploadStatus";
    // }
    // try {
    // // Set tempat menyimpan file dalam proyek
    // Path targetPath = Paths.get(System.getProperty("user.dir"), "src", "main",
    // "resources", "static",
    // file.getOriginalFilename());

    // // Simpan file di dalam proyek
    // file.transferTo(targetPath.toFile());

    // // Simpan URL atau path di dalam database
    // // String url = "http://localhost:8080/list-car"+file.getOriginalFilename();

    // Car image = new Car();
    // // image.setImage(url);;

    // carRepository.save(image);

    // model.addAttribute("message", "Berhasil mengunggah");
    // } catch (IOException e) {
    // e.printStackTrace();
    // model.addAttribute("message", "Gagal mengunggah berkas");
    // return "uploadStatus";
    // }
    // return "uploadStatus";
    // }

}
