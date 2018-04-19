package ftn.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.model.Segment;
import ftn.repository.SegmentRepository;

@Service
public class SegmentService {

    @Autowired
    private SegmentRepository segmentRepository;

    public Segment save(Segment segment) {
        return segmentRepository.save(segment);
    }

    public Segment findOne(Long id) {
        return segmentRepository.findOne(id);
    }

    public Collection<Segment> findBySalaId(Long salaId) {
        return segmentRepository.findBySalaId(salaId);
    }
    
    public List<Segment> findAll(){
    	return segmentRepository.findAll();
    }

}
