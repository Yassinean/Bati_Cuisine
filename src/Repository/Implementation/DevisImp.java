package Repository.Implementation;

import Config.DbConfig;
import Model.Devis;
import Repository.Interface.IDevis;

import java.sql.*;
import java.time.LocalDate;


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
    public Devis findDevisById(Integer id) {
        String sql = "SELECT * FROM devis WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs != null) {
                LocalDate dateEmission = rs.getDate("dateemission").toLocalDate();
                LocalDate dateValidite = rs.getDate("datevalidite").toLocalDate();
                Devis devis = new Devis(
                        rs.getDouble("montantestime"),
                        dateEmission,
                        dateValidite,
                        rs.getBoolean("accept"),
                        rs.getInt("projet_id")
                );
                devis.setId(rs.getInt(id));
                return devis;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void addDevis(Devis devis, Integer projet_id) {
        String sql = "INSERT INTO devis (montantEstime, dateEmission , dateValidite , accept, projet_id) VALUES (?,?,?,?,?) ";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, devis.getMontantEstime());
            stmt.setDate(2, Date.valueOf(devis.getDateEmission()));
            stmt.setDate(3, Date.valueOf(devis.getDateValidite()));
            stmt.setBoolean(4, devis.isAccept());
            stmt.setInt(5, projet_id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

