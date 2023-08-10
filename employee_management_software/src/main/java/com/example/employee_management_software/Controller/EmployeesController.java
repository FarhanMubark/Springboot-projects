package com.example.employee_management_software.Controller;

import com.example.employee_management_software.ApiResponse.ApiResponse;
import com.example.employee_management_software.Model.EmployeesModel;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeesController {

    ArrayList<EmployeesModel> employees = new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<EmployeesModel> getEmployees(){
        return employees;
    }

    @PostMapping("/add")
    public ResponseEntity addEmployees(@RequestBody @Valid EmployeesModel employeesModel, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }

        employees.add(employeesModel);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Employee add"));
    }

    @PutMapping("/update/{index}")
    public ResponseEntity updateEmployees(@PathVariable int index, @Valid @RequestBody EmployeesModel employeesModel, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }

        employees.set(index, employeesModel);

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Employee updated !"));
    }

    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteEmployees(@PathVariable int index){
        if(index<0||index>employees.size()){
            return ResponseEntity.status(500).body(new ApiResponse("not found in array list"));
        }
        employees.remove(index);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Employee deleted !"));
    }

      @PutMapping("/checkannual/{id}/{day}")
    public ResponseEntity checkAnnual(@PathVariable String id,@PathVariable int day){
        for (EmployeesModel model:employees){
            if (id.equals(model.getId())){
                if (model.isOnLeave()){
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("The Employee is Already on leave !"));
                }
                int days  = model.getAnnualLeave() - day;
                model.setOnLeave(true);
                model.setAnnualLeave(days);
                return ResponseEntity.status(200).body(new ApiResponse("Operation Done!"));
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Operation Fail"));
    }


}
