-- Create table
CREATE TABLE candidates (
  id           UUID PRIMARY KEY,
  fio          VARCHAR(255) NOT NULL,
  age          SMALLINT NOT NULL CHECK (age BETWEEN 14 AND 99),
  position     VARCHAR(255) NOT NULL,
  cv_info      TEXT NOT NULL,
  comment      TEXT,
  status       VARCHAR(255) NOT NULL
);
