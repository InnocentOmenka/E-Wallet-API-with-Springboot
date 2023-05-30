package com.innocodes.EWalletAPIwithSpringBoot.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "accounts")
@Entity
public class Account extends Base{
    private static final long serialVersionUID = 2L;


    private String accountUUID;


    private String userUUID;

    @Column(name = "account_name", nullable = false)
    private String account_name;

    @Column(name = "account_number", nullable = false)
    private String account_number;

    @Column(name = "bank_name", nullable = false)
    private String bank_name;
}
