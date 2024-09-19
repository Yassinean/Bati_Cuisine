package Model;

public class Composant {
    protected int id;
    protected String nom;
    protected String typeComposant;
    protected double tauxTva;
    protected int projet_id;


    public Composant(String nom, String typeComposant, double tauxTva , int projet_id) {
        this.nom = nom;
        this.typeComposant = typeComposant;
        this.tauxTva = tauxTva;
        this.projet_id = projet_id;
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

    public int getProjet_id() {
        return projet_id;
    }

    public void setProjet_id(int projet_id) {
        this.projet_id = projet_id;
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
