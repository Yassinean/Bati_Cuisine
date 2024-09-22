package Service.Interface;

import Model.Projet;

import java.util.List;

public interface IProjetService {
    Projet getProjectById(Integer id);
    void createProject(Projet projet);
    void updateProject(Integer id , Projet projet);
}
