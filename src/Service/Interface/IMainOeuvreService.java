package Service.Interface;

import Model.MainOeuvre;

import java.util.List;

public interface IMainOeuvreService {
    MainOeuvre getMainOeuvreById(Integer id);
    List<MainOeuvre> getAllMainOeuvres();
    void createMainOeuvre(MainOeuvre mainOeuvre,int id);
}
