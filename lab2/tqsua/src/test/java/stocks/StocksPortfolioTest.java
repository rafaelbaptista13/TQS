import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // para usar anotacoes
class StocksPortfolioTest {

    @Mock
    IStockmarketService market;

    @InjectMocks
    StocksPortfolio portfolio;

    @Test
    void getTotalValueTest() {
        when(market.getPrice("EBAY")).thenReturn(4.0);
        when(market.getPrice("MSFT")).thenReturn(2.0);
        
        portfolio.addStock(new Stock("EBAY", 3));
        portfolio.addStock(new Stock("MSFT", 5));

        double total = 3*4 + 5*2;
        assertThat(portfolio.getTotalValue(), is(total));
        verify(market, times(2)).getPrice(anyString());
    }


}