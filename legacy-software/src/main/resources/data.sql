-- Insertion des médecins
INSERT INTO doctors (doctor_number, first_name, last_name, specialization, phone_number, email, department) VALUES
('DR001', 'Pierre', 'Dubois', 'Cardiologie', '0123456789', 'pierre.dubois@hopital.fr', 'Cardiologie'),
('DR002', 'Marie', 'Laurent', 'Pédiatrie', '0123456790', 'marie.laurent@hopital.fr', 'Pédiatrie'),
('DR003', 'Jean', 'Martin', 'Neurologie', '0123456791', 'jean.martin@hopital.fr', 'Neurologie');

-- Insertion des patients
INSERT INTO patients (patient_number, first_name, last_name, date_of_birth, gender, address, phone_number) VALUES
('PT001', 'Sophie', 'Moreau', '1985-06-15', 'F', '12 rue des Lilas, Paris', '0687654321'),
('PT002', 'Lucas', 'Bernard', '1990-03-22', 'M', '45 avenue Victor Hugo, Lyon', '0687654322'),
('PT003', 'Emma', 'Petit', '1978-11-30', 'F', '8 rue de la Paix, Marseille', '0687654323');

-- Insertion des rendez-vous
INSERT INTO appointments (appointment_number, patient_id, doctor_id, appointment_date, status, description, room_number) VALUES
('RDV001', 1, 1, '2024-02-15 09:00:00', 'CONFIRMÉ', 'Consultation cardiologique', 'A101'),
('RDV002', 2, 2, '2024-02-15 10:30:00', 'CONFIRMÉ', 'Suivi pédiatrique', 'B202'),
('RDV003', 3, 3, '2024-02-15 14:00:00', 'CONFIRMÉ', 'Consultation neurologique', 'C303');

-- Insertion des dossiers médicaux
INSERT INTO medical_records (record_number, patient_id, diagnosis, treatment, prescription, notes, record_date, doctor_id, blood_pressure, temperature, weight) VALUES
('DM001', 1, 'Hypertension artérielle', 'Traitement médicamenteux', 'Bétabloquants', 'Patient à surveiller', '2024-02-15', 1, '140/90', 37.2, 75.5),
('DM002', 2, 'Rhinopharyngite', 'Traitement symptomatique', 'Paracétamol', 'Repos conseillé', '2024-02-15', 2, '110/70', 38.5, 25.0),
('DM003', 3, 'Migraine chronique', 'Traitement préventif', 'Antimigraineux', 'Suivi régulier nécessaire', '2024-02-15', 3, '120/80', 36.8, 65.0);

-- Insertion des résultats de laboratoire
INSERT INTO lab_results (patient_history_id, test_name, result_value, test_date, notes) VALUES
(1, 'Numération formule sanguine', 'Normal', '2024-02-15', 'Pas d''anomalie détectée'),
(2, 'Test COVID-19', 'Négatif', '2024-02-15', 'Test PCR'),
(3, 'Bilan lipidique', 'Cholestérol légèrement élevé', '2024-02-15', 'Régime alimentaire conseillé');

-- Insertion des traitements
INSERT INTO treatments (name, patient_history_id, treatment_date, notes) VALUES
('Traitement antihypertenseur', 1, '2024-02-15', 'Suivi mensuel'),
('Antibiothérapie', 2, '2024-02-15', 'Traitement sur 7 jours'),
('Traitement antimigraineux', 3, '2024-02-15', 'Traitement préventif quotidien');

-- Insertion des ordonnances
INSERT INTO prescriptions (prescription_number, patient_id, medicines, notes, total_cost, is_billed, inventory_updated) VALUES
('ORD001', 1, 'Bétabloquant 50mg, Aspirine 100mg', 'Prendre matin et soir', 45.60, true, true),
('ORD002', 2, 'Paracétamol 500mg, Vitamine C', 'Prendre si fièvre', 22.30, true, true),
('ORD003', 3, 'Sumatriptan 50mg', 'Prendre dès les premiers symptômes', 35.80, true, true);

-- Insertion des factures
INSERT INTO bills (bill_number, patient_id, doctor_id, bill_date, total_amount, status) VALUES
('FAC001', 1, 1, '2024-02-15', 150.00, 'PAYÉE'),
('FAC002', 2, 2, '2024-02-15', 80.00, 'EN ATTENTE'),
('FAC003', 3, 3, '2024-02-15', 120.00, 'PAYÉE');

-- Insertion des détails des factures
INSERT INTO bill_details (bill_id, treatment_name, quantity, unit_price, line_total) VALUES
(1, 'Consultation cardiologique', 1, 150.00, 150.00),
(2, 'Consultation pédiatrique', 1, 80.00, 80.00),
(3, 'Consultation neurologique', 1, 120.00, 120.00);

-- Insertion des assurances
INSERT INTO insurance (policy_number, patient_id, provider, coverage_percentage, max_coverage, expiry_date) VALUES
('POL001', 1, 'Assurance Santé Plus', 80.00, 2000.00, '2025-12-31'),
('POL002', 2, 'MutuelleSanté', 90.00, 3000.00, '2025-12-31'),
('POL003', 3, 'AssurMed', 75.00, 1500.00, '2025-12-31');

-- Insertion des factures fournisseurs
INSERT INTO supplier_invoices (invoice_number, supplier_name, invoice_date, total_amount) VALUES
('FOUR001', 'Pharmacie Centrale', '2024-02-15', 1200.00),
('FOUR002', 'MedEquip', '2024-02-15', 2500.00),
('FOUR003', 'LabSupplies', '2024-02-15', 800.00);

-- Insertion des détails des factures fournisseurs
INSERT INTO supplier_invoice_details (invoice_id, inventory_id, quantity, unit_price) VALUES
(1, 1, 100, 12.00),
(2, 2, 5, 500.00),
(3, 3, 200, 4.00);

-- Insertion de l'historique des prix
INSERT INTO price_history (inventory_id, old_price, new_price, change_date) VALUES
(1, 10.00, 12.00, '2024-01-01'),
(2, 450.00, 500.00, '2024-01-15'),
(3, 3.50, 4.00, '2024-02-01');

-- Insertion de l'historique des patients
INSERT INTO patient_history (patient_id, visit_date, diagnosis, symptoms, notes) VALUES
(1, '2024-02-15', 'Hypertension artérielle', 'Maux de tête, vertiges', 'Patient à surveiller régulièrement'),
(2, '2024-02-15', 'Rhinopharyngite', 'Fièvre, congestion nasale', 'Évolution favorable'),
(3, '2024-02-15', 'Migraine chronique', 'Céphalées intenses', 'Traitement préventif mis en place');

-- Insertion de l'inventaire
INSERT INTO inventory (item_code, name, quantity, unit_price, reorder_level, last_restocked) VALUES
('MED001', 'Paracétamol 500mg', 1000, 0.15, 200, '2024-02-01 10:00:00'),
('MED002', 'Amoxicilline 1g', 500, 0.45, 100, '2024-02-01 10:00:00'),
('MAT001', 'Seringues 10ml', 2000, 0.25, 500, '2024-02-01 10:00:00'),
('PRO001', 'Gants médicaux M', 5000, 0.10, 1000, '2024-02-01 10:00:00'),
('HYG001', 'Solution hydroalcoolique', 200, 3.50, 50, '2024-02-01 10:00:00'),
('PAN001', 'Compresses stériles', 3000, 0.05, 500, '2024-02-01 10:00:00'),
('PAN002', 'Sparadrap hypoallergénique', 150, 2.20, 30, '2024-02-01 10:00:00'),
('MED003', 'Bétabloquant 50mg', 300, 0.30, 100, '2024-02-01 10:00:00'),
('MED004', 'Sumatriptan 50mg', 200, 1.20, 50, '2024-02-01 10:00:00'),
('PRO002', 'Masques chirurgicaux', 1000, 5.00, 200, '2024-02-01 10:00:00'); 