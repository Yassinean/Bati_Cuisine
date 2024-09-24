package Repository.Interface;

import Model.Enum.EtatProjet;
import Model.Projet;

import java.util.*;

public interface IProjet {
    Projet findProjectById(Integer id);
    Map <Integer,Projet> findAllProjects();
    Projet addProject(Projet projet , int client_id);
    void updateProject(Integer id,Projet projet);
    void updateStateProject(EtatProjet etatProjet, Integer id);
//    void deleteProject(Integer id);
}
