package by.fin.processing.dto;


import jakarta.validation.constraints.NotNull;
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
public class WeekendDto {
    @NotNull
    private Long weekendId;

    @NotNull
    private LocalDate calendarDate;

    private boolean isDayOff;
}
