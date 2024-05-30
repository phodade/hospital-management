package org.dnyanyog.controller;

import java.util.List;
import org.dnyanyog.dto.AppointmentRequest;
import org.dnyanyog.dto.AppointmentResponse;
import org.dnyanyog.entity.Appointments;
import org.dnyanyog.service.AppointmentManagementServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AppointmentManagementController {

  @Autowired 
  private AppointmentManagementServiceImp appointmentService;

  @PostMapping(
      path = "/api/v1/appointment/add",
      consumes = {"application/json", "application/xml"},
      produces = {"application/json", "application/xml"})
  public AppointmentResponse addAppointment(@RequestBody AppointmentRequest appointmentRequest)
      throws Exception {
    return appointmentService.addAppointment(appointmentRequest);
  }

  @PostMapping("/api/v1/appointment/{patient_id}")
  public AppointmentResponse updateAppointment(
      @PathVariable("patient_id") String patientId, @RequestBody AppointmentRequest request) {
    return appointmentService.updateAppointment(patientId, request);
  }

  @GetMapping(path = "/api/v1/appointment/{appointmentId}")
  public AppointmentResponse searchAppointment(@PathVariable("appointmentId") String appointmentId) {
    return appointmentService.searchAppointment(appointmentId);
  }
  
  @GetMapping("/api/v1/patient/{patient_id}/appointments")
  public List<Appointments> getAppointmentsByPatientId(@PathVariable("patient_id") String patientId) {
    return appointmentService.getAppointmentsByPatientId(patientId);
  }

  @DeleteMapping(path = "/api/v1/appointment/{appointmentId}")
  public AppointmentResponse deleteAppointment(@PathVariable("appointmentId") String appointmentId) {
    return appointmentService.deleteAppointment(appointmentId);
  }
}
