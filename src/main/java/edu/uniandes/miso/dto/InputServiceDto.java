package edu.uniandes.miso.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

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
	private String contract;
}
