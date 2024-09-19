package Repository.Interface;

import Model.Materiaux;

import java.util.List;

public interface IMateriaux {
    List<Materiaux> findAllMateriaux();
    Materiaux findMateriauxById(Integer id);
    void addMateriaux(Materiaux mainOeuvre);
}
