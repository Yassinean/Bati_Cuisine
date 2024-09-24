package Service.Interface;

import Model.Enum.EtatProjet;
import Model.Projet;

import java.util.List;
import java.util.Map;

public interface IProjetService {
    Projet getProjectById(Integer id);
    Map<Integer,Projet> getAllProjects();
    void createProject(Projet projet , int client_id);
    void updateProject(Integer id , Projet projet);
    void updateStateProject(EtatProjet etatProjet, Integer id);
}
