package Repository.Implementation;


import Model.Composant;
import Repository.Interface.IComposant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComposantImp implements IComposant {

    private Connection connection;

    public ComposantImp(Connection connection) {
        this.connection = connection;
    }


    @Override
    public List<Composant> findAllComposants() {
        List<Composant> composants = new ArrayList<>();
        String sql = "SELECT * FROM composant";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Composant composant = new Composant(
                        resultSet.getInt("id"),
                        resultSet.getString("nom"),
                        resultSet.getString("typecomposant"),
                        resultSet.getDouble("tauxtva"),
                        resultSet.getInt("projet_id")
                );
                composants.add(composant);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return composants;
    }

    @Override
    public Composant findComposantById(Integer id) {
        String sql = " SELECT * FROM composant WHERE id = ? ";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            statement.setInt(1, id);
            if (resultSet.next()) {
                return new Composant(resultSet.getInt("id"),
                        resultSet.getString("nom"),
                        resultSet.getString("typecomposant"),
                        resultSet.getDouble("tauxtva"),
                        resultSet.getInt("projet_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addComposant(Composant composant) {
        String sql = "INSERT INTO composant (nom , typecomposant , tauxtva , projet_id) VALUES (?,?,?,?) ";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, composant.getNom());
            statement.setString(2, composant.getTypeComposant());
            statement.setDouble(3, composant.getTauxTva());
            statement.setInt(4, composant.getProjet_id());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

