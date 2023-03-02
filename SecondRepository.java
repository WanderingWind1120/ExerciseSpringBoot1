import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("datasource2")
public interface SecondRepository extends JpaRepository<EntitySecond, Long> {
    @Query(value = "SELECT * FROM SECONDTABLE u", nativeQuery = true)
    public List<EntitySecond> findAll();

    @Query(value = "SELECT * FROM SECONDTABLE u WHERE u.id = :id", nativeQuery = true)
    public EntitySecond findById(@Param("id") long id);
}
