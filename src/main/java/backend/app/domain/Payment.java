package backend.app.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double amount;
    @OneToOne
    @JoinColumn(name="booking_id")
    private Booking booking;
    @OneToOne
    @JoinColumn(name="payment_gateway_id")
    private PaymentGateway paymentGateway;


}
