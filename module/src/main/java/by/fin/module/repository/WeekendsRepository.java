package by.fin.module.repository;


import by.fin.module.entity.Weekend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface WeekendsRepository extends JpaRepository<Weekend, Long> {
    Optional<Weekend> findByCalendarDate(LocalDate date);
}
