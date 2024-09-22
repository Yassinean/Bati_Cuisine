package Repository.Interface;

import Model.Projet;

import java.util.*;

public interface IProjet {
    Projet findProjectById(Integer id);
    Projet findProjectByName(String name);
    Projet addProject(Projet projet);
    void updateProject(Integer id,Projet projet);
//    void deleteProject(Integer id);
}
