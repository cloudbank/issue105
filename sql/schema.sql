DECLARE
  v_count INTEGER := 0;
  v_sql VARCHAR2(2000) := '';
BEGIN
  -- -----------------------------------------------------
  -- Table customer_security_sec_data
  -- -----------------------------------------------------
  v_sql := '
    CREATE TABLE customer_security_sec_data (
      security_identifier VARCHAR2(128) NOT NULL,
      report_date TIMESTAMP(0) NOT NULL,
      iv_type VARCHAR2(24) NOT NULL,
      security_name VARCHAR2(512) NOT NULL,
      final_maturity_date TIMESTAMP(0) NOT NULL,
      security_redemption_price NUMBER(20,7) NOT NULL,
      interest_rt NUMBER(20,7) NOT NULL,
      def_indicator SMALLINT NULL,
      der_step_indicator SMALLINT NULL,
      der_hybrid_indicator SMALLINT NULL,
      security_price NUMBER(20,7) NOT NULL,
      fx_rate NUMBER(20,7) NOT NULL,
      der_tips_inflationary_ratio NUMBER(20,7) NULL,
      io_hybrid_field VARCHAR2(128) NULL,
      as_400_rate_type VARCHAR2(128) NULL,
      prospective_method VARCHAR2(128) NULL,
      PRIMARY KEY (security_identifier, report_date))
    ';

  SELECT COUNT(*)
    INTO v_count
    FROM user_tables
   WHERE LOWER(table_name) = 'customer_security_sec_data';

  IF v_count = 0 THEN
    EXECUTE IMMEDIATE v_sql;
  END IF;


  -- -----------------------------------------------------
  -- Table customer_fund_data
  -- -----------------------------------------------------
  v_sql := '
    CREATE TABLE customer_fund_data (
      security_identifier VARCHAR2(128) NOT NULL,
      portfolio_number NUMBER(20,7) NOT NULL,
      portfolio_name VARCHAR2(256) NOT NULL,
      report_date TIMESTAMP(0) NOT NULL,
      PRIMARY KEY (portfolio_number, report_date)
     ,
      CONSTRAINT customer_fd_fk
        FOREIGN KEY (security_identifier , report_date)
        REFERENCES customer_security_sec_data (security_identifier , report_date)
       )
    ';

  SELECT COUNT(*)
    INTO v_count
    FROM user_tables
   WHERE LOWER(table_name) = 'customer_fund_data';

  IF v_count = 0 THEN
    EXECUTE IMMEDIATE v_sql;
    v_sql := 'CREATE INDEX customer_fd_idx ON customer_fund_data (security_identifier ASC, report_date ASC)';
    EXECUTE IMMEDIATE v_sql;
  END IF;


  -- -----------------------------------------------------
  -- Table customer_position_data
  -- -----------------------------------------------------
  v_sql := '
    CREATE TABLE customer_position_data (
      security_identifier VARCHAR2(128) NOT NULL,
      portfolio_number NUMBER(20,7) NOT NULL,
      report_date TIMESTAMP(0) NOT NULL,
      earned_inflationary_comp_base NUMBER(20,7) NULL,
      accrued_income NUMBER(20,7) NOT NULL,
      market_value NUMBER(20,7) NOT NULL,
      share_par_amount NUMBER(20,7) NOT NULL,
      earned_amortization_base NUMBER(20,7) NOT NULL,
      position_val_inf_adj_shares NUMBER(20,7) NULL,
      PRIMARY KEY (portfolio_number, report_date, security_identifier)
     ,
      CONSTRAINT customer_pd_fk
        FOREIGN KEY (security_identifier , report_date)
        REFERENCES customer_security_sec_data (security_identifier , report_date)
       )
    ';

  SELECT COUNT(*)
    INTO v_count
    FROM user_tables
   WHERE LOWER(table_name) = 'customer_position_data';

  IF v_count = 0 THEN
    EXECUTE IMMEDIATE v_sql;
    v_sql := 'CREATE INDEX customer_pd_idx ON customer_position_data (security_identifier ASC, report_date ASC)';
    EXECUTE IMMEDIATE v_sql;
  END IF;


  -- -----------------------------------------------------
  -- Table calculated_security_sec_data
  -- -----------------------------------------------------
  v_sql := '
    CREATE TABLE calculated_security_sec_data (
      security_identifier VARCHAR2(128) NOT NULL,
      report_date TIMESTAMP(0) NOT NULL,
      iv_type VARCHAR2(24) NOT NULL,
      security_name VARCHAR2(512) NOT NULL,
      final_maturity_date TIMESTAMP(0) NOT NULL,
      security_redemption_price NUMBER(20,7) NOT NULL,
      interest_rt NUMBER(20,7) NOT NULL,
      def_indicator SMALLINT NULL,
      der_step_indicator SMALLINT NULL,
      der_hybrid_indicator SMALLINT NULL,
      security_price NUMBER(20,7) NOT NULL,
      fx_rate NUMBER(20,7) NOT NULL,
      der_tips_inflationary_ratio NUMBER(20,7) NULL,
      io_hybrid_field VARCHAR2(128) NULL,
      as_400_rate_type VARCHAR2(128) NULL,
      prospective_method VARCHAR2(128) NULL,
      der_yield_calc_engine VARCHAR2(128) NULL,
      der_income_calc_engine VARCHAR2(128) NULL,
      der_one_day_security_yield NUMBER(20,7) NULL,
      der_redemption_date TIMESTAMP(0) NULL,
      der_redemption_price NUMBER(20,7) NULL,
      der_clean_price NUMBER(20,7) NULL,
      der_security_type VARCHAR2(128) NULL,
      PRIMARY KEY (security_identifier, report_date))
    ';

  SELECT COUNT(*)
    INTO v_count
    FROM user_tables
   WHERE LOWER(table_name) = 'calculated_security_sec_data';

  IF v_count = 0 THEN
    EXECUTE IMMEDIATE v_sql;
  END IF;


  -- -----------------------------------------------------
  -- Table calculated_fund_data
  -- -----------------------------------------------------
  v_sql := '
    CREATE TABLE calculated_fund_data (
      security_identifier VARCHAR2(128) NOT NULL,
      portfolio_number NUMBER(20,7) NOT NULL,
      portfolio_name VARCHAR2(256) NOT NULL,
      report_date TIMESTAMP(0) NOT NULL,
      PRIMARY KEY (portfolio_number, report_date)
     ,
      CONSTRAINT calculated_fd_fk
        FOREIGN KEY (security_identifier , report_date)
        REFERENCES calculated_security_sec_data (security_identifier , report_date)
       )
    ';

  SELECT COUNT(*)
    INTO v_count
    FROM user_tables
   WHERE LOWER(table_name) = 'calculated_fund_data';

  IF v_count = 0 THEN
    EXECUTE IMMEDIATE v_sql;
    v_sql := 'CREATE INDEX calculated_fd_idx ON calculated_fund_data (security_identifier ASC, report_date ASC)';
    EXECUTE IMMEDIATE v_sql;
  END IF;


  -- -----------------------------------------------------
  -- Table calculated_position_data
  -- -----------------------------------------------------
  v_sql := '
    CREATE TABLE calculated_position_data (
      security_identifier VARCHAR2(128) NOT NULL,
      portfolio_number NUMBER(20,7) NOT NULL,
      report_date TIMESTAMP(0) NOT NULL,
      earned_inflationary_comp_base NUMBER(20,7) NULL,
      accrued_income NUMBER(20,7) NOT NULL,
      market_value NUMBER(20,7) NOT NULL,
      share_par_amount NUMBER(20,7) NOT NULL,
      earned_amortization_base NUMBER(20,7) NOT NULL,
      position_val_inf_adj_shares NUMBER(20,7) NULL,
      der_one_day_security_income NUMBER(20,7) NULL,
      PRIMARY KEY (portfolio_number, report_date, security_identifier)
     ,
      CONSTRAINT calculated_pd_fk
        FOREIGN KEY (security_identifier , report_date)
        REFERENCES calculated_security_sec_data (security_identifier , report_date)
       )
    ';

  SELECT COUNT(*)
    INTO v_count
    FROM user_tables
   WHERE LOWER(table_name) = 'calculated_position_data';

  IF v_count = 0 THEN
    EXECUTE IMMEDIATE v_sql;
    v_sql := 'CREATE INDEX calculated_pd_idx ON calculated_position_data (security_identifier ASC, report_date ASC)';
    EXECUTE IMMEDIATE v_sql;
  END IF;

END;
