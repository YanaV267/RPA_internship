package by.fin.processing.dto;


import by.fin.module.entity.ExchangeRate;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeekendDto {
    @NotNull
    private Long weekendId;

    @NotNull
    private Date calendarDate;

    private boolean isDayOff;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<ExchangeRate> cours;
}
