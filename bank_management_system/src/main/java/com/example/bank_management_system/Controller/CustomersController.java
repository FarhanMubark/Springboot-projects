package com.example.bank_management_system.Controller;

import com.example.bank_management_system.ApiResponse.ApiResponse;
import com.example.bank_management_system.Model.Customers;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomersController {

    ArrayList<Customers> customers = new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<Customers> getCustomers(){
        return customers;
    }

    @PostMapping("/add")
    public ApiResponse addCustomers(@RequestBody Customers cust){
        customers.add(cust);
        return new ApiResponse("added successfully",200);
    }

    @PutMapping("/update/{index}")
    public ApiResponse updateCustomers(@PathVariable int index, @RequestBody Customers cust){
        customers.set(index,cust);
        return new ApiResponse("updated successfully",200);
    }

    @DeleteMapping("/delete/{index}")
    public  ApiResponse deleteCustomers(@PathVariable int index){
        customers.remove(index);
        return new ApiResponse("deleted successfully",200);
    }

    @PutMapping("/deposit/{id}/{amount}")
    public ApiResponse deposit(@PathVariable String id,@PathVariable int amount){

            for (Customers cust2 : customers) {
                if (cust2.getId().equals(id) && amount > 0) {
                    int balance = cust2.getBalance() + amount;
                    cust2.setBalance(balance);
                    return new ApiResponse("Money add successfully",200);
                }

            }
        return new ApiResponse("The Money Not Added",200);
    }

    @PutMapping("/withdraw/{id}/{amount}")
    public ApiResponse withdraw(@PathVariable String id,@PathVariable int amount){
        for (Customers cust2 : customers) {
            if (cust2.getId().equals(id) && amount <= cust2.getBalance()) {
                amount = cust2.getBalance() - amount;
                cust2.setBalance(amount);
                return new ApiResponse("withdraw opration done successfully",200);
            }

        }
        return new ApiResponse("withdraw opration Fail",200);
    }

}
