CREATE TABLE IF NOT EXISTS users
(
    id         UUID                                  NOT NULL,
    fulll_name VARCHAR(255)                          NOT NULL,
    email      VARCHAR(512)                          NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (id),
    CONSTRAINT UQ_USER_EMAIL UNIQUE (email)
    );

CREATE TABLE IF NOT EXISTS bookings
(
    id      UUID NOT NULL,
    comment VARCHAR(512),
    CONSTRAINT pk_booking PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS conference_rooms
(
    id          UUID                                  NOT NULL,
    name        VARCHAR(255)                          NOT NULL,
    description VARCHAR(512)                          NOT NULL,
    CONSTRAINT pk_room PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS time_slots
(
    id         UUID                                  NOT NULL,
    start_time TIMESTAMP WITHOUT TIME ZONE           NOT NULL,
    end_time   TIMESTAMP WITHOUT TIME ZONE           NOT NULL,
    avaliable  BOOLEAN,
    booking_id UUID REFERENCES bookings (id) ON DELETE CASCADE,
    room_id    UUID REFERENCES conference_rooms (id) ON DELETE CASCADE,
    CONSTRAINT pk_timeslot PRIMARY KEY (id)
    );

DELETE
from users;
DELETE
from bookings;
DELETE
FROM conference_rooms;
DELETE
FROM time_slots;