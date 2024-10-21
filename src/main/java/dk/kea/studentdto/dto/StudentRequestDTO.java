package dk.kea.studentdto.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record StudentRequestDTO(String name, String password, LocalDate bornDate, LocalTime bornTime) {
}
