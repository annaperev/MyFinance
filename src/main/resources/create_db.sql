create sequence account_id_seq;

alter sequence account_id_seq owner to postgres;

create sequence currency_id_seq;

alter sequence currency_id_seq owner to postgres;

create sequence item_id_seq;

alter sequence item_id_seq owner to postgres;

create sequence operation_id_seq;

alter sequence operation_id_seq owner to postgres;

create sequence tranfer_id_seq;

alter sequence tranfer_id_seq owner to postgres;



-- Table: public.currency

-- DROP TABLE public.currency;

CREATE TABLE public.currency
(
    id bigint NOT NULL DEFAULT nextval('currency_id_seq'::regclass),
    long_name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    short_name character varying(3) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT currency_pkey PRIMARY KEY (id),
    CONSTRAINT uk_r4mwq1i7lgx54qw2uksk79eh3 UNIQUE (short_name)

)

TABLESPACE pg_default;

ALTER TABLE public.currency
    OWNER to postgres;



-- Table: public.account

-- DROP TABLE public.account;

CREATE TABLE public.account
(
    id bigint NOT NULL DEFAULT nextval('account_id_seq'::regclass),
    amount numeric(10,2) NOT NULL,
    comment text COLLATE pg_catalog."default",
    name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    currency_id bigint NOT NULL,
    CONSTRAINT account_pkey PRIMARY KEY (id),
    CONSTRAINT uk_bb9lrmwswqvhcy1y430ki00ir UNIQUE (name)
,
    CONSTRAINT fk316pn109iutn6yqoxrqp09cpc FOREIGN KEY (currency_id)
        REFERENCES public.currency (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.account
    OWNER to postgres;





-- Table: public.item

-- DROP TABLE public.item;

CREATE TABLE public.item
(
    id bigint NOT NULL DEFAULT nextval('item_id_seq'::regclass),
    name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    type integer NOT NULL,
    parent_item_id bigint NOT NULL,
    CONSTRAINT item_pkey PRIMARY KEY (id),
    CONSTRAINT fka3rmlgvinyx0de6jvogft00r8 FOREIGN KEY (parent_item_id)
        REFERENCES public.item (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.item
    OWNER to postgres;



-- Table: public.operation

-- DROP TABLE public.operation;

CREATE TABLE public.operation
(
    id bigint NOT NULL DEFAULT nextval('operation_id_seq'::regclass),
    amount numeric(10,2) NOT NULL,
    comment text COLLATE pg_catalog."default",
    date date NOT NULL,
    type integer NOT NULL,
    account_id bigint NOT NULL,
    item_id bigint NOT NULL,
    CONSTRAINT operation_pkey PRIMARY KEY (id),
    CONSTRAINT fkcd49uxcdcmy8dmfeyfcq9klp4 FOREIGN KEY (item_id)
        REFERENCES public.item (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkloy20r01mn4truqqu460w3j9q FOREIGN KEY (account_id)
        REFERENCES public.account (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.operation
    OWNER to postgres;



-- Table: public.tranfer

-- DROP TABLE public.tranfer;

CREATE TABLE public.tranfer
(
    id bigint NOT NULL DEFAULT nextval('tranfer_id_seq'::regclass),
    amount_from numeric(10,2) NOT NULL,
    amount_to numeric(10,2) NOT NULL,
    comment text COLLATE pg_catalog."default",
    date date NOT NULL,
    account_from_id bigint NOT NULL,
    account_to_id bigint NOT NULL,
    CONSTRAINT tranfer_pkey PRIMARY KEY (id),
    CONSTRAINT fkepbgr4ngmhfe3u24xpi7tcrtf FOREIGN KEY (account_from_id)
        REFERENCES public.account (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkp18di2te0genks62nwbnj6v31 FOREIGN KEY (account_to_id)
        REFERENCES public.account (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.tranfer
    OWNER to postgres;
