package gg.jte.springframework.boot.autoconfigure.reactive;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webtestclient.autoconfigure.AutoConfigureWebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = {"gg.jte.development-mode=true", "spring.main.web-application-type=reactive"})
@AutoConfigureWebTestClient
public class JteSpringBootReactiveTests {

    @Test
    void contextLoads() {
    }

    @Test
    void greeting(@Autowired WebTestClient client) throws Exception {
        client.get()
                .uri("/greet?subject=World")
                .exchange()
                .expectAll(
                        spec -> spec.expectStatus().isOk(),
                        spec -> spec.expectBody(String.class).value(v -> {
                            assertThat(v).contains("Hello World!");
                        })
                );
    }

    // WebFlux forces text/html for FragmentsRendering fragments (see
    // ViewResolutionResultHandler#renderFragment, which passes a hardcoded MediaType.TEXT_HTML),
    // so a custom content type like text/vnd.turbo-streams.html cannot be produced on the reactive
    // stack. This test documents that behavior; the turbo-streams content type is covered on the
    // servlet stack in JteSpringBootServletTests#stream.
    @Test
    void stream(@Autowired WebTestClient client) throws Exception {
        client.get()
                .uri("/stream")
                .exchange()
                .expectAll(
                        spec -> spec.expectStatus().isOk(),
                        spec -> spec.expectHeader().contentType("text/html;charset=UTF-8"),
                        spec -> spec.expectBody(String.class).value(v -> {
                            assertThat(v).contains("Hello World!");
                        })
                );
    }
}
