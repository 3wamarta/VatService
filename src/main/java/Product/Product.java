package Product;

import java.math.BigDecimal;
import java.util.UUID;

public class Product {
    private UUID id;
    private BigDecimal netPrice;
    private Type type;

    public Product(UUID id, BigDecimal netPrice, Type type) {
        this.id = id;
        this.netPrice = netPrice;
        this.type = type;
    }

    public BigDecimal getNetPrice() {
        return netPrice;
    }

    public Type getType() {
        return type;
    }

}
