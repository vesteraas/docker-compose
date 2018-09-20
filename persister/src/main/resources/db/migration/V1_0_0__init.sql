CREATE TABLE data
(
	data_id BIGINT NOT NULL
		PRIMARY KEY,
	data_value VARCHAR(128) NOT NULL
)
GO

CREATE TABLE data_seq
(
	seq_name varchar(255) NOT NULL
		PRIMARY KEY,
	seq_number BIGINT
)
GO
