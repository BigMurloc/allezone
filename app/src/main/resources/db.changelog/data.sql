
INSERT INTO public.app_user
    (id, username, password)
VALUES
    (1, 'admin', '$2y$10$qAS5abhSNAZ16UwoJJYlG.0lQOWUB1C67hDLbdv1XNPGkLvjMfCd6');

INSERT INTO public.app_authority
    (id, authority)
VALUES
    (1, 'admin'),
    (2, 'grant-authority');

INSERT INTO public.app_user_authority
    (app_user_id, authority)
VALUES
    (1, 'admin');

ALTER SEQUENCE hibernate_sequence RESTART WITH 2;


