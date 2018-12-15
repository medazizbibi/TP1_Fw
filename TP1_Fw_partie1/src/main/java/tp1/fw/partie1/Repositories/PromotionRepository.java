package tp1.fw.partie1.Repositories;

import org.springframework.data.repository.CrudRepository;
import tp1.fw.partie1.Domain.Promotion;

public interface PromotionRepository extends CrudRepository<Promotion,String> {
}
