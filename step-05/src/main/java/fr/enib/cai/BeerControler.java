package fr.enib.cai;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jooby.mvc.GET;
import org.jooby.mvc.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.io.IOException;
import java.nio.file.Paths;

@Path("/beer")
public class BeerControler {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final ObjectMapper mapper;

    @Inject
    public BeerControler(ObjectMapper jacksonMapper) {
        mapper = jacksonMapper;
    }

    @GET
    @Path("/:beerId")
    public Beer retrieveSpecificBeer(String beerId) throws IOException {
        log.info("Received request for "+ beerId);

        Beer beer = mapper.readValue(Paths.get("./beers/" + beerId + ".json").toFile(), Beer.class);

        return beer;
    }
}
