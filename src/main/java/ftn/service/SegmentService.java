package ftn.service;

import ftn.model.Segment;
import ftn.repository.SegmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SegmentService {

    @Autowired
    private SegmentRepository segmentRepository;

    public Segment save(Segment segment) {
        return segmentRepository.save(segment);
    }

}
