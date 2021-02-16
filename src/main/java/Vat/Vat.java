package Vat;

import Product.Type;

import java.math.BigDecimal;

public class Vat {
    private String country;
    private Type productType;
    private BigDecimal amount;

    public Vat(String country, Type productType, BigDecimal amount) {
        this.country = country;
        this.productType = productType;
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCountry() {
        return country;
    }

    public Type getProductType() {
        return productType;
    }

    public boolean hasCountryAndProductType(String country, Type productType) {
        return this.country.equals(country) && this.productType == productType;
    }
}
