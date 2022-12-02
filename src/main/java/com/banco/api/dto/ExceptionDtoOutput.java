package com.banco.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ExceptionDtoOutput implements Serializable {
    private static final long serialVersionUID = -300224212242453222L;
    private Boolean canShowModal;
}