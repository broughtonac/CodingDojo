-- 1
SELECT 
    SUM(amount)
FROM
    billing
WHERE
    MONTH(charged_datetime) = '3'
        AND YEAR(charged_datetime) = '2012';

-- 2
SELECT 
    SUM(amount)
FROM
    billing
		JOIN
    clients ON billing.client_id = clients.client_id
WHERE
    clients.client_id = 2;

-- 3
SELECT 
    domain_name
FROM
    sites
        JOIN
    clients ON sites.client_id = clients.client_id
WHERE
    clients.client_id = 10;

-- 4
SELECT 
    clients.client_id,
    YEAR(created_datetime),
    MONTH(created_datetime),
    (sites.site_id)
FROM
    sites
        JOIN
    clients ON sites.client_id = clients.client_id
WHERE
    clients.client_id = 1
GROUP BY YEAR(created_datetime) , MONTH(created_datetime);

-- 5
SELECT 
    sites.domain_name, COUNT(leads.leads_id) AS leads_generated
FROM
    leads
        JOIN
    sites ON leads.site_id = sites.site_id
WHERE
    YEAR(created_datetime) = 2011
        AND MONTH(created_datetime) < 3
GROUP BY sites.site_id;

-- 6
SELECT 
    clients.first_name, clients.last_name, COUNT(leads.leads_id)
FROM
    leads
        JOIN
    sites ON leads.site_id = sites.site_id
        JOIN
    clients ON sites.client_id = clients.client_id
WHERE
    YEAR(created_datetime) = 2011
GROUP BY clients.client_id;

-- 7
SELECT 
    clients.first_name,
    clients.last_name,
    MONTH(created_datetime),
    COUNT(leads.leads_id)
FROM
    leads
        JOIN
    sites ON leads.site_id = sites.site_id
        JOIN
    clients ON sites.client_id = clients.client_id
WHERE
    YEAR(created_datetime) = 2011
        AND MONTH(created_datetime) < 7
GROUP BY clients.client_id , MONTH(created_datetime);

-- 8
SELECT 
    clients.client_id,
    clients.first_name,
    clients.last_name,
    sites.domain_name,
    COUNT(leads.leads_id)
FROM
    leads
        JOIN
    sites ON leads.site_id = sites.site_id
        JOIN
    clients ON sites.client_id = clients.client_id
WHERE
    YEAR(created_datetime) = 2011
GROUP BY clients.client_id , sites.site_id
ORDER BY clients.client_id ASC;

-- 9
SELECT 
    clients.first_name,
    clients.last_name,
    YEAR(charged_datetime),
    MONTH(charged_datetime),
    SUM(amount)
FROM
    billing
        JOIN
    clients ON billing.client_id = clients.client_id
GROUP BY YEAR(charged_datetime) , MONTH(charged_datetime)
ORDER BY clients.client_id ASC;

-- 10
SELECT 
    clients.first_name,
    clients.last_name,
    GROUP_CONCAT(domain_name)
FROM
    sites
        JOIN
    clients ON sites.client_id = clients.client_id
GROUP BY clients.client_id
ORDER BY clients.client_id ASC;