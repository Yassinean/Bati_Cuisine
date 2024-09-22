package Service.Implementation;

import Model.MainOeuvre;
import Repository.Interface.IMainOeuvre;
import Service.Interface.IMainOeuvreService;

import java.util.List;

public class MainOeuvreServiceImp implements IMainOeuvreService {

    private IMainOeuvre mainOeuvreReposInterface;

    public MainOeuvreServiceImp(IMainOeuvre mainOeuvre){
        this.mainOeuvreReposInterface = mainOeuvre;
    }

    public MainOeuvreServiceImp(){}

    @Override
    public MainOeuvre getMainOeuvreById(Integer id) {
        return mainOeuvreReposInterface.findMainOeuvreById(id);
    }

    @Override
    public List<MainOeuvre> getAllMainOeuvres() {
        return mainOeuvreReposInterface.findAllMainOeuvres();
    }

    @Override
    public void createMainOeuvre(MainOeuvre mainOeuvre , int id) {
        mainOeuvreReposInterface.addMainOeuvre(mainOeuvre , id);
    }
}
