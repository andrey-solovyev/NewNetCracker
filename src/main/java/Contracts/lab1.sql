-- №1
SELECT *
FROM departments d;
-- №2
select c.customer_id,
       c.cust_first_name || ' ' || c.cust_last_name as NAME,
       c.CUST_EMAIL
from CUSTOMERS c
ORDER BY c.customer_id;
-- №3
select e.employee_id,
       e.first_name,
       e.last_name,
       e.job_id,
       e.email,
       e.phone_number,
       (CASE
            WHEN 100000 < ((e.salary + 100) * 12) AND ((e.salary + 100) * 12) < 150000
                THEN e.salary * 0.7
            WHEN ((e.salary + 100) * 12) > 150000
                THEN e.salary * 0.65
           END) AS Salary
from employees e
where (e.salary + 100) * 12 between 100000 and 200000
ORDER BY e.job_id, e.last_name, e.salary;
-- №4
SELECT c.country_id, c.country_name
FROM countries c
WHERE c.country_id IN ('DE', 'IT', 'RU')
ORDER BY c.country_name;
-- №5
SELECT e.first_name || ' ' || e.last_name as NAME
FROM employees e
WHERE e.last_name LIKE '_a%'
  AND e.first_name LIKE '%d%';
-- №6
SELECT *
FROM employees e
WHERE LENGTH(e.first_name) < 5
   OR LENGTH(e.last_name) < 5
ORDER BY (LENGTH(e.first_name) + LENGTH(e.last_name)), LENGTH(e.last_name), e.last_name, e.first_name;
-- №7
SELECT j.job_id, j.job_title, ROUND(((j.max_salary + j.min_salary) / 2) * 0.82 / 1000, 1) * 1000 AS AVG_SALARY
FROM JOBS j
ORDER BY AVG_SALARY DESC, j.job_id;

-- №8
SELECT c.CUST_LAST_NAME,
       c.CUST_FIRST_NAME,
       case
           WHEN c.CREDIT_LIMIT >= 3500 THEN 'A'
           WHEN c.CREDIT_LIMIT < 3500 AND c.CREDIT_LIMIT >= 1000 THEN 'B'
           ELSE 'C'
           end                                                           category,
       CASE WHEN c.CREDIT_LIMIT >= 3500 THEN 'Внимание, VIP-клиенты' end COMMENTS
FROM CUSTOMERS c
ORDER BY category;
-- №9
SELECT decode(
               EXTRACT(month from o.order_date),
               1, 'Январь',
               2, 'Февраль',
               3, 'Март',
               4, 'Апрель',
               5, 'Май',
               6, 'Июнь',
               7, 'Июль',
               8, 'Август‚',
               9, 'Сентябрь',
               10, 'Октябрь',
               11, 'Ноябрь',
               12, 'Декабрь'
           ) month
FROM orders o
WHERE date '1998-01-01' <= o.order_date
  and o.order_date < date '1999-01-01'
GROUP BY EXTRACT(month from o.ORDER_DATE);
-- №10

select DISTINCT to_char(o.ORDER_DATE, 'MONTH', 'nls_date_language=russian') as MONTH
from orders o
WHERE date '1998-01-01' <= o.order_date
  and o.order_date < date '1999-01-01';
-- №11

SELECT sysdate, to_char(sysdate, 'dd fmMONTH yyyy, day') AS RESULT
FROM dual;
SELECT sysdate,
       case
           WHEN to_char(trunc(sysdate, 'MONTH') + rownum - 1, 'day') IN ('воскресенье', 'суббота') then 'выходной'
           end comments
FROM dual
connect by rownum <= to_number(to_char(last_day(sysdate), 'DD'));


-- 12
SELECT e.employee_id,
       e.last_name || ' ' || e.first_name as emp_name,
       e.job_id,
       e.salary,
       e.commission_pct
FROM employees e
WHERE e.commission_pct is not null
ORDER BY e.commission_pct desc,
         e.employee_id;

-- 13

SELECT EXTRACT(YEAR FROM o.ORDER_DATE) AS                                                       year,
       SUM(CASE
               WHEN EXTRACT(MONTH FROM o.ORDER_DATE) BETWEEN 1 and 3 THEN o.ORDER_TOTAL END)    "q1",
       SUM(CASE WHEN EXTRACT(MONTH FROM o.ORDER_DATE) BETWEEN 4 and 6 THEN o.ORDER_TOTAL END)   "q2",
       SUM(CASE WHEN EXTRACT(MONTH FROM o.ORDER_DATE) BETWEEN 7 and 9 THEN o.ORDER_TOTAL END)   "q3",
       SUM(CASE WHEN EXTRACT(MONTH FROM o.ORDER_DATE) BETWEEN 10 and 12 THEN o.ORDER_TOTAL END) "q4",
       SUM(o.ORDER_TOTAL)                                                                       year_sum
from ORDERS o
where date '1995-01-01' <= O.ORDER_DATE
  and O.ORDER_DATE < date '2001-01-01'
GROUP BY EXTRACT(year from o.order_date)
ORDER BY EXTRACT(YEAR FROM o.ORDER_DATE);
-- 13 еще один вариант решения данной задачи
select extract(year from o.order_date) as year,
       sum(
               case
                   when to_char(o.order_date, 'Q') = '1'
                       then o.order_total
                   else null
                   end)                as "q1",
       sum(
               case
                   when to_char(o.order_date, 'Q') = '2'
                       then o.order_total
                   else null
                   end)                as "q2",
       sum(
               case
                   when to_char(o.order_date, 'Q') = '3'
                       then o.order_total
                   else null
                   end)                as "q3",
       sum(
               case
                   when to_char(o.order_date, 'Q') = '4'
                       then o.order_total
                   else null
                   end)                as "q4",
       sum(o.order_total)              as year_sum
from orders o
where date '1995-01-01' <= o.order_date
  and o.order_date < date '2001-01-01'
group by extract(year from o.order_date)
order by extract(year from o.order_date);
-- 14
select p.product_id,
       p.product_name,
       extract(year from p.warranty_period) * 12 + extract(month from p.warranty_period) as warranty_months,
       p.list_price,
       p.catalog_url
from product_information p
where regexp_like(p.product_name, '(\s|^)\d+\s*(mb|gb)', 'i')
  and not regexp_like(p.product_name, '^hd', 'i')
  and not regexp_like(substr(p.product_description, 1, 30), '(disk|drive|hard)', 'i')
order by to_number(trim(regexp_replace(
        regexp_replace(p.product_name, '\s\d*(gb)', regexp_replace(p.product_name, '\D') * 1024, 1, 1, 'i'),
        '[A-Z/-]'))) desc,
         p.list_price
;
--15

SELECT (to_date('21:30', 'HH24:MI') - to_date(to_char(sysdate, 'HH24:MI'), 'HH24:MI')) * 24 * 60 as minutes
FROM dual;