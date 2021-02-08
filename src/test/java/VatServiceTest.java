import Product.Product;
import Product.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.stream.Stream;

import static Vat.EuropeanCountries.POLAND;
import static java.util.stream.Stream.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

class VatServiceTest {

    private VatProvider vatProvider;
    private VatService serviceToTest;


    @BeforeEach
    void setUp() {
        vatProvider = Mockito.mock(VatProvider.class);
        Mockito.when(vatProvider.getVatFor(anyString(), any(Type.class))).thenReturn(new BigDecimal("0.05"));
        serviceToTest = new VatService(vatProvider);
    }

    @Test
    void shouldCalculateGrossPriceForBookInPoland() {
        //given
        Product product = new Product(UUID.randomUUID(), "My book", new BigDecimal("20.50"), Type.BOOK, POLAND);

        //when
        BigDecimal grossPrice = serviceToTest.getGrossPrice(product);

        //then
        assertThat(grossPrice).isEqualTo(new BigDecimal("21.53"));
    }

    @Test
    void shouldCalculateGrossPricesForBooksInPoland() {
        //given
        Product product = new Product(UUID.randomUUID(), "My book", new BigDecimal("20.50"), Type.BOOK, POLAND);

        //when
        Stream<BigDecimal> grossPrices = serviceToTest.getGrossPrices(of(product));

        //then
        assertThat(grossPrices)
                .hasSize(1)
                .contains(new BigDecimal("21.53"));
    }
}