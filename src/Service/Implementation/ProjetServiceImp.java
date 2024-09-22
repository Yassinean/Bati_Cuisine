package Service.Implementation;

import Model.Projet;
import Repository.Implementation.ProjetImp;
import Repository.Interface.IProjet;
import Service.Interface.IProjetService;

import java.util.List;


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

    public List<Projet> getAllProjects() {
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
}
