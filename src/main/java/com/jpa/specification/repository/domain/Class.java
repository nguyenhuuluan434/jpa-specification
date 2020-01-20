package com.jpa.specification.repository.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
public class Class implements Serializable {
    @Id
    @GeneratedValue
    private long id;
    private String name;
}
