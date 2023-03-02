import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class ApiRestController {

    @Autowired
    EntityService entityService;
    @Autowired
    @Qualifier("datasource1")
    private FirstRepository firstRepository;
    @Autowired
    @Qualifier("datasource2")
    private SecondRepository secondRepository;

    @GetMapping("/getInfo/{id}")
    public ResponseEntity getById(@RequestParam(value = "db", required = true, defaultValue = "1") int db
    , @PathVariable(value = "id", required = true) long id){
        if(db == 1){
            return ResponseEntity.ok().body(entityService.getFirst(id));
        }
        else {
            return ResponseEntity.ok().body(entityService.getSecond(id));
        }
    }

    @PutMapping("/editInfo/db1/{id}")
    public ResponseEntity editInfo1(@PathVariable(value = "id",required = true) long id
            , @RequestBody EntityFirst entityFirst){
        Optional<EntityFirst> optionalEntityFirst = Optional.ofNullable(firstRepository.findById(id));
        if(!optionalEntityFirst.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        EntityFirst existingEntityFirst = optionalEntityFirst.get();
        existingEntityFirst.setDetail(entityFirst.getDetail());
        existingEntityFirst.setTitle(entityFirst.getTitle());

        EntityFirst savedEntityFirst = firstRepository.saveAndFlush(existingEntityFirst);
        return ResponseEntity.ok().body(savedEntityFirst);
    }
    @PutMapping("/editInfo/db2/{id}")
    public ResponseEntity editInfo2(@PathVariable(value = "id", required = true) long id
    ,@RequestBody EntitySecond entitySecond){
        Optional<EntitySecond> optionalEntitySecond = Optional.ofNullable(secondRepository.findById(id));
        if(!optionalEntitySecond.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        EntitySecond existingEntitySecond = optionalEntitySecond.get();
        existingEntitySecond.setTitle(entitySecond.getTitle());
        existingEntitySecond.setDetail(entitySecond.getDetail());

        EntitySecond savedEntitySecond = secondRepository.saveAndFlush(existingEntitySecond);
        return ResponseEntity.ok().body(savedEntitySecond);
    }
    @PostMapping("/createInfo/db1")
    public ResponseEntity createInfo1(@RequestBody EntityFirst entityFirst){
        firstRepository.save(entityFirst);
        return ResponseEntity.ok().body(entityFirst);
    }

    @PostMapping("/createInfo/db2")
    public ResponseEntity createInfo2(@RequestBody EntitySecond entitySecond){
        secondRepository.save(entitySecond);
        return ResponseEntity.ok().body(entitySecond);
    }

    @DeleteMapping("/deleteInfo/{id}")
    public ResponseEntity deleteInfo(@RequestParam(value = "db", required = true, defaultValue = "1") int db
    ,@PathVariable(value = "id", required = true) long id){
        if(db ==1 ){
            if(!Optional.ofNullable(firstRepository.findById(id)).isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build()
            }
            else {
                firstRepository.deleteById(id);
                return ResponseEntity.ok().build();
            }
        }
        else
            if(!Optional.ofNullable(secondRepository.findById(id)).isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            else {
                secondRepository.deleteById(id);
                return ResponseEntity.ok().build();
            }
    }

}
