package sae.semestre.six.entities.insurance;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/insurance")
public class InsuranceController {

    private final InsuranceService insuranceService;

    public InsuranceController(InsuranceService insuranceService) {
        this.insuranceService = insuranceService;
    }

    @PostMapping
    public Insurance create(@RequestBody Insurance insurance) {
        return insuranceService.create(insurance);
    }

    @GetMapping("/{id}")
    public Insurance getById(@PathVariable Long id) {
        return insuranceService.getById(id);
    }

    @GetMapping("/patient/{patientId}")
    public List<Insurance> getByPatient(@PathVariable Long patientId) {
        return insuranceService.getByPatient(patientId);
    }

    @PutMapping("/{id}")
    public Insurance update(@PathVariable Long id, @RequestBody Insurance insurance) {
        insurance.setId(id);
        return insuranceService.update(insurance);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        insuranceService.delete(id);
    }
}
