CREATE TABLE IF NOT EXISTS owners(
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    birthdate DATE,
    password TEXT,
    role TEXT
);

CREATE TABLE IF NOT EXISTS cats(
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    birthdate DATE,
    breed TEXT,
    color TEXT,
    owner INTEGER REFERENCES owners(id)
);

CREATE TABLE IF NOT EXISTS friends(
    first_id INTEGER REFERENCES cats(id) ON DELETE CASCADE,
    second_id INTEGER REFERENCES cats(id) ON DELETE CASCADE,
    PRIMARY KEY (first_id, second_id)
);