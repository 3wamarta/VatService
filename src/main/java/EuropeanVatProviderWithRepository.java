import Product.Type;
import Vat.Vat;
import Vat.VatRepository;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;

public class EuropeanVatProviderWithRepository implements VatProvider {

    VatRepository repository = new VatRepository();
    private static final String POLAND = "Poland";
    private static final String DENMARK = "Denmark";
    private static final String GERMANY = "Germany";

    public EuropeanVatProviderWithRepository() {
        repository.addVatValue(POLAND, Type.BABY, new BigDecimal("0.05"));
        repository.addVatValue(POLAND, Type.BOOK, new BigDecimal("0.05"));
        repository.addVatValue(POLAND, Type.FOOD, new BigDecimal("0.08"));
        repository.addVatValue(POLAND, Type.CLOTHES, new BigDecimal("0.23"));
        repository.addVatValue(POLAND, Type.GAMES, new BigDecimal("0.23"));
        repository.addVatValue(POLAND, Type.SHOES, new BigDecimal("0.23"));
        repository.addVatValue(DENMARK, Type.BABY, new BigDecimal("0.08"));
        repository.addVatValue(DENMARK, Type.BOOK, new BigDecimal("0.08"));
        repository.addVatValue(DENMARK, Type.FOOD, new BigDecimal("0.08"));
        repository.addVatValue(DENMARK, Type.CLOTHES, new BigDecimal("0.08"));
        repository.addVatValue(DENMARK, Type.GAMES, new BigDecimal("0.08"));
        repository.addVatValue(DENMARK, Type.SHOES, new BigDecimal("0.08"));
        repository.addVatValue(GERMANY, Type.BABY, new BigDecimal("0.04"));
        repository.addVatValue(GERMANY, Type.BOOK, new BigDecimal("0.04"));
        repository.addVatValue(GERMANY, Type.FOOD, new BigDecimal("0.04"));
        repository.addVatValue(GERMANY, Type.CLOTHES, new BigDecimal("0.10"));
        repository.addVatValue(GERMANY, Type.GAMES, new BigDecimal("0.21"));
        repository.addVatValue(GERMANY, Type.SHOES, new BigDecimal("0.21"));

    }

    public BigDecimal getVatFor(String country, Type productType) {
        return repository.getVatFor(country, productType ).getAmount();
    }

}
