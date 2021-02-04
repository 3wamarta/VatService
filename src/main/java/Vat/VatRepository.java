package Vat;

import Product.Type;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class VatRepository {

    public VatRepository() {
        this.vatValues = new HashSet<>();
        this.vatValues.add(new Vat("Poland", Type.BABY, new BigDecimal("0.05")));
        this.vatValues.add(new Vat("Poland", Type.BOOK, new BigDecimal("0.05")));
        this.vatValues.add(new Vat("Poland", Type.FOOD, new BigDecimal("0.08")));
        this.vatValues.add(new Vat("Poland", Type.CLOTHES, new BigDecimal("0.23")));
        this.vatValues.add(new Vat("Poland", Type.GAMES, new BigDecimal("0.23")));
        this.vatValues.add(new Vat("Poland", Type.SHOES, new BigDecimal("0.23")));
    }

    Set<Vat> vatValues;

    public Vat getVatFor(String country, Type productType) throws VatNotFoundException {
        return vatValues.stream().filter(val -> val.getCountry().equals(country) && val.getProductType().equals(productType))
                .findFirst()
                .orElseThrow(() ->
                        {
                            String message = String.format("Vat for country %s and product type %s was not found", country, productType);
                            return new VatNotFoundException(message);
                        }
                );
    }

    public void addVatValue(String country, Type productType, BigDecimal amount) {
        vatValues.add(new Vat(country, productType, amount));
    }

    public boolean hasVatRateFor(String country, Type productType) {
        return vatValues.stream().anyMatch(vat -> country.equals(vat.getCountry()) && productType == vat.getProductType());
    }
}