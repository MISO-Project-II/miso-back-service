package edu.uniandes.miso.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InputServiceDto {
    private String name;
	private String description;
	private Long idUserCreator;
	private Long idSport;
	private BigDecimal price;
	private String contractType;
	private String eventType;

}
