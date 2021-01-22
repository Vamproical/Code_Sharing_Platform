package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import platform.model.Code;
import platform.repository.CodeRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {
    private final CodeRepository codeRepository;

    @Autowired
    public UserController(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    @GetMapping(path = "/code/{id}", produces = "text/html")
    public String getHtmlCode(@PathVariable String id, Model model) throws Exception {
        Code snippet = CodeService.checkRestrictions(id, codeRepository);
        model.addAttribute("code", snippet);
        return "code";
    }



    @GetMapping(path = "/code/new", produces = "text/html")
    public String getNewCode(Model model) {
        return "new";
    }

    @GetMapping(path = "/code/latest", produces = "text/html")
    public String getLatest(Model model) {
        model.addAttribute("codes", CodeService.reverseTop10(codeRepository)
                .stream()
                .limit(10)
                .collect(Collectors.toList()));
        return "latest";
    }
}
