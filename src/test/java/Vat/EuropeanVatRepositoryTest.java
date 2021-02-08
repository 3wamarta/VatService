package Vat;

import Product.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static Product.Type.*;
import static Vat.EuropeanCountries.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EuropeanVatRepositoryTest {

    public static final String UNEXISTING_COUNTRY = "Poland111";
    private EuropeanVatRepository repositoryToTest;


    @BeforeEach
    void setUp() {
        repositoryToTest = new EuropeanVatRepository();
    }

    @Test
    void shouldThrowExceptionWhenCountryIsIncorrect() {
        Exception exception = assertThrows(VatNotFoundException.class,
                () -> repositoryToTest.getVatFor(UNEXISTING_COUNTRY, BOOK));

        assertThat(exception.getMessage()).isEqualTo("Vat for country Poland111 and product type BOOK was not found");
    }

    @Test
    void shouldReturnFalseIfVatNotInRepository() {
        assertThat(repositoryToTest.hasVatValueFor(UNEXISTING_COUNTRY, BOOK)).isFalse();
    }

    @Test
    void shouldAddNewVatValueToRepository() {
        assertThat(repositoryToTest.hasVatValueFor(UNEXISTING_COUNTRY, BOOK)).isFalse();

        repositoryToTest.addVatValue(UNEXISTING_COUNTRY, BOOK, new BigDecimal("0.00"));

        assertThat(repositoryToTest.hasVatValueFor(UNEXISTING_COUNTRY, BOOK)).isTrue();
        Vat addedValue = repositoryToTest.getVatFor(UNEXISTING_COUNTRY, BOOK);
        assertThat(addedValue).isEqualToComparingFieldByField(
                new Vat(UNEXISTING_COUNTRY, BOOK, new BigDecimal("0.00")));
    }

    @ParameterizedTest(name = "should return {2} for country {0} and type {1} ")
    @MethodSource
    void shouldReturnProperPercentageForCountryAndType(String country, Type type, String expectedPercentage) {
        Vat result = repositoryToTest.getVatFor(country, type);
        assertThat(result).isEqualToComparingFieldByField(
                new Vat(country, type, new BigDecimal(expectedPercentage)));
    }

    private static Stream<Arguments> shouldReturnProperPercentageForCountryAndType() {
        return Stream.of(
                Arguments.of(POLAND, BOOK, "0.05"),
                Arguments.of(POLAND, BABY, "0.05"),
                Arguments.of(POLAND, FOOD, "0.08"),
                Arguments.of(POLAND, CLOTHES, "0.23"),
                Arguments.of(POLAND, GAMES, "0.23"),
                Arguments.of(POLAND, SHOES, "0.23"),
                Arguments.of(DENMARK, BOOK, "0.08"),
                Arguments.of(DENMARK, BABY, "0.08"),
                Arguments.of(DENMARK, FOOD, "0.08"),
                Arguments.of(DENMARK, CLOTHES, "0.08"),
                Arguments.of(DENMARK, GAMES, "0.08"),
                Arguments.of(DENMARK, SHOES, "0.08"),
                Arguments.of(GERMANY, BOOK, "0.04"),
                Arguments.of(GERMANY, BABY, "0.04"),
                Arguments.of(GERMANY, FOOD, "0.04"),
                Arguments.of(GERMANY, CLOTHES, "0.10"),
                Arguments.of(GERMANY, GAMES, "0.21"),
                Arguments.of(GERMANY, SHOES, "0.21")
        );
    }

}
