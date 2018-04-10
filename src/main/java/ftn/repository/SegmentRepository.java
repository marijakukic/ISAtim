package ftn.repository;

import ftn.model.Segment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface SegmentRepository extends JpaRepository<Segment, Long> {

    Collection<Segment> findBySalaId(Long salaId);

}
