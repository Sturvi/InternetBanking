--
-- PostgreSQL database dump
--

-- Dumped from database version 14.9 (Ubuntu 14.9-0ubuntu0.22.04.1)
-- Dumped by pg_dump version 14.9 (Ubuntu 14.9-0ubuntu0.22.04.1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: operations; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.operations (
    operation_id bigint NOT NULL,
    amount numeric(38,2),
    created_at timestamp(6) without time zone,
    operation_type character varying(255),
    updated_at timestamp(6) without time zone,
    user_id bigint,
    CONSTRAINT operations_operation_type_check CHECK (((operation_type)::text = ANY ((ARRAY['DEPOSIT'::character varying, 'WITHDRAWAL'::character varying, 'TRANSFER'::character varying])::text[])))
);


--
-- Name: operations_operation_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.operations_operation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: operations_operation_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.operations_operation_id_seq OWNED BY public.operations.operation_id;


--
-- Name: users; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.users (
    id bigint NOT NULL,
    balance numeric(38,2),
    created_at timestamp(6) without time zone,
    updated_at timestamp(6) without time zone
);


--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- Name: operations operation_id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.operations ALTER COLUMN operation_id SET DEFAULT nextval('public.operations_operation_id_seq'::regclass);


--
-- Name: users id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- Data for Name: operations; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.operations (operation_id, amount, created_at, operation_type, updated_at, user_id) FROM stdin;
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.users (id, balance, created_at, updated_at) FROM stdin;
\.


--
-- Name: operations_operation_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.operations_operation_id_seq', 1, false);


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.users_id_seq', 1, false);


--
-- Name: operations operations_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.operations
    ADD CONSTRAINT operations_pkey PRIMARY KEY (operation_id);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: operations fk6s2v80e9ppot2sb1bba5w6fx3; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.operations
    ADD CONSTRAINT fk6s2v80e9ppot2sb1bba5w6fx3 FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- PostgreSQL database dump complete
--

