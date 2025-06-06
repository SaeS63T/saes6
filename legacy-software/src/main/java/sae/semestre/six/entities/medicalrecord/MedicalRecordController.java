package sae.semestre.six.entities.medicalrecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sae.semestre.six.entities.medicalrecord.dto.MedicalRecordRequest;

@RestController
@RequestMapping("/medical-records")
public class MedicalRecordController {

    @Autowired
    private MedicalRecordService medicalRecordService;

    @PostMapping("/create-with-bill")
    public MedicalRecord createWithBill(@RequestBody MedicalRecordRequest request) {
        return medicalRecordService.createRecordWithBilling(
                request.getPatientId(),
                request.getDoctorId(),
                request.getDiagnosis(),
                request.getItems()
        );
    }
}
