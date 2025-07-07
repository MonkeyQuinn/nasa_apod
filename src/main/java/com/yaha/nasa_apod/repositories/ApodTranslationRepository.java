package com.yaha.nasa_apod.repositories;

import com.yaha.nasa_apod.model.entities.ApodTranslation;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ApodTranslationRepository extends JpaRepository<ApodTranslation, Long> {

    Optional<ApodTranslation> findTopByLocale(String locale, Sort sort);

    Optional<ApodTranslation> findByLocaleAndApod_Date(String locale, LocalDate date);

    @EntityGraph(attributePaths = "apod")
    List<ApodTranslation> findAllByLocale(String locale, Sort sort);

    @Query("""
            select t.apod.date
            from ApodTranslation t
            where t.locale = :locale
            and t.apod.date < :date
            order by t.apod.date desc
            limit 1
            """)
    Optional<LocalDate> findPrevDateByLocale(String locale, LocalDate date);

    @Query("""
            select t.apod.date
            from ApodTranslation t
            where t.locale = :locale
            and t.apod.date > :date
            order by t.apod.date asc
            limit 1
            """)
    Optional<LocalDate> findNextDateByLocale(String locale, LocalDate date);

}

