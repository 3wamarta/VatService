import Product.Type;
import Vat.EuropeanVatRepository;

import java.math.BigDecimal;

public class EuropeanVatProviderWithRepository implements VatProvider {

    EuropeanVatRepository repository;

    public EuropeanVatProviderWithRepository(EuropeanVatRepository repository) {
        this.repository = repository;
    }

    public BigDecimal getVatFor(String country, Type productType) {
        return repository.getVatFor(country, productType ).getAmount();
    }

}
