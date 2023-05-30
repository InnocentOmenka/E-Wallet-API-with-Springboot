package com.innocodes.EWalletAPIwithSpringBoot.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Indexed;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@Table(name = "wallet")
@Entity
public class Wallet extends Base implements Serializable {
    private static final long serialVersionUID = 2L;
    private String walletUUID;

    private String email;

    @Column(nullable = false)
    private BigDecimal balance;

    public Wallet() {super();}
}
