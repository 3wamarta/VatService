package Vat;

import Product.Type;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static Vat.EuropeanCountries.*;

public class EuropeanVatRepository implements VatRepository {

    private Set<Vat> vatValues;

    public EuropeanVatRepository() {
        this.vatValues = new HashSet<>();
        this.vatValues.add(new Vat(POLAND, Type.BABY, new BigDecimal("0.05")));
        this.vatValues.add(new Vat(POLAND, Type.BOOK, new BigDecimal("0.05")));
        this.vatValues.add(new Vat(POLAND, Type.FOOD, new BigDecimal("0.08")));
        this.vatValues.add(new Vat(POLAND, Type.CLOTHES, new BigDecimal("0.23")));
        this.vatValues.add(new Vat(POLAND, Type.GAMES, new BigDecimal("0.23")));
        this.vatValues.add(new Vat(POLAND, Type.SHOES, new BigDecimal("0.23")));
        this.vatValues.add(new Vat(DENMARK, Type.BABY, new BigDecimal("0.08")));
        this.vatValues.add(new Vat(DENMARK, Type.BOOK, new BigDecimal("0.08")));
        this.vatValues.add(new Vat(DENMARK, Type.FOOD, new BigDecimal("0.08")));
        this.vatValues.add(new Vat(DENMARK, Type.CLOTHES, new BigDecimal("0.08")));
        this.vatValues.add(new Vat(DENMARK, Type.GAMES, new BigDecimal("0.08")));
        this.vatValues.add(new Vat(DENMARK, Type.SHOES, new BigDecimal("0.08")));
        this.vatValues.add(new Vat(GERMANY, Type.BABY, new BigDecimal("0.04")));
        this.vatValues.add(new Vat(GERMANY, Type.BOOK, new BigDecimal("0.04")));
        this.vatValues.add(new Vat(GERMANY, Type.FOOD, new BigDecimal("0.04")));
        this.vatValues.add(new Vat(GERMANY, Type.CLOTHES, new BigDecimal("0.10")));
        this.vatValues.add(new Vat(GERMANY, Type.GAMES, new BigDecimal("0.21")));
        this.vatValues.add(new Vat(GERMANY, Type.SHOES, new BigDecimal("0.21")));

    }

    @Override
    public Vat getVatFor(String country, Type productType) throws VatNotFoundException {
        return vatValues.stream()
                .filter(vat -> vat.hasCountryAndProductType(country, productType) )
                .findFirst()
                .orElseThrow(() -> {
                            String message = String.format("Vat for country %s and product type %s was not found", country, productType);
                            return new VatNotFoundException(message);
                        }
                );
    }

    @Override
    public void addVatValue(String country, Type productType, BigDecimal amount) {
        vatValues.add(new Vat(country, productType, amount));
    }

    @Override
    public boolean hasVatValueFor(String country, Type productType) {
        return vatValues.stream()
                .anyMatch(vat -> vat.hasCountryAndProductType(country, productType));
    }
}