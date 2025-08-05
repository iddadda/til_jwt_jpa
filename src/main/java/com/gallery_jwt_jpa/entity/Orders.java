package com.gallery_jwt_jpa.entity;


import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@EqualsAndHashCode
public class Orders extends Created{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    외래키
    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Members members;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 500)
    private String address;

    @Column(nullable = false, length = 10)
    private String payment;

    @Column(length = 16)
    private String cardNumber;

    @Column(nullable = false)
    private long amount;







}
