DROP TABLE buy;
DROP TABLE menu;
DROP TABLE reg_menu;
DROP TABLE menu_category;
DROP TABLE subscribe;
DROP TABLE default_subscribe;
DROP TABLE users;

CREATE TABLE IF NOT EXISTS users
(
  id
  int
  not
  null
  auto_increment,
  username
  varchar
(
  100
) not null unique,
  password varchar
(
  255
) not null,
  display_name varchar
(
  100
) not null,
  email varchar
(
  255
) not null,
  status char
(
  1
) not null default '0',
  created_at datetime not null default current_timestamp,
  updated_at datetime,
  primary key
(
  id
)
  );

CREATE TABLE IF NOT EXISTS default_subscribe
(
  user_id
  int
  not
  null,
  default_subscribe_user_id
  int
  not
  null,
  primary
  key
(
  user_id
),
  foreign key
(
  default_subscribe_user_id
) references users
(
  id
)
  );

CREATE TABLE IF NOT EXISTS subscribe
(
  user_id
  int
  not
  null,
  target_user_id
  int
  not
  null,
  status
  char
(
  1
) not null default '0',
  created_at datetime not null default current_timestamp,
  updated_at datetime,
  primary key
(
  user_id,
  target_user_id
),
  foreign key
(
  user_id
) references users
(
  id
),
  foreign key
(
  target_user_id
) references users
(
  id
)
  );

CREATE TABLE IF NOT EXISTS menu_category
(
  id
  int
  not
  null
  auto_increment,
  user_id
  int
  not
  null,
  name
  varchar
(
  255
) not null,
  created_at datetime not null default current_timestamp,
  updated_at datetime,
  primary key
(
  id
),
  foreign key
(
  user_id
) references users
(
  id
)
  );

CREATE TABLE IF NOT EXISTS reg_menu
(
  id
  int
  not
  null
  auto_increment,
  menu_category_id
  int
  not
  null,
  name
  varchar
(
  255
) not null,
  created_at datetime not null default current_timestamp,
  updated_at datetime,
  primary key
(
  id
),
  foreign key
(
  menu_category_id
) references menu_category
(
  id
)
  );

CREATE TABLE IF NOT EXISTS menu
(
  id
  int
  not
  null
  auto_increment,
  target_date
  date
  not
  null,
  name
  varchar
(
  255
),
  user_id int not null,
  created_at datetime not null default current_timestamp,
  updated_at datetime,
  primary key
(
  id
),
  foreign key
(
  user_id
) references users
(
  id
)
  );

CREATE INDEX idx_menu_target_date ON menu (target_date);

CREATE TABLE IF NOT EXISTS buy
(
  id
  int
  not
  null
  auto_increment,
  menu_id
  int
  not
  null,
  name
  varchar
(
  3000
),
  created_at datetime not null default current_timestamp,
  updated_at datetime,
  primary key
(
  id
),
  foreign key
(
  menu_id
) references menu
(
  id
)
  );
