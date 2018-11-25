package fr.enib.cai;

import org.jooby.json.Jackson;
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

    use(new Jackson());

    use(BeersControler.class);
    use(BeerControler.class);

    assets("/img/**", Paths.get("../img"));
    assets("/**");
  }
  
  public static void main(final String[] args) {
    run(App::new, args);
  }

}
