package Repository.Interface;

import Model.Devis;

import java.util.List;

public interface IDevis {
    List<Devis> findAllDevis();
    Devis findDevisById(Integer id);
    void addDevis(Devis devis);

}
