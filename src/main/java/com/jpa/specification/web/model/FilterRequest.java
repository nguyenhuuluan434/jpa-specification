package com.jpa.specification.web.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class FilterRequest implements Serializable {

    private static final long serialVersionUID = -5399096635923165466L;
    private Boolean active;
    private String zipCodePattern;
}
