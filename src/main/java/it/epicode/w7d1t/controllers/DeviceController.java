package it.epicode.w7d1t.controllers;

import it.epicode.w7d1t.exceptions.BadRequestException;
import it.epicode.w7d1t.exceptions.ErrorResponse;
import it.epicode.w7d1t.models.objects.CustomResponse;
import it.epicode.w7d1t.models.requests.DeviceRequest;
import it.epicode.w7d1t.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

    @GetMapping("/devices")
    public CustomResponse getAll(Pageable pageable){
        return new CustomResponse(
                HttpStatus.OK.toString(),
                deviceService.getAll(pageable));
    }

    @GetMapping("/devices/{id}")
    public CustomResponse getById(@PathVariable int id){
        return new CustomResponse(
                HttpStatus.OK.toString(),
                deviceService.getById(id));
    }

    @PostMapping("/devices")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomResponse save(@RequestBody @Validated DeviceRequest deviceRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()) throw new BadRequestException(ErrorResponse.handleValidationMessages(bindingResult));
        return new CustomResponse(
                HttpStatus.CREATED.toString(),
                deviceService.save(deviceRequest));
    }

    @PutMapping("/devices/{id}")
    public CustomResponse update(@PathVariable int id, @RequestBody @Validated DeviceRequest deviceRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()) throw new BadRequestException(ErrorResponse.handleValidationMessages(bindingResult));
        return new CustomResponse(
                HttpStatus.OK.toString(),
                deviceService.update(id, deviceRequest));
    }

    @DeleteMapping("/devices/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CustomResponse delete(@PathVariable int id){
        deviceService.delete(id);
        return new CustomResponse(HttpStatus.NO_CONTENT.toString(), null);
    }
}
