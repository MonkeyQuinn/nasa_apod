package com.yaha.nasa_apod.repositories;

import com.yaha.nasa_apod.model.entities.Apod;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ApodRepository extends JpaRepository<Apod, Long> {

    Optional<Apod> findTopByOrderByDateDesc();

    Optional<Apod> findByDate(LocalDate date);

    Optional<Apod> findTopByDateBefore(LocalDate date, Sort sort);

    Optional<Apod> findTopByDateAfter(LocalDate date, Sort sort);

}
