package Repository.Implementation;

import Config.DbConfig;
import Model.Enum.MainOeuvreType;
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
            MainOeuvreType mainOeuvreType = null;
            String valeur = resultSet.getString("mainoeuvretype");
            mainOeuvreType = MainOeuvreType.Ouvrier.valueOf(valeur);
                MainOeuvre mainOuevre = new MainOeuvre(
                        resultSet.getDouble("tauxhoraire"),
                        resultSet.getDouble("heuretravail"),
                        resultSet.getDouble("productiviteouvrier"),
                        mainOeuvreType
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
                MainOeuvreType mainOeuvreType = null ;
                String valeur = resultSet.getString("mainoeuvretype");
                mainOeuvreType = MainOeuvreType.Ouvrier.valueOf(valeur);
                return new MainOeuvre(
                        resultSet.getDouble("tauxhoraire"),
                        resultSet.getDouble("heuretravail"),
                        resultSet.getDouble("productiviteouvrier"),
                        mainOeuvreType
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addMainOeuvre(MainOeuvre mainOeuvre , int projet_id) {
        String sql = "INSERT INTO maindoeuvre (nom ,tauxtva , projet_id , tauxhoraire , heuretravail , productiviteouvrier , mainoeuvretype) VALUES (?,?,?,?,?,?,?::mainoeuvretype) ";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, mainOeuvre.getNom());
            statement.setDouble(2, mainOeuvre.getTauxTva());
            statement.setInt(3, projet_id);
            statement.setDouble(4, mainOeuvre.getTauxHoraire());
            statement.setDouble(5, mainOeuvre.getHeureTravail());
            statement.setDouble(6, mainOeuvre.getProductiviteOuvrier());
            statement.setString(7, mainOeuvre.getMainOeuvreType().name());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

