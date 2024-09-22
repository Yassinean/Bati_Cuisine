package Model;

public abstract class Composant {
    protected int id;
    protected String nom;
    protected double tauxTva;


    public Composant(String nom,  double tauxTva ) {
        this.nom = nom;
        this.tauxTva = tauxTva;
    }

    public Composant(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getTauxTva() {
        return tauxTva;
    }

    public void setTauxTva(double tauxTva) {
        this.tauxTva = tauxTva;
    }

    @Override
    public String toString() {
        return "Composant =>" +
                "id = " + id +
                "\n Nom : " + nom +
                "\n tauxTva=" + tauxTva;
    }
}
