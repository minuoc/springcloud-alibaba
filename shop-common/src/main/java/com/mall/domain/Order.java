package com.mall.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "shop_order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "oid", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long oid;

    @Column(name = "number")
    private Integer number;

    @Column(name = "pid")
    private Integer pid;

    @Column(name = "pname")
    private String pname;

    @Column(name = "pprice")
    private Double pprice;

    @Column(name = "uid")
    private Integer uid;

    @Column(name = "username")
    private String username;

}
