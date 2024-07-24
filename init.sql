CREATE TABLE public.animal (
	id varchar(255) NOT NULL,
	category varchar(255) NULL,
	dt_birth date NULL,
	description varchar(255) NULL,
	img_url varchar(255) NULL,
	"name" varchar(255) NULL,
	status varchar(255) NULL,
	dt_adoption date NULL,
	CONSTRAINT animal_category_check CHECK (((category)::text = ANY ((ARRAY['MAMMALS'::character varying, 'BIRDS'::character varying, 'REPTILES'::character varying, 'AMPHIBIANS'::character varying, 'FISH'::character varying, 'INSECTS'::character varying, 'ARACHNIDS'::character varying, 'CRUSTACEANS'::character varying, 'MOLLUSKS'::character varying, 'ECHINODERMS'::character varying])::text[]))),
	CONSTRAINT animal_pkey PRIMARY KEY (id),
	CONSTRAINT animal_status_check CHECK (((status)::text = ANY ((ARRAY['AVAIABLE'::character varying, 'ADOPTED'::character varying])::text[])))
);