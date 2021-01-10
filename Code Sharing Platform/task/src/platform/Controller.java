package platform;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {
    private List<Code> codeList = new ArrayList<>();

    public Controller() {
        Code code = new Code("public static void main(String[] args) {SpringApplication.run(CodeSharingPlatform.class, args);");
        codeList.add(code);
    }

    @GetMapping(path = "/api/code", produces = "application/json;charset=UTF-8")
    public Code getApiCode() {
        return codeList.get(0);
    }

    @GetMapping(path = "/code", produces = "text/html")
    public ResponseEntity<String> getHtmlCode() {
        return ResponseEntity.ok()
                .body("<title>Code</title>"
                        + "<pre>" + codeList.get(0).getCode() + "</pre>");
    }
}
