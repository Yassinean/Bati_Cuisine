package Repository.Interface;

import Model.Devis;

public interface IDevis {
    Devis findDevisById(Integer id);
    void addDevis(Devis devis , Integer projet_id);

}
