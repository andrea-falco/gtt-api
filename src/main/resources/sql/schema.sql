CREATE TABLE gtt_stop
(
    id         VARCHAR(36),
    name       VARCHAR(128),
    number     VARCHAR(128),
    area       VARCHAR(256),
    accessible BOOLEAN,
    CONSTRAINT gtt_stop_pk PRIMARY KEY (id)
);

CREATE TABLE gtt_line
(
    id          VARCHAR(36),
    number      VARCHAR(128),
    destination VARCHAR(256),
    CONSTRAINT gtt_line_pk PRIMARY KEY (id)
);

CREATE TABLE gtt_time
(
    id         VARCHAR(36),
    time       TIME,
    realtime   BOOLEAN,
    accessible BOOLEAN,
    CONSTRAINT gtt_time_pk PRIMARY KEY (id)
);

CREATE TABLE gtt_timetable
(
    id      VARCHAR(36),
    line_id VARCHAR(36),
    CONSTRAINT gtt_timetable_pk PRIMARY KEY (id),
    CONSTRAINT gtt_timetable_fk_line FOREIGN KEY (line_id) REFERENCES gtt_line (id) ON DELETE CASCADE
);

CREATE TABLE gtt_timetable_time
(
    timetable_id VARCHAR(36),
    time_id      VARCHAR(36),
    CONSTRAINT gtt_timetable_time_pk PRIMARY KEY (timetable_id, time_id),
    CONSTRAINT gtt_timetable_time_fk_timetable FOREIGN KEY (timetable_id) REFERENCES gtt_timetable (id) ON DELETE CASCADE,
    CONSTRAINT gtt_timetable_time_fk_time FOREIGN KEY (time_id) REFERENCES gtt_time (id) ON DELETE CASCADE
);

CREATE TABLE gtt_request
(
    id          VARCHAR(36),
    timestamp   TIMESTAMP(3),
    CONSTRAINT gtt_request_pk PRIMARY KEY (id)
);

CREATE TABLE gtt_request_stop
(
    request_id VARCHAR(36) NOT NULL,
    stop_id    VARCHAR(36),
    CONSTRAINT gtt_request_stop_pk PRIMARY KEY (request_id),
    CONSTRAINT gtt_request_stop_fk_request FOREIGN KEY (request_id) REFERENCES gtt_request (id) ON DELETE CASCADE,
    CONSTRAINT gtt_request_stop_fk_stop FOREIGN KEY (stop_id) REFERENCES gtt_stop (id) ON DELETE CASCADE
);

CREATE TABLE gtt_request_timetable
(
    request_id   VARCHAR(36) NOT NULL,
    timetable_id VARCHAR(36),
    CONSTRAINT gtt_request_timetable_pk PRIMARY KEY (request_id, timetable_id),
    CONSTRAINT gtt_request_timetable_fk_request FOREIGN KEY (request_id) REFERENCES gtt_request (id) ON DELETE CASCADE,
    CONSTRAINT gtt_request_timetable_fk_timetable FOREIGN KEY (timetable_id) REFERENCES gtt_timetable (id) ON DELETE CASCADE
);