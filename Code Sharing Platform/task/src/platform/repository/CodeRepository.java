package platform.repository;

import org.springframework.data.repository.CrudRepository;
import platform.model.Code;

import java.util.List;

public interface CodeRepository extends CrudRepository<Code, String> {
    List<Code> findAllByViewsAndTime(long views, long time);
}
