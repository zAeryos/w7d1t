package it.epicode.w7d1t.services;

import it.epicode.w7d1t.exceptions.AlreadyAssignedException;
import it.epicode.w7d1t.exceptions.DecommissionedException;
import it.epicode.w7d1t.exceptions.MaintenanceException;
import it.epicode.w7d1t.exceptions.NotFoundException;
import it.epicode.w7d1t.models.enums.DeviceStatus;
import it.epicode.w7d1t.models.objects.Device;
import it.epicode.w7d1t.models.objects.Employee;
import it.epicode.w7d1t.models.requests.DeviceRequest;
import it.epicode.w7d1t.repositories.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {
    @Autowired
    private DeviceRepository    deviceRepository;
    @Autowired
    private EmployeeService  employeeService;

    public Page<Device> getAll(Pageable pageable) {

        return deviceRepository.findAll(pageable);

    }

    public Device getById(int id) {

        return deviceRepository.findById(id).orElseThrow(() -> new NotFoundException("Device with id " + id + " not found."));

    }

    public Device save(DeviceRequest deviceRequest) throws NotFoundException{

        checkStatus(deviceRequest);

        Employee    employee    = deviceRequest.getEmployeeId() == null ? null : employeeService.getById(deviceRequest.getEmployeeId());
        Device      device      = new Device();

        device.setDeviceStatus  (deviceRequest.getDeviceStatus());
        device.setDeviceType    (deviceRequest.getDeviceType());
        device.setEmployee      (employee);

        return deviceRepository.save(device);

    }

    public Device update(int id , DeviceRequest deviceRequest) throws NotFoundException{

        checkStatus(deviceRequest);

        Employee    employee    = employeeService.getById(deviceRequest.getEmployeeId());
        Device      device      = getById(id);

        device.setDeviceStatus  (deviceRequest.getDeviceStatus());
        device.setDeviceType    (deviceRequest.getDeviceType());
        device.setEmployee      (employee);

        return deviceRepository.save(device);

    }

    public void delete(int id)throws NotFoundException{

        Device device = getById(id);
        deviceRepository.delete(device);

    }

    public void checkStatus(DeviceRequest deviceRequest) {
        if (deviceRequest.getDeviceStatus().equals(DeviceStatus.ASSIGNED)) {
            throw new AlreadyAssignedException("The device is already assigned.");
        } else if (deviceRequest.getDeviceStatus().equals(DeviceStatus.IN_MAINTENANCE)) {
            throw new MaintenanceException("The device is currently in maintenance.");
        } else if (deviceRequest.getDeviceStatus().equals(DeviceStatus.DECOMMISSIONED)) {
            throw new DecommissionedException("The device has been decommissioned.");
        }
    }
}
