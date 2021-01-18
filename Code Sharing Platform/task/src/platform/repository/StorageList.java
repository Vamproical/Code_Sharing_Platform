package platform.repository;

import org.springframework.stereotype.Component;
import platform.model.Code;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class StorageList {
    private final List<Code> codes = new ArrayList<>();

    public StorageList() {
    }

    public Optional<Code> getCode(int index) {
        try {
            return Optional.of(codes.get(index));
        } catch (IndexOutOfBoundsException e) {
            return Optional.empty();
        }
    }

    public int getID(Code code) {
        return codes.indexOf(code);
    }

    public List<Code> getAllCodes() {
        return codes;
    }

    public void addCode(Code code) {
        codes.add(code);
    }
}
