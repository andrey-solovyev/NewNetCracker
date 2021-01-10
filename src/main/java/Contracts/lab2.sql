-- 1
SELECT DISTINCT *
FROM CUSTOMERS c
         inner join ORDERS O on c.CUSTOMER_ID = O.CUSTOMER_ID
where date '1999-07-01' <= o.order_date
  and o.order_date < date '1999-08-01'
order by c.CUSTOMER_ID;
-- 2
SELECT DISTINCT c.CUSTOMER_ID, c.cust_first_name || ' ' || c.cust_last_name as NAME, SUM(o.ORDER_TOTAL) AS ORDER_TOTAL
FROM CUSTOMERS c
         left join ORDERS O on c.CUSTOMER_ID = O.CUSTOMER_ID and date '2000-01-01' <= o.order_date
    and o.order_date < date '2001-01-01'
group by c.customer_id,
         c.cust_last_name,
         c.cust_first_name
order by ORDER_TOTAL,
         c.customer_id;
-- 3
SELECT *
FROM EMPLOYEES e
WHERE NOT EXISTS(SELECT JOB_ID FROM JOB_HISTORY j WHERE e.EMPLOYEE_ID = j.EMPLOYEE_ID)
ORDER BY e.HIRE_DATE DESC, e.EMPLOYEE_ID;
-- 4
SELECT W.WAREHOUSE_ID, W.WAREHOUSE_NAME, COUNT(PI.PRODUCT_ID) AS amount_product
FROM WAREHOUSES W
         LEFT JOIN INVENTORIES I on w.WAREHOUSE_ID = I.WAREHOUSE_ID
         LEFT JOIN PRODUCT_INFORMATION PI on I.PRODUCT_ID = PI.PRODUCT_ID
GROUP BY W.WAREHOUSE_ID, W.WAREHOUSE_NAME
ORDER BY amount_product DESC, W.WAREHOUSE_ID;
--5
select e.*
from employees e
         join departments d
              on d.department_id = e.department_id
         join locations l
              on l.location_id = d.location_id
where l.country_id like 'US'
order by e.employee_id;
--6
select pi.product_id,
       pi.product_name,
       pi.list_price,
       nvl(pd.translated_description, 'Нет описания')
from product_information pi
         left join product_descriptions pd on
    pd.product_id = pi.product_id and pd.language_id like 'RU'
order by pi.category_id,
         pi.product_id;
--7
select pi.product_id,
       pi.product_name,
       pi.list_price,
       nvl(pd.TRANSLATED_NAME, 'Нет названия на русском') AS name_pd
from order_items oi
         join orders o on
    o.order_id = oi.order_id
         right join product_information pi on
    pi.product_id = oi.product_id
         left join product_descriptions pd on
    pd.product_id = pi.product_id and pd.language_id like 'RU'
where o.order_id is null
order by pi.list_price desc nulls last,
         pi.product_id;
--8
select c.customer_id,
       c.cust_first_name || ' ' || c.cust_last_name as cust_name,
       o1.large_sum_orders_count1                   as large_sum_orders_count,
       o1.max_order_sum1                            as max_order_sum
from customers c
         join (
    select max(o.order_total)   as max_order_sum1,
           count(o.order_total) as large_sum_orders_count1,
           o.customer_id
    from orders o
    where o.order_total > 2 *
                          (
                              select round(avg(o.order_total), 0)
                              from orders o
                          )
    group by o.customer_id
) o1 on o1.customer_id = c.customer_id
order by large_sum_orders_count desc,
         c.customer_id;
--9
select c.customer_id,
       c.cust_last_name || ' ' || c.cust_first_name as cust_name,
       sum_2000.order_sum                           as orders_sum
from customers c
         left join (
    select sum(o.order_total) as order_sum,
           o.customer_id
    from orders o
    where date '2000-01-01' <= o.order_date
      and o.order_date < date '2001-01-01'
    group by o.customer_id
) sum_2000 on sum_2000.customer_id = c.customer_id
order by orders_sum desc nulls last,
         c.customer_id;
--10

select c.customer_id,
       c.cust_last_name || ' ' || c.cust_first_name as cust_name,
       sum_2000.order_sum                           as orders_sum
from customers c
         left join (
    select sum(o.order_total) as order_sum,
           o.customer_id
    from orders o
    where date '2000-01-01' <= o.order_date
      and o.order_date < date '2001-01-01'
    group by o.customer_id
) sum_2000 on sum_2000.customer_id = c.customer_id
where sum_2000.order_sum is not null
order by orders_sum desc,
         c.customer_id;
--11
SELECT e.employee_id,
       e.last_name || ' ' || e.first_name           AS name_manager,
       c.customer_id,
       c.cust_last_name || ' ' || c.cust_first_name AS name_customer,
       o.order_date,
       o.order_total,
       oi.count_different_product
FROM employees e
         LEFT JOIN
     (
         SELECT *
         FROM orders o1
         WHERE o1.order_id =
               (
                   SELECT MAX(o2.order_id)
                   FROM orders o2
                   WHERE o2.sales_rep_id = o1.sales_rep_id
               )
     ) o
     ON o.sales_rep_id = e.employee_id
         LEFT JOIN
     customers c
     ON c.customer_id = o.customer_id
         LEFT JOIN
     (
         SELECT oi1.order_id,
                COUNT(oi1.order_id) AS count_different_product
         FROM order_items oi1
         GROUP BY oi1.order_id
     ) oi
     ON oi.order_id = o.order_id
WHERE e.job_id = 'SA_MAN'
   OR e.job_id = 'SA_REP'
ORDER BY o.order_date DESC NULLS LAST,
         o.order_total DESC,
         e.employee_id
;
--12
select max(100 - round(o.order_total * 100 / sum(oi1.total), 2)) as max_discount_percent
from orders o
         join (select (select p.list_price from product_information p where oi.product_id = p.product_id) *
                      oi.quantity as total,
                      oi.order_id
               from order_items oi) oi1 on oi1.order_id = o.order_id
group by oi1.order_id, o.order_total;
--13
select iv.product_id,
       pi.product_name,
       pi.list_price,
       w.warehouse_id,
       w.warehouse_name,
       c.country_name
from inventories iv
         left join inventories iv2 on
    iv.product_id = iv2.product_id
         join product_information pi on
    pi.product_id = iv.product_id
         join warehouses w on
    w.warehouse_id = iv.warehouse_id
         join locations l on
    l.location_id = w.location_id
         join countries c on
    c.country_id = l.country_id
group by iv.product_id, pi.product_name,
         pi.list_price, w.warehouse_id,
         w.warehouse_name, c.country_name
having count(iv.product_id) = 1
order by c.country_name, w.warehouse_id, pi.product_name;
--14
select ct.country_id,
       nvl((select count(*) from customers c where ct.country_id = c.cust_address_country_id), 0) as customers_count
from countries ct
group by ct.country_id
order by customers_count desc;
--15
select c.customer_id,
       c.cust_last_name || ' ' || c.cust_first_name as cust_name,
       o10.order_date1,
       o10.order_date2,
       o10.min_orders_interval
from customers c
         join (
    select trunc(o1.order_date)                               as order_date1,
           trunc(o2.order_date)                               as order_date2,
           o1.customer_id                                     as cust_id,
           min((trunc(o2.order_date) - trunc(o1.order_date))) as min_orders_interval
    from orders o1
             left join orders o2 on
        o2.customer_id = o1.customer_id
    where o1.order_date != o2.order_date
    group by o1.customer_id,
             trunc(o1.order_date),
             trunc(o2.order_date)
    having min((trunc(o2.order_date) - trunc(o1.order_date))) = (
        select min(abs(trunc(o4.order_date) - trunc(o3.order_date)))
        from orders o3
                 left join orders o4 on
            o4.customer_id = o3.customer_id
        where o3.customer_id = o1.customer_id
          and o3.order_date != o4.order_date
    )
) o10 on
    o10.cust_id = c.customer_id
order by c.customer_id;
