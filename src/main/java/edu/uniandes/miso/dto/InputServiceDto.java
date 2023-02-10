package edu.uniandes.miso.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InputServiceDto {
    private String name;
    private String category;
	private Long idUserCreator;
}
