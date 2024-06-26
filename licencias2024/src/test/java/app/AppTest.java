package app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

public class AppTest {
        public static Gestor gestor;

        @BeforeAll
        public static void setUp() {
                gestor = new Gestor();
        }

        @AfterEach
        public void close() {
                gestor.dropDB();
        }

}
