package Repository.Implementation;

import Config.DbConfig;
import Model.Enum.EtatProjet;
import Model.Projet;
import Repository.Interface.IProjet;

import java.sql.*;

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
    public Projet findProjectById(Integer id) {
        String sql = " SELECT * FROM projet WHERE id = ? ";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            statement.setInt(1, id);
            if (resultSet.next()) {
                String state = resultSet.getString("etat");
                EtatProjet stateP = null;
                if (state != null) {
                    stateP = EtatProjet.valueOf(state);
                }
                return new Projet(
                        resultSet.getString("nomProjet"),
                        resultSet.getDouble("margeBenificiaire"),
                        resultSet.getDouble("coutTotal"),
                        resultSet.getDouble("surface"),
                        stateP);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Projet findProjectByName(String name) {
        String sql = " SELECT * FROM projet WHERE nom = ? ";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            statement.setString(1, name);
            if (resultSet.next()) {
                String state = resultSet.getString("etat");
                EtatProjet stateP = null;
                if (state != null) {
                    stateP = EtatProjet.valueOf(state);
                }
                Projet projet = new Projet(
                        resultSet.getString("nomProjet"),
                        resultSet.getDouble("margeBenificiaire"),
                        resultSet.getDouble("coutTotal"),
                        resultSet.getDouble("surface"),
                        stateP);
                projet.setId(resultSet.getInt("id"));
                return projet;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Projet addProject(Projet projet) {
        String sql = "INSERT INTO projet (nomProjet, margeBenificiare, coutTotal, surface, etatProjet, client_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, projet.getNomProjet());
            statement.setDouble(2, projet.getMargeBeneficiaire());
            statement.setDouble(3, projet.getCoutTotal());
            statement.setDouble(4, projet.getSurface());
            statement.setObject(5, projet.getEtatProjet(), java.sql.Types.OTHER);
            statement.setInt(6, projet.getClient());

            // Execute the update
            statement.executeUpdate();

            // Retrieve generated keys
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                projet.setId(generatedKeys.getInt(1));  // Set the generated project ID
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projet;
    }


    @Override
    public void updateProject(Integer id, Projet projet) {
        String sql = "UPDATE TABLE projet SET nomProjet = ? , margeBenificiare = ? , coutTotal = ? , surface = ? ,etatProjet = ? , client_id = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, projet.getNomProjet());
            statement.setDouble(2, projet.getMargeBeneficiaire());
            statement.setDouble(3, projet.getCoutTotal());
            statement.setDouble(4, projet.getSurface());
            statement.setObject(5, projet.getEtatProjet());
            statement.setObject(6, projet.getClient());
            statement.setObject(6, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}

