package spring.api.uteating.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import spring.api.uteating.entity.DishOption;
import spring.api.uteating.entity.DishOptionKey;

import java.util.List;

@Repository
public interface DishOptionRepository extends JpaRepository<DishOption, DishOptionKey> {
    @Query(value="select * from dish_option where dish_id=?",nativeQuery = true)
    List<DishOption> findDishOptionByDishId(Long dish_id);
}
