package com.abraham.creditservice.repository;

import com.abraham.creditservice.models.StatisticsModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Arrays;

@Repository
@RequiredArgsConstructor
public class EntityManagerRepository {

//    private final EntityManager entityManager;
    private final JdbcTemplate jdbcTemplate;

    public StatisticsModel getStatistics() {
//        entityManager.createStoredProcedureQuery("get_mean_median_mode", "mean","median","mode")
//        StoredProcedureQuery query = entityManager
//                .createStoredProcedureQuery("get_mean_median_mode()", "mean", "median", "mode");
//        query.execute();
//        return StatisticsModel.builder()
//                .mean((Double) query.getOutputParameterValue("mean"))
//                .median((Double) query.getOutputParameterValue("median"))
//                .mode((Double) query.getOutputParameterValue("mode"))
//                .build();
        return jdbcTemplate.queryForObject(
                "SELECT * FROM get_mean_median_mode()",
                (rs, rowNum) -> {
                    return StatisticsModel.builder()
                            .mean(rs.getBigDecimal("mean"))
                            .median(rs.getBigDecimal("median"))
                            .mode(rs.getBigDecimal("mode"))
                            .build();
                }
        );
    }
}
