package belt.spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import belt.spring.models.Show;
import belt.spring.models.User;
import belt.spring.repositories.ShowRepository;

@Service
public class ShowService {

private final ShowRepository showRepository;
	
	public ShowService(ShowRepository showRepository) {
		this.showRepository = showRepository;
	}
	
    // find user by id
    public Show findShowById(Long id) {
    	Optional<Show> u = showRepository.findById(id);
    	
    	if(u.isPresent()) {
            return u.get();
    	} else {
    	    return null;
    	}
    }
	// create a new show
	public Show createShow(Show newShow) {
		
		return showRepository.save(newShow);
	}
	
	// find all shows
	public List<Show> findAll() {
		
		return showRepository.findAll();
	}
	
	
	
	// find an show by id
	public Show getShowById(Long id) {
		
		Optional<Show> show = showRepository.findById(id);
		if(show.isPresent()) {
			return show.get();
		} else {
			return null;
		}
	}
	
	// update an show
	public Show updateShow(Show updatedShow) {
		
		return showRepository.save(updatedShow);
	}
	
	// delete an show
	public void delete(Long id) {
		
		showRepository.deleteById(id);
	}
}
