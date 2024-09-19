package Repository.Implementation;

import Config.DbConfig;
import Model.Materiaux;
import Repository.Interface.IMateriaux;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MateriauxImp implements IMateriaux {

    private Connection connection;

    public MateriauxImp() {
        try {
            this.connection = DbConfig.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Materiaux> findAllMateriaux() {
        List<Materiaux> materiaux = new ArrayList<>();
        String sql = "SELECT * FROM materiaux";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                Materiaux material = new Materiaux(
                        resultSet.getString("nom"),
                        resultSet.getString("typecomposant"),
                        resultSet.getDouble("tauxtva"),
                        resultSet.getInt("projet_id"),
                        resultSet.getDouble("coutunitaire"),
                        resultSet.getDouble("quantite"),
                        resultSet.getDouble("couttransport"),
                        resultSet.getDouble("coefficientqualite")
                );
                materiaux.add(material);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return materiaux;
    }

    @Override
    public Materiaux findMateriauxById(Integer id) {
        String sql = " SELECT * FROM materiaux WHERE id = ? ";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            statement.setInt(1, id);
            if (resultSet.next()) {

                return new Materiaux(
                        resultSet.getString("nom"),
                        resultSet.getString("typecomposant"),
                        resultSet.getDouble("tauxtva"),
                        resultSet.getInt("projet_id"),
                        resultSet.getDouble("coutunitaire"),
                        resultSet.getDouble("quantite"),
                        resultSet.getDouble("couttransport"),
                        resultSet.getDouble("coefficientqualite")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addMateriaux(Materiaux material) {
        String sql = "INSERT INTO materiaux (nom , typecomposant ,tauxtva , projet_id , coutunitaire , quantite , couttransport , coefficientqualite) VALUES (?,?,?,?,?,?,?,?) ";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, material.getNom());
            statement.setString(2, material.getTypeComposant());
            statement.setDouble(3, material.getTauxTva());
            statement.setInt(4, material.getProjet_id());
            statement.setDouble(5, material.getCoutUnitaire());
            statement.setDouble(6, material.getQuantite());
            statement.setDouble(7, material.getCoutTransport());
            statement.setDouble(8, material.getCoefficientQualite());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

