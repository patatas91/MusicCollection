CREATE TABLE artist (
    id bigint NOT NULL,
    name CHARACTER VARYING(255),
    year INTEGER,
    styles bigint,
    members bigint,
    related bigint,

    CONSTRAINT artist_pk PRIMARY KEY (id),
    CONSTRAINT related_fk FOREIGN KEY (related) REFERENCES artist (id),
    CONSTRAINT people_fk FOREIGN KEY (members) REFERENCES people(id),
    CONSTRAINT style_fk FOREIGN KEY (style) REFERENCES style(id)
);

CREATE TABLE people (
    id bigint NOT NULL,
    name character varying(255),
    years integer,

    CONSTRAINT people_pk PRIMARY KEY (id)
);

CREATE TABLE style (
    id bigint NOT NULL,
    name character varying(255),

    CONSTRAINT style_pk PRIMARY KEY (id)
);

CREATE SEQUENCE artist_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;

CREATE SEQUENCE people_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;

CREATE SEQUENCE style_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;