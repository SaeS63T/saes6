# ms-assurance

- http://localhost:8888/
- http://localhost:8888/remboursement/search/remboursementByNomProduitAndNomAssurance
- http://localhost:8888/swagger-ui/index.html

## Jeu de données
```
INSERT INTO remboursements (nom_produit, nom_assurance, montant_base, taux_remboursement, montant_rembourse)
VALUES 
('CONSULTATION', 'SECUREPLUS', 30.0, 70.0, 21.0),
('CONSULTATION', 'PROTEXIA', 30.0, 65.0, 19.5),
('CONSULTATION', 'SANTEVIE', 30.0, 50.0, 15.0),
('CONSULTATION', 'COVIMUT', 30.0, 75.0, 22.5),
('CONSULTATION', 'ASSURPRO', 30.0, 60.0, 18.0),
('HOSPITALISATION', 'SECUREPLUS', 1200.0, 90.0, 1080.0),
('HOSPITALISATION', 'PROTEXIA', 1200.0, 85.0, 1020.0),
('HOSPITALISATION', 'COVIMUT', 1200.0, 70.0, 840.0),
('HOSPITALISATION', 'ASSURPRO', 1200.0, 65.0, 780.0),
('ANALYSES', 'SECUREPLUS', 50.0, 60.0, 30.0),
('ANALYSES', 'SANTEVIE', 50.0, 80.0, 40.0),
('ANALYSES', 'COVIMUT', 50.0, 50.0, 25.0),
('ANALYSES', 'ASSURPRO', 50.0, 80.0, 40.0),
('RADIOLOGIE', 'SECUREPLUS', 80.0, 75.0, 60.0),
('RADIOLOGIE', 'PROTEXIA', 80.0, 70.0, 56.0),
('RADIOLOGIE', 'ASSURPRO', 80.0, 50.0, 40.0),
('DENTISTERIE', 'SANTEVIE', 150.0, 60.0, 90.0),
('DENTISTERIE', 'PROTEXIA', 150.0, 80.0, 120.0),
('DENTISTERIE', 'ASSURPRO', 150.0, 70.0, 105.0),
('VACCINATION', 'SECUREPLUS', 20.0, 100.0, 20.0),
('VACCINATION', 'PROTEXIA', 20.0, 50.0, 10.0),
('VACCINATION', 'COVIMUT', 20.0, 90.0, 18.0),
('PHYSIOTHERAPIE', 'SECUREPLUS', 50.0, 70.0, 35.0),
('PHYSIOTHERAPIE', 'SANTEVIE', 50.0, 80.0, 40.0),
('PHARMACIE', 'SECUREPLUS', 10.0, 100.0, 10.0),
('PHARMACIE', 'PROTEXIA', 10.0, 90.0, 9.0),
('CHIRURGIE', 'SECUREPLUS', 5000.0, 95.0, 4750.0),
('CHIRURGIE', 'PROTEXIA', 5000.0, 85.0, 4250.0),
('MATERNITE', 'SECUREPLUS', 2000.0, 80.0, 1600.0),
('MATERNITE', 'SANTEVIE', 2000.0, 75.0, 1500.0);
```
Les mêmes instructions d'insertion se trouvent dans `src/main/resources/data.sql` afin que les données soient chargées automatiquement au démarrage du service.
