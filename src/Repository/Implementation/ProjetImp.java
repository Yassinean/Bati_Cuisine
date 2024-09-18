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

    private ProjetImp() {
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
                        resultSet.getDouble("margebenificiaire"),
                        resultSet.getDouble("couttotal"),
                        stateP
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return projets;
    }

    @Override
    public Projet findProjectById(Integer id) {
        return null;
    }

    @Override
    public void addProject(Projet projet) {

    }
}
