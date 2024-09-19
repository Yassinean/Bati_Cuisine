package Service.Implementation;

import Model.Composant;
import Repository.Interface.IComposant;
import Service.Interface.IComposantService;

import java.util.List;

public class ComposantServiceImp implements IComposantService {

    private IComposant composantReposInterface;

    private ComposantServiceImp(IComposant composantReposInterface){
        this.composantReposInterface = composantReposInterface;
    }

    @Override
    public Composant getComposantById(Integer id) {
        return composantReposInterface.findComposantById(id);
    }

    @Override
    public List<Composant> getAllComposants() {
        return composantReposInterface.findAllComposants();
    }

    @Override
    public void createComposant(Composant composant) {
        composantReposInterface.addComposant(composant);
    }

}
