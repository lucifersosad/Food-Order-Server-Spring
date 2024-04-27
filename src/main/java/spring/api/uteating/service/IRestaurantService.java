package spring.api.uteating.service;

import spring.api.uteating.entity.Restaurant;

import java.util.List;

public interface IRestaurantService {
    List<Restaurant> findAll();

    List<Restaurant> findAllById(Iterable<Long> longs);

    <S extends Restaurant> S save(S entity);

    long count();

    void deleteById(Long aLong);

    void deleteAll();

    @Deprecated
    Restaurant getById(Long aLong);
}
