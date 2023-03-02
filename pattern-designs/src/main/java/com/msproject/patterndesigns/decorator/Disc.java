package com.msproject.patterndesigns.decorator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Disc {

    private int id;
    private String title;
    private String year;
    private int price;

}
