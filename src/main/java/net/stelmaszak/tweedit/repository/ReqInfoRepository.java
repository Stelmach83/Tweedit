package net.stelmaszak.tweedit.repository;

import net.stelmaszak.tweedit.filter.ReqInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReqInfoRepository extends JpaRepository<ReqInfo, Long> {

    List<ReqInfo> findAllByOrderByDataCzasDesc();

}
