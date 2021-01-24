DROP VIEW IF EXISTS auction_view;
DROP VIEW IF EXISTS auction_parameter_view;
CREATE VIEW auction_view
AS SELECT
       a.id,
       a.title,
       a.description,
       a.price,
       u.username,
       c.name AS "category",
       s.name AS "section",
       photo.link AS "thumbnail"
   FROM auction a, "user" u, category c, section s, photo photo
   WHERE a.creator_id = u.id
     AND a.category_id = c.id
     AND c.section_id = s.id
     AND photo.auction_id = a.id
     AND photo."order" = 1;

CREATE VIEW auction_parameter_view
AS
SELECT
    a_p.auction_id,
    a_p.parameter_id,
    p.key,
    a_p.value
FROM auction_parameter a_p,
     parameter p,
     auction a
WHERE a_p.parameter_id = p.id
  AND a_p.auction_id = a.id;
