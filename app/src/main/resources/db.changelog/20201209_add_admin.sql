INSERT INTO public.users
    (id, username, password)
VALUES
    (1, 'admin', '$2y$10$qAS5abhSNAZ16UwoJJYlG.0lQOWUB1C67hDLbdv1XNPGkLvjMfCd6');

INSERT INTO public.authorities
    (id, authorities)
VALUES
    (1, 'admin');
