package sae.semestre.six.entities.prescription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/prescriptions")
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    @PostMapping("/add")
    public String addPrescription(
            @RequestParam String patientId,
            @RequestParam String[] medicines,
            @RequestParam String notes) {
        return prescriptionService.addPrescription(patientId, medicines, notes);
    }

    @GetMapping("/patient/{patientId}")
    public List<String> getPatientPrescriptions(@PathVariable String patientId) {
        return prescriptionService.getPatientPrescriptions(patientId);
    }

    @GetMapping("/inventory")
    public Map<String, Integer> getInventory() {
        return prescriptionService.getInventory();
    }

    @PostMapping("/refill")
    public String refillMedicine(@RequestParam String medicine, @RequestParam int quantity) {
        return prescriptionService.refillMedicine(medicine, quantity);
    }

    @GetMapping("/cost/{prescriptionId}")
    public double calculateCost(@PathVariable String prescriptionId) {
        return prescriptionService.calculateCost(prescriptionId);
    }

    @DeleteMapping("/clear")
    public void clearAllData() {
        prescriptionService.clearAllData();
    }
}
