package Service.Interface;

import Model.Materiaux;

import java.util.List;

public interface IMateriauxService {
    Materiaux getMaterialById(Integer id);
    List<Materiaux> getAllMateriauxs();
    void createMateriaux(Materiaux materiaux);
}
