package com.msproject.ksqlprocessorservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Domain implements Serializable {

    private String domain;
    private String create_date;
    private String update_date;
    private String country;
    private boolean isDead;
    private String A;
    private String NS;
    private String CNAME;
    private String MX;
    private String TXT;

}
