package by.fin.module.repository;


import by.fin.module.entity.Rate;
import by.fin.module.entity.Weekend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface RateRepository extends JpaRepository<Rate, Long> {
    Optional<Rate> findByWeekendAndCurrencyType(Weekend weekend, String currencyType);

    List<Rate> findByCurrencyType(String currencyType);

    @Query("select avg(r.value) from Rate r where extract(month from r.weekend.calendarDate) = :monthNumber " +
            "and r.currencyType = :currency and r.weekend.dayOff = false")
    Optional<BigDecimal> findByCurrencyTypeAndMonthNumber(String currency, int monthNumber);
}
