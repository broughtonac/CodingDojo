-- alter table users auto_increment = 1;
-- alter table messages auto_increment = 1;
-- alter table comments auto_increment = 1;

-- SELECT
-- 	messages.id,
--     first_name,
--     last_name,
--     message,
--     messages.created_at,
--     GROUP_CONCAT(CASE
--             WHEN comments.user_id = users.id THEN comment
--         END
--         ORDER BY comments.created_at DESC),
--     GROUP_CONCAT(CASE
--             WHEN comments.user_id = users.id THEN first_name
--         END
--         ORDER BY comments.created_at DESC) AS comment_first_name,
--     GROUP_CONCAT(CASE
--             WHEN comments.user_id = users.id THEN last_name
--         END
--         ORDER BY comments.created_at DESC) AS comment_last_name,
--     GROUP_CONCAT(CASE
--             WHEN comments.user_id = users.id THEN comments.created_at
--         END
--         ORDER BY comments.created_at DESC) AS comment_created_at
-- FROM
--     users
-- 		JOIN
--     messages ON users.id = messages.user_id
--         LEFT JOIN
--     comments ON messages.id = comments.message_id
-- GROUP BY messages.id, first_name , last_name , message , messages.created_at
-- ORDER BY messages.created_at DESC;

SELECT
	messages.id,
    first_name,
    last_name,
    message,
    messages.created_at,
    GROUP_CONCAT(CASE
            WHEN comments.message_id = messages.id THEN comment
        END
        ORDER BY comments.created_at DESC) AS comment,
    GROUP_CONCAT(CASE
            WHEN comments.message_id = messages.id THEN first_name
        END
        ORDER BY comments.created_at DESC) AS comment_first_name,
    GROUP_CONCAT(CASE
            WHEN comments.message_id = messages.id THEN last_name
        END
        ORDER BY comments.created_at DESC) AS comment_last_name,
    GROUP_CONCAT(CASE
            WHEN comments.message_id = messages.id THEN comments.created_at
        END
        ORDER BY comments.created_at DESC) AS comment_created_at
FROM
    users
		JOIN
    messages ON users.id = messages.user_id
        LEFT JOIN
    comments ON messages.id = comments.message_id
GROUP BY messages.id, first_name , last_name , message , messages.created_at
ORDER BY messages.created_at DESC;

-- SELECT messages.id, first_name, last_name, message, messages.created_at, GROUP_CONCAT(CASE WHEN comments.message_id = messages.id AND comments.user_id = users.id THEN comment END ORDER BY comments.created_at DESC) AS comment, GROUP_CONCAT(CASE WHEN comments.message_id = messages.id AND comments.user_id = users.id THEN first_name END ORDER BY comments.created_at DESC) AS comment_first_name, GROUP_CONCAT(CASE WHEN comments.message_id = messages.id AND comments.user_id = users.id THEN last_name END ORDER BY comments.created_at DESC) AS comment_last_name, GROUP_CONCAT(CASE WHEN comments.message_id = messages.id AND comments.user_id = users.id THEN comments.created_at END ORDER BY comments.created_at DESC) AS comment_created_at FROM users JOIN messages ON users.id = messages.user_id LEFT JOIN comments ON messages.id = comments.message_id GROUP BY messages.id, first_name , last_name , message , messages.created_at ORDER BY messages.created_at DESC;
-- select * from comments;