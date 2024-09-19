package Repository.Implementation;

import Config.DbConfig;
import Model.Devis;
import Repository.Interface.IDevis;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DevisImp implements IDevis {

    private Connection connection;

    public DevisImp() {
        try {
            this.connection = DbConfig.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Devis> findAllDevis() {
        List<Devis> devises = new ArrayList<>();
        String sql = "SELECT * FROM devis";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Devis devis = new Devis(
                        resultSet.getInt("id"),
                        resultSet.getDouble("montantEstime"),
                        resultSet.getDate("dateEmission").toLocalDate(),
                        resultSet.getDate("dateValidite").toLocalDate(),
                        resultSet.getBoolean("accept")
                );
                devises.add(devis);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return devises;
    }

    @Override
    public Devis findDevisById(Integer id) {
        String sql = " SELECT * FROM devis WHERE id = ? ";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            statement.setInt(1,id);
            if (resultSet.next()){
                return new Devis(resultSet.getInt("id"),
                        resultSet.getDouble("montantEstime"),
                        resultSet.getDate("dateEmission").toLocalDate(),
                        resultSet.getDate("dateValidite").toLocalDate(),
                        resultSet.getBoolean("accept")
                );
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addDevis(Devis devis) {
        String sql = "INSERT INTO devis (montantEstime, dateEmission , dateValidite , accept) VALUES (?,?,?,?) ";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1,devis.getMontantEstime());
            statement.setDate(2, Date.valueOf(devis.getDateEmission()));
            statement.setDate(3, Date.valueOf(devis.getDateValidite()));
            statement.setBoolean(4,devis.isAccept());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}

