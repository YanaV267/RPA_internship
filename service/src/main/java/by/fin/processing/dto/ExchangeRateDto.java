package by.fin.processing.dto;


import by.fin.module.entity.Weekend;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRateDto {
    private Long exchangeRateId;

    @NotNull
    @Pattern(regexp = "[A-Z]{3}")
    private String currencyType;

    @NotNull
    private Weekend weekend;

    @NotNull
    @DecimalMin(value = "0.0001")
    @DecimalMax(value = "100")
    private BigDecimal value;
}
