package com.nnk.springboot.service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CurveService {

    @Autowired
    private CurvePointRepository repository;

    /**
     *
     * @return all CurvePoint from the database
     */
    public List<CurvePoint> findAllCurve() {
        return repository.findAll();
    }

    /**
     *
     * @param id of the CurvePoint we want to find
     * @return Optional with CurvePoint we found if exist or Optional empty
     */
    public Optional<CurvePoint> findCurveById(Integer id) {
        return repository.findById(id);
    }

    /**
     *
     * @param curvePoint we want to save in database
     */
    public void saveCurve(CurvePoint curvePoint) {
        repository.save(curvePoint);
    }

    /**
     *
     * @param newCurvePoint we send to update
     */
    public void updateCurvePoint(CurvePoint newCurvePoint) {
        repository.save(newCurvePoint);
    }

    /**
     *
     * @param id of the CurvePoint we want to delete from the database
     */
    public void deleteCurveById(Integer id) {
        repository.deleteById(id);
    }
}
