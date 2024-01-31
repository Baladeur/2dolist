package com.wcs._2dolist.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "text", nullable = false)
    private String text;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_creation")
    private Date dateCreation;

    @Column(name = "is_read")
    private Boolean read;

    // Constructors, getters, setters
}
