package pl.obol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.obol.model.Proposition;

@Repository
public interface PropositionRepository extends JpaRepository<Proposition, Long> {
}
