package Model;

import Model.Enum.MainOeuvreType;

public class MainOeuvre extends Composant{

    private double tauxHoraire;
    private double heureTravail;
    private double productiviteOuvrier;
    private MainOeuvreType mainOeuvreType;

    public MainOeuvre(){}

    public MainOeuvre(String nom, double tauxTva, double tauxHoraire, double heureTravail, double productiviteOuvrier , MainOeuvreType mainOeuvreType) {
        super(nom, tauxTva);
        this.tauxHoraire = tauxHoraire;
        this.heureTravail = heureTravail;
        this.productiviteOuvrier = productiviteOuvrier;
        this.mainOeuvreType = mainOeuvreType;
    }

    public MainOeuvre(double tauxHoraire, double heureTravail, double productiviteOuvrier , MainOeuvreType mainOeuvreType){
        this.tauxHoraire = tauxHoraire;
        this.heureTravail = heureTravail;
        this.productiviteOuvrier = productiviteOuvrier;
        this.mainOeuvreType = mainOeuvreType;

    }

    public double getTauxHoraire() {
        return tauxHoraire;
    }

    public void setTauxHoraire(double tauxHoraire) {
        this.tauxHoraire = tauxHoraire;
    }

    public double getHeureTravail() {
        return heureTravail;
    }

    public void setHeureTravail(double heureTravail) {
        this.heureTravail = heureTravail;
    }

    public double getProductiviteOuvrier() {
        return productiviteOuvrier;
    }

    public void setProductiviteOuvrier(double productiviteOuvrier) {
        this.productiviteOuvrier = productiviteOuvrier;
    }

    public MainOeuvreType getMainOeuvreType() {
        return mainOeuvreType;
    }

    public void setMainOeuvreType(MainOeuvreType mainOeuvreType) {
        this.mainOeuvreType = mainOeuvreType;
    }

    @Override
    public String toString() {
        return "MainOeuvre => " +
                "id = " + id +
                "\nTaux Horaire : " + tauxHoraire +
                "\nHeure de Travail : " + heureTravail +
                "\nProductivite Ouvrier : " + productiviteOuvrier +
                "\nType de main d'oeuvre : " + mainOeuvreType
                ;
    }
}
