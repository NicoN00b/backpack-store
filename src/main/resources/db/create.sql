SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS backpacks (
  id int PRIMARY KEY auto_increment,
  brand VARCHAR,
  model VARCHAR,
  description VARCHAR,
  waterResistance int,
  durability int,
  productId int,
  price DOUBLE,
  leash BOOLEAN,
  camelback BOOLEAN,
  pannier BOOLEAN,
  type VARCHAR
);