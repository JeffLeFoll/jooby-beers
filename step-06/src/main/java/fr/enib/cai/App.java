package fr.enib.cai;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import org.jongo.Jongo;
import org.jongo.MongoCursor;
import org.jooby.json.Jackson;
import org.jooby.mongodb.Jongoby;
import org.jooby.mongodb.Mongodb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import org.jooby.Jooby;

/**
 * @author jooby generator
 */
public class App extends Jooby {

  private final Logger log = LoggerFactory.getLogger(getClass());

  {
    get("/", () -> "Hello World!");

    use(new Jackson());
    use(new Mongodb());
    use(new Jongoby());

    get("/beers", req -> {
      log.info("Received request for beers from", req.ip());

      Jongo jongo = require(Jongo.class);
      MongoCursor<Beer> beers = jongo.getCollection("beers").find().as(Beer.class);

      return beers;
    });

    get("/beer/:beerId", req -> {
      log.info("Received request for "+req.param("beerId").value() + " from", req.ip());

      String beerId = req.param("beerId").value();

      Jongo jongo = require(Jongo.class);
      Beer beer = jongo.getCollection("beers").findOne("{id: #}", beerId).as(Beer.class);

      return beer;
    });

    use(BeersControler.class);
    use(BeerControler.class);

    assets("/img/**", Paths.get("../img"));
    assets("/**");
  }
  
  public static void main(final String[] args) {
    run(App::new, args);
  }

}
