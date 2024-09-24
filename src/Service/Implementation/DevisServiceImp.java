package Service.Implementation;

import Model.Devis;
import Repository.Implementation.DevisImp;
import Repository.Interface.IDevis;
import Service.Interface.IDevisService;

public class DevisServiceImp implements IDevisService {

    private IDevis devisReposInterface = new DevisImp();

    public DevisServiceImp(IDevis devisReposInterface) {
        this.devisReposInterface = devisReposInterface;
    }

    public DevisServiceImp(){}

    @Override
    public Devis getDevisById(Integer id) {
        return devisReposInterface.findDevisById(id);
    }

    @Override
    public void createDevis(Devis devis,Integer projet_id) {
        devisReposInterface.addDevis(devis , projet_id);
    }

}
