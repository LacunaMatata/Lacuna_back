package LacunaMatata.Lacuna.entity.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment {
    private int paymentId;
    private int paymentOrderId;
    private String paymentApproveId;
    private String paymentMethod;
    private BigDecimal amount;
    private String paymentStatus;
    private LocalDateTime createdDate;
}
