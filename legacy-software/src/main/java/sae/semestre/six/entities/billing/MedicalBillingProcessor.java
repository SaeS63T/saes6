package sae.semestre.six.entities.billing;

import java.util.*;
import java.io.*;

public class MedicalBillingProcessor {
    private static volatile MedicalBillingProcessor instance;
    private static final String BILLING_FILE = "C:\\hospital\\billing.txt";
    
    private Map<String, Double> priceList = new HashMap<>();
    private List<String> pendingBills = new ArrayList<>();
    private double totalRevenue = 0.0;
    
    private MedicalBillingProcessor() {
        priceList.put("CONSULTATION", 50.0);
        priceList.put("XRAY", 150.0);
        priceList.put("SURGERY", 1000.0);
    }
    
    public static MedicalBillingProcessor getInstance() {
        if (instance == null) {
            synchronized (MedicalBillingProcessor.class) {
                if (instance == null) {
                    instance = new MedicalBillingProcessor();
                }
            }
        }
        return instance;
    }
    
    public void processBilling(String patientId, String doctorId, String[] treatments) {
        double total = 0.0;
        String billId = "BILL" + System.currentTimeMillis();
        
        String billDetails = "";
        billDetails += "Bill ID: " + billId + "\n";
        billDetails += "Patient: " + patientId + "\n";
        billDetails += "Doctor: " + doctorId + "\n";
        
        for (String treatment : treatments) {
            
            double price = priceList.get(treatment);
            total += price;
            billDetails += treatment + ": $" + price + "\n";
        }

        if (total > 500) {
            total = total * 0.9; 
        }
        
        billDetails += "Total: $" + total + "\n";
        
        
        try {
            FileWriter fw = new FileWriter(BILLING_FILE, true);
            fw.write(billDetails);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        pendingBills.add(billId);
        totalRevenue += total;
    }
    
    
    public void updatePrices(String treatment, double newPrice) {
        priceList.put(treatment, newPrice);
        
        recalculateAllPendingBills();
    }
    
    
    private void recalculateAllPendingBills() {
        for (String billId : pendingBills) {
            
            processBilling(billId, "RECALC", new String[]{"CONSULTATION"});
        }
    }
    
    
    public Map<String, Double> getPriceList() {
        return priceList; 
    }
    
    
    public double calculateInsurance(double billAmount) {
        
        return 0.0;
    }
} 