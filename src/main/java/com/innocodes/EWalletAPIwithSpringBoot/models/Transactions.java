package com.innocodes.EWalletAPIwithSpringBoot.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;


@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@Table(name = "transactions")
@Entity
public class Transactions extends Base implements Serializable {
    private static final long serialVersionUID = 2L;


    private String reference;
    private String domain;
    private String status;
    private String source;
    private String transactionType;
    private String message;
    private String description;
    private String request_code;
    private String customerEmail;
    private boolean paid;
    private BigDecimal amount;
    private String gateway_response;
    private String channel;
    private String currency;
    private String ip_address;

//    =========== Withdrawal ================

    private String integration;
    private String reason;
    private String transfer_code;

    public Transactions() {
        super();
    }
}

