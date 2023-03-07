package com.msproject.relationaldb.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "band")
@ToString
@Getter
@Setter
public class Band implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "country")
    private String country;

    @ToString.Exclude
    @ManyToOne
    private Genre genre;

    @ToString.Exclude
    @OneToMany
    private List<Disc> discs;

}
