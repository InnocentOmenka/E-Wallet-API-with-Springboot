package com.innocodes.EWalletAPIwithSpringBoot.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.stereotype.Indexed;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
//@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Entity
@ToString

@AllArgsConstructor

public class User extends Base implements Serializable {

    private static final long serialVersionUID = 2L;


    private String uuid;

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Column(nullable = false)
    private String roles;

    @Column(nullable = false)
    private String password;

    private Date lastLoginDate;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @Column(name = "country")
    private String country;

    @Column(name = "state")
    private String state;

    @Column(name = "home_address")
    private String homeAddress;

    @Column(length = 15)
    private String phoneNumber;

    public User(){
        super();
    }
}
