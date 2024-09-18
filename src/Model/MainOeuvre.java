package Model;

public class MainOeuvre extends Composant{
    private int id;
    private double tauxHoraire;
    private double heureTravail;
    private double productiviteOuvrier;

    public MainOeuvre(int id, String nom, String typeComposant, double tauxTva, int id1, double tauxHoraire, double heureTravail, double productiviteOuvrier) {
        super(id, nom, typeComposant, tauxTva);
        this.id = id1;
        this.tauxHoraire = tauxHoraire;
        this.heureTravail = heureTravail;
        this.productiviteOuvrier = productiviteOuvrier;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
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
