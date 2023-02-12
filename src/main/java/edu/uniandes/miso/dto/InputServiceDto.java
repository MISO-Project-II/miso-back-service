package edu.uniandes.miso.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import edu.uniandes.miso.entity.Contract;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InputServiceDto {
    private String name;
	private String description;
	private Long idUserCreator;
	private Long idSport;
	private BigDecimal price;
	private Contract contract;
}
