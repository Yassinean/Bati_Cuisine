package Service.Implementation;

import Model.Projet;
import Repository.Implementation.ProjetImp;
import Repository.Interface.IProjet;
import Service.Interface.IProjetService;


public class ProjetServiceImp implements IProjetService {

    private IProjet projetReposInterface = new ProjetImp();

    public ProjetServiceImp(IProjet projetReposInterface) {
        this.projetReposInterface = projetReposInterface;
    }

    public ProjetServiceImp() {}

    @Override
    public Projet getProjectById(Integer id) {
        return projetReposInterface.findProjectById(id);
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
