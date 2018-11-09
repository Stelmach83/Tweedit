package net.stelmaszak.tweedit.service;

import net.stelmaszak.tweedit.filter.ReqInfo;
import net.stelmaszak.tweedit.repository.ReqInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class FilterServiceImpl implements FilterService {

    @Autowired
    private ReqInfoRepository reqInfoRepository;

    @Override
    public List<ReqInfo> getAllLogs() {
        return reqInfoRepository.findAllByOrderByDataCzasDesc();
    }
}
