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
public class PurchaseInfo {
    private int purchaseId;
    private int purchaseUserId;
    private BigDecimal totalAmount;
    private int purchaseCount;
    private int vipCriteriaMet;
    private int premiumCriteriaMet;
    private LocalDateTime lastPurchaseDate;
}
