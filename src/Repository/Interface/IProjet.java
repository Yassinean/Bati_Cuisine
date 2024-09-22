package Repository.Interface;

import Model.Projet;

import java.util.*;

public interface IProjet {
    Projet findProjectById(Integer id);
    Projet findProjectByName(String name);
    List <Projet> findAllProjects();
    Projet addProject(Projet projet , int client_id);
    void updateProject(Integer id,Projet projet);
//    void deleteProject(Integer id);
}
