package com.abraham.creditservice.repository;

import com.abraham.creditservice.entities.Credit;
import jakarta.persistence.Tuple;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;

import java.util.List;
import java.util.Optional;

public interface CreditRepository extends JpaRepository<Credit, Integer> {

    @Query("""
            SELECT sum(c.amount)
            FROM Credit c
            WHERE c.id = :userId
            AND c.status='ACTIVE'
            """)
    Optional<Double> creditUsed(int userId);

    @Query("""
            SELECT count(c.status) AS count, c.status AS status
            FROM Credit c
            GROUP BY c.status
            ORDER BY c.status
            """)
    List<Tuple> getMetrics();

    @Query(value = """
            CREATE OR REPLACE FUNCTION get_mean_median_mode()
                RETURNS TABLE
                        (
                            mean   NUMERIC,
                            median NUMERIC,
                            mode   NUMERIC
                        )
            AS
            $$
            BEGIN
                RETURN QUERY SELECT AVG(amount) AS mean, NULL::NUMERIC, NULL::NUMERIC
                             FROM credits;
            
                RETURN QUERY
                    WITH OrderedAmounts AS (SELECT amount,
                                                   ROW_NUMBER() OVER (ORDER BY amount) AS row_num,
                                                   COUNT(*) OVER ()                    AS total_rows
                                            FROM credits)
                    SELECT AVG(amount) AS median, NULL::NUMERIC, NULL::NUMERIC
                    FROM OrderedAmounts
                    WHERE row_num IN (
                                      (total_rows + 1) / 2,
                                      (total_rows + 2) / 2
                        );
            
                RETURN QUERY
                    SELECT amount AS mode, NULL::NUMERIC, NULL::NUMERIC
                    FROM credits
                    GROUP BY amount
                    ORDER BY COUNT(*) DESC, amount ASC
                        FETCH FIRST ROW ONLY;
            END;
            $$ LANGUAGE plpgsql;
            """, nativeQuery = true)
    @Modifying
    @Transactional
    void createProcedure();


}
