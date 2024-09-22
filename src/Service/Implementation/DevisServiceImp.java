package Service.Implementation;

import Model.Devis;
import Repository.Interface.IDevis;
import Service.Interface.IDevisService;

public class DevisServiceImp implements IDevisService {

    private IDevis devisReposInterface;

    private DevisServiceImp(IDevis devisReposInterface) {
        this.devisReposInterface = devisReposInterface;
    }

    @Override
    public void createProject(Devis devis) {
        devisReposInterface.addDevis(devis);
    }

}
