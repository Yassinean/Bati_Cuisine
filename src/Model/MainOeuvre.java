package Model;

public class MainOeuvre extends Composant{

    private double tauxHoraire;
    private double heureTravail;
    private double productiviteOuvrier;

    public MainOeuvre(String nom, String typeComposant, double tauxTva, int projet_id, double tauxHoraire, double heureTravail, double productiviteOuvrier) {
        super(nom, typeComposant, tauxTva, projet_id);
        this.tauxHoraire = tauxHoraire;
        this.heureTravail = heureTravail;
        this.productiviteOuvrier = productiviteOuvrier;
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

    @Override
    public String toString() {
        return "MainOeuvre => " +
                "id = " + id +
                "\nTaux Horaire : " + tauxHoraire +
                "\nHeure de Travail : " + heureTravail +
                "\nProductivite Ouvrier=" + productiviteOuvrier;
    }
}
