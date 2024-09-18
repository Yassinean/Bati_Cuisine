package Model;

public class Composant {
    protected int id;
    protected String nom;
    protected String typeComposant;
    protected double tauxTva;

    public Composant(int id, String nom, String typeComposant, double tauxTva) {
        this.id = id;
        this.nom = nom;
        this.typeComposant = typeComposant;
        this.tauxTva = tauxTva;
    }

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

    public String getTypeComposant() {
        return typeComposant;
    }

    public void setTypeComposant(String typeComposant) {
        this.typeComposant = typeComposant;
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
                "\n Type de Composant : " + typeComposant +
                "\n tauxTva=" + tauxTva;
    }
}
