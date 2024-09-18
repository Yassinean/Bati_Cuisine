package Repository.Implementation;

import Config.DbConfig;
import Model.EtatProjet;
import Model.Projet;
import Repository.Interface.IProjet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    public List<Projet> findAllProjects() {
        List<Projet> projets= new ArrayList<>();
        String sql = "SELECT * FROM projet";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                String state = resultSet.getString("etatprojet");
                EtatProjet stateP = null;
                if (state != null ){
                    stateP = EtatProjet.valueOf(state);
                }
                Projet projet = new Projet(
                        resultSet.getInt("id"),
                        resultSet.getString("nomProjet"),
                        resultSet.getDouble("margebenificiare"),
                        resultSet.getDouble("couttotal"),
                        stateP
                );
                projets.add(projet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return projets;
    }

    @Override
    public Projet findProjectById(Integer id) {
        String sql = " SELECT * FROM projet WHERE id = ? ";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            statement.setInt(1,id);
            if (resultSet.next()){
                String state = resultSet.getString("etat");
                EtatProjet stateP = null;
                if (state != null){
                    stateP = EtatProjet.valueOf(state);
                }
                return new Projet(resultSet.getInt("id"),
                        resultSet.getString("nomProjet"),
                        resultSet.getDouble("margeBenificiaire"),
                        resultSet.getDouble("coutTotal"),
                        stateP);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addProject(Projet projet) {
        String sql = "INSERT INTO projet (nomProjet,margeBenificiare,coutTotal,etatProjet) VALUES (?,?,?,?) ";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1,projet.getNomProjet());
            statement.setDouble(2,projet.getMargeBeneficiaire());
            statement.setDouble(3,projet.getCoutTotal());
            statement.setObject(4,projet.getEtatProjet(),java.sql.Types.OTHER);
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}

