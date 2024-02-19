package it.epicode.w7d1t.models.requests;

import it.epicode.w7d1t.models.enums.DeviceStatus;
import it.epicode.w7d1t.models.enums.DeviceType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DeviceRequest {

    @NotNull(       message = "Device type is mandatory")
    private DeviceType      deviceType;
    @NotNull(       message = "Device status is mandatory")
    private DeviceStatus    deviceStatus;
    @NotNull(       message = "Employee id is mandatory")
    @Min(value = 1, message = "The minimum value for the employee id is 1")
    private Integer         employeeId;

}
