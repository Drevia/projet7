package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
public class CurvePointServiceTest {

    @Mock
    CurvePointRepository curvePointRepository;

    @InjectMocks
    CurveService service;


    @Test
    void findCurveById_OK() {
        when(curvePointRepository.findById(any())).thenReturn(Optional.of(new CurvePoint()));
        assertDoesNotThrow(() -> service.findCurveById(1));
    }

    @Test
    void findAllCurve_OK() {
        List<CurvePoint> curvePointList = new ArrayList<>();
        CurvePoint curvePoint = new CurvePoint(1, 2, 3);
        curvePointList.add(curvePoint);

        when(curvePointRepository.findAll()).thenReturn(curvePointList);
        assertDoesNotThrow(() -> service.findAllCurve());
        assertEquals(1, curvePointList.size());
    }

    @Test
    void saveTrade_OK() {
        when(curvePointRepository.save(any(CurvePoint.class))).thenReturn(new CurvePoint());
        assertDoesNotThrow(() -> service.saveCurve(new CurvePoint()));
    }

    @Test
    void updateTrade_OK() {
        CurvePoint curvePoint = new CurvePoint(1, 2, 3);
        when(curvePointRepository.findById(anyInt())).thenReturn(Optional.of(curvePoint));

        assertDoesNotThrow(() -> service.updateCurvePoint(1,
                new CurvePoint(4, 5, 6)));
        verify(curvePointRepository, times(1)).save(curvePoint);
    }

    @Test
    void deleteTrade_Ok() {
        doNothing().when(curvePointRepository).deleteById(anyInt());
        assertDoesNotThrow(() -> service.deleteCurveById(anyInt()));
    }
}
