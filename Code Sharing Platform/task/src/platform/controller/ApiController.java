package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import platform.model.Code;
import platform.repository.StorageList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ApiController {
    private final StorageList storageList;

    @Autowired
    public ApiController(StorageList storageList) {
        this.storageList = storageList;
    }

    @GetMapping(path = "/api/code/{N}", produces = "application/json;charset=UTF-8")
    public Code getApiCode(@PathVariable int N) {
        return storageList.getCode(N).isEmpty()
                ? new Code("no code snippet")
                : storageList.getCode(N).get();
    }

    @PostMapping(value = "/api/code/new")
    public ResponseEntity<String> postNewCode(@RequestBody Code code) {
        storageList.addCode(code);
        return new ResponseEntity<>("{id: \"" + storageList.getID(code) + "\"}", HttpStatus.OK);
    }

    @GetMapping(path = "/api/code/latest", produces = "application/json;charset=UTF-8")
    public List<Code> getLatest() {
        List<Code> temp = new ArrayList<>(storageList.getAllCodes());
        Collections.reverse(temp);
        return temp.stream().limit(10).collect(Collectors.toList());
    }
}
