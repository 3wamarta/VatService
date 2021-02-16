import Product.Type;
import Vat.EuropeanVatRepository;
import Vat.VatRepository;

import java.math.BigDecimal;

public class EuropeanVatProvider implements VatProvider {

    private VatRepository repository;

    public EuropeanVatProvider(EuropeanVatRepository repository) {
        this.repository = repository;
    }

    public BigDecimal getVatFor(String country, Type productType) {
        return repository.getVatFor(country, productType ).getAmount();
    }

}
