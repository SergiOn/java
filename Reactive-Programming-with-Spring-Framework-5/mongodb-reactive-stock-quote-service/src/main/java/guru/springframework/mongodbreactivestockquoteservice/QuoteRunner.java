package guru.springframework.mongodbreactivestockquoteservice;

import guru.springframework.mongodbreactivestockquoteservice.client.StockQuoteClient;
import guru.springframework.mongodbreactivestockquoteservice.domain.Quote;
import guru.springframework.mongodbreactivestockquoteservice.repositories.QuoteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;

@Component
public class QuoteRunner implements CommandLineRunner {

    private final StockQuoteClient stockQuoteClient;
    private final QuoteRepository repository;

    public QuoteRunner(StockQuoteClient stockQuoteClient, QuoteRepository repository) {
        this.stockQuoteClient = stockQuoteClient;
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {

        Flux<Quote> quoteFlux = repository.findWithTailableCursorBy();

        Disposable disposable = quoteFlux.subscribe(quote -> {
            System.out.println("*#*#*#*#*#*#*#*#*#*#*#*#* Id: " + quote.getId());
        });

        disposable.dispose();
    }
}

////@Component
//public class QuoteRunner implements CommandLineRunner {
//
//    private final StockQuoteClient stockQuoteClient;
//
//    public QuoteRunner(StockQuoteClient stockQuoteClient) {
//        this.stockQuoteClient = stockQuoteClient;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        Flux<Quote> quoteFlux = stockQuoteClient.getQuoteStream();
//
//        quoteFlux.subscribe(System.out::println);
//    }
//}
