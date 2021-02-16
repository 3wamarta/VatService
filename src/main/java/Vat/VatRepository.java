package Vat;

import Product.Type;

import java.math.BigDecimal;

public interface VatRepository {
    Vat getVatFor(String country, Type productType) throws VatNotFoundException;

    void addVatValue(String country, Type productType, BigDecimal amount);

    boolean hasVatValueFor(String country, Type productType);
}
