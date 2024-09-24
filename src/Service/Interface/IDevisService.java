package Service.Interface;

import Model.Devis;

public interface IDevisService {
    Devis getDevisById(Integer id);

    void createDevis(Devis devis, Integer projet_id);

}
