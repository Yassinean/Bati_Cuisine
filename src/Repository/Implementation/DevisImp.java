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

