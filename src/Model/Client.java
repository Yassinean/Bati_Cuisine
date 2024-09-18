package Model;

public class Client {
    private int id;
    private String nom;
    private String address;
    private String telephone;
    private boolean estProfessionel;

    public Client(int id, String nom, String address, String telephone, boolean estProfessionel) {
        this.id = id;
        this.nom = nom;
        this.address = address;
        this.telephone = telephone;
        this.estProfessionel = estProfessionel;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public boolean isEstProfessionel() {
        return estProfessionel;
    }

    public void setEstProfessionel(boolean estProfessionel) {
        this.estProfessionel = estProfessionel;
    }

    @Override
    public String toString() {
        return "Client =>" +
                "\nid=" + id +
                "\n Nom='" + nom  +
                "\n Address='" + address +
                "\n Telephone='" + telephone +
                "\n estProfessionel=" + estProfessionel;
    }
}
