package com.rentio;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;

public class ModularityTest {
    
    @Test
      void verifyModularStructure() {
        ApplicationModules modules = ApplicationModules.of(RentioApplication.class);

        modules.forEach(module -> System.out.println(module));

        modules.verify();
      }
}
