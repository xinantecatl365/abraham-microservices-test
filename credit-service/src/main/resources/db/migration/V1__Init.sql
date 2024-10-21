CREATE TABLE credits
(
    id            SERIAL PRIMARY KEY,                           -- Auto-incrementing ID
    amount        NUMERIC(15, 2) NOT NULL,
    interest_rate REAL           NOT NULL,
    start_date    DATE           NOT NULL DEFAULT CURRENT_DATE, -- Auto-set to the current date
    end_date      DATE           Not NULL,
    status        VARCHAR(50)    NOT NULL,                      -- Enum stored as string
    customer_id   INTEGER        NOT NULL                       -- Foreign key to the customer
);



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
    -- Calculate Mean
    RETURN QUERY SELECT AVG(amount) AS mean, NULL::NUMERIC, NULL::NUMERIC
                 FROM credits;

-- Calculate Median
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

-- Calculate Mode (most frequent value)
    RETURN QUERY
        SELECT amount AS mode, NULL::NUMERIC, NULL::NUMERIC
        FROM credits
        GROUP BY amount
        ORDER BY COUNT(*) DESC, amount ASC
            FETCH FIRST ROW ONLY;
END;
$$ LANGUAGE plpgsql;

