package Repository.Interface;

import Model.MainOeuvre;

import java.util.List;

public interface IMainOeuvre {
    List<MainOeuvre> findAllMainOeuvres();
    MainOeuvre findMainOeuvreById(Integer id);
    void addMainOeuvre(MainOeuvre mainOeuvre , int id);
}
