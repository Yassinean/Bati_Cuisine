package Repository.Interface;

import Model.Projet;

import java.util.*;

public interface IProjet {
    List <Projet> findAllProjects();
    Projet findProjectById(Integer id);
    void addProject(Projet projet);
}
