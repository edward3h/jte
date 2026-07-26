package gg.jte.springframework.boot.autoconfigure.reactive;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.result.view.FragmentsRendering;

@Controller
public class JteSpringBootReactiveTestController {

    @GetMapping("/greet")
    public String greeting(@RequestParam("subject") String subject, Model model) {
        model.addAttribute("subject", subject);
        return "greeting";
    }

    @GetMapping("/stream")
    public FragmentsRendering stream(Model model) {
        model.addAttribute("subject", "World");
        return FragmentsRendering.fragment("greeting")
                .build();

    }
}
