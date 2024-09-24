package Repository.Implementation;

import Config.DbConfig;
import Model.*;
import Model.Enum.EtatProjet;
import Model.Enum.MainOeuvreType;
import Repository.Interface.IProjet;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjetImp implements IProjet {

    private Connection connection;

    public ProjetImp() {
        try {
            this.connection = DbConfig.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Projet findProjectById(Integer id)
    {
        Projet projet = null;
        String queryProjet = "SELECT p.id as projet_id, p.nomprojet, p.surface, p.margebenificiare, p.couttotal, p.etatprojet as etatProjet, " +
                "c.id as client_id, c.nom as client_nom, c.address " +
                "FROM projet p " +
                "JOIN client c ON p.client_id = c.id " +
                "WHERE p.id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(queryProjet)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                projet = new Projet();
                projet.setId(rs.getInt("projet_id"));
                projet.setNomProjet(rs.getString("nomprojet"));
                projet.setSurface(rs.getDouble("surface"));
                projet.setCoutTotal(rs.getDouble("couttotal"));
                projet.setMargeBeneficiaire(rs.getDouble("margebenificiare"));
                projet.setEtatProjet(EtatProjet.valueOf(rs.getString("etatProjet")));

                Client client = new Client();
                client.setId(rs.getInt("client_id"));
                client.setNom(rs.getString("client_nom"));
                client.setAddress(rs.getString("address"));
                projet.setClient(client);

                projet.setMateriauxes(new ArrayList<>());
                projet.setMainOeuvres(new ArrayList<>());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        if (projet != null) {
            String queryMateriaux = "SELECT * FROM materiaux WHERE projet_id = ?";
            try (PreparedStatement stmt = connection.prepareStatement(queryMateriaux)) {
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Materiaux materiel = new Materiaux(
                            rs.getString("nom"),
                            rs.getDouble("tauxtva"),
                            rs.getDouble("coutunitaire"),
                            rs.getDouble("quantite"),
                            rs.getDouble("couttransport"),
                            rs.getDouble("coefficientqualite")
                    );
                    materiel.setId(rs.getInt("id"));
                    projet.getMateriauxes().add(materiel);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            String queryMainDoeuvre = "SELECT * FROM maindoeuvre WHERE projet_id = ?";
            try (PreparedStatement stmt = connection.prepareStatement(queryMainDoeuvre)) {
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    MainOeuvre mainDoeuvre = new MainOeuvre(
                            rs.getString("nom"),
                            rs.getDouble("tauxtva"),
                            rs.getDouble("tauxhoraire"),
                            rs.getDouble("heurestravail"),
                            rs.getDouble("productiviteouvrier"),
                            MainOeuvreType.valueOf(rs.getString("mainoeuvretype"))
                    );
                    mainDoeuvre.setId(rs.getInt("id"));
                    projet.getMainOeuvres().add(mainDoeuvre);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return projet;

    }

    @Override
    public Map<Integer, Projet> findAllProjects() {
        String sql = "SELECT p.id AS projet_id, " +
                "p.nomprojet, " +
                "p.surface AS projet_surface, " +
                "p.margebenificiare, " +
                "p.couttotal, " +
                "p.etatprojet AS etatProjet, " +
                "c.id AS client_id, " +
                "c.nom AS client_nom, " +
                "c.telephone AS client_telephone," +
                "c.estprofessionel AS client_estprofessionel," +
                "c.address " +
                "FROM projet p " +
                "JOIN client c ON p.client_id = c.id";

        Map<Integer, Projet> projects = new HashMap<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int projetId = rs.getInt("projet_id");
                Projet projet = new Projet();
                projet.setId(projetId);
                projet.setNomProjet(rs.getString("nomprojet"));
                projet.setSurface(rs.getDouble("projet_surface"));
                projet.setCoutTotal(rs.getDouble("couttotal"));
                projet.setMargeBeneficiaire(rs.getDouble("margebenificiare"));
                String state = rs.getString("etatProjet");
                if (state != null) {
                    projet.setEtatProjet(EtatProjet.valueOf(state));
                } else {
                    projet.setEtatProjet(null); // or handle the null case appropriately
                }

                Client client = new Client();
                client.setId(rs.getInt("client_id"));
                client.setNom(rs.getString("client_nom"));
                client.setAddress(rs.getString("address"));
                client.setTelephone(rs.getString("client_telephone"));
                client.setEstProfessionel(rs.getBoolean("client_estprofessionel"));
                projet.setClient(client);

                projet.setMateriauxes(new ArrayList<>());
                projet.setMainOeuvres(new ArrayList<>());

                projects.put(projetId, projet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return projects;
        }

        String materiauxSql = "SELECT * FROM materiaux WHERE projet_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(materiauxSql)) {
            for (Projet projet : projects.values()) {
                stmt.setInt(1, projet.getId());
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        Materiaux materiel = new Materiaux(
                                rs.getString("nom"),
                                rs.getDouble("tauxtva"),
                                rs.getDouble("coutunitaire"),
                                rs.getDouble("quantite"),
                                rs.getDouble("couttransport"),
                                rs.getDouble("coefficientqualite")
                        );
                        materiel.setId(rs.getInt("id"));
                        projet.getMateriauxes().add(materiel);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String mainDoeuvreSql = "SELECT * FROM maindoeuvre WHERE projet_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(mainDoeuvreSql)) {
            for (Projet projet : projects.values()) {
                stmt.setInt(1, projet.getId());
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        MainOeuvre mainDoeuvre = new MainOeuvre(
                                rs.getString("nom"),
                                rs.getDouble("tauxtva"),
                                rs.getDouble("tauxhoraire"),
                                rs.getDouble("heurestravail"),
                                rs.getDouble("productiviteouvrier"),
                                MainOeuvreType.valueOf(rs.getString("mainoeuvretype"))
                        );
                        mainDoeuvre.setId(rs.getInt("id"));
                        projet.getMainOeuvres().add(mainDoeuvre);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return projects;
    }



    @Override
    public Projet addProject(Projet projet, int client_id) {
        String sql = "INSERT INTO projet (nomProjet, margeBenificiare, coutTotal, surface, etatProjet, client_id) VALUES (?, ?, ?, ?, ?::etat_projet, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, projet.getNomProjet());
            statement.setDouble(2, projet.getMargeBeneficiaire());
            statement.setDouble(3, projet.getCoutTotal());
            statement.setDouble(4, projet.getSurface());
            statement.setString(5, projet.getEtatProjet().name()); // Keep this as String
            statement.setInt(6, client_id);

            // Execute the update
            statement.executeUpdate();

            // Retrieve generated keys
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                projet.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projet;
    }



    @Override
    public void updateProject(Integer id, Projet projet) {
        String sql = "UPDATE projet SET margeBenificiare = ? , coutTotal = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, projet.getMargeBeneficiaire());
            statement.setDouble(2, projet.getCoutTotal());
            statement.setInt(3, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateStateProject(EtatProjet etatProjet, Integer id) {
        String sql = "UPDATE projet SET etatprojet = ?::etat_Projet WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, etatProjet.name());
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

