package platform;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@RestController
@RequestMapping
public class Controller {
    private final Code code;

    public Controller() {
        code = new Code("public static void main(String[] args) {SpringApplication.run(CodeSharingPlatform.class, args);");
    }

    @GetMapping(path = "/api/code", produces = "application/json;charset=UTF-8")
    public Code getApiCode() {
        return code;
    }

    @GetMapping(path = "/code", produces = "text/html")
    public String getHtmlCode() {
        String replace = readFile("C:\\Users\\Никита\\IdeaProjects\\Code Sharing Platform\\Code Sharing Platform\\task\\src\\resources\\templates\\code.html");
        return processHTML(replace);
    }

    @GetMapping(path = "/code/new", produces = "text/html")
    public String getNewCode() {
        return readFile("C:\\Users\\Никита\\IdeaProjects\\Code Sharing Platform\\Code Sharing Platform\\task\\src\\resources\\templates\\createNew.html");
    }

    @PostMapping(value = "/api/code/new")
    public ResponseEntity<String> postNewCode(@RequestBody Code code1) {
        code.setCode(code1.getCode());
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

    private String processHTML(String html) {
        return html.replace("CODE", code.getCode()).replace("DATE", code.getDate());
    }

    private String readFile(String path) {
        File file = new File(path);
        StringBuilder builder = new StringBuilder();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                builder.append(scanner.nextLine()).append("\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println("No file found: ");
        }
        return builder.toString();
    }
}
