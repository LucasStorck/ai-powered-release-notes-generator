package org.lucas.releasenotes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "org.lucas.releasenotes.models")
public class ReleaseNotesApplication {

  public static void main(String[] args) {
    SpringApplication.run(ReleaseNotesApplication.class, args);
  }

}
