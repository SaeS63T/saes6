package sae.semestre.six.ms_assurance;

import jakarta.persistence.*;

@Entity
@Table(name = "remboursement")
public class Remboursement {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String nomProduit;
    private String nomAssurance;
    private double montantBase;
    private double tauxRemboursement;
    private double montantRembourse;

    // Getters et setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public String getNomAssurance() {
        return nomAssurance;
    }

    public void setNomAssurance(String nomAssurance) {
        this.nomAssurance = nomAssurance;
    }

    public double getMontantBase() {
        return montantBase;
    }

    public void setMontantBase(double montantBase) {
        this.montantBase = montantBase;
    }

    public double getTauxRemboursement() {
        return tauxRemboursement;
    }

    public void setTauxRemboursement(double tauxRemboursement) {
        this.tauxRemboursement = tauxRemboursement;
    }

    public double getMontantRembourse() {
        return montantRembourse;
    }

    public void setMontantRembourse(double montantRembourse) {
        this.montantRembourse = montantRembourse;
    }
}
