PGDMP         2            	    {            internet_banking #   14.9 (Ubuntu 14.9-0ubuntu0.22.04.1) #   14.9 (Ubuntu 14.9-0ubuntu0.22.04.1)     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    26976    internet_banking    DATABASE     e   CREATE DATABASE internet_banking WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'en_US.UTF-8';
     DROP DATABASE internet_banking;
                postgres    false            �            1259    26978    users    TABLE     �   CREATE TABLE public.users (
    id bigint NOT NULL,
    balance numeric(38,2),
    created_at timestamp(6) without time zone,
    updated_at timestamp(6) without time zone
);
    DROP TABLE public.users;
       public         heap    postgres    false            �            1259    26977    users_id_seq    SEQUENCE     u   CREATE SEQUENCE public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.users_id_seq;
       public          postgres    false    210            �           0    0    users_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;
          public          postgres    false    209            c           2604    26981    users id    DEFAULT     d   ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);
 7   ALTER TABLE public.users ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    209    210    210            �          0    26978    users 
   TABLE DATA           D   COPY public.users (id, balance, created_at, updated_at) FROM stdin;
    public          postgres    false    210   �
       �           0    0    users_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.users_id_seq', 1, false);
          public          postgres    false    209            e           2606    26983    users users_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    210            �      x������ � �     