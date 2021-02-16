import Product.Type;
import Vat.EuropeanVatRepository;
import Vat.Vat;
import Vat.VatNotFoundException;
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
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EuropeanVatProviderTest {

    public static final String VAT_VALUE = "0.01";
    private EuropeanVatProvider vatProvider;
    private EuropeanVatRepository repository;


    @BeforeEach
    void setUp() {
        repository = mock(EuropeanVatRepository.class);
        vatProvider = new EuropeanVatProvider(repository);
    }

    @ParameterizedTest(name = "should return 0.01 for country {0} and type {1} ")
    @MethodSource
    void shouldReturnVatValueForValidCountryAndType(String country, Type type) {
        when(repository.getVatFor(country, type)).thenReturn(new Vat(country, type, new BigDecimal(VAT_VALUE)));

        BigDecimal actual = vatProvider.getVatFor(country, type);
        assertThat(actual).isEqualTo(new BigDecimal(VAT_VALUE));
    }

    @Test
    void shouldThrowExceptionForInvalidCountry() {
        when(repository.getVatFor("Polad", BOOK))
                .thenThrow(new VatNotFoundException("Vat for country Polad and product type BOOK was not found"));

        assertThatThrownBy(() -> vatProvider.getVatFor("Polad", BOOK))
                .isInstanceOf(VatNotFoundException.class)
                .hasMessage("Vat for country Polad and product type BOOK was not found");

    }

    private static Stream<Arguments> shouldReturnVatValueForValidCountryAndType() {
        return Stream.of(
                Arguments.of(POLAND, BOOK),
                Arguments.of(GERMANY, BABY),
                Arguments.of(DENMARK, FOOD)
        );
    }
}
