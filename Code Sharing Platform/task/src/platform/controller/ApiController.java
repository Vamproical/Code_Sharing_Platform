package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import platform.model.Code;
import platform.repository.CodeRepository;

import java.util.List;

@RestController
public class ApiController {
    private final CodeRepository codeRepository;

    @Autowired
    public ApiController(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    @GetMapping(path = "/api/code/{id}", produces = "application/json;charset=UTF-8")
    public Code getApiCode(@PathVariable long id) {
        return codeRepository.findById(id).isEmpty()
                ? new Code("no code snippet")
                : codeRepository.findById(id).get();
    }

    @PostMapping(value = "/api/code/new")
    public ResponseEntity<String> postNewCode(@RequestBody Code code) {
        long id = codeRepository.save(code).getCodeId();
        System.out.println(id);
        return new ResponseEntity<>("{id: \"" + id + "\"}", HttpStatus.OK);
    }

    @GetMapping(path = "/api/code/latest", produces = "application/json;charset=UTF-8")
    public List<Code> getLatest() {
        return codeRepository.findTop10ByOrderByCodeIdDesc();
    }
}
