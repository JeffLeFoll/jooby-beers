package fr.enib.cai;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jongo.Jongo;
import org.jooby.mvc.GET;
import org.jooby.mvc.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.io.IOException;
import java.nio.file.Paths;

@Path("/v2/beer")
public class BeerControler {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final Jongo jongo;

    @Inject
    public BeerControler(Jongo jongo) {
        this.jongo = jongo;
    }

    @GET
    @Path("/:beerId")
    public Beer retrieveSpecificBeer(String beerId) throws IOException {
        log.info("Received request for "+ beerId);

        Beer beer = jongo.getCollection("beers").findOne("{id: #}", beerId).as(Beer.class);

        return beer;
    }
}
