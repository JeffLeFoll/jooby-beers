package fr.enib.cai;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jooby.json.Jackson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

import org.jooby.Jooby;

/**
 * @author jooby generator
 */
public class App extends Jooby {

  private final Logger log = LoggerFactory.getLogger(getClass());

  {
    get("/", () -> "Hello World!");

    use(new Jackson());

    get("/beers", req -> {
      log.info("Received request for beers from", req.ip());

      ObjectMapper mapper = new ObjectMapper();
      List<Beer> beers = mapper.readValue(Paths.get("./beers/beers.json").toFile(), new TypeReference<List<Beer>>(){});

      return beers;
    });

    get("/beer/:beerId", req -> {
      log.info("Received request for "+req.param("beerId").value() + " from", req.ip());

      ObjectMapper mapper = new ObjectMapper();
      Beer beer = mapper.readValue(Paths.get("./beers/" + req.param("beerId").value() + ".json").toFile(), Beer.class);

      return beer;
    });

    assets("/img/**", Paths.get("../img"));
    assets("/**");
  }
  
  public static void main(final String[] args) {
    run(App::new, args);
  }

}
