package org.example.repository;

import org.example.entity.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * Репозиторий для доступа к данным бел. рубля по отношению к другим валютам.
 */
@Repository
public interface RateRepository extends JpaRepository<Rate, Long> {
   boolean existsByDate(Date date);
   boolean existsByDateAndCurId(Date date, Long curId);
}
