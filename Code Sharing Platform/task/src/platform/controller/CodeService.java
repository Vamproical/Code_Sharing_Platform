package platform.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;
import platform.model.Code;
import platform.repository.CodeRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CodeService {

    public static Code checkRestrictions(@PathVariable String id, CodeRepository codeRepository) {
        Optional<Code> snippetOption = codeRepository.findById(id);
        Code snippet = snippetOption.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "This code snippet does not exist."));
        if (snippet.isTimeRestriction()) {
            long passedSeconds = snippet.getTimePasses();
            snippet.setTime(passedSeconds > snippet.getTime() ? 0L : snippet.getTime() - passedSeconds);
            if (snippet.getTime() == 0) {
                codeRepository.deleteById(snippet.getCodeId());
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This code snippet does not exist.");
            } else {
                codeRepository.save(snippet);
            }
        }
        if (snippet.isViewsRestriction()) {
            System.out.println("views");
            snippet.setViews(snippet.getViews() > 0 ? snippet.getViews() - 1 : 0L);
            if (snippet.getViews() == 0) {
                codeRepository.deleteById(snippet.getCodeId());
            } else {
                codeRepository.save(snippet);
            }
        }
        return snippet;
    }

    public static List<Code> reverseTop10(CodeRepository codeRepository) {
        List<Code> latest = codeRepository.findAllByViewsAndTime(0, 0);
        Collections.reverse(latest);
        return latest;
    }
}
