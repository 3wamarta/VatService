import Product.Product;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.stream.Stream;

public class VatService {

    private VatProvider vatProvider;

    public VatService(VatProvider vatProvider) {
        this.vatProvider = vatProvider;
    }

    BigDecimal getGrossPrice(Product product, String country) {
        BigDecimal vatValue = vatProvider.getVatFor(country, product.getType());
        return calculateGrossPrice(product.getNetPrice(), vatValue);
    }

    Stream<BigDecimal> getGrossPrices(Stream<Product> product, String country) {
        return product.map(p -> {
            BigDecimal vatValue = vatProvider.getVatFor(country, p.getType());
            return calculateGrossPrice(p.getNetPrice(), vatValue);
        });
    }

    private BigDecimal calculateGrossPrice(BigDecimal netPrice, BigDecimal vatValue) {
        MathContext m = new MathContext(4);
        return netPrice.multiply(vatValue.add(BigDecimal.ONE)).round(m);
    }
}
