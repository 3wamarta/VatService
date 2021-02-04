package Vat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static Product.Type.BOOK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class VatRepositoryTest {

    private VatRepository repositoryToTest;

    @BeforeEach
    void setUp() {
        repositoryToTest = new VatRepository();
    }

    @Test
    void shouldThrowExceptionWhenCountryIsIncorrect() {
        //when
        Exception exception = assertThrows(VatNotFoundException.class,
                () -> repositoryToTest.getVatFor("Poland111", BOOK));

        //then
        assertThat(exception.getMessage()).isEqualTo("Vat for country Poland111 and product type BOOK was not found");
    }

    @Test
    void shouldReturnFalseIfVatNotInRepository() {
        assertThat(repositoryToTest.hasVatRateFor("Poland111", BOOK)).isFalse();
    }

    @Test
    void shouldReturnTrueIfVatAlreadyInRepository() {
        repositoryToTest.addVatValue("Poland111", BOOK, new BigDecimal("0.00"));
        assertThat(repositoryToTest.hasVatRateFor("Poland111", BOOK)).isTrue();
    }

    @Test
    void shouldAddNewVatValueToRepository() {
        assertThat(repositoryToTest.hasVatRateFor("Poland111", BOOK)).isFalse();

        repositoryToTest.addVatValue("Poland111", BOOK, new BigDecimal("0.00"));

        Vat addedValue = repositoryToTest.getVatFor("Poland111", BOOK);
        assertThat(addedValue.getAmount()).isEqualByComparingTo(new BigDecimal("0.00"));
    }

}