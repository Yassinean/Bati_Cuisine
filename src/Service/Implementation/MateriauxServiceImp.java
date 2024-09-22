package Service.Implementation;

import Model.Materiaux;
import Repository.Implementation.MateriauxImp;
import Repository.Interface.IMateriaux;
import Service.Interface.IMateriauxService;

import java.util.List;

public class MateriauxServiceImp implements IMateriauxService {
    private IMateriaux materiauxReposInterface = new MateriauxImp();

    public MateriauxServiceImp(IMateriaux materiaux){
        this.materiauxReposInterface = materiaux;
    }

    public MateriauxServiceImp(){}

    @Override
    public Materiaux getMaterialById(Integer id) {
        return materiauxReposInterface.findMateriauxById(id);
    }

    @Override
    public List<Materiaux> getAllMateriauxs() {
        return materiauxReposInterface.findAllMateriaux();
    }

    @Override
    public void createMateriaux(Materiaux materiaux, int id) {
        materiauxReposInterface.addMateriaux(materiaux,id);
    }
}
