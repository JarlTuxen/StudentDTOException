package dk.kea.studentdtoexception.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record StudentResponseDTO(Long id, String name, LocalDate bornDate, LocalTime bornTime) {
}

