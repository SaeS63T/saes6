package sae.semestre.six.entities.prescription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sae.semestre.six.entities.billing.BillingService;
import sae.semestre.six.entities.patient.Patient;
import sae.semestre.six.entities.patient.PatientRepository;

import java.io.FileWriter;
import java.util.*;

@Service
public class PrescriptionService {

    private static final Map<String, List<String>> patientPrescriptions = new HashMap<>();
    private static final Map<String, Integer> medicineInventory = new HashMap<>();

    private static final Map<String, Double> medicinePrices = new HashMap<String, Double>() {{
        put("PARACETAMOL", 5.0);
        put("ANTIBIOTICS", 25.0);
        put("VITAMINS", 15.0);
    }};

    private static int prescriptionCounter = 0;
    private static final String AUDIT_FILE = "C:\\hospital\\prescriptions.log";

    @Autowired
    private BillingService billingService;

    @Autowired
    private PatientRepository patientDao;

    @Autowired
    private PrescriptionRepository prescriptionDao;

    public String addPrescription(String patientId, String[] medicines, String notes) {
        try {
            prescriptionCounter++;
            String prescriptionId = "RX" + prescriptionCounter;

            Prescription prescription = new Prescription();
            prescription.setPrescriptionNumber(prescriptionId);

            Patient patient = patientDao.findById(Long.parseLong(patientId));
            prescription.setPatient(patient);
            prescription.setMedicines(String.join(",", medicines));
            prescription.setNotes(notes);

            double cost = calculateCost(prescriptionId);
            prescription.setTotalCost(cost);

            prescriptionDao.save(prescription);

            new FileWriter(AUDIT_FILE, true)
                    .append(new Date().toString() + " - " + prescriptionId + "\n")
                    .close();

            List<String> currentPrescriptions = patientPrescriptions.getOrDefault(patientId, new ArrayList<>());
            currentPrescriptions.add(prescriptionId);
            patientPrescriptions.put(patientId, currentPrescriptions);

            billingService.processBill(
                    patientId,
                    "SYSTEM",
                    new String[]{"PRESCRIPTION_" + prescriptionId}
            );

            for (String medicine : medicines) {
                int current = medicineInventory.getOrDefault(medicine, 0);
                medicineInventory.put(medicine, current - 1);
            }

            return "Prescription " + prescriptionId + " created and billed";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed: " + e.toString();
        }
    }

    public List<String> getPatientPrescriptions(String patientId) {
        return patientPrescriptions.getOrDefault(patientId, new ArrayList<>());
    }

    public Map<String, Integer> getInventory() {
        return medicineInventory;
    }

    public String refillMedicine(String medicine, int quantity) {
        medicineInventory.put(medicine, medicineInventory.getOrDefault(medicine, 0) + quantity);
        return "Refilled " + medicine;
    }

    public double calculateCost(String prescriptionId) {
        return medicinePrices.values().stream()
                .mapToDouble(Double::doubleValue)
                .sum() * 1.2; // Placeholder logic
    }

    public void clearAllData() {
        patientPrescriptions.clear();
        medicineInventory.clear();
        prescriptionCounter = 0;
    }
}

