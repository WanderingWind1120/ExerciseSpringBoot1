import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("datasource1")
public interface FirstRepository extends JpaRepository<EntityFirst, Long> {
    @Query(value = "SELECT * FROM FIRSTTABLE u WHERE u.id = :id", nativeQuery = true)
    public EntityFirst findById(@Param("id") long id);

    @Query(value = "SELECT * FROM FIRSTTABLE u", nativeQuery = true)
    public List<EntityFirst> findAll();

}
