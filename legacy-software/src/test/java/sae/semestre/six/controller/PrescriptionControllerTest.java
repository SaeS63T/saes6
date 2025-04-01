package sae.semestre.six.controller;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;

public class PrescriptionControllerTest {
    
    private PrescriptionController prescriptionController;
    
    @Before
    public void setUp() {
        prescriptionController = new PrescriptionController();
    }
    
    
    @Test
    public void testAddAndRetrievePrescription() {
        String result = prescriptionController.addPrescription(
            "PAT001",
            new String[]{"PARACETAMOL"},
            "Test notes"
        );
        
        assertTrue(result.contains("created"));
        
        List<String> prescriptions = prescriptionController.getPatientPrescriptions("PAT001");
        assertFalse(prescriptions.isEmpty());
        
        
        assertTrue(prescriptions.get(0).startsWith("RX"));
    }
    
    
    @Test
    public void testInventory() {
        prescriptionController.refillMedicine("PARACETAMOL", 10);
        assertEquals(10, (int) prescriptionController.getInventory().get("PARACETAMOL"));
    }
    
    
    @Test
    public void testClearData() {
        prescriptionController.clearAllData();
        assertTrue(prescriptionController.getInventory().isEmpty());
    }
} 