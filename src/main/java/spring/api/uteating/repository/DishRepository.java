package spring.api.uteating.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.api.uteating.entity.Dish;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {
}
