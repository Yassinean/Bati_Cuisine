package Service.Implementation;

import Model.Enum.EtatProjet;
import Model.Projet;
import Repository.Implementation.ProjetImp;
import Repository.Interface.IProjet;
import Service.Interface.IProjetService;

import java.util.List;
import java.util.Map;


public class ProjetServiceImp implements IProjetService {

    private IProjet projetReposInterface = new ProjetImp();

    public ProjetServiceImp(IProjet projetReposInterface) {
        this.projetReposInterface = projetReposInterface;
    }

    public ProjetServiceImp() {
    }

    @Override
    public Projet getProjectById(Integer id) {
        return projetReposInterface.findProjectById(id);
    }

    public Map<Integer,Projet> getAllProjects() {
        return projetReposInterface.findAllProjects();
    }

    @Override
    public void createProject(Projet projet , int client_id) {
        projetReposInterface.addProject(projet,client_id);
    }

    @Override
    public void updateProject(Integer id, Projet projet) {
        projetReposInterface.updateProject(id, projet);
    }

    @Override
    public void updateStateProject(EtatProjet etatProjet, Integer id) {
        projetReposInterface.updateStateProject(etatProjet,id);
    }

}
