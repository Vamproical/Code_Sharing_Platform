package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import platform.model.Code;
import platform.repository.CodeRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ApiController {
    private final CodeRepository codeRepository;

    @Autowired
    public ApiController(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    @GetMapping(path = "/api/code/{uuid}", produces = "application/json;charset=UTF-8")
    public Code getApiCodeByUUID(@PathVariable String uuid) {
        return CodeService.checkRestrictions(uuid, codeRepository);
    }

    @PostMapping(value = "/api/code/new")
    public ResponseEntity<String> postNewCode(@RequestBody Code code) {
        if (code.getTime() > 0) {
            code.setTimeRestriction(true);
        }
        if (code.getViews() > 0) {
            code.setViewsRestriction(true);
        }
        String id = codeRepository.save(code).getCodeId();
        return new ResponseEntity<>("{id: \"" + id + "\"}", HttpStatus.OK);
    }

    @GetMapping(path = "/api/code/latest", produces = "application/json;charset=UTF-8")
    public List<Code> getLatest() {
        return CodeService.reverseTop10(codeRepository)
                .stream()
                .limit(10)
                .collect(Collectors.toList());
    }
}
