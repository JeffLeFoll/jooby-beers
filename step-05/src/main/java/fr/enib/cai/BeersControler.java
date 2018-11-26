package fr.enib.cai;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jooby.mvc.GET;
import org.jooby.mvc.Header;
import org.jooby.mvc.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@Path("/beers")
public class BeersControler {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final ObjectMapper mapper;

    @Inject
    public BeersControler(ObjectMapper jacksonMapper) {
        mapper = jacksonMapper;
    }

    @GET
    public List<Beer> listsAllBeers(@Header("User-Agent") String ua) throws IOException {
        log.info("Received request for beers from" + ua);

        List<Beer> beers = mapper.readValue(Paths.get("./beers/beers.json").toFile(), new TypeReference<List<Beer>>(){});

        return beers;
    }
}
