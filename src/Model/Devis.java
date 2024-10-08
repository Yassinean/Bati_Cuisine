package Model;

import java.time.LocalDate;

public class Devis {
    private int id;
    private double montantEstime;
    private LocalDate dateEmission;
    private LocalDate dateValidite;
    private boolean accept;
    private int projet_id;

    public Devis(double montantEstime, LocalDate dateEmission, LocalDate dateValidite, boolean accept , int projet_id) {
        this.montantEstime = montantEstime;
        this.dateEmission = dateEmission;
        this.dateValidite = dateValidite;
        this.accept = accept;
        this.projet_id = projet_id;
    }

    public Devis(){}
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMontantEstime() {
        return montantEstime;
    }

    public void setMontantEstime(double montantEstime) {
        this.montantEstime = montantEstime;
    }

    public LocalDate getDateEmission() {
        return dateEmission;
    }

    public void setDateEmission(LocalDate dateEmission) {
        this.dateEmission = dateEmission;
    }

    public LocalDate getDateValidite() {
        return dateValidite;
    }

    public void setDateValidite(LocalDate dateValidite) {
        this.dateValidite = dateValidite;
    }

    public boolean isAccept() {
        return accept;
    }

    public void setAccept(boolean accept) {
        this.accept = accept;
    }

    @Override
    public String toString() {
        return "Devis =>" +
                "\nid = " + id +
                "\n Montant Estime : " + montantEstime +
                "\nDate Emission : " + dateEmission +
                "\nDate Validite : " + dateValidite +
                "\nAccept : " + accept;
    }
}
