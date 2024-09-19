package Repository.Implementation;

import Config.DbConfig;
import Model.MainOeuvre;
import Repository.Interface.IMainOeuvre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainOeuvreImp implements IMainOeuvre {

    private Connection connection;

    public MainOeuvreImp() {
        try {
            this.connection = DbConfig.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<MainOeuvre> findAllMainOeuvres() {
        List<MainOeuvre> mainOuevres = new ArrayList<>();
        String sql = "SELECT * FROM maindoeuvre";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                MainOeuvre mainOuevre = new MainOeuvre(
                        resultSet.getString("nom"),
                        resultSet.getString("typecomposant"),
                        resultSet.getDouble("tauxtva"),
                        resultSet.getInt("projet_id"),
                        resultSet.getDouble("tauxhoraire"),
                        resultSet.getDouble("heuretravail"),
                        resultSet.getDouble("productiviteouvrier")
                );
                mainOuevres.add(mainOuevre);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return mainOuevres;
    }

    @Override
    public MainOeuvre findMainOeuvreById(Integer id) {
        String sql = " SELECT * FROM maindoeuvre WHERE id = ? ";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            statement.setInt(1, id);
            if (resultSet.next()) {

                return new MainOeuvre(
                        resultSet.getString("nom"),
                        resultSet.getString("typecomposant"),
                        resultSet.getDouble("tauxtva"),
                        resultSet.getInt("projet_id"),
                        resultSet.getDouble("tauxhoraire"),
                        resultSet.getDouble("heuretravail"),
                        resultSet.getDouble("productiviteouvrier")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addMainOeuvre(MainOeuvre mainOeuvre) {
        String sql = "INSERT INTO maindoeuvre (nom , typecomposant ,tauxtva , projet_id , tauxhoraire , heuretravail , productiviteouvrier) VALUES (?,?,?,?,?,?,?) ";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, mainOeuvre.getNom());
            statement.setString(2, mainOeuvre.getTypeComposant());
            statement.setDouble(3, mainOeuvre.getTauxTva());
            statement.setInt(4, mainOeuvre.getProjet_id());
            statement.setDouble(5, mainOeuvre.getTauxHoraire());
            statement.setDouble(6, mainOeuvre.getHeureTravail());
            statement.setDouble(7, mainOeuvre.getProductiviteOuvrier());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

