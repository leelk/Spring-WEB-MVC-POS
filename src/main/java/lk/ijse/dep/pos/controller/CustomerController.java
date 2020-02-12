package lk.ijse.dep.pos.controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/customers")
@CrossOrigin
@RestController
public class CustomerController {


    @GetMapping
    public String getAllCustomers(){
        return "all Customers";
    }
}
