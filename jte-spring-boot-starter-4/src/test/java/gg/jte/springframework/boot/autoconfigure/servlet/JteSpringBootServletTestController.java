package gg.jte.springframework.boot.autoconfigure.servlet;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.FragmentsRendering;

@Controller
public class JteSpringBootServletTestController {

    @GetMapping("/greet")
    public String greeting(@RequestParam("subject") String subject, Model model) {
        model.addAttribute("subject", subject);
        return "greeting";
    }

    @GetMapping(value = "/stream")
    public FragmentsRendering stream(Model model) {
        model.addAttribute("subject", "World");
        return FragmentsRendering.fragment("greeting")
                .header(HttpHeaders.CONTENT_TYPE, "text/vnd.turbo-streams.html")
                .build();

    }

}
