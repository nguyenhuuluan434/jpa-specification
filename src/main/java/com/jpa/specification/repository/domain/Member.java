package com.jpa.specification.repository.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class Member implements Serializable {

    @Id
    @GeneratedValue
    private long id;
    private String firstName;
    private String lastName;
    private String zipCode;
    private String interests;
    private boolean active;
    @JoinTable(name = "Member_Class",
            joinColumns = @JoinColumn(
                    name = "member_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "class_id",
                    referencedColumnName = "id"
            ))
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Class> classes;
}
