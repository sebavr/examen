package belt.spring.repositories;



import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import belt.spring.models.Rating;


@Repository
public interface RatingRepository extends CrudRepository<Rating,Long> {

	@Query("SELECT COUNT(r) FROM Rating r")
	long countRating();
	
	
	//@Query("SELECT show_id FROM Rating  WHERE show_id=?1")
	//List<Object[]> getShow(Long id);
	
	//@Query("SELECT r.show.id,r.user.id,r.number FROM Rating r ORDER BY r.number ASC")
	//List<Rating> findAllNumbers();
	public List<Rating> findAllByOrderByNumberAsc();
	
}