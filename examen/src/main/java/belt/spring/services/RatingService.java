package belt.spring.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import belt.spring.models.Rating;
import belt.spring.models.User;
import belt.spring.repositories.RatingRepository;

@Service 
public class RatingService { 

	 private final RatingRepository ratingRepository;
	    
	    public RatingService(RatingRepository ratingRepository) {
	        this.ratingRepository = ratingRepository;
	    }
	   
	    /*public List<Object[]>  buscarshow(Long id){
	    	return ratingRepository.getShow(id);
	    }
	    */
	    
	    

		public Rating saveRating(Rating r) {
	       
	        return ratingRepository.save(r);
	    }
	    
	    public List<Rating> findAll() {
			
			return (List<Rating>) ratingRepository.findAll();
		}
	    //ordenados
	    public List<Rating> buscaTodos() {
			
	 			return  ratingRepository.findAllByOrderByNumberAsc();
	 		}
	    
	    // find rating by id
	    public Rating findRatingById(Long id) {
	    	Optional<Rating> u = ratingRepository.findById(id);
	    	
	    	if(u.isPresent()) {
	            return u.get();
	    	} else {
	    	    return null;
	    	}
	    }

		
	    

}
