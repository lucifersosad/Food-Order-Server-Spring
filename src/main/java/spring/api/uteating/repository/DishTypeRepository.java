package spring.api.uteating.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.api.uteating.entity.DishTypeEntity;

@Repository
public interface DishTypeRepository extends JpaRepository<DishTypeEntity, Long> {
}
