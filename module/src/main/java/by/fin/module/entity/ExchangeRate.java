package by.fin.module.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "exchange_rates")
public class ExchangeRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exchange_rate_id")
    private Long exchangeRateId;

    @Column(name = "currency_type")
    private String currencyType;

    @JoinColumn(name = "weekend_id")
    @ManyToOne
    private Weekend weekend;

    @Column(name = "exchange_rate_value")
    private BigDecimal value;
}
