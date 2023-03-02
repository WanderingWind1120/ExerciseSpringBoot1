import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntityService {
    @Autowired
    FirstRepository firstRepository;

    @Autowired
    SecondRepository secondRepository;

    public List<EntityFirst> getAll1(){
        return firstRepository.findAll();
    }
    public List<EntitySecond> getAll2(){
        return secondRepository.findAll();
    }

    public EntityFirst getFirst(long id){
        return firstRepository.findById(id);
    }

    public EntitySecond getSecond(long id){
        return secondRepository.findById(id);
    }

    public int countFirst(){
        return firstRepository.findAll().size();
    }

    public int countSecond(){
        return secondRepository.findAll().size();
    }
}
