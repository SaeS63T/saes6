package sae.semestre.six.entities.prescription;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sae.semestre.six.entities.billing.BillingService;
import sae.semestre.six.entities.patient.Patient;
import sae.semestre.six.entities.patient.PatientRepository;
import sae.semestre.six.entities.inventory.Inventory;
import sae.semestre.six.entities.inventory.InventoryRepository;

import java.io.FileWriter;
import java.util.*;

@Service
public class PrescriptionService {

    private static final Map<String, List<String>> patientPrescriptions = new HashMap<>();
    private static final Map<String, Integer> medicineInventory = new HashMap<>();

    private static final Map<String, Double> medicinePrices = new HashMap<>();

    private static int prescriptionCounter = 0;

    @Value("${hospital.prescription.audit}")
    private String auditFile;

    @Autowired
    private BillingService billingService;

    @Autowired
    private PatientRepository patientDao;

    @Autowired
    private InventoryRepository inventoryDao;

    @Autowired
    private PrescriptionRepository prescriptionDao;

    @PostConstruct
    private void loadData() {
        for (Inventory item : inventoryDao.findAll()) {
            medicinePrices.put(item.getName(), item.getUnitPrice());
            medicineInventory.put(item.getName(), item.getQuantity());
        }
    }

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

            new FileWriter(auditFile, true)
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
        Inventory inv = inventoryDao.findByItemCode(medicine);
        int newQty = inv.getQuantity() + quantity;
        inventoryDao.updateStock(medicine, newQty);
        medicineInventory.put(medicine, newQty);
        return "Refilled " + medicine;
    }

    public double calculateCost(String prescriptionId) {
        Prescription prescription = prescriptionDao.findByPrescriptionNumber(prescriptionId);
        String[] meds = prescription.getMedicines().split(",");
        double total = 0.0;
        for (String med : meds) {
            Double price = medicinePrices.get(med);
            if (price != null) {
                total += price;
            }
        }
        return total * 1.2; // Placeholder markup
    }

    public void clearAllData() {
        patientPrescriptions.clear();
        medicineInventory.clear();
        prescriptionCounter = 0;
    }
}

