package by.fin.processing.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RateWrapperDto {
    @NotNull
    @Pattern(regexp = "[A-Z]{3}")
    private String currencyType;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;
}
