package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import platform.model.Code;
import platform.repository.CodeRepository;

@Controller
public class UserController {
    private final CodeRepository codeRepository;

    @Autowired
    public UserController(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    @GetMapping(path = "/code/{id}", produces = "text/html")
    public String getHtmlCode(@PathVariable Long id, Model model) {
        model.addAttribute("code",
                codeRepository.findById(id).isEmpty()
                        ? new Code("no code snippet")
                        : codeRepository.findById(id).get());
        return "code";
    }


    @GetMapping(path = "/code/new", produces = "text/html")
    public String getNewCode(Model model) {
        return "new";
    }

    @GetMapping(path = "/code/latest", produces = "text/html")
    public String getLatest(Model model) {
        model.addAttribute("codes", codeRepository.findTop10ByOrderByCodeIdDesc());
        return "latest";
    }
}
