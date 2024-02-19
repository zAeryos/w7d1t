package it.epicode.w7d1t.controllers;

import com.cloudinary.Cloudinary;
import it.epicode.w7d1t.exceptions.BadRequestException;
import it.epicode.w7d1t.exceptions.ErrorResponse;
import it.epicode.w7d1t.models.objects.CustomResponse;
import it.epicode.w7d1t.models.requests.EmployeeRequest;
import it.epicode.w7d1t.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private Cloudinary      cloudinary;

    @GetMapping("/employees")
    public CustomResponse getAll(Pageable pageable){
        return new CustomResponse (
                HttpStatus.OK.toString(),
                employeeService.getAll(pageable));
    }

    @GetMapping("/employees/{id}")
    public CustomResponse getById(@PathVariable int id){
        return new CustomResponse (
                HttpStatus.OK.toString(),
                employeeService.getById(id));
    }

    @PostMapping("/employees")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomResponse save(@RequestBody @Validated EmployeeRequest employeeRequest, BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            String validationErrors = ErrorResponse.handleValidationMessages(bindingResult);
            throw new BadRequestException(validationErrors);
        }
        return new CustomResponse (
                HttpStatus.CREATED.toString(),
                employeeService.save(employeeRequest));
    }

    @PutMapping("/employees/{id}")
    public CustomResponse update(@PathVariable int id, @RequestBody @Validated EmployeeRequest employeeRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()) throw new BadRequestException(ErrorResponse.handleValidationMessages(bindingResult));
        return new CustomResponse (
                HttpStatus.OK.toString(),
                employeeService.update(id, employeeRequest));
    }

    @DeleteMapping("/employees/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CustomResponse delete(@PathVariable int id){
        employeeService.delete(id);
        return new CustomResponse (HttpStatus.NO_CONTENT.toString(), null);
    }

    @PatchMapping("/employees/{id}/upload")
    public CustomResponse uploadPfp(@PathVariable int id, @RequestParam("upload") MultipartFile file) throws IOException {
        return new CustomResponse (HttpStatus.OK.toString(), employeeService.updatePfp(id, (String)cloudinary.uploader().upload(file.getBytes(), new HashMap()).get("url")));
    }
}
