package Model;

import Model.Enum.EtatProjet;

import java.util.List;

public class Projet {
    private int id;
    private String nomProjet;
    private double margeBeneficiaire;
    private double coutTotal;
    private double surface;
    private Client client;
    private List<Materiaux> materiauxes;
    private List<MainOeuvre> mainOeuvres;
    private List<Composant> composants;
    private EtatProjet etatProjet;

    public Projet(String nomProjet, double margeBeneficiaire, double coutTotal, double surface,EtatProjet etatProjet) {
        this.nomProjet = nomProjet;
        this.margeBeneficiaire = margeBeneficiaire;
        this.coutTotal = coutTotal;
        this.surface = surface;
        this.etatProjet = etatProjet;
    }

    public Projet(){}

    public Projet(String nomProjet , double surface){
        this.nomProjet = nomProjet;
        this.surface = surface;
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

    public EtatProjet getEtatProjet() {
        return etatProjet;
    }

    public void setEtatProjet(EtatProjet etatProjet) {
        this.etatProjet = etatProjet;
    }

    public double getSurface() {
        return surface;
    }

    public void setSurface(double surface) {
        this.surface = surface;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Materiaux> getMateriauxes() {
        return materiauxes;
    }

    public void setMateriauxes(List<Materiaux> materiauxes) {
        this.materiauxes = materiauxes;
    }

    public List<MainOeuvre> getMainOeuvres() {
        return mainOeuvres;
    }

    public void setMainOeuvres(List<MainOeuvre> mainOeuvres) {
        this.mainOeuvres = mainOeuvres;
    }

    public List<Composant> getComposants() {
        return composants;
    }

    public void setComposants(List<Composant> composants) {
        this.composants = composants;
    }

    @Override
    public String toString() {
        return "Projet =>" +
                "\nid= " + id +
                "\nNom du Projet : " + nomProjet +
                "\nMarge Beneficiaire : " + margeBeneficiaire +
                "\nCout Total : " + coutTotal;
    }
}
