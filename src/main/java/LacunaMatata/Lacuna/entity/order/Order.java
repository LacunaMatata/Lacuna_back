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
public class Order {
    private int orderId;
    private int orderUserId;
    private BigDecimal totalAmount;
    private String status;
    private LocalDateTime createdDate;
}
