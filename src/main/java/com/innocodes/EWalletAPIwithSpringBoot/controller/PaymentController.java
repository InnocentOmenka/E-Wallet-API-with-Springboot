package com.innocodes.EWalletAPIwithSpringBoot.controller;

import com.innocodes.EWalletAPIwithSpringBoot.dtos.paystack.InitiateTransactionDto;
import com.innocodes.EWalletAPIwithSpringBoot.dtos.responseDto.ApiResponse;
import com.innocodes.EWalletAPIwithSpringBoot.dtos.responseDto.TransactionResponseDto;
import com.innocodes.EWalletAPIwithSpringBoot.service.PaymentServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/payment")
@Tag(name = "Payment Endpoint", description = "<h3>To deposit: </h3> " +
        "<ol>" +
        "<li>Go to '/deposit/initiate' endpoint and enter your details. The <b>callback</b> and <b>metadata</b> are optional. just leave them the way they are</li> " +
        "<li>Copy the payment link returned in the response object to any browser to make your payment.</li>" +
        "<li>Copy the reference code returned if it was successful and go to '/verify/{payment_reference}' endpoint to verify if your deposit was successful</li>" +
        "</ol> " +
        "<h3> For withdrawal which is implemented as transfer:</h3> " +
        "<ol> " +
        "<li>Go to 'withdrawal/create-transfer-recipient' endpoint to create you Transfer recipient</li> " +
        "<li>Copy the <b>reference</b> code 'TRF_....' and the <b>recipient</b> code 'RCP_....' generated and got to initiate withdrawal endpoint</li> " +
        "<li>After initiating your withdrawal, you will receive a error code response because the payment was a test payment</li>" +
        "</ol> " +
        "<p>You can use the <b>validate-account-details</b> endpoint to validate your account details and the " +
        "<b>banks</b> endpoint to see all available banks</p>")

public class PaymentController {

    @Autowired
    private PaymentServices paymentServices;

    @Operation(summary = "Initiates a transaction to get a payment link")
    @PostMapping("/deposit/initiate")
    public ResponseEntity<ApiResponse> getPaymentUrl(@RequestBody InitiateTransactionDto transactionDto) {

        return ResponseEntity.ok(paymentServices.getPaymentLink(transactionDto));
    }

    @Operation(summary = "Webhook to listen for changes in any transaction status")
    @PostMapping("/status-webhook")
    public ResponseEntity<String> getPaymentStatus(@RequestBody TransactionResponseDto transactionResponseDto) {

        return ResponseEntity.ok().body("Thank you");
    }

    @Operation(summary = "Checks if a transaction was successful or not")
    @GetMapping("/verify/{payment_reference}")
    public ResponseEntity<ApiResponse<TransactionResponseDto>> confirmPayment(
            @Parameter(description = "Use the reference number generated when the transaction was initiated")
            @PathVariable String payment_reference) {

        return ResponseEntity.ok(paymentServices. verifyTransaction(payment_reference));
    }

}
