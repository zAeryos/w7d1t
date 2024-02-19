package it.epicode.w7d1t.models.objects;

import it.epicode.w7d1t.models.enums.DeviceStatus;
import it.epicode.w7d1t.models.enums.DeviceType;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "devices")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "devices_sequence")
    @SequenceGenerator(name = "devices_sequence", initialValue = 1, allocationSize = 1)
    private int             id;

    @Column(name = "device_status")
    @Enumerated(EnumType.STRING)
    private DeviceStatus    deviceStatus;
    @Column(name = "device_type")
    @Enumerated(EnumType.STRING)
    private DeviceType      deviceType;
    @ManyToOne
    @JoinColumn(name = "employee_fk")
    private Employee        employee;

    public Device(Employee employee, DeviceStatus deviceStatus, DeviceType deviceType) {
        this.employee       = employee;
        this.deviceStatus   = deviceStatus;
        this.deviceType     = deviceType;
    }

    public Device(){}

}
