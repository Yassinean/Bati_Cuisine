package Service.Implementation;

import Model.Materiaux;
import Repository.Interface.IMateriaux;
import Service.Interface.IMateriauxService;

import java.util.List;

public class MateriauxServiceImp implements IMateriauxService {
    private IMateriaux materiauxReposInterface;

    public MateriauxServiceImp(IMateriaux materiaux){
        this.materiauxReposInterface = materiaux;
    }


    @Override
    public Materiaux getMaterialById(Integer id) {
        return materiauxReposInterface.findMateriauxById();
    }

    @Override
    public List<Materiaux> getAllMateriauxs() {
        return materiauxReposInterface.findAllMateriaux();
    }

    @Override
    public void createMateriaux(Materiaux materiaux) {
        materiauxReposInterface.addMateriaux(materiaux);
    }
}
