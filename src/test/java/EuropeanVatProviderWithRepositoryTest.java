import Product.Type;
import Vat.VatNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static Product.Type.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EuropeanVatProviderWithRepositoryTest {

    private static final String POLAND = "Poland";
    private static final String DENMARK = "Denmark";
    private static final String GERMANY = "Germany";

    private EuropeanVatProviderWithRepository vatProvider;

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

    @BeforeEach
    void setUp() {
        vatProvider = new EuropeanVatProviderWithRepository();
    }

    @ParameterizedTest(name = "should return {2} for country {0} and type {1} ")
    @MethodSource
    void shouldReturnProperPercentageForCountryAndType(String country, Type type, String expectedPercentage) {
        BigDecimal actual = vatProvider.getVatFor(country, type);
        assertThat(actual).isEqualTo(new BigDecimal(expectedPercentage));
    }

    @Test
    void shouldThrowExceptionForInvalidCountry() {
        assertThatThrownBy(() -> vatProvider.getVatFor("Polad", BOOK))
                .isInstanceOf(VatNotFoundException.class)
                .hasMessage("Vat for country Polad and product type BOOK was not found");
    }
}