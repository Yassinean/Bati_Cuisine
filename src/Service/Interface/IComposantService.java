package Service.Interface;

import Model.Composant;

import java.util.List;

public interface IComposantService {
    Composant getComposantById(Integer id);
    List<Composant> getAllComposants();
    void createComposant(Composant projet);
}
