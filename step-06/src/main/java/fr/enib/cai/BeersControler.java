package fr.enib.cai;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.DBCursor;
import org.jongo.Jongo;
import org.jongo.MongoCursor;
import org.jooby.mvc.GET;
import org.jooby.mvc.Header;
import org.jooby.mvc.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Path("/v2/beers")
public class BeersControler {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final Jongo jongo;

    @Inject
    public BeersControler(Jongo jongo) {
        this.jongo = jongo;
    }

    @GET
    public List<Beer> listsAllBeers(@Header("User-Agent") String ua) throws IOException {
        log.info("Received request for beers from" + ua);

        MongoCursor<Beer> mongoBeers = jongo.getCollection("beers").find().as(Beer.class);

        List<Beer> beers = new ArrayList<>();
        mongoBeers.iterator().forEachRemaining(beer -> beers.add(beer));

        return beers;
    }
}
