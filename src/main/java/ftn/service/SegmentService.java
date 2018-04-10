package ftn.service;

import ftn.model.Segment;
import ftn.repository.SegmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SegmentService {

    @Autowired
    private SegmentRepository segmentRepository;

    public Segment save(Segment segment) {
        return segmentRepository.save(segment);
    }

    public Collection<Segment> findBySalaId(Long salaId) {
        return segmentRepository.findBySalaId(salaId);
    }

}
