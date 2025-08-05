package com.gallery_jwt_jpa.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Items extends Created {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 50)
    private String name;

    @Column(nullable = false,length = 100)
    private String imgPath;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int discountPer;
}
