package Model;

public class Projet {
    private int id;
    private String nomProjet;
    private double margeBeneficiaire;
    private double coutTotal;

    public Projet(int id, String nomProjet, double margeBeneficiaire, double coutTotal) {
        this.id = id;
        this.nomProjet = nomProjet;
        this.margeBeneficiaire = margeBeneficiaire;
        this.coutTotal = coutTotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomProjet() {
        return nomProjet;
    }

    public void setNomProjet(String nomProjet) {
        this.nomProjet = nomProjet;
    }

    public double getMargeBeneficiaire() {
        return margeBeneficiaire;
    }

    public void setMargeBeneficiaire(double margeBeneficiaire) {
        this.margeBeneficiaire = margeBeneficiaire;
    }

    public double getCoutTotal() {
        return coutTotal;
    }

    public void setCoutTotal(double coutTotal) {
        this.coutTotal = coutTotal;
    }

    @Override
    public String toString() {
        return "Projet =>" +
                "\nid=" + id +
                "\nNom du Projet='" + nomProjet + '\'' +
                "\nMarge Beneficiaire=" + margeBeneficiaire +
                "\nCout Total=" + coutTotal;
    }
}
