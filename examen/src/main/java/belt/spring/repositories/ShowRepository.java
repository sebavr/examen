package belt.spring.repositories;




import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import belt.spring.models.Show;

@Repository
public interface ShowRepository extends CrudRepository<Show,Long>, JpaRepository<Show,Long> {

	
	
	// descending order
	
	
}
