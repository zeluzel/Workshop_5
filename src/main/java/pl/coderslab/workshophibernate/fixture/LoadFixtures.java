package pl.coderslab.workshophibernate.fixture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class LoadFixtures {

    private final BookFixture bookFixture;

    @Autowired
    public LoadFixtures(BookFixture bookFixture) {
        this.bookFixture = bookFixture;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        bookFixture.loadIntoDb();
    }

}
