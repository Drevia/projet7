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

    public List<CurvePoint> findAllCurve() {
        return repository.findAll();
    }

    public Optional<CurvePoint> findCurveById(Integer id) {
        return repository.findById(id);
    }

    public void saveCurve(CurvePoint curvePoint) {
        repository.save(curvePoint);
    }

    public void updateCurvePoint(Integer id, CurvePoint newCurvePoint) {
        CurvePoint curvePoint = repository.findById(id).orElseThrow();
        curvePoint.setCurveId(newCurvePoint.getCurveId());
        curvePoint.setTerm(newCurvePoint.getTerm());
        curvePoint.setValue(newCurvePoint.getValue());
        curvePoint.setCreationDate(newCurvePoint.getCreationDate());
        curvePoint.setAsOfDate(newCurvePoint.getAsOfDate());

        repository.save(curvePoint);

    }

    public void deleteCurveById(Integer id) {
        repository.deleteById(id);
    }
}
