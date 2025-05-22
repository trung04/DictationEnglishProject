CREATE TABLE topic_images (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    file_name VARCHAR(255) NOT NULL,
    file_type VARCHAR(100) NOT NULL,
    file_path VARCHAR(255) NOT NULL,
    topic_id BIGINT NOT NULL,
    display_order INT,
    FOREIGN KEY (topic_id) REFERENCES topics(topic_id) ON DELETE CASCADE
); 