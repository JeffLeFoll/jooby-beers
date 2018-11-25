package fr.enib.cai;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Paths;

import org.jooby.Jooby;

/**
 * @author jooby generator
 */
public class App extends Jooby {

  private final Logger log = LoggerFactory.getLogger(getClass());

  {
    get("/", () -> "Hello World!");
  
    get("/beers", (req, res) -> {
      log.info("Received request for beers from", req.ip());
      res.send("Hello beers");
    });

    get("/beer/:beerId", (req, res) -> {
      log.info("Received request for "+req.param("beerId").value() + " from", req.ip());
      res.send("Hello beer " + req.param("beerId").value());
    });

    assets("/img/**", Paths.get("../img"));
    assets("/**");
  }
  
  public static void main(final String[] args) {
    run(App::new, args);
  }

}
