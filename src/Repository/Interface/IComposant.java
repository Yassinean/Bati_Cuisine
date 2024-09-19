package Repository.Interface;

import Model.Composant;

import java.util.List;

public interface IComposant {
    List<Composant> findAllComposants();
    Composant findComposantById(Integer id);
    void addComposant(Composant composant);
}
