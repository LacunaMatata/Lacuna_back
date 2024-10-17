package LacunaMatata.Lacuna.entity.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItem {
    private int orderItemId;
    private int orderId;
    private int orderProductId;
    private int productDetailId;
    private int quantity;
    private BigDecimal priceAtPurchase;
}
