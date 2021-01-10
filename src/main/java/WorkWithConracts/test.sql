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

SELECT *
FROM employees e
WHERE LENGTH(e.first_name) < 5
   OR LENGTH(e.last_name) < 5
ORDER BY (LENGTH(e.first_name) + LENGTH(e.last_name)), LENGTH(e.last_name), e.last_name, e.first_name;
SELECT *
FROM JOBS;
SELECT j.job_id, j.job_title, ROUND(((j.max_salary + j.min_salary) / 2) * 0.82 / 1000, 1) * 1000 AS AVG_SALARY
FROM JOBS j
ORDER BY AVG_SALARY DESC, j.job_id;

SELECT c.cust_last_name, c.cust_first_name
FROM customers c;
SELECT j.job_id, j.job_title, AVG(((j.max_salary + j.min_salary) / 2) * 0.82) AS AVG_SALARY
FROM JOBS j
GROUP BY j.job_id;



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



SELECT *
FROM PRODUCT_INFORMATION p
WHERE regexp_like(lower(p.product_name), '^[^HD]*(gb|mb');

select DISTINCT to_char(o.ORDER_DATE, 'fmMONTH ')                          AS MONTH,
                to_char(o.ORDER_DATE, 'yyyy', 'nls_date_language=russian') AS YEAR,
                COUNT(*) over (ORDER BY o.ORDER_DATE)                      as orders
from ORDERS o
group by to_char(o.order_date, 'month'), to_char(o.order_date, 'yyyy')
having count(*) > 2
   and sum(o.order_total) > 20000
order by extract(month from to_date(Month, 'MM'));
;;
select to_char(o.order_date, 'month') as Month,
       to_char(o.order_date, 'yyyy')  as Year,
       count(*)                       as count_order,
       count(o.order_total)           as total
from orders o
group by to_char(o.order_date, 'month'),
         to_char(o.order_date, 'yyyy')
having count(*) > 2
   and sum(o.order_total) > 20000
order by extract(month from to_date(Month, 'MM'));
;
select e.DEPARTMENT_ID
from EMPLOYEES e
group by e.department_id, e.first_name
HAVING COUNT(e.FIRST_NAME) > 1
order by e.DEPARTMENT_ID;
select e.department_id
from employees e
group by e.department_id, e.first_name
having count(e.first_name) > 1
order by e.department_id;
SELECT EXTRACT(MONTH FROM o.ORDER_DATE)
from ORDERS o;
SELECT EXTRACT(YEAR FROM O.ORDER_DATE) AS YEAR,
       SUM(CASE
               WHEN EXTRACT(MONTH FROM O.ORDER_DATE) < 7 THEN
                   O.ORDER_TOTAL
           END)                        AS SUMPART1,
       COUNT(CASE
                 WHEN EXTRACT(MONTH FROM O.ORDER_DATE) < 7 THEN
                     O.ORDER_TOTAL
           END)                        AS COUNTPART1,
       SUM(CASE
               WHEN 6 < EXTRACT(MONTH FROM O.ORDER_DATE) THEN
                   O.ORDER_TOTAL
           END)                        AS SUMPART2,
       COUNT(CASE
                 WHEN 6 < EXTRACT(MONTH FROM O.ORDER_DATE) THEN
                     O.ORDER_TOTAL
           END)                        AS COUNTPART2
FROM ORDERS O
GROUP BY EXTRACT(YEAR FROM O.ORDER_DATE)
ORDER BY YEAR
;
SELECT o.ORDER_ID,
       to_char(o.ORDER_DATE, 'dd fmMONTH yyyy, day') AS order_date,
       c.CUST_LAST_NAME || ' ' || c.CUST_FIRST_NAME  as emp_name,
       count(d.LINE_ITEM_ID)                         as "count"
FROM ORDERS o
         left join CUSTOMERS c on o.CUSTOMER_ID = c.CUSTOMER_ID
         left join ORDER_ITEMS d on o.ORDER_ID = d.ORDER_ID
group by o.ORDER_ID, o.ORDER_DATE, c.CUST_LAST_NAME, c.CUST_FIRST_NAME
order by o.ORDER_DATE;



SELECT p.PRODUCT_ID, p.PRODUCT_NAME, pd.TRANSLATED_DESCRIPTION
FROM PRODUCT_INFORMATION p
         left join ORDER_ITEMS o on p.PRODUCT_ID = o.PRODUCT_ID
         left join ORDERS r on o.ORDER_ID = r.ORDER_ID
         left join PRODUCT_DESCRIPTIONS PD on p.PRODUCT_ID = PD.PRODUCT_ID and pd.LANGUAGE_ID = 'RU'
WHERE not date '1998-01-01' <= r.ORDER_DATE
  and r.ORDER_DATE < date '1999-01-01';

SELECT *
FROM EMPLOYEES e
WHERE (SELECT count(*) FROM EMPLOYEES q WHERE q.MANAGER_ID = e.EMPLOYEE_ID) > 7;


SELECT c.CUSTOMER_ID,
       c.PHONE_NUMBERS,
       c.cust_first_name || ' ' || c.cust_last_name                                 as NAME,
       (SELECT MAX(o.ORDER_DATE) FROM ORDERS o WHERE o.CUSTOMER_ID = c.CUSTOMER_ID) as order_date,
       (SELECT o.ORDER_TOTAL
        FROM ORDERS o
        WHERE o.ORDER_DATE = (SELECT MAX(o.ORDER_DATE) FROM ORDERS o WHERE o.CUSTOMER_ID = c.CUSTOMER_ID)
          and o.CUSTOMER_ID = c.CUSTOMER_ID)                                        as order_total
FROM CUSTOMERS c
ORDER BY order_date DESC nulls last ;


--
-- Для каждого клиента выбрать информацию о последнем заказе.
-- Вывести поля: код клиента, номер клиента, имя клиента (фамилия + имя), дата заказа, сумма заказа.
-- Выбрать два последних поля подзапросами в select.
-- Чтобы выбрать сумму заказа из последнего заказа необходимо выбрать заказ с максимальной датой заказа для данного клиента (подзапрос в подзапросе).
-- Вывести вначале клиентов, у которых были заказы, затем тех, у кого не было.
-- Тех у кого были - упорядочить по дате последнего заказа в обратном порядке.
-- Если даты последнего заказа совпадают или не было вообще заказов - упорядочить по коду клиента.


SELECT TRUNC(SYSDATE, 'MM') + LEVEL - 1 AS day
FROM dual
CONNECT BY TRUNC(TRUNC(SYSDATE, 'MM') + LEVEL - 1, 'MM') = TRUNC(SYSDATE, 'MM')
;
SELECT  TO_CHAR (TRUNC (SYSDATE, 'MM'), 'YYYYMMDD')+(LEVEL - 1) each_date
FROM    DUAL a
CONNECT BY LEVEL < (TO_NUMBER (TO_CHAR (TRUNC (SYSDATE, 'MM') - 1, 'DD'))+1)