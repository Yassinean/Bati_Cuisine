package Model;

public class Materiaux extends Composant {
    private double coutUnitaire;
    private double quantite;
    private double coutTransport;
    private double coefficientQualite;

    public Materiaux(String nom, double tauxTva, double coutUnitaire, double quantite, double coutTransport, double coefficientQualite) {
        super(nom, tauxTva);
        this.coutUnitaire = coutUnitaire;
        this.quantite = quantite;
        this.coutTransport = coutTransport;
        this.coefficientQualite = coefficientQualite;
    }

    public Materiaux(){
        super();
    }

    public Materiaux(String nomMaterial,  double coutU, double quantite, double coutT, double coeff) {
        this.nom = nomMaterial;
        this.coutUnitaire = coutU;
        this.coutTransport = coutT;
        this.quantite = quantite;
        this.coefficientQualite = coeff;
    }

    public double getCoutUnitaire() {
        return coutUnitaire;
    }

    public void setCoutUnitaire(double coutUnitaire) {
        this.coutUnitaire = coutUnitaire;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public double getCoutTransport() {
        return coutTransport;
    }

    public void setCoutTransport(double coutTransport) {
        this.coutTransport = coutTransport;
    }

    public double getCoefficientQualite() {
        return coefficientQualite;
    }

    public void setCoefficientQualite(double coefficientQualite) {
        this.coefficientQualite = coefficientQualite;
    }

    @Override
    public String toString() {
        return "Materiaux =>" +
                "\nCout Unitaire : " + coutUnitaire +
                "\nQuantite : " + quantite +
                "\nCout Transport : " + coutTransport +
                "\nCoefficient Qualite : " + coefficientQualite +
                '}';
    }
}
