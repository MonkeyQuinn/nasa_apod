package com.yaha.nasa_apod.repositories;

import com.yaha.nasa_apod.model.entities.Apod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface ApodRepository extends JpaRepository<Apod, Long> {

    Optional<Apod> findTopByOrderByDateDesc();

    Optional<Apod> findByDate(LocalDate date);

    Optional<Apod> findTopByDateBeforeOrderByDateDesc(LocalDate date);

    Optional<Apod> findTopByDateAfterOrderByDateAsc(LocalDate date);

    List<Apod> findAllByOrderByDateDesc();
}
