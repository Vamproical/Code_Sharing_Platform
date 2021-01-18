package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import platform.model.Code;
import platform.repository.StorageList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {
    private final StorageList storageList;

    @Autowired
    public UserController(StorageList storageList) {
        this.storageList = storageList;
    }

    @GetMapping(path = "/code/{N}", produces = "text/html")
    public String getHtmlCode(@PathVariable int N, Model model) {
        model.addAttribute("code",
                storageList.getCode(N).isEmpty()
                        ? new Code("no code snippet")
                        : storageList.getCode(N).get());
        return "code";
    }


    @GetMapping(path = "/code/new", produces = "text/html")
    public String getNewCode(Model model) {
        return "new";
    }

    @GetMapping(path = "/code/latest", produces = "text/html")
    public String getLatest(Model model) {
        List<Code> reverse = new ArrayList<>(storageList.getAllCodes());
        Collections.reverse(reverse);
        model.addAttribute("codes", reverse.stream().limit(10).collect(Collectors.toList()));
        return "latest";
    }
}
