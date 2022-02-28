package com.mall.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "shop_product")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "pid", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pid;

    @Column(name = "pname")
    private String pname;

    @Column(name = "pprice")
    private Double pprice;

    @Column(name = "stock")
    private Integer stock;

}
