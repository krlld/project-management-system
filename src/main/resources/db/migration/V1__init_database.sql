CREATE TABLE IF NOT EXISTS projects
(
    project_id  BIGSERIAL PRIMARY KEY,
    name        VARCHAR(255)   NOT NULL,
    description VARCHAR(255)   NOT NULL,
    budget      NUMERIC(19, 2) NOT NULL,
    start_at    DATE           NOT NULL,
    end_at      DATE           NOT NULL,
    status      VARCHAR(255)   NOT NULL,
    deleted     BOOLEAN DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS tasks
(
    task_id     BIGSERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    start_at    DATE         NOT NULL,
    end_at      DATE         NOT NULL,
    status      VARCHAR(255) NOT NULL,
    project_id  BIGINT       NOT NULL,
    deleted     BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (project_id) REFERENCES projects (project_id)
);