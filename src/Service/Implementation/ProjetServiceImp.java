package Service.Implementation;

import Model.Projet;
import Repository.Interface.IProjet;
import Service.Interface.IProjetService;

import java.util.List;

public class ProjetServiceImp implements IProjetService {

    private IProjet projetReposInterface;

    private ProjetServiceImp(IProjet projetReposInterface) {
        this.projetReposInterface = projetReposInterface;
    }

    @Override
    public Projet getProjectById(Integer id) {
        return projetReposInterface.findProjectById(id);
    }

    @Override
    public List<Projet> getAllProjects() {
        return projetReposInterface.findAllProjects();
    }

    @Override
    public void createProject(Projet projet) {
        projetReposInterface.addProject(projet);
    }

    @Override
    public void updateProject(Integer id, Projet projet) {
        projetReposInterface.updateProject(id, projet);
    }
}
