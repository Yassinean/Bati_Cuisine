package Repository.Interface;

import Model.Projet;

import java.util.*;

public interface IProjet {
    List <Projet> findAllProjects();
    Projet findProjectById(Integer id);
    void addProject(Projet projet);
    void updateProject(Integer id,Projet projet);
//    void deleteProject(Integer id);
}
